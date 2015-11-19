package data;

import java.net.InetAddress;

/**
 * Created by pierre on 09/11/15.
 */
public class LocalUser extends User {
    /* attributs */
    private Boolean connected;
    private InetAddress broadcastAddress;

    /* Constructeur */
    public LocalUser(String nickname, InetAddress ip, InetAddress bIp) {
        super(nickname,ip);
        this.broadcastAddress = bIp;
    }

    /* methodes */
    public void setConnected(Boolean connected) {
        this.connected = connected;
    }
    public Boolean getConnected() {
        return this.connected;
    }

    public InetAddress getBroadcastAddress() {
        return this.broadcastAddress;
    }
}
