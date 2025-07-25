package Library.People;

import Library.Books.Book;

import java.util.HashSet;
import java.util.Set;

public class Reader extends Person{

    private Set<Book> books;
    private int balance;

    public Reader(String name, int balance) {
        super(name);
        books = new HashSet<>();
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reader)) return false;
        Reader reader = (Reader) o;
        return this.getName().equals(reader.getName());
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}
