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
    private JPanel panel = new JPanel();
    private JList<String> userList;


    /* Constructor */
    public UserListWindow() {
        super();
        //caractéristique de la fenetre
        this.setTitle("User List");
        this.setSize(250,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);


        Box b1 = Box.createHorizontalBox();
        JLabel title = new JLabel("Connected Users ");
        JButton refresh = new JButton("Refresh");
        b1.add(title);
        b1.add(refresh);
        b1.setAlignmentX(Component.LEFT_ALIGNMENT);

        String stringList[] = {"Coucou", "Papa", "Pizza", "Coucou", "Papa", "Pizza", "Coucou", "Papa", "Pizza", "Coucou", "Papa", "Pizza", "Coucou", "Papa", "Pizza", "Coucou", "Papa", "Pizza", "Coucou", "Papa", "Pizza", "Coucou", "Papa", "Pizza", "Coucou", "Papa", "Pizza", "Coucou", "Papa", "Pizza", "Coucou", "Papa", "Pizza", };
        Box b2 = Box.createVerticalBox();
        this.userList = new JList<>(stringList);
        this.userList.setFixedCellWidth(240);
        b2.add(this.userList);
        b2.setAlignmentX(Component.LEFT_ALIGNMENT);
        b2.setAutoscrolls(true);

        Box b = Box.createVerticalBox();
        b.add(b1);
        b.add(Box.createRigidArea(new Dimension(10,30)));
        b.add(b2);
        b.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.panel.add(b);

        this.setContentPane(panel);
        this.setVisible(true);
        System.out.println("Je devrais être là");
    }

    /* Methodes */

    public void actionPerformed (ActionEvent e) {

    }
}
