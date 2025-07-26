package Library.Books;

import Library.People.Author;

import java.time.LocalDate;

public class Fantasy extends Book{
    public Fantasy(String name) {
        super(name);
    }

    public Fantasy(Author author, String name, double price, int edition, LocalDate date_of_purchase, String categoryName) {
        super(author, name, price, edition, date_of_purchase, categoryName);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
