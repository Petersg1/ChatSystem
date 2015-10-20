package Network;

import Packet.Hello;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by pierre on 20/10/15.
 */
public class ChatNi {

    public void sendHello() throws IOException {
        SendController sender = new SendController();
        sender.sHello(new Hello("Helene", InetAddress.getLocalHost()));
    }

    public void listenPacket() throws IOException, ClassNotFoundException {
        ReceiveController receiver = new ReceiveController();
        receiver.ListenUdpPacket();
    }

}
