package network;

import data.*;
import packet.Bye;
import packet.Hello;

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

    /* Constructeurs */
    public ChatNi(Data data) {
        this.data = data;
    }


    //Envoie un hello
    public void sendHello() throws IOException {
        SendController sender = new SendController();
        sender.sHello(new Hello("Helene", InetAddress.getLocalHost()));
    }

    //Pour écouter les packets
    public void listenPacket() throws IOException, ClassNotFoundException {
        ReceiveController receiverThread = new ReceiveController(this);
        receiverThread.start();
    }

    /* Method Process */

    public void processHello(Hello helloReceived) {
        System.out.println("J'ai reçu un hello de " + helloReceived.getNickname() + " d'adresse " + helloReceived.getIp() + ".");
        this.data.addUser(helloReceived.getNickname(), helloReceived.getIp());
    }

    public void processBye(Bye byeReceived){
        System.out.println("J'ai reçu un bye de " + byeReceived.getNickname() + "d'adresse " + byeReceived.getIp() + ".");
        this.data.removeUser(byeReceived.getIp());
    }


    public void processWeirdPacket() {
        System.out.println("Something was received, but we don't know what :/");
    }
}
