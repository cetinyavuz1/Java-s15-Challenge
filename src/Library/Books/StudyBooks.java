package Library.Books;

import Library.People.Author;
import Library.enums.Status;

import java.time.LocalDate;

public class StudyBooks extends Book{
    public StudyBooks(String name) {
        super(name);
    }

    public StudyBooks(long book_ID, Author author, String name, double price, Status status, int edition, LocalDate date_of_purchase, String categoryName) {
        super(book_ID, author, name, price, status, edition, date_of_purchase, categoryName);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
