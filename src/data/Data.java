package data;

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


    /* Constructeur */
    public Data(String userName) throws UnknownHostException, SocketException {
        this.usersList = new UsersList();

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

    }

    /* Methods */

    public LocalUser getLocalUser() {
        return localUser;
    }

    public void addUser(String nickname, InetAddress ip) {
        this.usersList.add(nickname, ip);
    }
    public void addUser(User user) {
        this.usersList.add(user);
    }

    public void removeUser(User user) {
        this.usersList.remove(user);
    }

    public void removeUser(InetAddress ip) {
        this.usersList.remove(ip);
    }

    public Boolean userConnected() {
        return localUser.getConnected();
    }

    public ArrayList<User> getUserList(){
        return usersList.getUserList();
    }

}
