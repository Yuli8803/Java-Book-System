import javax.swing.JOptionPane; // In order to use the GUI in the application
import java.io.*;

// This class demonstrates the application of OOP principles:
// - Encapsulation: Private fields and controlled access in PrintedBook and AudioBook.
// - Inheritance: PrintedBook and AudioBook inherit from the abstract Book class.
// - Polymorphism: Methods like `getCost` are overridden in the subclasses.
// - Abstraction: Book is an abstract class that provides a blueprint for book types.

// This class also handles user interaction and file operations for persistence.

public class BookApplicationSystem
{
    private static final String PRINTED_BOOKS_FILE = "printed_books.txt"; // Text file for printed books
    private static final String AUDIO_BOOKS_FILE = "audio_books.txt"; // Text file for audiobooks

    public static void main (String[] args)
    {
        String stringInput;
        int input = 0; // To retrieve input from user on the first main screen
        int bookNum = 0; // To retrieve input from user on the printed book menu screen
        int audioNum = 0; // To retrieve input from user on the audiobook menu screen
        boolean running = true; // Main loop control
        PrintedBook printedBook = null; // Creates a PrintedBook object
        AudioBook audioBook = null; // Creates a AudioBook object


        // Loads the existing data from the file into the PrintedBook object
        File printFile = new File(PRINTED_BOOKS_FILE);
        if (printFile.exists())
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(printFile)))
            {
                String printedLine;
                while ((printedLine = reader.readLine()) != null)
                {
                    String[] parts = printedLine.split(",");
                    if (parts.length == 5)
                    { // Title, Author, Genre, Cost, Pages
                        String title = parts[0];
                        String author = parts[1];
                        String genre = parts[2];
                        double cost = Double.parseDouble(parts[3]);
                        int totalPages = Integer.parseInt(parts[4]);

                        // Recreate PrintedBook object and add it to the list
                        printedBook = new PrintedBook (title, author, genre, cost, totalPages);
                        printedBook.storeLastThreeBooks(printedBook); // Populate the last three books list
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error Loading Printed Books: " + e.getMessage());
            }
        }

        // Loads the existing data from the file into the AudioBook object
        File audioFile = new File(AUDIO_BOOKS_FILE);
        if (audioFile.exists())
        {
            try (BufferedReader audioReader = new BufferedReader(new FileReader(audioFile)))
            {
                String audioLine;

                while ((audioLine = audioReader.readLine()) != null)
                {
                    String [] audioParts = audioLine.split(",");
                    if (audioParts.length == 5)
                    { // Title, Author, Genre, Cost, Pages
                        String audioTitle = audioParts[0];
                        String audioAuthor = audioParts[1];
                        String audioGenre = audioParts[2];
                        double audioCost = Double.parseDouble(audioParts[3]);
                        double audioLength = Double.parseDouble(audioParts[4]);

                        // Recreate audioBook object and add it to the list
                        audioBook = new AudioBook(audioTitle, audioAuthor, audioGenre, audioCost, audioLength);
                        audioBook.storesLastThreeAudiobooks(audioBook); // Populate the last three books list

                    }
                }

            }catch (IOException e)
            {
                JOptionPane.showMessageDialog(null, "Error Loading AudioBooks" + e.getMessage());
            }
        }


        while (running) { // Makes sure program stays running unless user exits

            // Checks if user inputs a valid integer that is between 1 and 3
            do {
                boolean validInput = false; // Check if users input is valid and resets for the next iteration

                while (!validInput)
                {
                    try
                    {
                        JOptionPane.showMessageDialog(null, " Book Application System\n--------------------------------------");
                        stringInput = JOptionPane.showInputDialog("Please select an option\n1.Printed Books\n2.AudioBooks\n3.Exit");
                        input = Integer.parseInt(stringInput);
                        validInput = true;

                    }catch (NumberFormatException e )
                    {
                        JOptionPane.showMessageDialog(null,"Invalid input. Please enter a valid integer");
                    }
                }
                if (1 > input || input > 3)
                {
                    JOptionPane.showMessageDialog(null, "Please select an option between 1 and 3");
                }

            } while (1 > input || input > 3);

            // Handle the Main Menu Selection
            switch (input) {
                case 1: // Printed Book Menu
                    boolean printBookMenu = true;
                    while (printBookMenu) {

                        // Checks if user inputs a valid integer and that its between 1 and 6
                        do
                        {
                            boolean validInputPrintedBook = false; // Check if users input is valid and resets for the next iteration

                            while(!validInputPrintedBook)
                            {
                                try
                                {
                                    String book = JOptionPane.showInputDialog(null, "1.Add a New Book\n2.Display Last 3 Books\n3.Show Books by Genre\n4.Calculate Average Pages\n5.Total Cost of Printed Books\n6.Return to main menu");
                                    bookNum = Integer.parseInt(book);
                                    validInputPrintedBook = true;

                                }catch (NumberFormatException e)
                                {
                                    JOptionPane.showMessageDialog(null,"Invalid input. Please enter a valid integer");
                                }

                            }

                            if (1 > bookNum || bookNum > 6) {
                                JOptionPane.showMessageDialog(null, "Invalid option. Please select a valid menu option.");
                            }

                        }while (1 > bookNum || bookNum > 6);

                        // SubMenu for Printed Book
                        switch (bookNum) {
                            case 1: // Add Book
                                String bookTitle = JOptionPane.showInputDialog(null, "Enter Title: ");
                                String bookAuthor = JOptionPane.showInputDialog(null, "Enter Author: ");
                                String bookGenre = JOptionPane.showInputDialog(null, "Enter Genre");
                                String bookCost = JOptionPane.showInputDialog(null, "Enter Cost: ");
                                double bookCostValue = Double.parseDouble(bookCost);
                                String bookTotalPages = JOptionPane.showInputDialog(null, "Enter Number of Pages: ");
                                int totalPages = Integer.parseInt(bookTotalPages);

                                printedBook = new PrintedBook(bookTitle, bookAuthor, bookGenre, bookCostValue, totalPages);
                                printedBook.storeLastThreeBooks(printedBook);

                                // Append the new book to the text file
                                try (PrintWriter writer = new PrintWriter(new FileWriter("printed_books.txt", true))) {
                                    writer.println(printedBook.getTitle() + "," + printedBook.getAuthor() + "," + printedBook.getGenre() + "," + printedBook.getCost() + "," + printedBook.getTotalPages());
                                } catch (IOException e) {
                                    JOptionPane.showMessageDialog(null, "Error saving the book to file: " + e.getMessage());
                                }

                                JOptionPane.showMessageDialog(null, "Book added successfully!");

                                break;

                            case 2: // Display last 3 book
                                if (printedBook == null) {
                                    JOptionPane.showMessageDialog(null, "No Printed Books Available to Display");

                                } else {
                                    printedBook.displayLastThreeBooks();
                                }
                                break;

                            case 3: // Display the # of books in each genre
                                if (printedBook == null) {
                                    JOptionPane.showMessageDialog(null, "No Printed Books Available to Display");
                                } else {
                                    JOptionPane.showMessageDialog(null, printedBook.getNumberOfBooks());
                                }
                                break;

                            case 4: // Gets the average of all printed book pages
                                if (printedBook == null) {
                                    JOptionPane.showMessageDialog(null, "No Printed Books Available to Display");
                                } else {
                                    JOptionPane.showMessageDialog(null, String.format("The Average Number of Pages for all Printed Books is %.2f ",printedBook.calculateAverageOfPages()));
                                }
                                break;

                            case 5: // Gets the total cost of all the printed books
                                if (printedBook == null) {
                                    JOptionPane.showMessageDialog(null, "No Printed Books Available to Display");
                                } else {
                                    JOptionPane.showMessageDialog(null, String.format("The Total Cost of all Printed Books is $%.2f", printedBook.getTotalCost()));
                                }
                                break;

                            case 6: // Return to the Main Menu
                                printBookMenu = false; // Exit Printed Book Menu
                                break;
                        }
                    }
                    break;

                case 2: // Audiobook Menu
                    boolean audioBookMenu = true;
                    while (audioBookMenu) {

                        do // Checks if user inputs a valid integer that is between 1 and 6
                        {
                            boolean validInputAudioBook = false; // Checks if user input is valid and resets for the next iteration

                            while (!validInputAudioBook)
                            {
                                try
                                {
                                    String audio = JOptionPane.showInputDialog(null, "1.Add a New AudioBook\n2.Display Last 3 AudioBooks\n3.Show AudioBooks by Genre\n4.Calculate Average Lengths\n5.Total cost of AudioBooks\n6.Return to main menu");
                                    audioNum = Integer.parseInt(audio);
                                    validInputAudioBook = true;
                                }catch (NumberFormatException e )
                                {
                                    JOptionPane.showMessageDialog(null,"Invalid input. Please enter a valid integer");
                                }
                            }

                            if (1 > audioNum || audioNum > 6)
                            {
                                JOptionPane.showMessageDialog(null, "Invalid option. Please select a valid menu option.");
                            }

                        }while (1 > audioNum || audioNum > 6);

                        // Sub menu for AudioBooks
                        switch (audioNum) {
                            case 1: // Add Audiobook
                                String audioTitle = JOptionPane.showInputDialog(null, "Enter Title: ");
                                String audioAuthor = JOptionPane.showInputDialog(null, "Enter Author: ");
                                String audioGenre = JOptionPane.showInputDialog(null, "Enter Genre");
                                String audioCost = JOptionPane.showInputDialog(null, "Enter Cost: ");
                                double audioCostValue = Double.parseDouble(audioCost);
                                String audioLength = JOptionPane.showInputDialog(null, "Enter Length in Minutes: ");
                                double audioLengthValue = Double.parseDouble(audioLength);

                                audioBook = new AudioBook(audioTitle, audioAuthor, audioGenre, audioCostValue, audioLengthValue);
                                audioBook.storesLastThreeAudiobooks(audioBook);

                                // Append the new book to the text file
                                try (PrintWriter writer = new PrintWriter(new FileWriter("audio_books.txt", true))) {
                                    writer.println(audioBook.getTitle() + "," + audioBook.getAuthor() + "," + audioBook.getGenre() + "," + audioBook.getCost() + "," + audioBook.getTotalLength());
                                } catch (IOException e) {
                                    JOptionPane.showMessageDialog(null, "Error saving the book to file: " + e.getMessage());
                                }

                                JOptionPane.showMessageDialog(null, "Book added successfully!");

                                break;

                            case 2: // Display last 3 books
                                if (audioBook == null) {
                                    JOptionPane.showMessageDialog(null, "No Available AudioBooks to Display");
                                } else {
                                    audioBook.displayLastThreeAudiobooks();
                                }
                                break;

                            case 3: // Display the # of books in each genre
                                if (audioBook == null) {
                                    JOptionPane.showMessageDialog(null, "No Available AudioBooks to Display");
                                } else {
                                    JOptionPane.showMessageDialog(null, audioBook.getNumberOfBooks());
                                }
                                break;

                            case 4: // Gets the average length of all AudioBooks
                                if (audioBook == null) {
                                    JOptionPane.showMessageDialog(null, "No Available AudioBooks to Display");
                                } else {
                                    JOptionPane.showMessageDialog(null, String.format("The Average Length of all Audiobooks is %.2f", audioBook.calculatesAverageLength()) + " Minutes");
                                }
                                break;

                            case 5: // Get the total cost of all AudioBooks
                                if (audioBook == null) {
                                    JOptionPane.showMessageDialog(null, "No Available AudioBooks to Display");
                                } else {
                                    JOptionPane.showMessageDialog(null, String.format("The Total Cost of all AudioBooks is $%.2f", audioBook.getTotalCost()));
                                }
                                break;

                            case 6: // Returns to the Main Menu
                                audioBookMenu = false; // Exit Audiobook Menu
                                break;
                        }
                    }
                    break;

                case 3: // Exit Program
                    running = false; // Exit Main Menu Loop
                    JOptionPane.showMessageDialog(null, "Exiting Now...");
                    break;
            }
        }
    }
}
