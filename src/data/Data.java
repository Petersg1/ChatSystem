package data;

import system.ChatSystem;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Created by pierre on 09/11/15.
 */
public class Data {

    /* Attributs */
    private UsersList usersList;
    private LocalUser localUser;
    private ChatSystem chatSystem;

    /* Constructeur */
    public Data(String userName, ChatSystem cc) throws UnknownHostException, SocketException {
        this.usersList = new UsersList();
        this.chatSystem = cc;
        String ip;
        InetAddress addr = InetAddress.getLocalHost();
        InetAddress broadcastAddr = InetAddress.getLocalHost();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                // filters out 127.0.0.1 and inactive interfaces
                NetworkInterface iface = interfaces.nextElement();
                if (!iface.isLoopback() && iface.isUp()) {
                    for (InterfaceAddress interfaceaddr : iface.getInterfaceAddresses()) {
                        broadcastAddr = interfaceaddr.getBroadcast();
                        addr = interfaceaddr.getAddress();
                        ip = addr.getHostAddress();
                        System.out.println(iface.getDisplayName() + " " + ip);
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        if (addr == InetAddress.getLocalHost()) {
            System.out.println("/!\\ Adresse local : " + addr + "C'est pas bon !");
        } else {
            this.localUser = new LocalUser(userName, addr, broadcastAddr);
            System.out.println("/!\\ Adresse utilisée : " + addr);
            System.out.println("/!\\ Adresse broadcast utilisée : " + broadcastAddr);
        }
        this.usersList.add("Broadcast", broadcastAddr);
    }

    /* Methods */

    public LocalUser getLocalUser() {
        return localUser;
    }

    public void addUser(String nickname, InetAddress ip) {
        this.usersList.add(nickname, ip);
        this.chatSystem.notifyUserListModified();
    }


    public void removeUser(InetAddress ip) {
        this.usersList.remove(ip);
        this.chatSystem.notifyUserListModified();
    }

    public Boolean userConnected() {
        return localUser.getConnected();
    }

    public ArrayList<User> getUserList(){
        return usersList.getUserList();
    }

}
