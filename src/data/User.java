package data;

import java.net.InetAddress;

/**
 * Created by pierre on 07/11/15.
 */
public class User {

    /* Attribus */
    private String nickname;
    private InetAddress ip;

    /* Constructeur */
    public User (String nickname, InetAddress ip) {
        this.nickname = nickname;
        this.ip = ip;
    }

    public InetAddress getIp() {
        return ip;
    }
}
