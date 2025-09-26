import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintedBook extends BookAbstract
{
    private int totalPages;
    private static List<PrintedBook> printedBooks = new ArrayList<>(); // Creating a List to store all PrintedBooks
    private static List<PrintedBook> lastThreeBooks = new ArrayList<>(); // Creating a List to store only the last three books

    // Inheritance: The AudioBook class inherits common properties and methods from the Book class.
    // A contractor method to store user's PrintedBook information
    public PrintedBook(String title, String author, String genre, double cost, int totalPages)
    {
        super(title, author, genre, cost);
        this.totalPages = totalPages;
    }


    // This method computes the average pages for all printed books.
    public double calculateAverageOfPages()
    {
        if(printedBooks.isEmpty())
        {
            return 0;
        }

        int totalPages = 0;
        for(PrintedBook book : printedBooks)
            totalPages += book.getTotalPages();

        return (double) totalPages / printedBooks.size();
    }
    // Example of encapsulation: The `getCost` method provides controlled access to calculate the cost.
    // This method computes the cost per book.
    @Override
    public double getCost()
    {
        return totalPages * 10;
    }

    // This method computes the cost of all printed books.
    @Override
    public double getTotalCost()
    {

        if(printedBooks.isEmpty())
            return 0;

        int totalPages = 0;

        for (PrintedBook book : printedBooks)
            totalPages += book.getTotalPages();

        return (double) totalPages * 10;
    }

    // This method gets total pages per book
    public int getTotalPages()
    {
        return totalPages;
    }


    // This method returns the number of books in each genre.
    @Override
    public Map<String, Integer> getNumberOfBooks()
    {
        List <String> genre = new ArrayList<>();

        for (PrintedBook book : printedBooks)
        {
            genre.add(book.getGenre());
        }

        Map <String, Integer> results = new HashMap<>();

        for (String list : genre)
        {
            String temp = list.toLowerCase();

            if (results.containsKey(temp))
            {
                results.put(temp, results.get(temp) + 1);
            }else
            {
                results.put(temp, 1);
            }

        }

        return results;
    }

    // Method where is stores the last 3 printed books.
    public static void storeLastThreeBooks(PrintedBook book)
    {
        printedBooks.add(book);
        lastThreeBooks.add(book);

        if (lastThreeBooks.size() > 3)
        {
            lastThreeBooks.remove(0);
        }
    }


    // Method where it displays full details of the last three books.
    public static void displayLastThreeBooks ()
    {
        StringBuilder message = new StringBuilder("Displaying the last three books\n");

        for (PrintedBook book : lastThreeBooks)
        {
            message.append(book).append("\n");

        }

        JOptionPane.showMessageDialog(null, message.toString());
    }

    // This method displays printed book information
    @Override
    public String toString() {
        return "Title: " + getTitle() +
                ", Author: " + getAuthor() +
                ", Genre: " + getGenre() +
                ", Cost: $" + String.format("%.2f", getCost()) +
                ", Pages: " + totalPages;
    }



}
