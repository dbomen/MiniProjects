import java.io.*;
import java.net.*;
import java.util.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

public class ChatServer {

	protected int serverPort = 1234;
	protected List<Socket> clients = new ArrayList<Socket>(); // list of clients
	protected List<String> clientNames = new ArrayList<String>(); // list of client names
	protected String projectPath = "L:/.zrelo obdobje/.School/programiranje/projects/ChatRoomJava";


	public static void main(String[] args) throws Exception {
		new ChatServer();
	}

	public ChatServer() {
		ServerSocket serverSocket = null;

		// create socket
		try {
			serverSocket = new ServerSocket(this.serverPort); // create the ServerSocket
		} catch (Exception e) {
			System.err.println("[system] could not create socket on port " + this.serverPort);
			e.printStackTrace(System.err);
			System.exit(1);
		}

		// start listening for new connections
		System.out.println("[system] listening ...");
		try {
			while (true) {
				Socket newClientSocket = serverSocket.accept(); // wait for a new client connection
				synchronized(this) {
					clients.add(newClientSocket); // add client to the list of clients
				}
				ChatServerConnector conn = new ChatServerConnector(this, newClientSocket); // create a new thread for communication with the new client
				conn.start(); // run the new thread
			}
		} catch (Exception e) {
			System.err.println("[error] Accept failed.");
			e.printStackTrace(System.err);
			System.exit(1);
		}

		// close socket
		System.out.println("[system] closing server socket ...");
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	// send a message to all clients connected to the server
	public void sendToAllClients(String message) throws Exception {
		Iterator<Socket> i = clients.iterator();
		while (i.hasNext()) { // iterate through the client list
			Socket socket = (Socket) i.next(); // get the socket for communicating with this client
			try {
				DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // create output stream for sending messages to the client
				out.writeUTF(message); // send message to the client
			} catch (Exception e) {
				System.err.println("[system] could not send message to a client");
				e.printStackTrace(System.err);
			}
		}
	}

	// ko posiljamo clientu x oz. ce je prislo do napake pri privatnem posiljanju
	public void sendToClientX(String message, Socket x) throws Exception {
		try {
			DataOutputStream out = new DataOutputStream(x.getOutputStream()); // create output stream for sending messages to the client
			out.writeUTF(message); // send message to the client
		} catch (Exception e) {
			System.err.println("[system] could not send message to a client");
			e.printStackTrace(System.err);
		}
	}

	public void removeClient(Socket socket) { // v konsoli se izpise koga smo izpisali <ime><port> ter tudi lists size before and after
											  // zato, ker imamo dva lista in s tem preverimo, da ni napake pri sinhronizaciji obeh listov
		synchronized(this) {
			System.out.println("--------------------------------------------");
			System.out.printf("[SYSTEM] REMOVING CLIENT: <%s><%s>\n", clientNames.get(clients.indexOf(socket)), socket.getPort());
			System.out.printf("[SYSTEM] OLD LISTS SIZE: <%s><%s>\n", clientNames.size(), clients.size());
			removeClientsName(socket);
			clients.remove(socket);
			System.out.printf("[SYSTEM] NEW LISTS SIZE: <%s><%s>\n[SYSTEM] REMOVED\n", clientNames.size(), clients.size());
			System.out.println("--------------------------------------------");
		}
	}

	// returns index, where clients name will be
	public int makeRoomForClientsName() {
		this.clientNames.add(" ");
		return this.clientNames.size() - 1;
	}

	public void addClientsName(String name, int index) {
		this.clientNames.set(index, name);
	}

	public void removeClientsName(Socket socket) {
		int index = this.clients.indexOf(socket);
		this.clientNames.remove(index);
	}

	public void writeNewUser(String name, String password) {
		
		String newUserPath = String.format("%s/users/@%s", this.projectPath, name);
		
		// create necesery folders / files
		this.createNewFolder(newUserPath);
		this.createNewFolder(newUserPath + "/OfflineMessages");
		this.createNewFile(newUserPath + "/Password.txt");

		// add password, uporabimo lahko kar this.writeToChat metodo, saj isto naredi
		this.writeToChat(newUserPath + "/Password.txt", password);
	}

	public boolean findUser(String name, String password) {

		String path = String.format("%s/users/@%s", this.projectPath, name);
		if (this.fileExsists(path)) {

			String knownPassword = null;
			try {

				BufferedReader reader = new BufferedReader(new FileReader(path + "/Password.txt"));
				knownPassword = reader.readLine();
				reader.close();
			} catch (IOException e) {
				System.out.println("PROBLEM WITH FINDING USER");
				e.printStackTrace();
			}

			if (knownPassword.equals(password))  return true;
		}
		return false;
	}

	public boolean CheckNameAvailibilty(String name) {
		
		return !(this.fileExsists(String.format("%s/users/@%s", this.projectPath, name)));
	}

	public String getListOfOnlineUsers() {
		StringBuilder list = new StringBuilder();
		list.append("[SYSTEM] LIST OF ONLINE USERS:\n");
		list.append("----------\n");
		for (String name : this.clientNames) {
			list.append(String.format("- %s\n", name));
		}
		list.append("----------");

		return list.toString();
	}

	public String getListOfUsers() {
		StringBuilder list = new StringBuilder();
		list.append("[SYSTEM] LIST OF ALL USERS:\n");
		list.append("----------\n");
		File dir = new File(this.projectPath + "/users");
		File[] listOfUsers = dir.listFiles();
		if (listOfUsers != null) {
			for (File user : listOfUsers) {
				String userFileName = user.getName();
				// if it is a user file add it to list
				if (userFileName.charAt(0) == '@') {
					userFileName = userFileName.substring(1, userFileName.length());
					list.append((this.clientNames.contains(userFileName)) ? String.format("- %s [ONLINE]\n", userFileName) : String.format("- %s [NOT ONLINE]\n", userFileName));
				}
			}
		}
		list.append("----------");

		return list.toString();
	}

	public boolean isOnline(String user) {

		for (String name : this.clientNames) {
			if (name.equals(user))  return true;
		}
		return false;
	}

	public boolean fileExsists(String fileName) {
		try {
			File tmpF = new File(fileName);
			return tmpF.exists();
		} catch (Exception e) {
			System.out.println("PROBLEM WITH FILE EXSIST");
			e.printStackTrace();
			return false;
		}
	}

	public String realFileName(String fileName1, String fileName2) {
		return (this.fileExsists(fileName1)) ? fileName1 : fileName2;
	}

	// -----
	// SAME FUNCTION, BUT DIFFERENT INPUT
	// if you only have 2 names
	public String getHistory(String user1, String user2) {
		String fileName1 = String.format("%s/histories/H%s%s.txt", this.projectPath, user1, user2);
		String fileName2 = String.format("%s/histories/H%s%s.txt", this.projectPath, user2, user1);
		if (!this.fileExsists(fileName1) && !this.fileExsists(fileName2)) return String.format("[SYSTEM] YOU DO NOT HAVE HISTROY WITH <%s>", user2);

		return getHistory(realFileName(fileName1, fileName2)); // the real file name
	}

	// if you already have the path
	public String getHistory(String fileName) {
		StringBuilder history = new StringBuilder();

		history.append(String.format("[SYSTEM] <%s> HISTORY:\n", fileName));
		history.append("----------\n");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = reader.readLine()) != null) {
				history.append(line + "\n");
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("PROBLEM WITH READER (getHistory)");
			e.printStackTrace();
			return null;
		}
		history.append("----------");		
		return history.toString();
	}
	// -----

	public void createNewFolder(String path) {

		new File(path).mkdirs();
	}

	public void createNewFile(String path) {

		try {
			File newChat = new File(path);
			newChat.createNewFile();
		} catch (IOException e) {
			System.out.println("PROBLEM WITH CREATING NEW FILE");
			e.printStackTrace();
		}
	}

	public void removeFile(String path) {

		new File(path).delete();
	}

	public void writeToChat(String chatName, String msg) {

		if (!this.fileExsists(chatName))  return;

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(chatName, true));
			writer.write(msg + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("PROBLEM WITH WRITER (write to chat)");
			e.printStackTrace();
		}
	}

	public String getChatName(String user1, String user2) {
		String chatName1 = String.format("%s/histories/H%s%s.txt", this.projectPath, user1, user2);
		String chatName2 = String.format("%s/histories/H%s%s.txt", this.projectPath, user2, user1);
		if (!this.fileExsists(chatName1) && !this.fileExsists(chatName2))  this.createNewFile(chatName1); // if chat does not exsist than we make a new one

		String chatName = this.realFileName(chatName1, chatName2);

		// pogledamo, ce je history == 100, ce je deletamo prvi line
		if (this.historyTooLong(chatName))  this.removeFirstLine(chatName);

		return chatName;
	}

	public int numberOfLines(String fileName) {

		int lines = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			while (reader.readLine() != null)  lines++;
			reader.close();
		} catch (IOException e) {
			System.out.println("PROBLEM WITH READER (historyTooLong)");
			e.printStackTrace();
		}
		return lines;
	}

	// if history == 100 lines return true
	public boolean historyTooLong(String chatName) {

		return (this.numberOfLines(chatName) == 50);
	}

	// it is not so bad that we loop thru the whole file, because it is only max. 50 lines
	public void removeFirstLine(String chatName) {

		StringBuilder history_new = new StringBuilder();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(chatName));
			boolean firstLine = true;
			String line;
			while ((line = reader.readLine()) != null) {
				if (firstLine)  firstLine = false;
				else {
					history_new.append(line + "\n");
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("PROBLEM WITH READER (removeFirstLine)");
			e.printStackTrace();
		}

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(chatName, false));
			writer.write(history_new.toString());
			writer.close();
		} catch (IOException e) {
			System.out.println("PROBLEM WITH WRITER (removeFirstLine)");
			e.printStackTrace();
		}	
	}

	public void addToOfflineMessages(String chat, String user, String msg) {

		String strippedChatName = chat.substring(chat.indexOf('H'));
		strippedChatName = strippedChatName.substring(0, strippedChatName.indexOf('.'));

		String fileName = String.format("%s/users/@%s/OfflineMessages/%sCNT.txt", this.projectPath, user, strippedChatName);

		// ce file ne obstaja ga naredi
		if (!this.fileExsists(fileName))  this.createNewFile(fileName);

		int cnt = 1;
		if (this.numberOfLines(fileName) == 0) { // ce je file prazen, pustimo cnt na 1
		}
		else { // sicer preberemo cnt in ga povecamo za 1

			// preberemo cnt
			try {

				BufferedReader reader = new BufferedReader(new FileReader(fileName));
				String line = reader.readLine();
				cnt = Integer.valueOf(line.substring(line.length() - 1)) + 1;
				reader.close();
			} catch (IOException e) {
				System.out.println("PROBLEM WITH ADDING OFFLINE MESSAGE, reader");
				e.printStackTrace();
			}
		}

		// napisemo novo
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
			writer.write(String.format("%s.txt, %d", strippedChatName, cnt));
			writer.close();
		} catch (IOException e) {
			System.out.println("PROBLEM WITH ADDING OFFLINE MESSAGE, writer");
			e.printStackTrace();
		}
	}

	// returns the contents of the offline message
	public String removeFromOfflineMessages(String chat, String fileToReadFrom, int numberOfMessages) {
		
		StringBuilder content = new StringBuilder();

		// preberemo vsebino in vzamemo samo zadnjih <numberOfMessages>
		String[] messages = this.getHistory(fileToReadFrom).split("\n");
		for (int i = messages.length - numberOfMessages - 1; i < messages.length - 1; i++) {
			if (i != messages.length - numberOfMessages - 1)  content.append('\n');
			content.append(messages[i]);
		}

		// izbrisemo file
		this.removeFile(chat);

		return content.toString();
	}

	// dobimo niz, dveh imen, vrne ime, ki se ne matcha
	public String findNonMatching(String niz, String podniz) {

		int startIx = niz.indexOf(podniz);
		int endIx = startIx + podniz.length();

		if (startIx == 0)  return niz.substring(endIx);
		else 			   return niz.substring(0, startIx);
	}
}

