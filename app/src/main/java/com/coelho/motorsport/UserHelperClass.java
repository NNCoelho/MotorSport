package com.coelho.motorsport;

public class UserHelperClass {

    String name, username, email, PhoneNo, password;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String username, String email, String phoneNo, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        PhoneNo = phoneNo;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}