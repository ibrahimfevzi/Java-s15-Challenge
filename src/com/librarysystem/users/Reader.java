package com.librarysystem.users;

import com.librarysystem.management.Library;
import com.librarysystem.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Reader extends Person {
    private static Scanner scanner;
    private static Library library;
    private List<Book>  borrowedBooks; // Değişiklik: Ödünç alınan kitapları saklayan liste



    public Reader(Scanner scanner, Library library) {
        this.scanner = scanner;
        this.library = library;
        this.borrowedBooks = new ArrayList<>(); // Değişiklik: Ödünç alınan kitapları saklamak için liste oluşturuluyor

    }

    public void borrowBook() {
        System.out.println("\nKitap ödünç alınıyor...");
        System.out.print("Kitap adını giriniz: ");
        String title = scanner.nextLine();
        System.out.print("Üye adını giriniz: ");
        String readerName = scanner.nextLine();

        Book borrowedBook = library.findBookByTitle(title);
        if ((borrowedBook != null)) {
            library.lendBook(title, readerName);
            addBorrowedBook(borrowedBook);
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }
//
    public  void returnBook() {
        System.out.println("\nKitap geri alınıyor...");
        System.out.print("Kitap adını giriniz: ");
        String title = scanner.nextLine();
        System.out.print("Üye adını giriniz:");
        String readerName = scanner.nextLine();

        library.takeBackBook(title, readerName);
        removeBorrowedBook(library.findBookByTitle(title));
    }

    public void showBook() {
        System.out.print("Üye adını giriniz: ");
        String readerName = scanner.nextLine();

        System.out.println( readerName + " tarafından ödünç alınan kitaplar: ");

        for (Book book : borrowedBooks) {
            System.out.println("Kitap Adı: " + book.getTitle() + ", Yazar: " + book.getAuthor());
        }
    }

    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    public void removeBorrowedBook(Book book) {

        borrowedBooks.remove(book);
    }

    @Override
    public void whoYouAre() {
        System.out.println("Ben" + name + " isimli kullanıcı üyeyim.");
    }

}
