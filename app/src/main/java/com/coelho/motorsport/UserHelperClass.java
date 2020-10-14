package com.coelho.motorsport;

public class UserHelperClass {

    String name, username, email, location, motorcycle, password;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String username, String email, String location, String motorcycle, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.location = location;
        this.motorcycle = motorcycle;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMotorcycle() {
        return motorcycle;
    }

    public void setMotorcycle(String motorcycle) {
        this.motorcycle = motorcycle;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}