package com.librarysystem.models;

public class Book {
    private String bookID;
    private String author;
    private String title;
    private double price;
    private BookStatus status;
    private String edition;
    private String dateOfPurchase;




    public Book(String bookID, String author, String title, double price, String edition, String dateOfPurchase) {
        this.bookID = bookID;
        this.author = author;
        this.title = title;
        this.price = price;
        this.status = BookStatus.AVAILABLE;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void updateStatus(BookStatus status) {
        this.status = status;
    }


    public void display() {
        System.out.println("Kitap ID  : " + bookID);
        System.out.println("Kitap Adı : " + title);
        System.out.println("Yazar     : " + author);
        System.out.println("Fiyat     : $" + price);
        System.out.println("Basım Yılı: " + edition);
        System.out.println("Alındığı T: " + dateOfPurchase);
        System.out.println("Durum     : " + status);
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Object getBookID() {
        return bookID;
    }
}
