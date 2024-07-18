import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

public class AdminLogin extends JFrame implements ActionListener {

    private JButton button;
    private JTextField username;
    private JPasswordField password;

    public AdminLogin() {
        setTitle("Login Page");
        setSize(400, 250); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBackground(new Color(34, 40, 49)); 

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE); 
        username = new JTextField();
        username.setBackground(new Color(44, 62, 80)); 
        username.setForeground(Color.WHITE); 

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE); 
        password = new JPasswordField();
        password.setBackground(new Color(44, 62, 80)); 
        password.setForeground(Color.WHITE); 

        button = new JButton("Login");
        button.setBackground(new Color(52, 152, 219)); 
        button.setForeground(Color.WHITE); 
        button.addActionListener(this);

        panel.add(usernameLabel);
        panel.add(username);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(button);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

  
}
