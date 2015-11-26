package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.awt.event.WindowListener;

/**
 * Created by pierre on 26/11/15.
 */
public class ChatWindow extends JFrame implements ActionListener {

    private Gui gui;
    private JPanel panel = new JPanel(new BorderLayout());
    private JScrollPane centerPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;

    public ChatWindow(Gui gui, String name, InetAddress ip) {
        super();
        this.gui = gui;
        //caractéristique de la fenetre
        this.setTitle("User List");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        this.topPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Conversation with " + name);
        topPanel.add(title, BorderLayout.CENTER);

        JTextArea messagesPrinted = new JTextArea("");
        this.centerPanel = new JScrollPane(messagesPrinted);

        this.bottomPanel = new JPanel(new BorderLayout());
        JTextField textToSend = new JTextField();
        JButton bSend = new JButton("Send");
        bSend.addActionListener(this);
        this.bottomPanel.add(textToSend, BorderLayout.CENTER);
        this.bottomPanel.add(bSend, BorderLayout.EAST);

        this.panel.add(topPanel);
        this.panel.add(centerPanel);
        this.panel.add(bottomPanel);




        this.setContentPane(panel);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
