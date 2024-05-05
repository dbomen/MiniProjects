import java.io.*;
import java.net.*;

public class ChatClient extends Thread
{
	protected int serverPort = 1234;
	protected Socket socketToServer;
	protected DataInputStream in;
	protected DataOutputStream out;
	protected BufferedReader std_in;
	protected boolean logedIn;
	protected String messageFromServer;

	public static void main(String[] args) throws Exception {
		new ChatClient();
	}

	public ChatClient() throws Exception {
		this.messageFromServer = null;
		this.socketToServer = null;
		this.in = null;
		this.out = null;

		this.std_in = new BufferedReader(new InputStreamReader(System.in));
		String userInput;

		// connect to the chat server
		try {
			String connectingMessage = "[system] connecting to chat server ...";
			System.out.println(connectingMessage);

			socketToServer = new Socket("localhost", serverPort); // create socket connection
			in = new DataInputStream(socketToServer.getInputStream()); // create input stream for listening for incoming messages
			out = new DataOutputStream(socketToServer.getOutputStream()); // create output stream for sending messages
			System.out.println("[system] connected");

			ChatClientMessageReceiver message_receiver = new ChatClientMessageReceiver(in, this); // create a separate thread for listening to messages from the chat server
			message_receiver.start(); // run the new thread
		} catch (Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}

		// login in / sing up
		LoginGUI loginGui = new LoginGUI(this);
		while (true) {
			String currentText = loginGui.errorMsg.getText();
			if (this.messageFromServer != null && !this.messageFromServer.equals(currentText)) {
				if (this.messageFromServer.contains("SUCCESS"))  break;
				loginGui.putErrorMsg(this.messageFromServer);
				this.messageFromServer = null;
			}
		}
		loginGui.setVisible(false);
		loginGui.dispose();
		
		String name = this.messageFromServer.substring(this.messageFromServer.indexOf(' ') + 1);
		// MainGUI mainGui = new MainGUI(this, name);

		// read from STDIN and send messages to the chat server
		try {
			while ((userInput = std_in.readLine()) != null) { // read a line from the console
				this.sendMessage(userInput, this.out); // send the message to the chat server
			}
		} catch (Exception e) {
			this.cleanUp();
		}

		this.cleanUp();
	}

	public void cleanUp() {
		// cleanup
		try {
			this.out.close();
			in.close();
			std_in.close();
			this.socketToServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendMessage(String message, DataOutputStream out) {
		try {
			out.writeUTF(message); // send the message to the chat server
			out.flush(); // ensure the message has been sent
		} catch (IOException e) {
			System.err.println("[system] could not send message");
			e.printStackTrace(System.err);
		}
	}
}

// wait for messages from the chat server and print the out
class ChatClientMessageReceiver extends Thread {
	private DataInputStream in;
	private ChatClient client;

	public ChatClientMessageReceiver(DataInputStream in, ChatClient client) {
		this.in = in;
		this.client = client;
	}

	public void run() {
		try {
			String message;
			while ((message = this.in.readUTF()) != null) { // read new message
				System.out.println(message); // print the message to the console
				this.client.messageFromServer = message;
			}
		} catch (Exception e) {
			System.err.println("[system] could not read message");
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
}
