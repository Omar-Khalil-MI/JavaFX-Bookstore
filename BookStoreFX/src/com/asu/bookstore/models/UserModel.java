package com.asu.bookstore.models;

import com.asu.bookstore.gui.HelperGUI;
import com.asu.bookstore.main.Main;

public abstract class UserModel {

    protected boolean isAdmin;
    protected String userName;
    protected String mail;
    protected int passwordHash;
    protected AddressModel address;
    
    public UserModel(){
        isAdmin = false;
        userName = null;
        mail = null;
        passwordHash = 0;
        address = null;
    }
    
    public UserModel(boolean isAdmin, String userName, String mail,
            int passwordHash, AddressModel address) {
        this.isAdmin = isAdmin;
        this.userName = userName;
        this.mail = mail;
        this.passwordHash = passwordHash;
        this.address = address;
    }

    public static void handleLogin(String enteredUsername, int enteredPassword) {
        boolean valid = false;
        String message = "User Not Found!";
        UserModel user = null;
        for(int i = 0; i < Main.Users.size(); i++){
            user = Main.Users.get(i);
            if (enteredUsername.equals(user.getUserName())) {
                if (enteredPassword == user.getPasswordHash()) {
                    message = "Welcome Back!";
                    valid = true;
                }
                else message = "Incorrect Password!";
                break;
            }
        }
        if (valid) {
            if (user.getIsAdmin())
                    Main.SessionAdmin = user;
            else Main.SessionUser = (ReaderModel)user;
        }
        HelperGUI.get_ResultScene(valid, message);
    }
    
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public String getMail() {
        return mail;
    }

    public int getPasswordHash() {
        return passwordHash;
    }

    public AddressModel getAddress() {
        return address;
    }
    
    @Override
    public String toString() {
        return "User{" + "isAdmin=" + isAdmin + ", userName=" + userName + ", mail="
                + mail + ", password=" + passwordHash + ", address=" + address + '}';
    }   
}
