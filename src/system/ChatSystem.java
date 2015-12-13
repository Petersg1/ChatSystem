package system; /**
 * Created by pierre on 19/10/15.
 *
 */
import data.*;
import gui.Gui;
import network.*;
import data.User;
import packet.Bye;
import packet.Hello;
import packet.HelloBack;
import packet.Message;

import java.io.IOException;
import java.net.DatagramPacket;
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

    public void notifyUserListModified() {
        this.gui.updateUserlist();
    }

    public InetAddress getBroadcastIp() {
        return this.data.getLocalUser().getBroadcastAddress();
    }

    public void processHello(Hello helloReceived) throws IOException {
        System.out.println("J'ai reçu un hello de " + helloReceived.getNickname() + " d'adresse " + helloReceived.getIp() + ".");
        if (!helloReceived.getIp().equals(data.getLocalUser().getIp())) {
            this.data.addUser(helloReceived.getNickname(), helloReceived.getIp());
            this.chatNi.sendHelloBack(helloReceived.getIp());
        }
    }

    public void processBye(Bye byeReceived){
        System.out.println("J'ai reçu un bye de " + byeReceived.getNickname() + " d'adresse " + byeReceived.getIp() + ".");
        this.data.removeUser(byeReceived.getIp());
    }

    public void processHelloBack(HelloBack helloBackReceived) {
        System.out.println("J'ai reçu un helloBack de " + helloBackReceived.getNickname() + " d'adresse " + helloBackReceived.getIp() + ".");
        this.data.addUser(helloBackReceived.getNickname(), helloBackReceived.getIp());
    }

    public void processWeirdPacket() {
        System.out.println("Something was received, but we don't know what :/");
    }

    public void processMessage(Message messageReceived){
        System.out.println(messageReceived.getFrom()+ " : "+ messageReceived.getPayload());
        if (!messageReceived.getIp().equals(data.getLocalUser().getIp())) {
            this.data.addUser(messageReceived.getFrom(), messageReceived.getIp());
            this.gui.newMessageArrived(messageReceived);
        }
    }
}

