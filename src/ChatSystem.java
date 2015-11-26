/**
 * Created by pierre on 19/10/15.
 *
 */
import data.*;
import gui.*;
import network.*;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;


public class ChatSystem {
// Attributs pour méthodes intermédiaires GUI Controller
    Data data;
    ChatNi chatNi;

    //Fonction principale
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {


        /* Attribus acteurs */

        //UserListWindow userListWindow = new UserListWindow();
        Gui gui = new Gui();
        Data data = new Data("ln");
        ChatNi network = new ChatNi(data);


        Scanner sc = new Scanner(System.in);
        Integer choix = 1;
        String messagePayload;
        String ipUnicast;
        String name;
        System.out.println("What is your name? ");
        name = sc.nextLine();
        System.out.println("Make your choice.\n1- Listening packets\n2- Sending a hello\n3- Sending a Bye\n4- Sending a Broadcast Message\n5- Sending a Unicast Message\n0- Exit");

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
                    System.out.println("Que voulez vous dire en broadcast? ");
                    messagePayload = sc.nextLine();
                    network.sendMessageBroadcast(name, messagePayload);
                    break;
                case 5:
                    sc.nextLine();
                    System.out.println("A qui voulez vous parler en Unicast ? ");
                    ipUnicast=sc.nextLine();
                    System.out.println("Que voulez vous lui dire ? ");
                    messagePayload = sc.nextLine();
                    network.sendMessageUnicast(name,messagePayload, InetAddress.getByName(ipUnicast));
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

// méthodes intermédiaires GUI Controller

    public void performConnect(String userName) throws IOException {
        this.data = new Data(userName);
        chatNi.sendHello();
        data.getLocalUser().setConnected(true);
    }
    public void performDisconnect() throws IOException {
        chatNi.sendBye();
        data.getLocalUser().setConnected(false);
    }
    public void updateUserList() throws IOException {
        data.getUserList();
    }
    public void sendMessageUnicast(String name, String payload, InetAddress ip) throws IOException {
        chatNi.sendMessageUnicast(name,payload,ip);
    }
    public void sendMessageBroadcast(String name, String payload) throws IOException {
        chatNi.sendMessageBroadcast(name, payload);
    }
}

