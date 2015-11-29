package gui;

import data.User;

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
    private JPanel panel = new JPanel(new GridBagLayout());

    public ChatWindow(Gui gui, User talkingUser) {
        super();
        this.gui = gui;
        //caractéristique de la fenetre
        this.setTitle("User List");
        this.setLocationRelativeTo(null);
        this.setSize(600,610);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        GridBagConstraints gbc = new GridBagConstraints();


        JLabel title = new JLabel("Conversation with " + talkingUser.getNickname(), SwingConstants.CENTER);
        title.setBackground(Color.cyan);
        title.setPreferredSize(new Dimension(600,25));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridheight=1;
        gbc.gridwidth=2;
        panel.add(title, gbc);


        JTextArea conversation = new JTextArea("Yo");
        conversation.setPreferredSize(new Dimension(600,555));
        conversation.setAutoscrolls(true);
        conversation.setEditable(false);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridheight=1;
        gbc.gridwidth=2;
        panel.add(conversation, gbc);



        JTextField textToSend = new JTextField("Je suis là");
        textToSend.setPreferredSize(new Dimension(550,15));
        textToSend.setEditable(true);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridheight=1;
        gbc.gridwidth=1;
        gbc.fill=GridBagConstraints.BOTH;
        panel.add(textToSend, gbc);

        JButton bSend = new JButton("Send");
        title.setPreferredSize(new Dimension(50,15));
        bSend.addActionListener(this);
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridheight=1;
        gbc.gridwidth=1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(bSend, gbc);

        JPanel bigPanel = new JPanel();
        this.setContentPane(panel);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
