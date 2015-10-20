/**
 * Created by pierre on 19/10/15.
 */
import Network.*;

import java.io.IOException;
import java.util.Scanner;


public class ChatSystem {

    public static void main (String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        ChatNi network = new ChatNi();

        System.out.println("Make your choice.\n1- Listening a packet\n2- Sending a packet");
        Integer str = sc.nextInt();


        if (str==1) {
            network.listenPacket();
        } else if (str == 2) {
            network.sendHello();
        } else {
            System.out.println("Vous faites n'importe quoi. Au revoir.");
        }
    }
}
