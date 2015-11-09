package Data;

import java.net.InetAddress;

/**
 * Created by pierre on 09/11/15.
 */
public class LocalUser extends User {
    /* attributs */
    private Boolean connected;


    /* Constructeur */
    public LocalUser(String nickname, InetAddress ip) {
        super(nickname,ip);
    }

    /* methodes */
    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public Boolean getConnected() {
        return this.connected;
    }
}
