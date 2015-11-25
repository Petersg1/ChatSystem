package packet;

import java.net.InetAddress;


public class Bye extends Packet {

    static final long serialVersionUID = 40L;


    private String nickname;
    private InetAddress ip;

    /* Constructor */
    public Bye(String nickname, InetAddress ip){
        this.nickname = nickname;
        this.ip = ip;
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