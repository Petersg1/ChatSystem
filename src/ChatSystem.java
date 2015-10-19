/**
 * Created by pierre on 19/10/15.
 */
import Packet.*;
import Network.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class ChatSystem {

    public ChatSystem(){

    }



    public static void main (String[] args) throws IOException, ClassNotFoundException {
        SendController sender = new SendController();
        ReceiveController receiver = new ReceiveController();

        sender.sHello(new Hello("Helene", InetAddress.getLocalHost()));
        receiver.ListenUdpPacket();
    }
}
