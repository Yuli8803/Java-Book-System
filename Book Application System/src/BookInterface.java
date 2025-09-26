import java.util.List;
import java.util.Map;
public interface BookInterface
{
    // A default method to display details of the last six books.
    default void displayLastSixBooks(List<BookAbstract> books)
    {
        System.out.println("Displaying details of the last six books: ");
        books.stream()
                .skip(Math.max(0, books.size() - 6))
                .forEach(book -> System.out.println(book));
    }

    // Method where it returns the number of books in each genre
    Map <String,Integer> getNumberOfBooks();

    // Method where it returns the total cost of all books.
    double getTotalCost();

}
