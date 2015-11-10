package packet;

import java.net.InetAddress;


public class Bye extends Packet {
    private String nickname;
    private InetAddress ip;

    /* Constructor */
    public Bye(String nickname){
        this.nickname = nickname;
    }

    /* Methods */
    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}