package gui;

import data.User;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by pierre on 24/11/15.
 */
public class UserListWindow extends JFrame implements ActionListener, WindowListener, MouseListener{

    /* Attributes */
    private Gui gui;
    private JPanel panel = new JPanel(new BorderLayout());
    private JScrollPane jScrollPane;
    private JPanel topPanel;
    private DefaultListModel model = new DefaultListModel();
    private JList<String> jList = new JList<>(this.model);
    private ArrayList<User> userList;
    private JButton refresh;


    /* Constructor */
    public UserListWindow(Gui gui, ArrayList<User> l) {
        super();
        this.gui = gui;
        this.userList = l;

        this.addWindowListener(this);

        //caractéristique de la fenetre
        this.setTitle("User List");
        this.setSize(250, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);


        this.topPanel = new JPanel(new BorderLayout());
        this.refresh = new JButton("Refresh");
        this.refresh.addActionListener(this);
        this.topPanel.add(refresh, BorderLayout.CENTER);


        //Il faut faire defaultlistmodel et tout
        this.jScrollPane = new JScrollPane(this.jList); // il faut mettre une jlist dedans
        for(User user : userList) {

            System.out.println();
            this.model.addElement(user.getNickname() + " (" + user.getIp().toString().substring(1)+ ")");
        }
        this.jList.addMouseListener(this);

        this.panel.add(this.topPanel, BorderLayout.NORTH);
        this.panel.add(this.jScrollPane, BorderLayout.CENTER);

        this.setContentPane(panel);
        this.setVisible(true);
        System.out.println("Je devrais être là");
    }

    /* Methodes */

    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == this.refresh) {
            System.out.println("Je refresh");
            try {
                this.userList = this.gui.getUserList();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            this.model.clear();
            for(User user : userList) {
                this.model.addElement(user.getNickname() + " (" + user.getIp().toString().substring(1)+ ")");
            }
        }
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


    /* Les listeners */
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

    //Mouse Listener
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        String userString = this.jList.getSelectedValue();

        if(mouseEvent.getClickCount()==2 && userString!=null){
            User userSelected = this.userList.get(this.jList.getSelectedIndex());

            System.out.println("User selected : " + userSelected);

            gui.launchChatWindow(userSelected);

        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
