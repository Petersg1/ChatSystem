package gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

/**
 * Created by pierre on 24/11/15.
 */
public class UserListWindow extends JFrame implements ActionListener, WindowListener{

    /* Attributes */
    private Gui gui;
    private JPanel panel = new JPanel(new BorderLayout());
    private JScrollPane jScrollPane;
    private JPanel topPanel;
    private DefaultListModel model = new DefaultListModel();
    private JList<String> userList = new JList<>(this.model);


    /* Constructor */
    public UserListWindow(Gui gui) {
        super();
        this.gui = gui;

        this.addWindowListener(this);

        //caractéristique de la fenetre
        this.setTitle("User List");
        this.setSize(250,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);


        this.topPanel = new JPanel(new BorderLayout());
        JButton refresh = new JButton("Refresh");
        this.topPanel.add(refresh, BorderLayout.CENTER);


        String[] testList = {"Broadast", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred", "Henri", "Helene", "Alfred"};

        //Il faut faire defaultlistmodel et tout
        this.jScrollPane = new JScrollPane(this.userList); // il faut mettre une jlist dedans

        for (int i=0; i<49;i++) {
           this.model.addElement(testList[i]);
        }

        this.panel.add(this.topPanel, BorderLayout.NORTH);
        this.panel.add(this.jScrollPane, BorderLayout.CENTER);

        this.setContentPane(panel);
        this.setVisible(true);
        System.out.println("Je devrais être là");
    }

    /* Methodes */


    public void actionPerformed (ActionEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        try {
            this.gui.performGoodbye();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
