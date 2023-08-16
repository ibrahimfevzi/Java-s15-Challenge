package com.librarysystem.users;

public abstract class Person {
    protected String name;

    public abstract void whoYouAre();

    public String getName() {
        return name;
    }
}
