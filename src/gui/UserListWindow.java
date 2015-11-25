package gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pierre on 24/11/15.
 */
public class UserListWindow extends JFrame implements ActionListener{

    /* Attributes */
    private JPanel panel = new JPanel(new BorderLayout());
    private JScrollPane jScrollPane;
    private JPanel topPanel;
    private DefaultListModel model = new DefaultListModel();
    //private JList<String> userList = ;


    /* Constructor */
    public UserListWindow() {
        super();
        //caractéristique de la fenetre
        this.setTitle("User List");
        this.setSize(250,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);


        this.topPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Connected Users ");
        JButton refresh = new JButton("Refresh");
        refresh.setSize(10,10);
        this.topPanel.add(title, BorderLayout.WEST);
        this.topPanel.add(refresh, BorderLayout.EAST);



        this.panel.add(this.topPanel, BorderLayout.NORTH);
        this.panel.add(this.jScrollPane, BorderLayout.CENTER);

        this.setContentPane(panel);
        this.setVisible(true);
        System.out.println("Je devrais être là");
    }

    /* Methodes */

    public void actionPerformed (ActionEvent e) {

    }
}
