package Library;

import Library.Books.*;
import Library.People.Author;
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

    public Reader getReaderByName(String name){
        for(Reader item : readers){
            if(item.getName().equals(name)){
                return item;
            }
        }
        System.out.println("Yeni Üye");
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

        if (book.getStatus().equals(Status.AVAILABLE)) {
            readerAndBooks.get(reader).add(book);
            reader.setBalance(reader.getBalance() - BORROW_FEE);
            book.update_status();
            System.out.println(book.getName() + " kitabı başarıyla ödünç verildi.");
        } else if(book.getStatus().equals(Status.UNAVAILABLE)){
            System.out.println("Aradığınız kitap şu an başka bir üyemizdedir. Daha sonra tekrar deneyiniz.");
        }
    }

    public void take_back_book(Reader reader, Book book){
        readerAndBooks.get(reader).remove(book);
        reader.setBalance(reader.getBalance() + BORROW_FEE);
        book.update_status();
        System.out.println(book.getName() + " kitabı iade edildi.");
    }

    public void deleteBook(Book book){
        books.remove(book);
        System.out.println(book.getName() + " kitabı başarıyla silindi.");
    }

    public void show_book(){
        Set<String> bookNames = new HashSet<>();
        for(Book item : books){
            bookNames.add(item.getName());
        }
        System.out.println(bookNames);
    }

    Scanner scanner = new Scanner(System.in);

    public int getIntInput(){
        int input;
        while(true){
            try{
                input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e){
                System.out.println("Lütfen geçerli bir seçim yapınız.");
                scanner.nextLine();
            }
        }
    }

    public void librarySystem(){
        while(true){
            System.out.println("Lütfen yapmak istediğiniz işlemi seçiniz");
            System.out.println("1 - Kitap ödünç ver");
            System.out.println("2 - Kitap geri al");
            System.out.println("3 - Tüm kitapları göster");
            System.out.println("4 - Kitap ara");
            System.out.println("5 - Kitap ekle-sil");
            System.out.println("6 - Kitap bilgisi güncelle");
            System.out.println("7 - Kitap-Üye eşleşmesini göster");
            System.out.println("0 - Uygulamayı durdur");
            int sayi = getIntInput();
            switch (sayi){
                case 1:
                    System.out.println("Kitabı ödünç alacak üyenin ismini giriniz");
                    String borrowName = scanner.nextLine();
                    if(getReaderByName(borrowName) != null){
                        Reader reader = getReaderByName(borrowName);
                        System.out.println("Ödünç almak istediğiniz kitabın ismini giriniz.");
                        String bookName = scanner.nextLine();
                        Book borrowedBook = getBooksByName(bookName);
                        lend_book(reader, borrowedBook);
                    }else {
                        System.out.println("Kitabı ödünç alacak üyenin bakiyesini giriniz");
                        int bakiye = getIntInput();
                        Reader reader = new Reader(borrowName, bakiye);
                        System.out.println("Ödünç almak istediğiniz kitabın ismini giriniz.");
                        String bookName = scanner.nextLine();
                        Book borrowedBook = getBooksByName(bookName);
                        lend_book(reader, borrowedBook);
                    }
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
                    int aramaSayi = getIntInput();
                    switch (aramaSayi){
                        case 1:
                            System.out.println("ID giriniz.");
                            int aramaId = getIntInput();
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
                            System.out.println("Kategori seçiniz");
                            System.out.println("1 - Classic");
                            System.out.println("2 - Magazine");
                            System.out.println("3 - Science Fiction");
                            System.out.println("4 - Fantasy");
                            int aramaKategori = getIntInput();
                            switch (aramaKategori){
                                case 1:
                                    System.out.println(getBooksByCategory("Classic"));
                                    break;
                                case 2:
                                    System.out.println(getBooksByCategory("Magazine"));
                                    break;
                                case 3:
                                    System.out.println(getBooksByCategory("Science Fiction"));
                                    break;
                                case 4:
                                    System.out.println(getBooksByCategory("Fantasy"));
                                    break;
                                default:
                                    System.out.println("Lütfen geçerli bir seçim yapınız.");
                            }
                            break;
                        default:
                            System.out.println("Lütfen geçerli bir seçim yapınız");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("1 - Kitap ekle");
                    System.out.println("2 - Kitap sil");
                    int ekleSil = getIntInput();
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
                            int newEdition = getIntInput();
                            System.out.println("Kategori ismi giriniz");
                            String newCategory = scanner.nextLine();
                            switch (newCategory){
                                case "Classic":
                                    Book newClassic = new Classic(author, newBookName, Double.parseDouble(newPrice), newEdition, LocalDate.now(), newCategory);
                                    new_book(newClassic);
                                    System.out.println(newBookName + " kitabı başarıyla eklendi.");
                                    break;
                                case "Magazines":
                                    Book newMaganize = new Magazines(author, newBookName, Double.parseDouble(newPrice), newEdition, LocalDate.now(), newCategory);
                                    new_book(newMaganize);
                                    System.out.println(newBookName + " kitabı başarıyla eklendi.");
                                    break;
                                case "Science Fiction":
                                    Book newScienceFiction = new ScienceFiction(author, newBookName, Double.parseDouble(newPrice), newEdition, LocalDate.now(), newCategory);
                                    new_book(newScienceFiction);
                                    System.out.println(newBookName + " kitabı başarıyla eklendi.");
                                    break;
                                case "Fantasy":
                                    Book newFantasy = new Fantasy(author, newBookName, Double.parseDouble(newPrice), newEdition, LocalDate.now(), newCategory);
                                    new_book(newFantasy);
                                    System.out.println(newBookName + " kitabı başarıyla eklendi.");
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println("Silmek istediğiniz kitap ismini girin.");
                            String deleteBookName = scanner.nextLine();
                            Book deleteBookObj = getBooksByName(deleteBookName);
                            if(deleteBookObj == null){
                                break;
                            }
                            deleteBook(deleteBookObj);
                            break;
                        default:
                            System.out.println("Lütfen geçerli bir seçim yapınız.");
                            break;
                    }
                    break;
                case 6:
                    System.out.println("Güncellemek istediğiniz kitabın ismini giriniz");
                    String updateBookName = scanner.nextLine();
                    Book updateBook = getBooksByName(updateBookName);
                    System.out.println("Güncellemek istediğiniz bilgiyi seçiniz");
                    System.out.println("1 - İsim güncelle");
                    System.out.println("2 - Fiyat güncelle");
                    System.out.println("3 - Status güncelle");
                    System.out.println("4 - Edition güncelle");
                    System.out.println("5 - Satın alım tarihi güncelle");
                    System.out.println("6 - Kategori güncelle");
                    int updateSayi = getIntInput();
                    switch (updateSayi){
                        case 1:
                            System.out.println("Yeni ismi giriniz.");
                            String updateName = scanner.nextLine();
                            updateBook.setName(updateName);
                            System.out.println("İsim başarıyla güncellendi.");
                            break;
                        case 2:
                            System.out.println("Yeni fiyat giriniz.");
                            String updatePrice = scanner.nextLine();
                            updateBook.setPrice(Double.parseDouble(updatePrice));
                            System.out.println("Fiyat başarıyla güncellendi.");
                            break;
                        case 3:
                            updateBook.update_status();
                            System.out.println("Yeni status " + updateBook.getStatus());
                            break;
                        case 4:
                            System.out.println("Yeni edition giriniz.");
                            int updateEdition = getIntInput();
                            updateBook.setEdition(updateEdition);
                            System.out.println("Edition başarıyla güncellendi.");
                            break;
                        case 5:
                            System.out.println("Yeni kategori giriniz.");
                            String updateCategory = scanner.nextLine();
                            updateBook.setCategoryName(updateCategory);
                            System.out.println("Kategori başarıyla güncellendi.");
                            break;
                        default:
                            System.out.println("Lütfen geçerli bir seçim yapınız.");
                            break;
                    }
                    break;
                case 7:
                    System.out.println(readerAndBooks);
                    break;
                case 0:
                    System.out.println("Uygulama durduruluyor. İyi günler dileriz.");
                    return;
                default:
                    System.out.println("Lütfen geçerli bir seçim yapınız.");
                    break;
            }
        }
    }
}