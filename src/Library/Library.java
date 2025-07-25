package Library;

import Library.Books.Book;
import Library.Books.Journals;
import Library.Books.Magazines;
import Library.People.Author;
import Library.People.Person;
import Library.People.Reader;

import java.sql.SQLOutput;
import java.util.*;

public class Library {
    private Set<Book> books;
    private Set<Reader> readers;
    private Map<Reader, Set<Book>> readerAndBooks;
    private final int BORROW_FEE = 10;

    public Library(Set<Book> books) {
        this.books = books;
        this.readers = new HashSet<>();
        this.readerAndBooks = new HashMap<>();
    }

    public Book getBooksById(long bookId){
        for(Book item : books){
            if(item.getBook_ID() == bookId){
                return item;
            }
        }
        return null;
    }

    public Book getBooksByName(String bookName){
        for(Book item : books){
            if(item.get_title().equals(bookName)){
                return item;
            }
        }
        return null;
    }

    public Book getBooksFromReadersList(String bookName){
        for(Map.Entry<Reader, Set<Book>> item : readerAndBooks.entrySet()){
            for(Book elm : item.getValue()){
                if(elm.getName().equals(bookName)){
                    return elm;
                }
            }
        }
        return null;
    }

    public Set<Book> getBooksByAuthor(Author author){
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

    public Set<Book> getBooksByCategory(String category){
        Set<Book> categoryBooks = new HashSet<>();
        for(Book item : books){
            if(item.getCategoryName().equals(category)){
                categoryBooks.add(item);
            }
        }
        if(!categoryBooks.isEmpty()){
            return categoryBooks;
        }else {
            return null;
        }
    }

    public Reader getReaderByBookId(long id){
        for(Map.Entry<Reader, Set<Book>> item : readerAndBooks.entrySet()){
            for(Book book : item.getValue()){
                if(book.getBook_ID() == id){
                    return item.getKey();
                }
            }
        }
        return null;
    }

    public Reader getReaderByBookName(String bookName){
        for(Map.Entry<Reader, Set<Book>> item : readerAndBooks.entrySet()){
            for(Book book : item.getValue()){
                if(book.get_title().equals(bookName)){
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
        if (book == null) {
            System.out.println("Bu isimde bir kitap bulunamadı.");
            return;
        }

        if (!readers.contains(reader)) {
            readers.add(reader);
        }

        readerAndBooks.putIfAbsent(reader, new HashSet<>());

        if (readerAndBooks.get(reader).size() == 5) {
            System.out.println("Maksimum 5 adet kitap ödünç alabilirsiniz.");
            return;
        }

        if (reader.getBalance() < BORROW_FEE) {
            System.out.println("Bakiyeniz yetersiz.");
            return;
        }

        if (books.contains(book)) {
            books.remove(book);
            readerAndBooks.get(reader).add(book);
            reader.setBalance(reader.getBalance() - BORROW_FEE);
            System.out.println(book.getName() + " kitabı başarıyla ödünç verildi.");
        }
    }

    public void take_back_book(Reader reader, Book book){
        books.add(book);
        readerAndBooks.get(reader).remove(book);
        reader.setBalance(reader.getBalance() + BORROW_FEE);
        System.out.println(book.getName() + " kitabı iade edildi.");
    }

    public void deleteBook(Book book){
        books.remove(book);
    }

    public void show_book(){
        Set<String> bookNames = new HashSet<>();
        for(Book item : books){
            bookNames.add(item.getName());
        }
        System.out.println(bookNames);
        System.out.println(readerAndBooks);
    }

    Scanner scanner = new Scanner(System.in);

    public void librarySystem(){
        while(true){
            System.out.println("Lütfen yapmak istediğiniz işlemi seçiniz");
            System.out.println("Kitap ödünç vermek için 1 tuşuna basınız");
            System.out.println("Ödünç verilen kitabı almak için 2 tuşuna basınız");
            System.out.println("Tüm kitapları görmek için 3 tuşuna basınız");
            System.out.println("Sistemde kitap aramak için 4 tuşuna basınız");
            System.out.println("Kitap eklemek veya silmek için 5 tuşuna basınız");
            System.out.println("Uygulamayı durdurmak için 0 tuşuna basınız");
            int sayi = scanner.nextInt();
            scanner.nextLine();
            switch (sayi){
                case 1:
                    System.out.println("Kitabı ödünç alacak üyenin ismini giriniz");
                    String borrowName = scanner.nextLine();
                    System.out.println("Kitabı ödünç alacak üyenin bakiyesini giriniz");
                    int bakiye = scanner.nextInt();
                    scanner.nextLine();
                    Reader reader = new Reader(borrowName, bakiye);
                    System.out.println("Ödünç almak istediğiniz kitabın ismini giriniz.");
                    String bookName = scanner.nextLine();
                    Book borrowedBook = getBooksByName(bookName);
                    lend_book(reader, borrowedBook);
                    break;
                case 2:
                    System.out.println("İade edilecek kitabın ismini giriniz");
                    String takeBackBook = scanner.nextLine();
                    Reader takeBackReader = getReaderByBookName(takeBackBook);
                    Book takeBackBookName = getBooksFromReadersList(takeBackBook);
                    if (takeBackBookName == null) {
                        System.out.println("Böyle bir kitap sistemde bulunamadı.");
                        break;
                    }
                    if (takeBackReader == null) {
                        System.out.println("Bu kitabı şu anda ödünç alan bir kullanıcı bulunamadı.");
                        break;
                    }
                    take_back_book(takeBackReader, takeBackBookName);
                    break;
                case 3:
                    show_book();
                    break;

            }
        }
    }

}