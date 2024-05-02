package Client;
import javax.swing.*;
// import java.awt.event.*;

public class LoginGUI {


    private JLabel userLabel;
    private JTextField userText;
    private JLabel passwordLabel;
    private JPasswordField passwordText;
    private JLabel lenZero;
    private JButton loginButton;
    private JButton singupButton;

    
    public LoginGUI() {

		JFrame frame = new JFrame();
		frame.setTitle("JavaChatRoom, Connecting to server...");
		frame.setSize(350, 200);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);

		// user
		this.userLabel = new JLabel("User");
		this.userLabel.setBounds(10, 20, 80, 25);
		panel.add(this.userLabel);

		this.userText = new JTextField();
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);

		// password
		this.passwordLabel = new JLabel("Password");
		this.passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(this.passwordLabel);

		this.passwordText = new JPasswordField();
		this.passwordText.setBounds(100, 50, 165, 25);
		panel.add(this.passwordText);
        
        // error message: length == 0
        this.lenZero = new JLabel("");
        this.lenZero.setBounds(10, 110, 300, 25);
        panel.add(this.lenZero);

		// login button
		this.loginButton = new JButton("Login");
		this.loginButton.setBounds(10, 80, 80, 25);
		this.loginButton.addActionListener(e -> {

            String user = userText.getText();
            String password = passwordText.getText();

            if (user.length() == 0 || password.length() == 0) {
                this.lenZero.setText("ENTER NAME AND PASSWORD");
            }
            else {

                this.client.
            }
        });
		panel.add(this.loginButton);

		// singup button
        this.singupButton = new JButton("Login");
		this.singupButton.setBounds(10, 80, 80, 25);
		this.singupButton.addActionListener(e -> {

        });
		panel.add(this.singupButton);

		frame.setVisible(true);
    }

    public 
}
