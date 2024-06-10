import java.util.Comparator;

public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private String publicationYear;
    private String publisher;
    private String uniqueID;

    public Book (String title,String author, String publicationYear, String publisher,String uniqueID){
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.uniqueID = uniqueID;
    }
    public Book(String str){
        String[] temp = str.split(",");
        this.title = temp[0];
        this.author = temp[1];
        this.publicationYear = temp[2];
        this.publisher = temp[3];
        this.uniqueID = temp[4];
    }
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.title).append(", ").append(this.author).append(", ").append(this.publicationYear);
        stringBuilder.append(", ").append(this.publisher).append(", ").append(this.uniqueID);
        return stringBuilder.toString();
    }


    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }
}

