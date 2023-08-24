package com.librarysystem.util;

import com.librarysystem.management.Library;
import com.librarysystem.models.Book;
import com.librarysystem.models.MemberRecord;
import com.librarysystem.models.MemberType;
import com.librarysystem.users.Librarian;
import com.librarysystem.users.Reader;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LibraryApp  {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();
    private static MemberRecord reader;



    public static void main(String[] args) {
        System.out.println("Kütüphane Yönetim Sistemine Hoşgeldiniz!");

        Librarian librarian = new Librarian("Admin", "password");

        Reader reader = new Reader(scanner, library); // Reader nesnesini oluştururken scanner ve library nesnelerini geçiyoruz

        // Önceden tanımlı üye
        MemberRecord predefinedMember = new MemberRecord("1", "READER", "ibrahim");
        library.addMember(predefinedMember);

        // Önceden tanımlı kitap
        library.newBook("1", "1984", "Orwell", 29.99, "1st Edition", "2023-08-24");
        library.newBook("2", "Hayvan Çiftliği", "Orwell", 19.99, "1st Edition", "2023-08-24");



        while (true) {
            System.out.println("\n Yapmak istediğiniz işlemi seçiniz:");
            System.out.println("1. Kitap Ekle");
            System.out.println("2. Kitap Ödünç Ver");
            System.out.println("3. Kitap Geri Al");
            System.out.println("4. Kitap Ara");
            System.out.println("5. Fatura Görüntüle");
            System.out.println("6. Kitap Listele");
            System.out.println("7. Üye Listele");
            System.out.println("************** ÜYE İŞLEMLERİ **************");
            System.out.println("8.  Üye Ekle");
            System.out.println("9. Üye Sil");
            System.out.println("10. Üye Güncelle");
            System.out.println("11. Üyelik Doğrula");
            System.out.println("12. Ödünç Alınan Kitapları Listele");
            System.out.println("13. Çıkış Yap");
            System.out.print("Seçiminizi Girin: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    reader.borrowBook();
                    break;
                case 3:
                    reader.returnBook();
                    break;
                case 4:
                    searchBook();
                    break;
                case 5:
                    createBill();
                    break;
                case 6:
                    library.showBook();
                    break;
                case 7:
                   Library.listMembers();
                    break;
                case 8:
                    addMember();
                    break;
                case 9:
                    removeMember();
                    break;
                case 10:
                    updateMember();
                    break;
                case 11:
                    verifyMember();
                    break;
                case 12:
                    reader.showBook();
                    break;
                case 13:
                    System.out.println("Kütüphane Yönetim Sistemini Kullandığınız İçin Teşekkürler!");
                    System.exit(0);

                default:
                    System.out.println("Hatalı Seçim Yaptınız, Tekrar Deneyin!");
            }
        }
    }



    private static void createBill() {
        System.out.println("\nFatura oluşturuluyor...");
        System.out.print("Üyenin adını girin: ");
        String memberName = scanner.nextLine();

        library.createBill(memberName);
    }



    private static void verifyMember() {
        System.out.println("\nÜyelik doğrulanıyor...");
        System.out.print("Üyenin adını girin: ");
        String memberName = scanner.nextLine();

        library.verifyMember(memberName);
    }

    private static void searchBook() {
        System.out.println("\nKitap aranıyor...");
        System.out.print("Kitap adını girin: ");
        String title = scanner.nextLine();

        library.searchBook(title);
    }


    private static void addBook() {
        System.out.println("\nKitap ekleniyor...");
        System.out.print("BookID girin:");
        String bookID = scanner.nextLine();
        System.out.print("Kitap adını girin: ");
        String title = scanner.nextLine();
        System.out.print("Kitabın yazarını girin: ");
        String author = scanner.nextLine();
        System.out.print("Kitabın fiyatını girin: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Kitabın baskı yılını girin: " );
        String edition = scanner.nextLine();
        System.out.print("Kitabın satın alma tarihini girin: ");
        String dateOfPurchase = scanner.nextLine();

        library.newBook(bookID, title, author, price, edition, dateOfPurchase);
        System.out.println("Kitap başarıyla eklendi! " + "\n");
        System.out.println("BookID: " + bookID + "\n" + "Kitap Adı: " + title + "\n" + "Yazar: " + author + "\n" + "Fiyat: " + price + "\n" + "Baskı Yılı: " + edition + "\n" + "Satın Alma Tarihi: " + dateOfPurchase);

    }


    private static void addMember() {
        System.out.println("\nÜye ekleniyor...");

        System.out.print("Üye ID girin: ");
        String memberID = scanner.nextLine();
        System.out.print("Üye adını girin: ");
        String name = scanner.nextLine();
        System.out.print("Üyelik tipini girin (READER/AUTHOR):");
        String type = scanner.nextLine();

        try {
            MemberType memberType = MemberType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Hatalı üyelik tipi girdiniz. Üyelik tipi READER veya AUTHOR olmalıdır.");
            return;
        }

        if (library.isMemberIDExists(memberID)) {
            System.out.println("Üye ID'si zaten var.");
            return;
        } else {
            MemberRecord memberRecord = new MemberRecord(memberID, type, name);
            library.addMember(memberRecord);

            System.out.println("Üye başarıyla eklendi. " + "\n");
            System.out.println("Üye ID: " + memberID + "\n" + "Üye İsim: " + name + "\n" + "Üyelik Tipi : " + type);
        }
    }



    private static void removeMember() {
        System.out.println("\nÜye siliniyor...");
        System.out.print("Silinecek üyenin adını girin: ");
        String memberName = scanner.nextLine();

        boolean success = library.removeMember(memberName);
        if (success) {
            System.out.println("Üye başarıyla silindi. ");
        } else {
            System.out.println("Üye bulunamadı veya silinemedi.");
        }
    }

    private static void updateMember() {
        System.out.println("\nÜye bilgisi güncelleniyor...");
        System.out.print("Güncellenecek üyenin adını girin: ");
        String memberName = scanner.nextLine();

        MemberRecord existingMember = library.getMember(memberName);
        if (existingMember != null) {
            System.out.print("Üyenin yeni üyelik tipini girin (READER/AUTHOR):");
            String updatedMembershipType = scanner.nextLine();
            existingMember.setMembershipType(updatedMembershipType);

            System.out.println("Üye bilgisi başarıyla güncellendi.");
        } else {
            System.out.println("Üye bulunamadı veya güncellenemedi.");
        }
    }


}
