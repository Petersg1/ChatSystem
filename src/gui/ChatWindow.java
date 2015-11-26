package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

/**
 * Created by pierre on 26/11/15.
 */
public class ChatWindow extends JFrame implements ActionListener {

    private Gui gui;
    private JPanel panel = new JPanel(new BorderLayout());
    private JPanel centerPanel;
    private JPanel topPanel;

    public ChatWindow(Gui gui) {
        super();
        this.gui = gui;
        //caractéristique de la fenetre
        this.setTitle("User List");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        this.topPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("");


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }



}
