package network;

import data.*;
import packet.*;
import system.ChatSystem;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Calendar;

/**
 * Created by pierre on 20/10/15.
 *
 * Class facade
 */
public class ChatNi {


    /* Attributs */
    private Data data;
    private SendController sender;
    private Hello hello;
    private Bye bye;
    private HelloBack helloBack;
    private ChatSystem chatSystem;

    /* Constructeurs */
    public ChatNi(Data data, ChatSystem cc) {
        this.data = data;
        this.chatSystem = cc;
        this.sender = new SendController(data.getLocalUser().getBroadcastAddress());
        this.hello = new Hello(data.getLocalUser().getNickname(), data.getLocalUser().getIp());
        this.bye = new Bye(data.getLocalUser().getNickname(), data.getLocalUser().getIp());
        this.helloBack = new HelloBack(data.getLocalUser().getNickname(), data.getLocalUser().getIp());

    }


    //Envoie un hello
    public void sendHello() throws IOException {
        sender.sHello(this.hello);
    }

    public void sendHelloBack(InetAddress ip) throws IOException {
        this.sender.sHelloBack(this.helloBack, ip);
    }

    public void sendBye() throws IOException {
        sender.sBye(this.bye);
    }

    public void sendMessage(String payload, InetAddress ip, Boolean isBroadcast) throws IOException {
            sender.sMessageUnicast(new Message(Calendar.getInstance().getTime(), this.data.getLocalUser().getNickname(), payload, data.getLocalUser().getIp(), isBroadcast), ip);
     }

    //Pour écouter les packets
    public void listenPacket() throws IOException, ClassNotFoundException {
        ReceiveController receiverThread = new ReceiveController(this, data);
        receiverThread.start();
    }

    public void MessageReceived(Message message) {
        this.chatSystem.notifyMessage(message);
    }

}

    /* Method Process */


