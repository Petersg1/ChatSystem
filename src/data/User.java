package data;

import java.net.InetAddress;

/**
 * Created by pierre on 07/11/15.
 */
public class User {

    /* Attribus */
    protected String nickname;
    protected InetAddress ip;

    /* Constructeur */
    public User (String nickname, InetAddress ip) {
        this.nickname = nickname;
        this.ip = ip;
    }

    /* methodes */
    public InetAddress getIp() {
        return this.ip;
    }

    public String getNickname() {
        return this.nickname;
    }
}
