package com.librarysystem.users;

import com.librarysystem.models.Book;

import java.util.List;

public class Author extends Person {
    private List<Book> books;

    public Author(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }
    @Override
    public void whoYouAre() {
        System.out.println("I am an author named " + name);
    }
}
