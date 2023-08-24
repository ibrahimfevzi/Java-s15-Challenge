package com.librarysystem.management;

import com.librarysystem.models.Book;
import com.librarysystem.models.BookStatus;
import com.librarysystem.models.MemberRecord;
import com.librarysystem.users.Librarian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private List<Book> books = new ArrayList<>();  // Kitap listesini başlattık
    private static Map<String, MemberRecord> readers = new HashMap<>(); // Üye listesini başlatın

    private List<Librarian> librarians = new ArrayList<>(); // Burada kütüphanecileri saklamak için bir liste oluşturuluyor


    public List<Book> getBooks() {
        return books;
    }
    public static Map<String, MemberRecord> getReaders() {

        return readers;
    }


    public List<Librarian> getLibrarians() {
        return librarians;
    }

    public Library() {
        // Önceden tanımlı kütüphaneci
        Librarian defaultLibrarian = new Librarian("Admin", "1234");
        librarians.add(defaultLibrarian);
    }


    public void newBook( String bookID, String author, String title, double price, String edition, String dateOfPurchase ) {

        Book book = new Book(bookID,  title, author, price, edition, dateOfPurchase);
        books.add(book);
    }

    public boolean removeBook(String bookID) {
        Book book = findBookByTitle(bookID);
        if (book != null) {
            books.remove(book);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateBook(String bookID, String newTitle, String newAuthor, double newPrice,
                              String newEdition, String newDateOfPurchase) {
        Book book = findBookByTitle(bookID);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setPrice(newPrice);
            book.setEdition(newEdition);
            book.setDateOfPurchase(newDateOfPurchase);
            return true;
        }
        return false;
    }

    public void lendBook(String book, String reader) {

        Book book1 = findBookByTitle(book);
        MemberRecord member = getMember(reader);
        if (book1 != null && member != null) {
            if (book1.getStatus() == BookStatus.AVAILABLE && member.getNoBooksIssued() < member.getMaxBookLimit()) {
                book1.updateStatus(BookStatus.BORROWED);
                member.incBookIssued();
                System.out.println(book1.getTitle() + " " + member.getName() + " isimli üye tarafından ödünç alındı."
                        + "\n" + "Ödünç alınan kitap sayısı: " + member.getNoBooksIssued()
                        + "\n" + "Max. Kitap Limiti: " + member.getMaxBookLimit());
                createBill(reader);
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
            createBill(readerName);
        } else {
            System.out.println("Hatalı işlem.");
        }
    }


    public void createBill(String memberName) {
        MemberRecord member = getMember(memberName);
        double Amount = member.getNoBooksIssued() * 20;
        System.out.println( member.getName() + " isimli üye için fatura bedeli " +  ": $" + Amount);
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

    public boolean isBookIDExists(String bookID) {
        return findBookById(bookID) != null;
    }




    public Book getBook(String bookID) {
        return findBookByTitle(bookID);

    }



    public Book findBookById(String bookID) {
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {
                return book;
            }
        }
        System.out.println(bookID + " ID'li kitap bulunamadı.");
        return null;
    }

    public boolean removeBookById(String bookID) {
        Book book = findBookById(bookID);
        if (book != null) {
            books.remove(book);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateBookById(String bookID, String newTitle, String newAuthor, double newPrice,
                                  String newEdition, String newDateOfPurchase) {
        Book book = findBookById(bookID);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setPrice(newPrice);
            book.setEdition(newEdition);
            book.setDateOfPurchase(newDateOfPurchase);
            return true;
        }
        return false;
    }


}
