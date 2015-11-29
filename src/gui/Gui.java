package gui;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import data.User;
import packet.Message;
import system.ChatSystem;

/**
 * Created by pierre on 25/11/15.
 */
public class Gui {

    private LoginWindow loginWindow;
    private UserListWindow userListWindow;
    private ChatSystem chatSystem;
    private String myName;
    private HashMap<InetAddress,ChatWindow> chatWindows;


    /* Constructor */
    public Gui(ChatSystem chatSystem) throws IOException {
        this.chatSystem = chatSystem;
        this.loginWindow = new LoginWindow(this);
        this.chatWindows = new HashMap<InetAddress, ChatWindow>();
    }


    /* Methode */
    public void launchUserListWindow(String name) throws IOException, ClassNotFoundException {
        chatSystem.performConnect(name);
        this.myName = name;
        this.userListWindow = new UserListWindow(this, chatSystem.getUserList());
    }

    public void launchChatWindow(User talkingUser) {
        if (!chatWindows.containsKey(talkingUser.getIp())) {
            ChatWindow chatWindow = new ChatWindow(this, this.myName, talkingUser);
            this.chatWindows.put(talkingUser.getIp(), chatWindow);
        } else {
            System.out.println("/!\\Chatwindow already open for this user. /!\\");
        }
    }

    public ArrayList<User> getUserList() throws IOException {
        return chatSystem.getUserList();
    }

    public void performGoodbye() throws IOException {
        chatSystem.performDisconnect();
    }

    public void sendMessage(String payload, InetAddress ip, Boolean isBroadcast) throws IOException {
        this.chatSystem.sendMessage(payload,ip, isBroadcast);
    }

    public void newMessageArrived(Message message) {

        if (!message.isBroadcast()) {
            if (this.chatWindows.containsKey(message.getIp())) {
                this.chatWindows.get(message.getIp()).printMessage(message);
            } else {
                ChatWindow chatWindow = new ChatWindow(this, this.myName, new User(message.getFrom(), message.getIp()));
                this.chatWindows.put(message.getIp(), chatWindow);
            }
        } else {
            if (this.chatWindows.containsKey(this.getBroadcastIp())) {
                this.chatWindows.get(this.getBroadcastIp()).printMessage(message);
            } else {
                ChatWindow chatWindow = new ChatWindow(this, this.myName, new User("Broadcast", this.getBroadcastIp()));
                this.chatWindows.put(this.getBroadcastIp(), chatWindow);
            }
        }
    }

    public InetAddress getBroadcastIp() {
        return this.chatSystem.getBroadcastIp();
    }

    public void updateUserlist() {
        if (userListWindow != null)
            this.userListWindow.updateUserlist();
    }
}
