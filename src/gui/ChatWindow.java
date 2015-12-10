package gui;

import data.User;
import packet.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by pierre on 26/11/15.
 */
public class ChatWindow extends JFrame implements ActionListener {

    private Gui gui;
    private JPanel panel = new JPanel(new GridBagLayout());
    private JButton bSend;
    private JTextField textToSend;
    private JTextArea conversation;
    private String myName;
    private User talkingUser;

    public ChatWindow(Gui gui, String myNickname, User User) {
        super();
        this.gui = gui;
        this.myName = myNickname;
        this.talkingUser=User;
        //caractéristique de la fenetre
        this.setTitle(this.talkingUser.getNickname() + " (" + this.talkingUser.getIp().toString().substring(1) + ")");
        this.setLocationRelativeTo(null);
        this.setSize(600,620);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        GridBagConstraints gbc = new GridBagConstraints();


        JLabel title = new JLabel("Conversation with "); // + talkingUser.getNickname(), SwingConstants.CENTER);
        title.setSize(new Dimension(600,35));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridheight=1;
        gbc.gridwidth=2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);



        this.conversation = new JTextArea("");
        this.conversation.setFont(new Font("Arial", Font.BOLD,14));
        //conversation.setPreferredSize(new Dimension(600,555));
        conversation.setAutoscrolls(true);
        conversation.setEditable(false);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridheight=1;
        gbc.gridwidth=2;
        JScrollPane scrollPane = new JScrollPane(conversation);
        scrollPane.setPreferredSize(new Dimension(600,555));
        panel.add(scrollPane, gbc);

        this.textToSend = new JTextField("");
        this.textToSend.setPreferredSize(new Dimension(550,15));
        this.textToSend.setEditable(true);
        this.textToSend.addActionListener(this);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridheight=1;
        gbc.gridwidth=1;
        gbc.fill=GridBagConstraints.BOTH;
        panel.add(textToSend, gbc);

        this.bSend = new JButton("Send");
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
        this.textToSend.grabFocus();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.bSend || actionEvent.getSource() == this.textToSend) {
            try {
                if (this.gui.getUserList().contains(this.talkingUser)) {
                    String textToSend = this.textToSend.getText();
                    this.conversation.append(Calendar.getInstance().getTime() + " " + this.myName + " : ");
                    this.conversation.append(textToSend + "\n");
                    try {
                        if (this.talkingUser.getIp().equals(gui.getBroadcastIp()))
                            this.gui.sendMessage(textToSend, this.talkingUser.getIp(), true);
                        else
                            this.gui.sendMessage(textToSend, this.talkingUser.getIp(), false);
                        this.textToSend.setText("");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Your friend is disconnected");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void printMessage(Message message) {
        this.conversation.append(message.getTime() + " " + message.getFrom() + " : " + message.getPayload() + "\n");
    }
}
