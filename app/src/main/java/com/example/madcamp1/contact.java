package com.example.madcamp1;

public class contact {
    private int image;
    private String name;
    private String number;
    private String email;

    public contact(int image, String name, String number, String email)
    {
        this.image = image;
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public int getImage() { return image; }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public void setImage(int image) { this.image = image; }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
