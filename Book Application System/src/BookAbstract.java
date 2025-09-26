import java.util.Map;

// Abstraction: Book is an abstract class and serves as a blueprint for specific types of books (e.g., PrintedBook, AudioBook).

public abstract class BookAbstract implements BookInterface
{
    // Encapsulation: Private fields ensure data is not directly accessible.
    // Getters and setters provide controlled access to these fields.

    private String title; // Stores the title of print/audiobook
    private String author; // Stores the author of print/audiobook
    private String genre; // Stores the genre of print/audiobook
    private double cost; // Stores the cost of print/audiobook

    // A constructor method where it stores users printed/audiobook information
   public BookAbstract(String title, String author, String genre, double cost)
   {
       this.title = title;
       this.author = author;
       this.genre = genre;
       this.cost = cost;
   }

   // A getter method for the title
   public String getTitle()
   {
       return title;
   }

   // A getter method for the author
   public String getAuthor()
   {
       return author;
   }

   // A getter method for genre
   public String getGenre()
   {
       return genre;
   }
   // A getter method for the cost per print/audiobook
   public double getBaseCost()
   {
       return cost;
   }

    // Polymorphism: This method is overridden in subclasses (PrintedBook, AudioBook) to provide specific implementations.
    // An abstract method for computing the cost of a printed/audiobook.
   public abstract double getCost();

    // An abstract method for computing the total cost of all books.
   @Override
    public abstract double getTotalCost();

    // An abstract method for returning the number of books per genre.
   @Override
    public abstract Map <String, Integer> getNumberOfBooks();
}
