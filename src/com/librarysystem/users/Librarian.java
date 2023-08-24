package com.librarysystem.users;


public class Librarian extends Person {
    private String password;

    public Librarian(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        return "Librarian{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void whoYouAre() {
        System.out.println("Ben " + name + " isimli bir kütüphaneciyim.");
    }
}
