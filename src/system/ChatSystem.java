package system; /**
 * Created by pierre on 19/10/15.
 *
 */
import data.*;
import network.*;
import data.User;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Map;


public class ChatSystem {
// Attributs pour méthodes intermédiaires GUI Controller
    Data data;
    ChatNi chatNi;

// méthodes intermédiaires GUI Controller

    public void performConnect(String userName) throws IOException {
        this.data = new Data(userName);
        chatNi.sendHello();
        data.getLocalUser().setConnected(true);

    }
    public void performDisconnect() throws IOException {
        chatNi.sendBye();
        data.getLocalUser().setConnected(false);
    }
    public void sendMessageUnicast(String name, String payload, InetAddress ip) throws IOException {
        chatNi.sendMessageUnicast(name,payload,ip);
    }
    public void sendMessageBroadcast(String name, String payload) throws IOException {
        chatNi.sendMessageBroadcast(name, payload);
    }
    public Map<InetAddress, String> getUserList() throws IOException {
        return data.getUserList();
    }


}

