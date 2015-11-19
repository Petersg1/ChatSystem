package network;

import packet.*;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by pierre on 19/10/15.
 */
public class SendController {

    private InetAddress broadcastAddress;

    public SendController(InetAddress bIp) {
        this.broadcastAddress = bIp;
    }

    //Envoie un packet en broadcast sur 42025
    public void sendBroadcast(Packet packet) throws IOException {
        byte[] bytePacket = SerDeser.serialize(packet);
        DatagramSocket udpSocket = new DatagramSocket();
        DatagramPacket udpPacket = new DatagramPacket(bytePacket,bytePacket.length,this.broadcastAddress, 42025 );

        udpSocket.setBroadcast(true);
        udpSocket.send(udpPacket);
        udpSocket.close();

        System.out.println("Hi, just sent the packet to my local network");
    }

    public void sendUnicast(Packet packet, InetAddress ip) throws IOException {
        byte[] bytePacket = SerDeser.serialize(packet);
        DatagramSocket udpSocket = new DatagramSocket();
        DatagramPacket udpPacket = new DatagramPacket(bytePacket,bytePacket.length,ip, 42025 );

        udpSocket.send(udpPacket);
        udpSocket.close();

        System.out.println("Hi, just sent the packet to " + ip.toString());
    }

    //Envoie un hello à tous <3 enfin au localhost pour l'instant
    public void sHello(Hello helloPacket) throws IOException {
        sendBroadcast(helloPacket);
    }

    //Envoie un bye à tous <3 enfin au localhost pour l'instant
    public void sBye(Bye byePacket) throws IOException {
        sendBroadcast(byePacket);
    }

    public void sHelloBack(HelloBack helloBackPacket,InetAddress ip) throws IOException {
        sendUnicast(helloBackPacket, ip);
    }

    public void sMessage(Message messagePacket, InetAddress ip) throws IOException {
        sendUnicast(messagePacket, ip);
    }

    public void sMessageBroadcast(Message messagePacket) throws IOException {
        sendBroadcast(messagePacket);
    }

}
