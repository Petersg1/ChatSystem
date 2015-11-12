package network;

import data.Data;
import packet.Bye;
import packet.Hello;
import packet.HelloBack;
import packet.SerDeser;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


/**
 * Created by pierre on 19/10/15.
 */
public class ReceiveController extends Thread {

    /* Attributs */
    ChatNi chatNi;
    Data data;


    /* Constructeur */
    public ReceiveController(ChatNi chatNi, Data data) {
        this.chatNi = chatNi;
        this.data = data;
    }

    /* Methodes */
    @Override
    public void run() {
        System.out.println("Je suis dans le run");
        try {
            this.ListenUdpPacket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    private void ListenUdpPacket() throws IOException, ClassNotFoundException {
        byte[] buf = new byte[1000]; //Création du buffer de reception
        Object receivedObject;
        DatagramSocket serverSocket = new DatagramSocket(42025); //Ecoute sur le port 42025
        DatagramPacket receivedPacket = new DatagramPacket(buf, 1000); //Packet qui sera le packet reçu

        while (true) { // On écoute en permanence
            serverSocket.receive(receivedPacket); //ecoute
            receivedObject = SerDeser.deserialize(receivedPacket.getData()); //Cast la data du packet en object


            //Fais ce qu'il faut en conséquence de ce qui est reçu
            if (receivedObject instanceof Hello) {
                this.processHello((Hello) receivedObject);
            }
            else if (receivedObject instanceof Bye){
                this.processBye((Bye) receivedObject);
            }
            else if (receivedObject instanceof HelloBack) {
                this.processHelloBack((HelloBack) receivedObject);
            } else {
                this.processWeirdPacket();
            }
        }
    }

    public void processHello(Hello helloReceived) throws IOException {
        System.out.println("J'ai reçu un hello de " + helloReceived.getNickname() + " d'adresse " + helloReceived.getIp() + ".");
        this.data.addUser(helloReceived.getNickname(), helloReceived.getIp());
        this.chatNi.sendHelloBack(helloReceived.getIp());
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
