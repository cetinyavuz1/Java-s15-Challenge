import Library.Books.Book;
import Library.Books.Classic;
import Library.Library;
import Library.People.Author;
import Library.enums.Status;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        List<Book> bookList = List.of(new Classic(new Author("Fyodor"), "Suç ve Ceza", 39.90, 3, LocalDate.of(2021, 3, 15), "Classic"),
                new Classic(new Author("Leo"), "Savaş ve Barış", 49.90, 2, LocalDate.of(2020, 6, 10), "Classic"),
                new Classic(new Author("Miguel"), "Don Kişot", 45.00, 4, LocalDate.of(2022, 1, 5), "Classic"),
                new Classic(new Author("Jane"), "Aşk ve Gurur", 35.00, 1, LocalDate.of(2023, 9, 1), "Classic"),
                new Classic(new Author("Victor"), "Sefiller", 55.00, 3, LocalDate.of(2019, 11, 20), "Classic"),
                new Classic(new Author("Charles"), "İki Şehrin Hikayesi", 38.75, 2, LocalDate.of(2022, 4, 30), "Classic"),
                new Classic(new Author("Emily"), "Uğultulu Tepeler", 36.00, 1, LocalDate.of(2021, 7, 22), "Classic"),
                new Classic(new Author("Homer"), "Odysseia", 42.00, 5, LocalDate.of(2020, 12, 8), "Classic"),
                new Classic(new Author("Franz"), "Dava", 29.90, 3, LocalDate.of(2023, 2, 14), "Classic"));

        Set<Book> bookSet = new HashSet<>(bookList);
        Library library = new Library(bookSet);
        library.librarySystem();

    }
}