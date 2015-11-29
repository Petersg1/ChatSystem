package system; /**
 * Created by pierre on 19/10/15.
 *
 */
import data.*;
import gui.Gui;
import network.*;
import data.User;
import packet.Message;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Map;


public class ChatSystem {
// Attributs pour méthodes intermédiaires GUI Controller
    private Gui gui;
    private Data data;
    private ChatNi chatNi;

// méthodes intermédiaires GUI Controller

    public ChatSystem() throws IOException {
        this.gui = new Gui(this);
    }

    public void performConnect(String userName) throws IOException, ClassNotFoundException {
        this.data = new Data(userName, this);
        this.chatNi = new ChatNi(this.data, this);
        chatNi.listenPacket();
        chatNi.sendHello();
        data.getLocalUser().setConnected(true);

    }
    public void performDisconnect() throws IOException {
        chatNi.sendBye();
        data.getLocalUser().setConnected(false);
    }
    public void sendMessage(String payload, InetAddress ip, Boolean isBroadcast) throws IOException {
        chatNi.sendMessage(payload, ip, isBroadcast);
    }
    public ArrayList<User> getUserList() throws IOException {
        return data.getUserList();
    }

    public void notifyNewUserAdded() {
        this.gui.updateUserlist();
    }

    public void notifyMessage(Message message) {
        this.gui.newMessageArrived(message);
    }

    public InetAddress getBroadcastIp() {
        return this.data.getLocalUser().getBroadcastAddress();
    }
}

