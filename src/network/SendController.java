package network;

import packet.Bye;
import packet.Hello;
import packet.Packet;
import packet.SerDeser;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by pierre on 19/10/15.
 */
public class SendController {

    //Envoie un packet en broadcast sur 42025
    public void sendBroadcast(Packet packet) throws IOException {
        byte[] bytePacket = SerDeser.serialize(packet);
        DatagramSocket udpSocket = new DatagramSocket();
        DatagramPacket udpPacket = new DatagramPacket(bytePacket,bytePacket.length,InetAddress.getByName("255.255.255.255"), 42025 );

        udpSocket.setBroadcast(true);
        udpSocket.send(udpPacket);
        udpSocket.close();

        System.out.println("Hi, just sent the packet to my local network");
    }

    //Envoie un hello à tous <3 enfin au localhost pour l'instant
    public void sHello(Hello helloPacket) throws IOException {
        sendBroadcast(helloPacket);
    }

    //Envoie un bye à tous <3 enfin au localhost pour l'instant
    public void sBye(Bye byePacket) throws IOException {
        sendBroadcast(byePacket);
    }
}
