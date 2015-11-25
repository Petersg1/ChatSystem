/**
 * Created by pierre on 19/10/15.
 *
 */
import data.*;
import gui.*;
import network.*;

import java.io.IOException;
import java.util.Scanner;


public class ChatSystem {


    //Fonction principale
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {


        /* Attribus acteurs */

        //UserListWindow userListWindow = new UserListWindow();
        Data data = new Data();
        Gui gui = new Gui();
        ChatNi network = new ChatNi(data);


        Scanner sc = new Scanner(System.in);
        Integer choix = 1;
        String messagePayload;
        String name;
        System.out.println("What is your name? ");
        name = sc.nextLine();
        System.out.println("Make your choice.\n1- Listening packets\n2- Sending a hello\n3- Sending a Bye\n4- Sending a Message\n0- Exit");

        while (choix != 0) {
            choix = sc.nextInt();
            //mise sur écoute ou envoi d'un packet en fonction de ce que veut l'utilisateur
            switch (choix) {
                case 1:
                    network.listenPacket();
                    break;
                case 2:
                    network.sendHello();
                    break;
                case 3:
                    network.sendBye();
                    break;
                case 4:
                    sc.nextLine();
                    System.out.println("Que voulez vous dire ? ");
                    messagePayload = sc.nextLine();
                    network.sendMessageBroadcast(name, messagePayload);
                    break;
                case 0:
                    System.out.println("Au revoir madame/monsieur.");
                    //Mettre en place quelle chose pour arreter la thread.
                    break;
                default:
                    System.out.println("Vous faites n'importe quoi. Try again.");
            }
        }
    }
}

