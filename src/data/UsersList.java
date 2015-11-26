package data;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        if (this.list.contains(newUser)){
            System.out.println("User already in the list");
            this.printUserList();
        }
        else {
            this.list.add(newUser);
            //this.printUserList();
            this.nbUser += 1;
        }
    }
    public void add (String nickname, InetAddress ip) {
        boolean dedans = false;
        for (int i=0; i<this.list.size(); i++) {
            if (list.get(i).getIp().equals(ip)) {
                System.out.println("User already in the list");
                //this.printUserList();
                dedans = true;
            }
        }
        if (!dedans) {
            this.list.add(new User(nickname, ip));
            this.printUserList();
            this.nbUser += 1;
        }

    }

    public void remove (User user) {
        this.list.remove(user);
        //this.printUserList();
    }
    public void remove(InetAddress ip) {
        for (int i=0; i<this.list.size(); i++){
           if (list.get(i).getIp().equals(ip)){
               this.list.remove(i);
            }
        }
        this.printUserList();
    }

    /* /!\ Temporaire /!\ */
    public void printUserList() {
        for (int i=0; i<list.size();i++) {
            System.out.println(i + ") " + list.get(i).getNickname() + " " + list.get(i).ip);
        }
    }

    public int getNbUser() {
        return nbUser;
    }

    public Map<InetAddress,String > getUserList() {
        HashMap<InetAddress, String> m = new HashMap<>();
        for (User user : this.list) {
            m.put(user.getIp(),user.getNickname());
        }
    return m;
    }

}
