package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by pierre on 23/11/15.
 */
public class LoginWindow extends JFrame implements ActionListener {

    /* Attributes */
    private JPanel panel = new JPanel();
    private JButton bConnection;
    private JLabel logo;
    private JLabel usernameLabel;
    private JTextField username;

    /* Constructor */
    public LoginWindow() throws IOException {
        super();
        //caractéristique de la fenetre
        this.setTitle("Log in");
        this.setSize(300,350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        //Panel
        Box b1 = Box.createHorizontalBox();
        this.logo = new JLabel(new ImageIcon("images/logo_msn.png"));
        b1.add(this.logo);

        Box b2 = Box.createHorizontalBox();
        this.usernameLabel = new JLabel("Username : ");
        this.username = new JTextField();
        this.username.setPreferredSize(new Dimension(100, 30));
        b2.add(this.usernameLabel);
        b2.add(this.username);

        Box b3 = Box.createHorizontalBox();
        this.bConnection = new JButton("Connection");
        this.bConnection.addActionListener(this);
        b3.add(this.bConnection);

        Box b = Box.createVerticalBox();
        b.add(Box.createRigidArea(new Dimension(10,10)));
        b.add(b1);
        b.add(Box.createRigidArea(new Dimension(10,30)));
        b.add(b2);
        b.add(Box.createRigidArea(new Dimension(10,30)));
        b.add(b3);

        panel.add(b);

        //Affichage
        this.setContentPane(panel);
        this.setVisible(true);
    }

    /* Methodes */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bConnection) {

        }
    }
}
