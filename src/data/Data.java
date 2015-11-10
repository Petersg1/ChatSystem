package data;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by pierre on 09/11/15.
 */
public class Data {

    /* Attributs */
    private UsersList usersList;
    private LocalUser localUser;

    /* Constructeur */
    public Data() throws UnknownHostException, SocketException {
        this.usersList = new UsersList();

        String ip;
        InetAddress addr = InetAddress.getLocalHost();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    addr = addresses.nextElement();
                    ip = addr.getHostAddress();
                    System.out.println(iface.getDisplayName() + " " + ip);
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        if (addr == InetAddress.getLocalHost()) {
            System.out.println("/!\\ Adresse local : " + addr);
        } else {
            this.localUser = new LocalUser("Pierre", addr);
            System.out.println("/!\\ Adresse utilisée : " + addr);
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
}
