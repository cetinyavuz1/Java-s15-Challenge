package Library.Books;

import Library.People.Author;
import Library.enums.Status;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Book {
    public static int idCounter = 0;
    private long book_ID;
    private Author author;
    private String name;
    private double price;
    private Status status;
    private int edition;
    private LocalDate date_of_purchase;
    private String categoryName;

    public Book(String name){
        this.name = name;
    }

    public Book(Author author, String name, double price, Status status, int edition, LocalDate date_of_purchase, String categoryName) {
        this.book_ID = idCounter++;
        this.author = author;
        this.name = name;
        this.price = price;
        this.status = status;
        this.edition = edition;
        this.date_of_purchase = date_of_purchase;
        this.categoryName = categoryName;
    }

    public long getBook_ID() {
        return book_ID;
    }

    public void setBook_ID(long book_ID) {
        this.book_ID = book_ID;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public LocalDate getDate_of_purchase() {
        return date_of_purchase;
    }

    public void setDate_of_purchase(LocalDate date_of_purchase) {
        this.date_of_purchase = date_of_purchase;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String get_title(){
        return name;
    }

    public Author get_author(){
        return author;
    }

    public void update_status(){
        if(status == Status.AVAILABLE){
            status = Status.UNAVAILABLE;
        } else if (status == Status.UNAVAILABLE){
            status = Status.AVAILABLE;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return book_ID == book.book_ID && Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_ID, name);
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_ID=" + book_ID +
                ", author=" + author +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", edition=" + edition +
                ", date_of_purchase=" + date_of_purchase +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
