package Data;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by pierre on 07/11/15.
 */
public class UsersList {

/* Attribus */
    private ArrayList<User> list;
    private int nbUser;

    /* Constructeur */
    public UsersList() {
        this.list = new ArrayList<>();
        this.nbUser = 0;
    }

    /* Methodes */
    public void add (User newUser) {
        this.list.add(newUser);
    }

    public void add (String nickname, InetAddress ip) {
        this.list.add(new User(nickname,ip));
    }

    public void remove (User user) {
        this.list.remove(user);
    }
}
