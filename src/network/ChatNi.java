package network;

import data.*;
import packet.*;

import java.io.IOException;
import java.net.InetAddress;

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


    /* Constructeurs */
    public ChatNi(Data data) {
        this.data = data;
        this.sender = new SendController();
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

    //Pour écouter les packets
    public void listenPacket() throws IOException, ClassNotFoundException {
        ReceiveController receiverThread = new ReceiveController(this, data);
        receiverThread.start();
    }
}

    /* Method Process */


