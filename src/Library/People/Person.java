package Library.People;

public abstract class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String whoyouare() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}