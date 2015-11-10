package network;

import data.*;
import packet.*;

import java.io.IOException;

/**
 * Created by pierre on 20/10/15.
 *
 * Class facade
 */
public class ChatNi {


    /* Attributs */
    private Data data;
    private SendController sender;

    /* Constructeurs */
    public ChatNi(Data data) {
        this.data = data;
        this.sender = new SendController();
    }


    //Envoie un hello
    public void sendHello() throws IOException {
        sender.sHello(new Hello("Pierre", data.getLocalUser().getIp()));
    }

    public void sendBye() throws IOException {
        sender.sBye(new Bye("Pierre", data.getLocalUser().getIp()));
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
}
