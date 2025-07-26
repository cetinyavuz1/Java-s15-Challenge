package Library.People;

import Library.Books.Book;

import java.util.Set;

public class Author extends Person {

    public Set<Book> books;

    public Author(String name) {
        super(name);
    }

    public Author(String name, Set<Book> books) {
        super(name);
        this.books = books;
    }

    public void new_book(Book book){
        books.add(book);
    }

    public void show_book(){
        System.out.println(books);
    }

    @Override
    public String whoyouare() {
        return super.whoyouare();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return this.getName().equals(author.getName());
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
