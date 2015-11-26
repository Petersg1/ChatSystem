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
    private JLabel error;
    private JLabel usernameLabel;
    private JTextField username;
    private Gui gui;

    /* Constructor */
    public LoginWindow(Gui gui) throws IOException {
        super();
        this.gui = gui;
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
        this.username.addActionListener(this);
        b2.add(this.usernameLabel);
        b2.add(this.username);

        Box b3 = Box.createHorizontalBox();
        this.error = new JLabel(" ");
        this.error.setForeground(Color.red);
        b3.add(this.error);

        Box b4 = Box.createHorizontalBox();
        this.bConnection = new JButton("Connection");
        this.bConnection.addActionListener(this);
        b4.add(this.bConnection);



        Box b = Box.createVerticalBox();
        b.add(Box.createRigidArea(new Dimension(10,10)));
        b.add(b1);
        b.add(Box.createRigidArea(new Dimension(10,30)));
        b.add(b2);
        b.add(b3);
        b.add(Box.createRigidArea(new Dimension(10,30)));
        b.add(b4);

        this.panel.add(b);

        //Affichage
        this.setContentPane(panel);
        this.setVisible(true);
    }

    /* Methodes */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.bConnection || e.getSource() == this.username) {
            if (username.getText().length() >= 3) {
                try {
                    gui.launchUserListWindow(username.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                this.dispose();
            } else {
                this.error.setText("Too short");
            }
        }
    }
}
