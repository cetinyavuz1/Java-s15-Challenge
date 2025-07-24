package Library;

import Library.Books.Book;
import Library.People.Author;
import Library.People.Reader;

import java.util.*;

public class Library {
    private Set<Book> books;
    private Set<Reader> readers;
    private Map<Reader, Set<Book>> readerAndBooks;

    public Library(Set<Book> books) {
        this.books = books;
        this.readers = new HashSet<>();
        this.readerAndBooks = new HashMap<>();
    }

    public Book get_books(long id){
        for(Book item : books){
            if(item.getBook_ID() == id){
                return item;
            }
        }
        return null;
    }

    public Book get_books(String name){
        for(Book item : books){
            if(item.get_title().equals(name)){
                return item;
            }
        }
        return null;
    }

    public Set<Book> get_books(Author author){
        Set<Book> authorBooks = new HashSet<>();
        for(Book item : books){
            if(item.get_author().equals(author)){
                authorBooks.add(item);
            }
        }
        if(!authorBooks.isEmpty()){
            return authorBooks;
        }else {
            return null;
        }
    }

    public Reader get_reader(long id){
        for(Map.Entry<Reader, Set<Book>> item : readerAndBooks.entrySet()){
            for(Book book : item.getValue()){
                if(book.getBook_ID() == id){
                    return item.getKey();
                }
            }
        }
        return null;
    }

    public Reader get_reader(String name){
        for(Map.Entry<Reader, Set<Book>> item : readerAndBooks.entrySet()){
            for(Book book : item.getValue()){
                if(book.get_title().equals(name)){
                    return item.getKey();
                }
            }
        }
        return null;
    }

    public void new_book(Book book){
        books.add(book);
    }

    public void lend_book(Reader reader, Book book){
        books.remove(book);
        readers.add(reader);
        if(!readerAndBooks.containsKey(reader)){
            readerAndBooks.put(reader, new HashSet<>());
        }
        readerAndBooks.get(reader).add(book);
    }

    public void take_back_book(Reader reader, Book book){
        books.add(book);
        readerAndBooks.get(reader).remove(book);
    }

    public void deleteBook(Book book){
        books.remove(book);
    }

    public void show_book(){
        System.out.println(books);
    }

}