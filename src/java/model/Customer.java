/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Customer implements Serializable {
    private int id;
    private String name, address, id_card, phonenumber, email, username, password;
    private String dob;
    private boolean gender;

    public Customer() {
    }

    public Customer(int id, String name, String address, String id_card, String phonenumber, String email, String username, String password, String dob, boolean gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.id_card = id_card;
        this.phonenumber = phonenumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
    }
    
    public Customer(String name, String address, String id_card, String phonenumber, String email, String username, String password, String dob, boolean gender) {
        this.name = name;
        this.address = address;
        this.id_card = id_card;
        this.phonenumber = phonenumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
