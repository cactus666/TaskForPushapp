package com.pushapp.POJO;

import android.os.Parcel;
import android.os.Parcelable;

public class Password implements Parcelable {

    private String name;
    private String link;
    private String login;
    private String pass;
    private int id;

    public Password(String name, String link, String login, String pass, int id){
        this.name = name;
        this.link = link;
        this.login = login;
        this.pass = pass;
        this.id = id;
    }

    protected Password(Parcel in) {
        name = in.readString();
        link = in.readString();
        login = in.readString();
        pass = in.readString();
        id = in.readInt();
    }

    public static final Creator<Password> CREATOR = new Creator<Password>() {
        @Override
        public Password createFromParcel(Parcel in) {
            return new Password(in);
        }

        @Override
        public Password[] newArray(int size) {
            return new Password[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(link);
        dest.writeString(login);
        dest.writeString(pass);
        dest.writeInt(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