class ChatServerConnector extends Thread {
	private ChatServer server;
	private Socket socket;
	private String name;

	public ChatServerConnector(ChatServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
	}

	public void run() {
		// za inpute
		DataInputStream in;
		try {
			in = new DataInputStream(this.socket.getInputStream()); // create input stream for listening for incoming messages
		} catch (IOException e) {
			System.err.println("[system] could not open input stream!");
			e.printStackTrace(System.err);
			this.server.removeClient(socket);
			return;
		}

		// za outpute
		DataOutputStream out;
		try {
			out = new DataOutputStream(this.socket.getOutputStream()); // create input stream for listening for incoming messages
		} catch (IOException e) {
			System.err.println("[system] could not open output stream!");
			e.printStackTrace(System.err);
			this.server.removeClient(socket);
			return;
		}

		// singing in / up
		boolean logedIn = false;
		String name;
		int ixForClientsName = this.server.makeRoomForClientsName();
		// sendMessageToClient("Enter your name or do \"/singup\" to sing up: ", out);
		try {
			while (!logedIn) {

				String input = this.getMessageFromClient(in);
				if (input.equals("/singup")) { // if client wants to sing up
	
					boolean nameAvailable = false;
					name = null;
					String password = null;
					while (!nameAvailable) {
						this.sendMessageToClient("Enter name: ", out);
						name = this.getMessageFromClient(in);
						nameAvailable = this.server.CheckNameAvailibilty(name);
						if (!nameAvailable)  this.sendMessageToClient(String.format("NAME <%s> NOT AVAILABLE TRY AGAIN", name), out);
					}
					this.sendMessageToClient("Enter password: ", out);
					password = this.getMessageFromClient(in);
	
					this.server.writeNewUser(name, password);
					this.sendMessageToClient("SUCCESSFULY ADDED NEW USER", out);
					this.name = name;
					logedIn = true;
				} 
				else { // if client wants to sing in (log in)
	
					name = input.substring(0, input.indexOf(','));
					String password = input.substring(input.indexOf(' ') + 1); // added
					// this.sendMessageToClient("Enter password: ", out);
					// String password = this.getMessageFromClient(in);
					if (this.server.findUser(name, password)) { // if user in data base, log him in
						this.name = name;
	
						// if user already online give error message saying that
						if (this.server.isOnline(name)) {
							this.sendMessageToClient(String.format("USER <%s> IS ALREADY ONLINE ON ANOTHER DEVICE! Try again or sing up with \"/singup\": ", name), out);
						}
						else  logedIn = true;
					} else {
						this.sendMessageToClient(String.format("USER WITH NAME <%s>, PASSWORD <%s>, DOES NOT EXSIST! Try again or sing up with \"/singup\": ", name, password), out);
					}
				}
			}
			server.addClientsName(this.name, ixForClientsName);
		} catch (Exception e) {
			System.out.println("PROBLEM WITH LOGIN IN/ SING UP, removing user");
			this.server.removeClient(this.socket);
		}


		System.out.println("[system] connected with " + this.socket.getInetAddress().getHostName() + ":" + this.socket.getPort() + " | name: " + this.name);

		// ce ima kaksne neprebrane message, ki jih je dobil, ko je bil offline jim mu pokaze in izbrise file iz OfflineMessages
		File dir = new File(String.format("%s/users/@%s/OfflineMessages", this.server.projectPath, this.name));
		File[] offlineMessages = dir.listFiles();
		if (offlineMessages.length > 0) {
			this.sendMessageToClient(String.format("[SYSTEM] YOU HAVE OFFLINE MESSAGES FROM %d PEOPLE\n----------", offlineMessages.length), out);

			StringBuilder backLog = new StringBuilder();
			for (File offlineMessage : offlineMessages) {
	
				StringBuilder content = new StringBuilder();
				// preberemo vsebino
				try {
					
					BufferedReader reader = new BufferedReader(new FileReader(offlineMessage));
					content.append(reader.readLine());
					reader.close();
				} catch (IOException e) {
					System.out.println("PROBLEM WITH READING CONTENT OF OFFLINEMESSAGE FILE");
					e.printStackTrace();
				}
	
				int numberOfMessages = Integer.valueOf(content.toString().substring(content.toString().indexOf(' ') + 1));
				String sender = this.server.findNonMatching(content.toString().substring(1, content.toString().indexOf('.')), this.name);
				String fileToReadFrom = content.toString().substring(0, content.toString().indexOf(','));

				backLog.append(String.format("[SYSTEM] %d MESSAGES FROM %s\n-----\n", numberOfMessages, sender));
				backLog.append(this.server.removeFromOfflineMessages(offlineMessage.getAbsolutePath(), String.format("%s/histories/%s", this.server.projectPath, fileToReadFrom), numberOfMessages) + '\n');
				backLog.append("-----\n");
			}
			backLog.append("----------\n");
			this.sendMessageToClient(backLog.toString(), out);
		}



		while (true) { // infinite loop in which this thread waits for incoming messages and processes them
			String msg_received;
			try {
				msg_received = in.readUTF(); // read the message from the client
			} catch (Exception e) {
				System.err.println("[system] there was a problem while reading message client on port " + this.socket.getPort() + ", removing client");
				// e.printStackTrace(System.err);
				this.server.removeClient(this.socket);
				return;
			}

			if (msg_received.length() == 0) // invalid message
				continue;

			// gledamo tip sporocila in kaj bomo z njim naredili

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			if (msg_received.charAt(0) == '@') { // privatno sporocilo

				// najdemo ' ';
				int endIndex = 0;
				String ciljniClient;
				try {
					for (; msg_received.charAt(endIndex) != ' '; endIndex++);
					ciljniClient = msg_received.substring(1, endIndex);
				} catch (Exception e) {
					String msgToClient = "[ERROR][SYSTEM] PROBLEM WITH GETTING MESSAGE TO SEND, TRY AGAIN!";
					this.sendMessageToClient(msgToClient, out);
					System.out.println(msgToClient);
					continue;
				}

				// dobimo index od clientNames lista, da dobimo njegov socket
				int indexC = server.clientNames.indexOf(ciljniClient);

				String msg_send = "";
				Socket socketC;

				// ce ne najdemo naslovnika moramo posiljatelju nazaj poslati, da nismo nasli, sicer posljemu prejemniku privatno sporocilo
				if (indexC == -1) {

					// check if he is in the data base, if he is say that the client is not online
					// 									if he is not, say that client does not exsist

					msg_send = (this.server.CheckNameAvailibilty(ciljniClient) == true) ? String.format("[ERROR][SYSTEM][%s]: Client name <%s> not found!", dtf.format(now).toString(), ciljniClient)
																						: String.format("[ERROR][SYSTEM][%s]: Client name <%s> is offline", dtf.format(now).toString(), ciljniClient);

					socketC = this.socket;
					// priredimo error message za posiljanje nazaj prejemniku
					System.out.println(msg_send);
				} else {

					String chatName = this.server.getChatName(this.name, ciljniClient);
					
					// priredimo sporocilo za posiljanje
					msg_send = String.format("[PRIVATNO][%s][%s][%s]: %s", this.name, ciljniClient, dtf.format(now).toString(), msg_received.substring(endIndex + 1, msg_received.length()));
					this.server.writeToChat(chatName, msg_send);
					System.out.println(msg_send); // za izpis v serverju

					socketC = server.clients.get(indexC);
				}

				// posljemo sporocilo cilnjemu odjemalcu
				try {
					this.server.sendToClientX(msg_send, socketC);
				} catch (Exception e) {
					System.err.printf("[system] there was a problem while sending the message to client: name: %s | socket: %s", ciljniClient, socketC);
					e.printStackTrace(System.err);
					continue;
				}

			} 
			else if (msg_received.charAt(0) == '/') { // if its a command

				String msg_send = "[ERROR][SYSTEM] SOMETHING WITH TEXT FORMATING WRONG PLEASE TRY AGAIN!";
				if (msg_received.contains("/usersO")) {
					msg_send = this.server.getListOfOnlineUsers();
				}
				else if (msg_received.contains("/users")) {
					msg_send = this.server.getListOfUsers();
				}
				else if (msg_received.contains("/history")) {
					String user = "";
					try {
						user = msg_received.substring(msg_received.indexOf("@") + 1, msg_received.length());
						msg_send = this.server.getHistory(this.name, user);
					} catch (Exception e) { // posle problem message
						System.out.println("PROBLEM WITH /history");
					}
				}
				else if (msg_received.contains("/OFF")) {
					String user = "";
					try {
						int endIndex = msg_received.indexOf(" ", msg_received.indexOf("@"));
						user = msg_received.substring(msg_received.indexOf("@") + 1, endIndex);
						String chatName = this.server.getChatName(this.name, user);
						msg_send = String.format("[PRIVATNO][%s][%s][%s]: %s", this.name, user, dtf.format(now).toString(), msg_received.substring(endIndex + 1, msg_received.length()));
						this.server.writeToChat(chatName, msg_send);

						// if the user is already online we just send it to him
						if (this.server.clientNames.contains(user))  this.server.sendToClientX(msg_send, this.server.clients.get(this.server.clientNames.indexOf(user)));
						// else we put it to OfflineMessages
						else  this.server.addToOfflineMessages(this.server.getChatName(this.name, user), user, msg_send);

						System.out.println(msg_send);
						continue;

					} catch (Exception e) {
						System.out.println("PROBLEM WITH /OFF");
						e.printStackTrace();
					}
				}
				else { // unkown command
					msg_send = String.format("[SYSTEM] COMMAND <%s> IS UNKNOWN", msg_received);
				}

				this.sendMessageToClient(msg_send, out);
				System.out.println(msg_send);
			}
			else { // broadcast

				String msg_send = String.format("[JAVNO][%s][%s]: %s", this.name, dtf.format(now).toString(), msg_received);
				System.out.println(msg_send);

				try {
					this.server.sendToAllClients(msg_send); // send message to all clients
				} catch (Exception e) {
					System.err.println("[system] there was a problem while sending the message to all clients");
					e.printStackTrace(System.err);
					continue;
				}
			}
		}
	}

	public String getNameOfClient() {
		return this.name;
	}

	public void sendMessageToClient(String message, DataOutputStream out) {
		try { // pridobivanje inputa
			out.writeUTF(message);
		} catch (IOException e) {
			System.err.println("[system] problem with sending message to client");
			e.printStackTrace(System.err);
		}
	}

	public String getMessageFromClient(DataInputStream in) {
		String input = null;
		try {
			input = in.readUTF();
		} catch (IOException e) {
			System.err.println("[system] problem with getting message from client");
			e.printStackTrace(System.err);
		}
		return input;
	}
}
