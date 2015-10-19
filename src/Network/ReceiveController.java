package Network;

import Packet.Hello;
import Packet.SerDeser;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by pierre on 19/10/15.
 */
public class ReceiveController {

    public void ListenUdpPacket() throws IOException, ClassNotFoundException {
        byte[] buf = new byte[1000];
        Object receivedObject;
        DatagramSocket serverSocket = new DatagramSocket();
        DatagramPacket receivedPacket = new DatagramPacket(buf, 1000);

        serverSocket.receive(receivedPacket);
        receivedObject = SerDeser.deserialize(receivedPacket.getData());


        if (receivedObject instanceof Hello) {
            helloReceived();
        } else {
            somethingReceived();
        }

    }

    private void helloReceived() {
        System.out.println("J'ai reçu un hello !!!! :D");
    }

    private void somethingReceived() {
        System.out.println("Something was received, but not a hello");
    }

}
