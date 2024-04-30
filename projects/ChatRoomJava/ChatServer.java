import java.io.*;
import java.net.*;
import java.util.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

public class ChatServer {

	protected int serverPort = 1234;
	protected List<Socket> clients = new ArrayList<Socket>(); // list of clients
	protected List<String> clientNames = new ArrayList<String>(); // list of client names


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

	public void addClientsName(String name) {
		this.clientNames.add(name);
	}

	public void removeClientsName(Socket socket) {
		int index = this.clients.indexOf(socket);
		this.clientNames.remove(index);
	}

	public void writeNewUser(String name, String password) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("database.txt", true));
			writer.write(String.format("%s, %s\n", name, password));
			writer.close();
		} catch (IOException e) {
			System.out.println("PROBLEM WITH WRITER");
			e.printStackTrace();
		}
	}

	public boolean findUser(String name, String password) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("database.txt"));
			String searchingFor = String.format("%s, %s", name, password);
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.equals(searchingFor)) {
					reader.close();
					return true;
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("PROBLEM WITH WRITER");
			e.printStackTrace();
		}
		return false;
	}

	public boolean CheckNameAvailibilty(String name) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("database.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				String currentName = line.substring(0, line.indexOf(","));
				if (currentName.equals(name)) {
					reader.close();
					return false;
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("PROBLEM WITH READER");
			e.printStackTrace();
		}
		return true;
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
		try {
			BufferedReader reader = new BufferedReader(new FileReader("database.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				String currentName = line.substring(0, line.indexOf(","));
				list.append((this.clientNames.contains(currentName)) ? String.format("- %s [ONLINE]\n", currentName) : String.format("- %s [NOT ONLINE]\n", currentName));
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("PROBLEM WITH READER (getListOfUsers)");
			e.printStackTrace();
			return null;
		}
		list.append("----------");

		return list.toString();
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

	public String getHistory(String user1, String user2) {
		StringBuilder history = new StringBuilder();
		String fileName1 = String.format("histories/H%s%s.txt", user1, user2);
		String fileName2 = String.format("histories/H%s%s.txt", user2, user1);
		if (!this.fileExsists(fileName1) && !this.fileExsists(fileName2)) return String.format("[SYSTEM] YOU DO NOT HAVE HISTROY WITH <%s>", user2);

		String fileName = realFileName(fileName1, fileName2); // the real file name

		history.append(String.format("[SYSTEM] HISTORY BETWEEN <%s> and <%s>\n", user1, user2));
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

	public void createNewChat(String chatName) {

		try {
			File newChat = new File(chatName);
			newChat.createNewFile();
		} catch (IOException e) {
			System.out.println("PROBLEM WITH CREATING NEW CHAT");
			e.printStackTrace();
		}
	}

	public void writeToChat(String chatName, String msg) {

		if (!this.fileExsists(chatName))  return;

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(chatName, true));
			writer.write(msg + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("PROBLEM WITH WRITER");
			e.printStackTrace();
		}
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
		sendMessageToClient("Enter your name or do \"/singup\" to sing up: ", out);
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

				name = input;
				this.sendMessageToClient("Enter password: ", out);
				String password = this.getMessageFromClient(in);
				if (this.server.findUser(name, password)) { // if user in data base, log him in
					this.name = name;
					logedIn = true;
				} else {
					this.sendMessageToClient(String.format("USER WITH NAME <%s>, PASSWORD <%s>, does not exsist, try again or sing up with \"/singup\": ", name, password), out);
				}
			}
		}
		server.addClientsName(this.name);

		System.out.println("[system] connected with " + this.socket.getInetAddress().getHostName() + ":" + this.socket.getPort() + " | name: " + this.name);

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
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();

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

					
					String chatName1 = String.format("histories/H%s%s.txt", this.name, ciljniClient);
					String chatName2 = String.format("histories/H%s%s.txt", ciljniClient, this.name);
					if (!this.server.fileExsists(chatName1) && !this.server.fileExsists(chatName2))  this.server.createNewChat(chatName1); // if chat does not exsist than we make a new one

					String chatName = this.server.realFileName(chatName1, chatName2);

					socketC = server.clients.get(indexC);
					
					// priredimo sporocilo za posiljanje
					msg_send = String.format("[PRIVATNO][%s][%s][%s]: %s", this.name, ciljniClient, dtf.format(now).toString(), msg_received.substring(endIndex + 1, msg_received.length()));
					this.server.writeToChat(chatName, msg_send);
					System.out.println(msg_send); // za izpis v serverju
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
				else { // unkown command
					msg_send = String.format("[SYSTEM] COMMAND <%s> IS UNKNOWN", msg_received);
					this.sendMessageToClient(msg_send, out);
					System.out.println(msg_send);
					continue;
				}

				this.sendMessageToClient(msg_send, out);
				System.out.println(msg_send);
			}
			else { // broadcast

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
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
