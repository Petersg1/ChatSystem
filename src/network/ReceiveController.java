package network;

import data.Data;
import packet.*;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;


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
                this.chatNi.processHello((Hello) receivedObject);
            }
            else if (receivedObject instanceof Bye){
                this.chatNi.processBye((Bye) receivedObject);
            }
            else if (receivedObject instanceof HelloBack) {
                this.chatNi.processHelloBack((HelloBack) receivedObject);
            }
            else if (receivedObject instanceof Message) {
                this.chatNi.processMessage((Message) receivedObject);

            } else {
                this.chatNi.processWeirdPacket();
            }
        }
    }

}
