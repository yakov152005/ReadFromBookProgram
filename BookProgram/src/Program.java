import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        List<Book> bookList = new ArrayList<>();
        try {
            readFromFile(bookList);
            System.out.println("Read " + bookList.size() + " Books in the File");
        } catch (FileNotFoundException e) {
            System.out.println("This file is not found.");
            e.printStackTrace();
        }

        System.out.println();
        List<String> authors = authors(bookList,"Harcourt Brace Jovanovich");
        System.out.println("The authors with a same publisher is -- > " + authors);

        System.out.println();
        List<Book> booksWithSameYear = booksWithSameYear(bookList,"1847");
        System.out.println("The books with a same year is --> " );
        for (Book book : booksWithSameYear){
            System.out.println(book);
        }

        System.out.println();
        Map<String,List<String>> nameAuthorsWithHisBooks = nameAuthorsWithHisBooks(bookList);
        printAuthorsWithBooks(nameAuthorsWithHisBooks);

        System.out.println();
        System.out.println("Report: ");


    }

    public static void readFromFile(List<Book> bookList) throws FileNotFoundException {
        File file = new File("books.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            Book book = new Book(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim());
            System.out.println(book);
            bookList.add(book);
        }
        System.out.println();
        scanner.close();
    }
    public static List<String> authors(List<Book> bookList, String publisher) {
        List<String> authors = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getPublisher().equals(publisher)) {
                authors.add(book.getAuthor());
            }
        }
        return authors;
    }
    public static List<Book> booksWithSameYear(List<Book> bookList, String year){
        List<Book> booksWithSameYear = new ArrayList<Book>();
        for (Book book: bookList){
            if (book.getPublicationYear().equals(year)){
                booksWithSameYear.add(book);
            }
        }
        return booksWithSameYear;
    }

    public static Map<String, List<String>> nameAuthorsWithHisBooks(List<Book> bookList) {
        Map<String, List<String>> nameAuthorsWithHisBooks = new HashMap<>();
        for (Book book : bookList) {
            String author = book.getAuthor();
            String title = book.getTitle();
            if (!nameAuthorsWithHisBooks.containsKey(author)) {
                nameAuthorsWithHisBooks.put(author, new ArrayList<>());
            }
            nameAuthorsWithHisBooks.get(author).add(title);
        }
        return nameAuthorsWithHisBooks;
    }
    public static void printAuthorsWithBooks(Map<String, List<String>> authorsBooksMap) {
        for (Map.Entry<String, List<String>> entry : authorsBooksMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + String.join(", ", entry.getValue()));
        }
    }
}