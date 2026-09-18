import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {

        FileManager.loadData(library);

        System.out.println("========================================");
        System.out.println("     SMART LIBRARY MANAGEMENT SYSTEM");
        System.out.println("========================================");

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInteger("Enter your choice: ");

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    library.displayAllBooks();
                    break;

                case 3:
                    searchBook();
                    break;

                case 4:
                    registerStudent();
                    break;

                case 5:
                    library.displayAllStudents();
                    break;

                case 6:
                    issueBook();
                    break;

                case 7:
                    returnBook();
                    break;

                case 8:
                    library.displayStatistics();
                    break;

                case 9:
                    FileManager.saveData(library);
                    running = false;

                    System.out.println(
                            "\nThank you for using Smart Library Management System."
                    );
                    break;

                default:
                    System.out.println(
                            "\nInvalid choice. Please enter a number from 1 to 9."
                    );
            }
        }

        scanner.close();
    }

    // ==================== MAIN MENU ====================

    private static void displayMenu() {

        System.out.println("\n========================================");
        System.out.println("              MAIN MENU");
        System.out.println("========================================");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book");
        System.out.println("4. Register Student");
        System.out.println("5. View All Students");
        System.out.println("6. Issue Book");
        System.out.println("7. Return Book");
        System.out.println("8. Library Statistics");
        System.out.println("9. Exit");
        System.out.println("========================================");
    }

    // ==================== ADD BOOK ====================

    private static void addBook() {

        System.out.println("\n========== ADD BOOK ==========");

        String bookId = readText("Enter Book ID: ");
        String title = readText("Enter Book Title: ");
        String author = readText("Enter Author Name: ");

        if (bookId.isEmpty() ||
                title.isEmpty() ||
                author.isEmpty()) {

            System.out.println(
                    "Book details cannot be empty."
            );
            return;
        }

        Book book = new Book(bookId, title, author);

        library.addBook(book);
    }

    // ==================== SEARCH BOOK ====================

    private static void searchBook() {

        System.out.println("\n========== SEARCH BOOK ==========");
        System.out.println("1. Search by Book ID");
        System.out.println("2. Search by Book Title");

        int choice = readInteger("Enter search option: ");

        if (choice == 1) {

            String bookId = readText("Enter Book ID: ");

            Book book = library.searchBook(bookId);

            if (book == null) {

                System.out.println("Book not found.");

            } else {

                System.out.println("\nBook found:");
                book.displayBook();
            }

        } else if (choice == 2) {

            String keyword = readText("Enter title keyword: ");

            if (keyword.isEmpty()) {

                System.out.println(
                        "Search keyword cannot be empty."
                );
                return;
            }

            ArrayList<Book> results =
                    library.searchBooksByTitle(keyword);

            if (results.isEmpty()) {

                System.out.println("No books found.");

            } else {

                System.out.println(
                        "\n========== SEARCH RESULTS =========="
                );

                for (Book book : results) {
                    book.displayBook();
                }
            }

        } else {

            System.out.println(
                    "Invalid search option."
            );
        }
    }

    // ==================== REGISTER STUDENT ====================

    private static void registerStudent() {

        System.out.println("\n========== REGISTER STUDENT ==========");

        String studentId =
                readText("Enter Student ID: ");

        String name =
                readText("Enter Student Name: ");

        String branch =
                readText("Enter Branch: ");

        if (studentId.isEmpty() ||
                name.isEmpty() ||
                branch.isEmpty()) {

            System.out.println(
                    "Student details cannot be empty."
            );
            return;
        }

        Student student =
                new Student(studentId, name, branch);

        library.registerStudent(student);
    }

    // ==================== ISSUE BOOK ====================

    private static void issueBook() {

        System.out.println("\n========== ISSUE BOOK ==========");

        String bookId =
                readText("Enter Book ID: ");

        String studentId =
                readText("Enter Student ID: ");

        if (bookId.isEmpty() ||
                studentId.isEmpty()) {

            System.out.println(
                    "Book ID and Student ID cannot be empty."
            );
            return;
        }

        library.issueBook(bookId, studentId);
    }

    // ==================== RETURN BOOK ====================

    private static void returnBook() {

        System.out.println("\n========== RETURN BOOK ==========");

        String bookId =
                readText("Enter Book ID: ");

        String studentId =
                readText("Enter Student ID: ");

        if (bookId.isEmpty() ||
                studentId.isEmpty()) {

            System.out.println(
                    "Book ID and Student ID cannot be empty."
            );
            return;
        }

        library.returnBook(bookId, studentId);
    }

    // ==================== INPUT METHODS ====================

    private static String readText(String message) {

        System.out.print(message);

        return scanner.nextLine().trim();
    }

    private static int readInteger(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a valid number."
                );
            }
        }
    }
}
