import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AudioBook extends BookAbstract
{
    private double totalLength;
    private static List<AudioBook> audioBooks = new ArrayList<>();
    private static List<AudioBook> lastThreeAudio = new ArrayList<>();

    // Inheritance: The PrintedBook class inherits common properties and methods from the Book class.
    // A contractor method where it stores user AudioBook information
    public AudioBook(String title, String author, String genre, double cost, double totalLength)
    {
        super(title, author, genre, cost);
        this.totalLength = totalLength;
    }

    public double getTotalLength()
    {
        return totalLength;
    }


    // Method that computes the average length of all audiobooks.
    public double calculatesAverageLength()
    {
        if(audioBooks.isEmpty())
            return 0;

        double totalAmount = 0;

        for (AudioBook book : audioBooks)
        {
            totalAmount += book.totalLength;
        }

        return totalAmount / audioBooks.size();
    }

    // Example of encapsulation: The `getCost` method provides controlled access to calculate the cost.
    // This method computes the cost of a single audiobook.
    @Override
    public double getCost ()
    {
        return totalLength * 5;
    }

    // This method computes the total cost of all the audiobooks.
    @Override
    public double getTotalCost ()
    {
        if(audioBooks.isEmpty())
            return 0;

        double totalAmount = 0;

        for (AudioBook book : audioBooks)
        {
            totalAmount += book.totalLength;
        }

        return totalAmount * 5;

    }

    // This method gets the number of books of each genre.
    @Override
    public Map <String, Integer> getNumberOfBooks()
    {
        List <String> genre = new ArrayList<>();

        for (AudioBook book : audioBooks)
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

    // This method stores the full details of the last 3 audiobooks
    public static void storesLastThreeAudiobooks (AudioBook book)
    {
        audioBooks.add(book);
        lastThreeAudio.add(book);

        if (lastThreeAudio.size() > 3)
        {
            lastThreeAudio.remove(0);
        }
    }

    // This method displays the last 3 audiobook details
    public static void displayLastThreeAudiobooks ()
    {
        StringBuilder message = new StringBuilder("Displaying the last three audiobooks\n");

        for (AudioBook book : lastThreeAudio)
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
                ", Length: " + totalLength;
    }

}


