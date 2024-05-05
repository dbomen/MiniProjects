import java.awt.Color;

import javax.swing.*;

public class MainGUI extends JFrame {

	/* protected ChatClient client; */
	protected String name;

    
    public MainGUI(/* ChatClient client,  */String name) {

		/* this.client = client; */
		this.name = name;

		this.setTitle(String.format("JavaChatRoom, %s", this.name));
		this.setSize(750, 750);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// "Friends" PANEL
		// ---
		JPanel friends = new JPanel();
		friends.setBackground(Color.red);
		friends.setBounds(0, 0, 200, 750);
		// ---

		// ChatName PANEL
		// ---
		JPanel chatName = new JPanel();
		chatName.setBackground(Color.orange);
		chatName.setBounds(200, 0, 550, 100);

		// chat Name label
		JLabel nameOfChat = new JLabel("JAVNO");
		// nameOfChat.setBackground(Color.black);
		nameOfChat.setBounds(15, 15, 450, 70);
		chatName.add(nameOfChat);
		// ---

		// Chat PANEL
		// ---
		JPanel chat = new JPanel();
		chat.setBackground(Color.green);
		chat.setBounds(200, 0, 550, 550);

		// chat history / users textArea
		JTextArea histories = new JTextArea();
		histories.setBounds(200, 100, 550, 550);
		chat.add(histories);
		// ---

		// User input / functionalities PANEL
		// ---
		JPanel input = new JPanel();
		input.setBackground(Color.blue);
		input.setBounds(200, 550, 550, 100);

		// functinalities button
		JButton functionalities = new JButton();
		functionalities.setBounds(225, 575, 100, 75);
		input.add(functionalities);

		// input button
		JButton userInput = new JButton();
		userInput.setBounds(250, 575, 450, 75);
		input.add(userInput);
		// ---

		this.add(chatName);
		this.add(friends);
		this.add(chat);
		this.add(input);
		this.setVisible(true);
		this.setLocationRelativeTo(null); // odpre se na sredini
	}
}