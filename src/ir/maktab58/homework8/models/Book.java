package ir.maktab58.homework8.models;

import java.util.Objects;

/**
 * @author Taban Soleymani
 */
public class Book extends ReadingItems {
    private String authorName;
    private String genre;

    public Book(int id, String productName, long price, int count, String publisherName, String authorName, String genre) {
        super(id, productName, price, count, publisherName);
        this.authorName = authorName;
        this.genre = genre;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return authorName.equals(book.authorName) && genre.equals(book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), authorName, genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "authorName='" + authorName + '\'' +
                ", genre='" + genre + '\'' +
                "} " + super.toString();
    }
}
