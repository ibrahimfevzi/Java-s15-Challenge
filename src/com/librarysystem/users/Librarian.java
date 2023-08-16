package com.librarysystem.users;


public class Librarian extends Person {
    private String password;

    public Librarian(String name, String password) {
        this.name = name;
        this.password = password;
    }



    @Override
    public void whoYouAre() {
        System.out.println("Ben " + name + " isimli bir kütüphaneciyim.");
    }
}
