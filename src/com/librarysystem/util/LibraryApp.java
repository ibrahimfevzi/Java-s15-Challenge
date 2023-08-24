package com.librarysystem.util;

import com.librarysystem.management.Library;
import com.librarysystem.models.Book;
import com.librarysystem.models.MemberRecord;
import com.librarysystem.models.MemberType;
import com.librarysystem.users.Librarian;
import com.librarysystem.users.Reader;

import java.util.Scanner;

public class LibraryApp  {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library(); // Composition: LibraryApp sınıfı Library sınıfını kullanıyor


    public static void main(String[] args) {
        System.out.println("Kütüphane Yönetim Sistemine Hoşgeldiniz!");

        Librarian librarian = new Librarian("admin", "1234");

        Reader reader = new Reader(scanner, library); // Reader nesnesini oluştururken scanner ve library nesnelerini geçiyoruz, Composition

        // Önceden tanımlı üye
        MemberRecord predefinedMember = new MemberRecord("1", MemberType.READER, "10-10-2020", 0, 5,"ibrahim", "istanbul", "123456789");
        library.addMember(predefinedMember);

        // Önceden tanımlı kitap
        library.newBook("1", "1984", "Orwell", 29.99, "1st Edition", "2023-08-24");
        library.newBook("2", "Hayvan Çiftliği", "Orwell", 19.99, "1st Edition", "2023-08-24");



        while (true) {
            System.out.println("\n Yapmak istediğiniz işlemi seçiniz:");
            System.out.println("1. Kitap Ekle");
            System.out.println("2. Kitap Sil");
            System.out.println("3. Kitap Güncelle");
            System.out.println("4. Kitap Ödünç Ver");
            System.out.println("5. Kitap Geri Al");
            System.out.println("6. Kitap Ara");
            System.out.println("7. Fatura Görüntüle");
            System.out.println("8. Kitap Listele");
            System.out.println("9. Üye Listele");
            System.out.println("************** ÜYE İŞLEMLERİ **************");
            System.out.println("10.  Üye Ekle");
            System.out.println("11. Üye Sil");
            System.out.println("12. Üye Güncelle");
            System.out.println("13. Üyelik Doğrula");
            System.out.println("14. Ödünç Alınan Kitapları Listele");
            System.out.println("15. Çıkış Yap");
            System.out.print("Seçiminizi Girin: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    reader.borrowBook();
                    break;
                case 5:
                    reader.returnBook();
                    break;
                case 6:
                    searchBook();
                    break;
                case 7:
                    createBill();
                    break;
                case 8:
                    library.showBook();
                    break;
                case 9:
                   Library.listMembers();
                    break;
                case 10:
                    addMember();
                    break;
                case 11:
                    removeMember();
                    break;
                case 12:
                    updateMember();
                    break;
                case 13:
                    verifyMember();
                    break;
                case 14:
                    reader.showBook();
                    break;
                case 15:
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

        if (library.isBookIDExists(bookID)) {
            System.out.println("Kitap ID'si zaten var.");
            return;
        }else {
            library.newBook(bookID, title, author, price, edition, dateOfPurchase);
            System.out.println("Kitap başarıyla eklendi! " + "\n");
            System.out.println("BookID: " + bookID + "\n" + "Kitap Adı: " + title + "\n" + "Yazar: " + author + "\n" + "Fiyat: " + price + "\n" + "Baskı Yılı: " + edition + "\n" + "Satın Alma Tarihi: " + dateOfPurchase);

        }


    }

    private static void removeBook() {
        System.out.println("\nKitap siliniyor...");
        System.out.print("Silinecek kitabın ID'sini girin: ");
        String bookID = scanner.nextLine();

        boolean success = library.removeBookById(bookID); // Update the method name
        if (success) {
            System.out.println("Kitap başarıyla silindi.");
        } else {
            System.out.println("Kitap bulunamadı veya silinemedi.");
        }
    }


    private static void updateBook() {
        System.out.println("\nKitap bilgisi güncelleniyor...");
        System.out.print("Güncellenecek kitabın ID'sini girin: ");
        String bookID = scanner.nextLine();

        System.out.print("Yeni kitap adını girin: ");
        String newTitle = scanner.nextLine();
        System.out.print("Yeni yazarını girin: ");
        String newAuthor = scanner.nextLine();
        System.out.print("Yeni fiyatını girin: ");
        double newPrice = Double.parseDouble(scanner.nextLine());
        System.out.print("Yeni baskı yılını girin: ");
        String newEdition = scanner.nextLine();
        System.out.print("Yeni satın alma tarihini girin: ");
        String newDateOfPurchase = scanner.nextLine();

        boolean success = library.updateBookById(bookID, newTitle, newAuthor, newPrice, newEdition, newDateOfPurchase);
        if (success) {
            System.out.println("Kitap bilgileri başarıyla güncellendi.");
        } else {
            System.out.println("Kitap bulunamadı veya güncellenemedi.");
        }
    }




    private static void addMember() {
        System.out.println("\nÜye ekleniyor...");

        System.out.print("Üye ID girin: ");
        String memberID = scanner.nextLine();
        System.out.print("Üye adını girin: ");
        String name = scanner.nextLine();
        System.out.print("Üyelik tipini girin (READER/AUTHOR): ");
        String typeString = scanner.nextLine().toUpperCase(); // Küçük harfli girilen üyelik tipini büyük harfe çevirir

        MemberType type;
        try {
            type = MemberType.valueOf(typeString); // Üyelik tipini dönüştür ve doğrula
        } catch (IllegalArgumentException e) {
            System.out.println("Hatalı üyelik tipi girdiniz. Üyelik tipi READER veya AUTHOR olmalıdır.");
            return;
        }

        // Diğer üye bilgilerini alın
        System.out.print("Üyelik tarihini girin: ");
        String dateOfMembership = scanner.nextLine();
        System.out.print("Ödünç alınan kitap sayısı girin: ");
        int noBooksIssued = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Maksimum kitap limitini girin: ");
        int maxBookLimit = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Adresi girin: ");
        String address = scanner.nextLine();
        System.out.print("Telefon numarasını girin: ");
        String phoneNo = scanner.nextLine();

        // Yeni MemberRecord nesnesini oluştur
        MemberRecord memberRecord = new MemberRecord(memberID, type, dateOfMembership, noBooksIssued, maxBookLimit, name, address, phoneNo);

        if (library.isMemberIDExists(memberID)) {
            System.out.println("Üye ID'si zaten var.");
            return;
        } else {
            library.addMember(memberRecord);

            System.out.println("Üye başarıyla eklendi.\n");
            System.out.println("Üye ID: " + memberID + "\nÜye İsim: " + name + "\nÜyelik Tipi: " + type);
            System.out.println("Üyelik Tarihi: " + dateOfMembership + "\nÖdünç Alınan Kitap Sayısı: " + noBooksIssued);
            System.out.println("Maksimum Kitap Limiti: " + maxBookLimit + "\nAdres: " + address + "\nTelefon Numarası: " + phoneNo);
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
