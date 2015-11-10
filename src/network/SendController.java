package network;

import packet.Hello;
import packet.SerDeser;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by pierre on 19/10/15.
 */
public class SendController {

    //Envoie un hello à tous <3 enfin au localhost pour l'instant
    public void sHello(Hello helloPacket) throws IOException {

        byte[] byteHello = SerDeser.serialize(helloPacket);
        DatagramSocket udpSocket = new DatagramSocket();
        DatagramPacket udpPacket = new DatagramPacket(byteHello,byteHello.length,InetAddress.getByName("255.255.255.255"), 42025 );

        udpSocket.setBroadcast(true);
        udpSocket.send(udpPacket);
        udpSocket.close();

        System.out.println("Hi, just sent the packet to " + InetAddress.getLoopbackAddress());
    }
}
