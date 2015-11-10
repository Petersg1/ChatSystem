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

    @Override
    public boolean equals(Object object)
    {
        boolean isEqual=false;
        if (object != null && object instanceof User)
        {
            isEqual=(this.nickname.equals(((User)object).getNickname())&& this.ip.equals(((User)object).getIp()));
            System.out.println("vous etes bien rentrés dans le equals overridé");
        }
        return isEqual;
    }
}
