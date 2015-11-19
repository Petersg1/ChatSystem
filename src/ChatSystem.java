/**
 * Created by pierre on 19/10/15.
 *
 */
import data.*;
import network.*;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;


public class ChatSystem {


    //Fonction principale
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        /* Attribus acteurs */
        Data data = new Data();
        ChatNi network = new ChatNi(data);


        Scanner sc = new Scanner(System.in);
        Integer choix = 1;
        String messagePayload;
        String name;
        System.out.println("What is your name? ");
        name = sc.nextLine();
        System.out.println("Make your choice.\n1- Listening a packet\n2- Sending a packet\n3- Sending a Bye\n4- Sending a Message\n0- Exit");

        while (choix != 0) {
            choix = sc.nextInt();
            //mise sur écoute ou envoi d'un packet en fonction de ce que veut l'utilisateur
            if (choix == 1) {
                network.listenPacket();
            } else if (choix == 2) {
                network.sendHello();
            } else if (choix == 3) {
                network.sendBye();
            } else if (choix == 4) {
                sc.nextLine();
                System.out.println("Que voulez vous dire ? ");
                messagePayload = sc.nextLine();
                network.sendMessageBroadcast(name, messagePayload);
            } else if (choix == 0) {
                System.out.println("Au revoir madame/monsieur.");
                //Mettre en place quelle chose pour arreter la thread.
            } else {
                System.out.println("Vous faites n'importe quoi. Try again.");
            }
        }
    }
}

