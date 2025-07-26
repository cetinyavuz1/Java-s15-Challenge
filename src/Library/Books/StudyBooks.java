package Library.Books;

import Library.People.Author;
import Library.enums.Status;

import java.time.LocalDate;

public class StudyBooks extends Book{
    public StudyBooks(String name) {
        super(name);
    }

    public StudyBooks(Author author, String name, double price, int edition, LocalDate date_of_purchase, String categoryName) {
        super(author, name, price, edition, date_of_purchase, categoryName);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
