package com.edu.baogia.introducefood.model.object;

public class Account {
    private String username;
    private String password;
    private int iduser;
    private int quyen;

    public Account() {
    }

    public Account(String username, String password, int iduser, int quyen) {
        this.username = username;
        this.password = password;
        this.iduser = iduser;
        this.quyen = quyen;
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

    public int getQuyen() {
        return quyen;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }

}
