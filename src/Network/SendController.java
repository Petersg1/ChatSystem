package Network;

import Packet.Hello;
import Packet.SerDeser;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by pierre on 19/10/15.
 */
public class SendController {


    public void sHello(Hello helloPacket) throws IOException {

        byte[] byteHello = SerDeser.serialize(helloPacket);
        DatagramSocket udpSocket = new DatagramSocket();
        DatagramPacket udpPacket = new DatagramPacket(byteHello,byteHello.length,InetAddress.getLoopbackAddress(), 42025 );

        udpSocket.setBroadcast(true);
        udpSocket.send(udpPacket);
        udpSocket.close();

        System.out.println("Hi, just sent the packet to " + InetAddress.getLoopbackAddress());
    }
}
