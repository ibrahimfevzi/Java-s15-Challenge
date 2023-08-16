package com.librarysystem.management;

import com.librarysystem.models.Book;
import com.librarysystem.models.BookStatus;
import com.librarysystem.models.MemberRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private List<Book> books = new ArrayList<>();  // Kitap listesini başlatın
    private static Map<String, MemberRecord> readers = new HashMap<>(); // Üye listesini başlatın


    public List<Book> getBooks() {
        return books;
    }
    public static Map<String, MemberRecord> getReaders() {

        return readers;
    }


    public void newBook( String bookID, String author, String title, double price, String edition, String dateOfPurchase ) {

        Book book = new Book(bookID,  title, author, price, edition, dateOfPurchase);
        books.add(book);
        System.out.println("Kitap Eklendi: " + book.getTitle());
    }

    public void lendBook(String book, String reader) {

        Book book1 = findBookByTitle(book);
        MemberRecord member = getMember(reader);
        if (book1 != null && member != null) {
            if (book1.getStatus() == BookStatus.AVAILABLE && member.getNoBooksIssued() < member.getMaxBookLimit()) {
                book1.updateStatus(BookStatus.BORROWED);
                member.borrowBook();
                member.incBookIssued();
                System.out.println(book1.getTitle() + " " + member.getName() + " isimli üye tarafından ödünç alındı.");
            } else {
                System.out.println("Kitap kullanılabilir değil.");
            }
        } else {
            System.out.println("Hatalı işlem.");
        }

    }



    public void takeBackBook(String bookTitle, String readerName) {
        Book book = findBookByTitle(bookTitle);
        MemberRecord reader = getMember(readerName);

        if (book != null && reader != null && book.getStatus() == BookStatus.BORROWED) {
            book.updateStatus(BookStatus.AVAILABLE);
            reader.decBookIssued();
            System.out.println(bookTitle + "  " + readerName + " isimli üye tarafından geri verildi.");
        } else {
            System.out.println("Hatalı işlem.");
        }
    }

    public void calculateFine(String memberName) {
        MemberRecord member = getMember(memberName);
        // Implementation for calculating fine
        // Assuming some calculation based on overdue books
        int overdueBooks = member.getNoBooksIssued() - member.getMaxBookLimit();
        double fineAmount = overdueBooks * 2.0; // Assuming a fixed fine per overdue book
        System.out.println( member.getName() + " isimli üye için hesaplanan ceza: " +  ": $" + fineAmount);
    }

    public void createBill(String memberName) {
        MemberRecord member = getMember(memberName);
        // Implementation for creating a bill for the member
        // Might involve calculating fine and other charges
        calculateFine(memberName); // Calculate the fine first
    }


    public void showBook() {
        List<Book> bookList = getBooks();
        if (bookList != null && !bookList.isEmpty()) {  // Listeyi kontrol et
            System.out.println("\nKitap Listesi:");
            for (Book book : bookList) {
                book.display();
                System.out.println("----------");
            }
        } else {
            System.out.println("Kitap bulunamadı.");
        }
        }

    public static void listMembers() {
        Map<String, MemberRecord> readerMap = getReaders();
        if (readerMap != null && !readerMap.isEmpty()) {  // Map'i kontrol et
            System.out.println("\nÜye Listesi: ");
            for (Map.Entry<String, MemberRecord> entry : readerMap.entrySet()) {
                MemberRecord memberRecord = entry.getValue();
                System.out.println("ID: "+ memberRecord.getMemberID() + " " + "\nİsim: " + memberRecord.getName() + "\nÜyelik Tipi: " + memberRecord.getMemberType() + "\nÜzerindeki kitap sayısı: " + memberRecord.getNoBooksIssued() +
                        "\nMax. Kitap Limiti: " + memberRecord.getMaxBookLimit());
                System.out.println("----------");
            }
        } else {
            System.out.println("Üye bulunamadı.");
        }
    }


    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        System.out.println(title + " isimli kitap bulunamadı.");
        return null;
    }


    public MemberRecord getMember(String memberName) {
        return readers.get(memberName);
    }

    public void searchBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null) {
            book.display();
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    public void verifyMember(String memberName) {
        MemberRecord member = getMember(memberName);
        if (member != null) {
            System.out.println("Üye doğrulandı: " + member.getName());
        } else {
            System.out.println("Üye bulunamadı!");
        }
    }


    public void addMember(MemberRecord memberRecord) {
        readers.put(memberRecord.getName(), memberRecord);

    }

    public boolean removeMember(String memberName) {
        MemberRecord member = getMember(memberName);
        if (member != null) {
            readers.remove(memberName);
            return true;
        } else {
            return false;
        }
    }


    public boolean isMemberIDExists(String memberID) {
        return readers.containsKey(memberID);

    }



}
