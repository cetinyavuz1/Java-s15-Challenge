import Library.Books.Book;
import Library.Books.Journals;
import Library.Books.ScienceFiction;
import Library.Library;
import Library.People.Author;
import Library.enums.Status;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Journals book1 = new Journals(new Author("Fyodor"), "Suç ve Ceza", 39.90, Status.AVAILABLE, 3, LocalDate.of(2021, 3, 15), "Journals");
        Journals book2 = new Journals(new Author("Leo"), "Savaş ve Barış", 49.90, Status.AVAILABLE, 2, LocalDate.of(2020, 6, 10), "Journals");
        Journals book3 = new Journals(new Author("Miguel"), "Don Kişot", 45.00, Status.UNAVAILABLE, 4, LocalDate.of(2022, 1, 5), "Journals");
        Journals book4 = new Journals(new Author("Jane"), "Aşk ve Gurur", 35.00, Status.AVAILABLE, 1, LocalDate.of(2023, 9, 1), "Journals");
        Journals book5 = new Journals(new Author("Victor"), "Sefiller", 55.00, Status.UNAVAILABLE, 3, LocalDate.of(2019, 11, 20), "Journals");
        Journals book6 = new Journals(new Author("Charles"), "İki Şehrin Hikayesi", 38.75, Status.AVAILABLE, 2, LocalDate.of(2022, 4, 30), "Journals");
        Journals book7 = new Journals(new Author("Emily"), "Uğultulu Tepeler", 36.00, Status.AVAILABLE, 1, LocalDate.of(2021, 7, 22), "Journals");
        Journals book8 = new Journals(new Author("Homer"), "Odysseia", 42.00, Status.UNAVAILABLE, 5, LocalDate.of(2020, 12, 8), "Journals");
        Journals book9 = new Journals(new Author("Franz"), "Dava", 29.90, Status.AVAILABLE, 3, LocalDate.of(2023, 2, 14), "Journals");
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