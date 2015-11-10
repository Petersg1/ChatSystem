package packet;

/**
 * Created by helene on 10/11/15.
 */

import java.net.InetAddress;
public class Bye extends Packet {


    private String nickname;
    private InetAddress ip;

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public Bye(String nickname){
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}