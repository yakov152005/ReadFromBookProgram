import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {
        List<Book> bookList = new ArrayList<>();
        try {
            readFromFile(bookList);
            Collections.sort(bookList);
            for (Book book: bookList){
                System.out.println(book);
            }
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
        File file = new File("Report.txt");
        PrintWriter pw = new PrintWriter(file);

        pw.println("The total amount book: ");
        pw.print(bookList.size());


        int currentCount = 0;
        String nameAuthor = "";
        int maxCount = 0;
        for (int i = 0; i < bookList.size(); i++) {
            String currentHigherName = bookList.get(i).getAuthor();
            for (int j = 0; j < bookList.size(); j++) {
                if (currentHigherName.equals(bookList.get(j).getAuthor())){
                    currentCount++;
                }
            }
            if (currentCount > maxCount){
                maxCount = currentCount;
                nameAuthor = currentHigherName;
            }
            currentCount = 0;
        }
        pw.println(" ");
        pw.println("The name of the author who wrote the most books and the amount of books he wrote: ");
        pw.print(nameAuthor);
        pw.print(", ");
        pw.print(maxCount);

        int higherCount = 0, tempCount = 0;
        String currentYear = "";
        String theYear = "";
        for (int i = 0; i < bookList.size(); i++) {
            currentYear = bookList.get(i).getPublicationYear();
            for (int j = 0; j < bookList.size(); j++) {
                if (currentYear.equals(bookList.get(i).getPublicationYear())){
                    tempCount++;
                }
            }
            if (tempCount > higherCount){
                higherCount = tempCount;
                theYear = currentYear;
            }
            tempCount = 0;
        }
        pw.println(" ");
        pw.println("The year when the most books were published on the list: ");
        pw.print(theYear);
        pw.close();

        File newFile = new File("Report.txt");
        Scanner s = new Scanner(newFile);
        while (s.hasNextLine()){
            String line = s.nextLine();
            System.out.println(line);
        }
        s.close();

    }

    public static void readFromFile(List<Book> bookList) throws FileNotFoundException {
        File file = new File("books.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            Book book = new Book(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim());
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