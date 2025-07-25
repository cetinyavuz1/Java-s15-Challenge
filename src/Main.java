import Library.Books.Book;
import Library.Books.Journals;
import Library.Library;
import Library.People.Author;
import Library.enums.Status;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Journals book1 = new Journals(1, new Author("Fyodor"), "Suç ve Ceza", 39.90, Status.AVAILABLE, 3, LocalDate.of(2021, 3, 15), "Roman");
        Journals book2 = new Journals(2, new Author("Leo"), "Savaş ve Barış", 49.90, Status.AVAILABLE, 2, LocalDate.of(2020, 6, 10), "Roman");
        Journals book3 = new Journals(3, new Author("Miguel"), "Don Kişot", 45.00, Status.UNAVAILABLE, 4, LocalDate.of(2022, 1, 5), "Macera");
        Journals book4 = new Journals(4, new Author("Jane"), "Aşk ve Gurur", 35.00, Status.AVAILABLE, 1, LocalDate.of(2023, 9, 1), "Roman");
        Journals book5 = new Journals(5, new Author("Victor"), "Sefiller", 55.00, Status.UNAVAILABLE, 3, LocalDate.of(2019, 11, 20), "Dram");
        Journals book6 = new Journals(6, new Author("Charles"), "İki Şehrin Hikayesi", 38.75, Status.AVAILABLE, 2, LocalDate.of(2022, 4, 30), "Tarih");
        Journals book7 = new Journals(7, new Author("Emily"), "Uğultulu Tepeler", 36.00, Status.AVAILABLE, 1, LocalDate.of(2021, 7, 22), "Roman");
        Journals book8 = new Journals(8, new Author("Homer"), "Odysseia", 42.00, Status.UNAVAILABLE, 5, LocalDate.of(2020, 12, 8), "Epik");
        Journals book9 = new Journals(9, new Author("Franz"), "Dava", 29.90, Status.AVAILABLE, 3, LocalDate.of(2023, 2, 14), "Modern Klasik");
        Set<Book> bookSet = new HashSet<>();
        bookSet.add(book1);
        bookSet.add(book2);
        bookSet.add(book3);
        bookSet.add(book4);
        bookSet.add(book5);
        bookSet.add(book6);
        bookSet.add(book7);
        bookSet.add(book8);
        bookSet.add(book9);
        Library library = new Library(bookSet);

        library.librarySystem();
    }
}