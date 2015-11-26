package gui;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Map;

import system.ChatSystem;

/**
 * Created by pierre on 25/11/15.
 */
public class Gui {

    private LoginWindow loginWindow;
    private UserListWindow userListWindow;
    private ChatSystem chatSystem;


    /* Constructor */
    public Gui(ChatSystem chatSystem) throws IOException {
        this.chatSystem = chatSystem;
        this.loginWindow = new LoginWindow(this);
    }


    /* Methode */
    public void launchUserListWindow(String name) throws IOException {
        this.userListWindow = new UserListWindow(this);
        chatSystem.performConnect(name);
    }

    public Map<InetAddress,String> getUserList() throws IOException {
        return chatSystem.getUserList();
    }
}
