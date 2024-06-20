package com.asu.bookstore.models;

import com.asu.bookstore.gui.HelperGUI;
import com.asu.bookstore.main.Main;
import java.util.ArrayList;

public class ReaderModel extends UserModel {
    
    private String phoneNumber;
    private int readerID;
  
    //Constructors//
    public ReaderModel() {
        super();
        phoneNumber = null;
        readerID = -1;
    }
    
    public ReaderModel(boolean isAdmin, String userName, String mail, int password,
            AddressModel address, String phoneNumber) {
        super(isAdmin, userName, mail, password, address);
        readerID = Main.Users.size();
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    //Functions//
    public void updateReader() {
        Main.SessionUser.setAddress(address);
        Main.SessionUser.setMail(mail);
        Main.SessionUser.setPassword(passwordHash);
        Main.SessionUser.setUserName(userName);
        Main.SessionUser.setPhoneNumber(phoneNumber);
    }

    public static ArrayList<OrderModel> viewOrders() {
        ArrayList<OrderModel> list = new ArrayList<>();
        for (int i = 0; i < Main.Orders.size(); i++) {
            if (Main.Orders.get(i).getReaderName().equals(Main.SessionUser.getUserName())) 
                list.add(Main.Orders.get(i));
        }
        return list;
    }

    public void signupReader(String password, String confirmPassword) {
        boolean valid = true;
        String message = "Signed Up Successfully!";
        for(int i = 0; i < Main.Users.size(); i++){
            UserModel user = Main.Users.get(i);
            if (userName.equals( user.getUserName())) {
                valid = false;
                message = "Username Taken!";
                break;
            }
        }
        if (password.length() < 5) {
            valid = false;
            message = "Password Must Contain More Than 4 digits!";
        }
        else if (!(password.equals(confirmPassword))) {
            valid = false;
            message = "Password Does not Match!";
        }
        else if (phoneNumber.length() != 11) {
            valid = false;
            message = "Invalid Phone Number!";
        }
        else {
            for (int i = 0; i < phoneNumber.length(); i++) {
                if( phoneNumber.charAt(i)< 48 || phoneNumber.charAt(i) > 57) {
                    valid = false;
                    message = "Invalid Phone Number!";
                }
            }
        }    
        if (valid) {
            Main.Users.add(this);
            Main.SessionUser = (ReaderModel)Main.Users.getLast();
        }
        HelperGUI.get_ResultScene(valid, message);
    }
    
    //Getters-Setters//
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString() {
        return "Reader{" + "phoneNumber=" + phoneNumber + ", readerID=" + readerID + '}';
    }

    public int getReaderID() {
        return readerID;
    }

    public void setReaderID(int readerID) {
        this.readerID = readerID;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public int getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(int passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }
}