package Library.Books;

import Library.People.Author;
import Library.enums.Categories;
import Library.enums.Status;

import java.time.LocalDate;

public class ScienceFiction extends Book{
    public ScienceFiction(String name) {
        super(name);
    }

    public ScienceFiction(Author author, String name, double price, int edition, LocalDate date_of_purchase, Categories categoryName) {
        super(author, name, price, edition, date_of_purchase, categoryName);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
