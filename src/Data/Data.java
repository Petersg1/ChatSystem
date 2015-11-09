package Data;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by pierre on 09/11/15.
 */
public class Data {

    /* Attributs */
    private UsersList usersList;
    private LocalUser localUser;

    /* Constructeur */
    public Data() throws UnknownHostException {
        this.usersList = new UsersList();
        this.localUser = new LocalUser("", InetAddress.getLocalHost());
    }

    public void addUser(String nickname, InetAddress ip) {
        this.usersList.add(nickname, ip);
    }
}
