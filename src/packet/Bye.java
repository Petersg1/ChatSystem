package packet;

/**
 * Created by pierre on 10/11/15.
 */

import java.net.InetAddress;

public class Bye extends Packet{
    private String nickname;
    private InetAddress ip;

    public Bye(String nickname, InetAddress ip){
        this.nickname = nickname;
        this.ip = ip;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}