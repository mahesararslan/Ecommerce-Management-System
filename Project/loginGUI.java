import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class loginGUI extends JFrame implements ActionListener {

    JButton customer;
    JButton admin;
    JButton signup;

    public loginGUI() {
        
        this.setSize(400,400);
        this.setTitle("LOGIN");
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.getContentPane().setBackground(Color.lightGray);

        JLabel title = new JLabel();
        title.setText("LOGIN");
        title.setBounds(170,25,50,50);
        title.setForeground(Color.BLACK);

        customer = new JButton("Customer Login");
        customer.setBackground(Color.WHITE);
        customer.setBounds(130,125,130,40);
        customer.setFocusable(false);
        customer.addActionListener(this);
        
        admin = new JButton("Admin Login");
        admin.setBackground(Color.WHITE);
        admin.setBounds(130, 200, 130, 40);
        admin.setFocusable(false);
        admin.addActionListener(this);

        signup = new JButton("Sign Up");
        signup.setBackground(Color.WHITE);
        signup.setBounds(130,275,130,40);
        signup.setFocusable(false);
        signup.addActionListener(this);

        this.add(title);
        this.add(admin);
        this.add(customer);
        this.add(signup);
        this.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == customer) {
            Main.loginAsCustomer();
            
        }
        else if(e.getSource() == admin) {
            Main.loginAsAdmin();
            
        }
        else if(e.getSource() == signup) {
            Main.signUp();
            
        }
        dispose();

    }
}
