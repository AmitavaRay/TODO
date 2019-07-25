package com.stcet.navdrawer;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {
    @PrimaryKey
    String username;
    String password;
    String tempname, tempdue;
    String Holder_color="";

//    String name;

    String tempflag;
    RealmList<String> flag = new RealmList<>();
    RealmList<String> title = new RealmList<>();
    RealmList<String> due= new RealmList<>();

    public String getHolder_color() {
        return Holder_color;
    }

    public void setHolder_color(String holder_color) {
        Holder_color = holder_color;
    }

    public String getTempflag() {
        return tempflag;
    }

    public void setTempflag(String tempflag) {
        this.tempflag = tempflag;
        flag.add(tempflag);
    }

    public String getTempdue() {
        return tempdue;
    }

    public void setTempdue(String tempdue) {
        this.tempdue = tempdue;
        due.add(tempdue);
    }
    public String getTempname() {
        return tempname;
    }

    public void setTempname(String tempname) {
        this.tempname = tempname;
        title.add(tempname);
    }

    public String getUsername(){return this.username;}
    public void setUsername(String username){this.username=username;}

    public String getPassword(){return this.password;}
    public void setPassword(String password){this.password=password;}

//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
