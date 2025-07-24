package Library.People;

import Library.Books.Book;

import java.util.HashSet;
import java.util.Set;

public class Reader extends Person{

    private Set<Book> books;

    public Reader(String name) {
        super(name);
        books = new HashSet<>();
    }

    public void purchase_book(Book book){
        books.add(book);
        book.update_status();
    }

    public void borrow_book(Book book){
        books.add(book);
        book.update_status();
    }

    public void return_book(Book book){
        books.remove(book);
        book.update_status();
    }

    @Override
    public String whoyouare() {
        return super.whoyouare();
    }
}
