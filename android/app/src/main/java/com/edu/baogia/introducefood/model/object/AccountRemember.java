package com.edu.baogia.introducefood.model.object;

public class AccountRemember {
    private String username;
    private String password;
    private int iduser;
    private Boolean check;

    public AccountRemember() {
    }

    public AccountRemember(String username, String password, int iduser, Boolean check) {
        this.username = username;
        this.password = password;
        this.iduser = iduser;
        this.check = check;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }
}
