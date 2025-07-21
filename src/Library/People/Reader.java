package Library.People;

import Library.Books.Book;

import java.util.Set;

public class Reader extends Person{

    private Set<Book> books;

    public Reader(String name) {
        super(name);
    }

    public Reader(String name, Set<Book> books) {
        super(name);
        this.books = books;
    }

    public void purchase_book(Book book){
        books.add(book);
    }

    public void borrow_book(Book book){
        books.add(book);
    }

    public void return_book(Book book){
        books.remove(book);
    }

    @Override
    public String whoyouare() {
        return super.whoyouare();
    }
}
