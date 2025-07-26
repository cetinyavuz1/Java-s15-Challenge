package Library.Books;

import Library.People.Author;
import Library.enums.Status;

import java.time.LocalDate;

public class ScienceFiction extends Book{
    public ScienceFiction(String name) {
        super(name);
    }

    public ScienceFiction(Author author, String name, double price, Status status, int edition, LocalDate date_of_purchase, String categoryName) {
        super(author, name, price, status, edition, date_of_purchase, categoryName);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
