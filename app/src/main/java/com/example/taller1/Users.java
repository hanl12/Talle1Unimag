package com.example.taller1;

public class Users {
    private String Name;
    private String LName;
    private String Email;
    private String Password;
    private Character Sex;

    public Users(String name, String LName, String email, String password, Character sex) {
        this.Name = name;
        this.LName = LName;
        this.Email = email;
        this.Password = password;
        this.Sex = sex;
    }

    public String getName() {
        return Name;
    }

    public String getLName() {
        return LName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public Character getSex() {
        return Sex;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setSex(Character sex) {
        Sex = sex;
    }
}
