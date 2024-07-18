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

public class customerLogin2 extends JFrame implements ActionListener {

    private JButton button;
    private JTextField username;
    private JPasswordField password;

    public customerLogin2() {
        setTitle("Login Page");
        setSize(400, 250); // Increased frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBackground(new Color(34, 40, 49)); // Dark theme background color

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE); // White text color
        username = new JTextField();
        username.setBackground(new Color(44, 62, 80)); // Dark blue color
        username.setForeground(Color.WHITE); // White text color

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE); // White text color
        password = new JPasswordField();
        password.setBackground(new Color(44, 62, 80)); // Dark blue color
        password.setForeground(Color.WHITE); // White text color

        button = new JButton("Login");
        button.setBackground(new Color(52, 152, 219)); // Light blue color
        button.setForeground(Color.WHITE); // White text color
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

    public void actionPerformed(ActionEvent e) {
        boolean flag = false;
        if (e.getSource() == button) {
            String Username = username.getText();
            String Password = password.getText();
            Customer customer = Main.verifyCustomer(Username, Password);
            if (customer != null) {
                customer.getCustomerPortal().Menu();
                flag = true;
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect username or password", "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if(flag)
            dispose();
    }

    public static void main(String[] args) {
        customerLogin2 c = new customerLogin2();
    }
}
