package Library;

import Library.Books.*;
import Library.People.Author;
import Library.People.Person;
import Library.People.Reader;
import Library.enums.Status;
import java.time.LocalDate;
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
        System.out.println("Girilen ID ile bir kitap bulunamadı.");
        return null;
    }

    public Book getBooksByName(String bookName){
        for(Book item : books){
            if(item.get_title().equals(bookName)){
                return item;
            }
        }
        System.out.println("Girilen isim ile bir kitap bulunamadı.");
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
        System.out.println("Girilen isim ile bir kitap bulunamadı.");
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
            System.out.println("Girilen yazarın kitapları bulunamadı.");
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
            System.out.println("Girilen kategoride kitap bulunamadı.");
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
        System.out.println("Girilen ID'li kitaba sahip üye bulunamadı.");
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
        System.out.println("Girilen kitaba sahip üye bulunamadı.");
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
            System.out.println("1 - Kitap ödünç ver");
            System.out.println("2 - Kitap geri al");
            System.out.println("3 - Tüm kitapları göster");
            System.out.println("4 - Kitap ara");
            System.out.println("5 - Kitap ekle-sil");
            System.out.println("0 - Uygulamayı durdur");
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
                case 4:
                    System.out.println("1 - ID'ye göre");
                    System.out.println("2 - İsime göre");
                    System.out.println("3 - Yazara göre");
                    System.out.println("4 - Kategoriye göre");
                    int aramaSayi = scanner.nextInt();
                    scanner.nextLine();
                    switch (aramaSayi){
                        case 1:
                            System.out.println("ID giriniz.");
                            int aramaId = scanner.nextInt();
                            System.out.println(getBooksById(aramaId));
                            break;
                        case 2:
                            System.out.println("Kitap ismi giriniz.");
                            String aramaName = scanner.nextLine();
                            System.out.println(getBooksByName(aramaName));
                            break;
                        case 3:
                            System.out.println("Yazar ismi giriniz.");
                            String aramaYazar = scanner.nextLine();
                            Author author = new Author(aramaYazar);
                            System.out.println(getBooksByAuthor(author));
                            break;
                        case 4:
                            System.out.println("Kategori ismi giriniz");
                            String aramaKategori = scanner.nextLine();
                            System.out.println(getBooksByCategory(aramaKategori));
                            break;
                    }
                    break;
                case 5:
                    System.out.println("1 - Kitap ekle");
                    System.out.println("2 - Kitap sil");
                    int ekleSil = scanner.nextInt();
                    scanner.nextLine();
                    switch (ekleSil){
                        case 1:
                            System.out.println("Kitap ismi giriniz");
                            String newBookName = scanner.nextLine();
                            System.out.println("Yazar ismi giriniz");
                            String authorName = scanner.nextLine();
                            Author author = new Author(authorName);
                            System.out.println("Fiyat giriniz");
                            String newPrice = scanner.nextLine();

                            System.out.println("Edition giriniz");
                            int newEdition = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Kategori ismi giriniz");
                            String newCategory = scanner.nextLine();
                            switch (newCategory){
                                case "Journals":
                                    Book newJournal = new Journals(author, newBookName, Double.parseDouble(newPrice), Status.AVAILABLE, newEdition, LocalDate.now(), newCategory);
                                    new_book(newJournal);
                                    System.out.println(newBookName + " kitabı başarıyla eklendi.");
                                    break;
                                case "Magazines":
                                    Book newMaganize = new Magazines(author, newBookName, Double.parseDouble(newPrice), Status.AVAILABLE, newEdition, LocalDate.now(), newCategory);
                                    new_book(newMaganize);
                                    System.out.println(newBookName + " kitabı başarıyla eklendi.");
                                    break;
                                case "Science Fiction":
                                    Book newScienceFiction = new ScienceFiction(author, newBookName, Double.parseDouble(newPrice), Status.AVAILABLE, newEdition, LocalDate.now(), newCategory);
                                    new_book(newScienceFiction);
                                    System.out.println(newBookName + " kitabı başarıyla eklendi.");
                                    break;
                                case "Study Books":
                                    Book newStudyBook = new StudyBooks(author, newBookName, Double.parseDouble(newPrice), Status.AVAILABLE, newEdition, LocalDate.now(), newCategory);
                                    new_book(newStudyBook);
                                    System.out.println(newBookName + " kitabı başarıyla eklendi.");
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println("Silmek istediğiniz kitap ismini girin.");
                            String deleteBookName = scanner.nextLine();
                            Book deleteBookObj = getBooksByName(deleteBookName);
                            deleteBook(deleteBookObj);
                            System.out.println(deleteBookName + " kitabı başarıyla silindi.");
                    }
            }
            if(sayi == 0 ){
                break;
            }
        }
    }
}