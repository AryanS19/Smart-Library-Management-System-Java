import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String DATA_FOLDER = "data";
    private static final String BOOK_FILE = DATA_FOLDER + "/books.txt";
    private static final String STUDENT_FILE = DATA_FOLDER + "/students.txt";

    // ==================== SAVE ALL DATA ====================

    public static void saveData(Library library) {

        createDataFolder();

        saveBooks(library.getBooks());
        saveStudents(library.getStudents());

        System.out.println("Library data saved successfully.");
    }

    // ==================== SAVE BOOKS ====================

    private static void saveBooks(ArrayList<Book> books) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(BOOK_FILE))) {

            for (Book book : books) {

                writer.write(
                        book.getBookId() + "|" +
                        book.getTitle() + "|" +
                        book.getAuthor() + "|" +
                        book.isAvailable()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error while saving books: " + e.getMessage()
            );
        }
    }

    // ==================== SAVE STUDENTS ====================

    private static void saveStudents(ArrayList<Student> students) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(STUDENT_FILE))) {

            for (Student student : students) {

                writer.write(
                        student.getStudentId() + "|" +
                        student.getName() + "|" +
                        student.getBranch() + "|" +
                        String.join(",", student.getIssuedBooks())
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error while saving students: " + e.getMessage()
            );
        }
    }

    // ==================== LOAD ALL DATA ====================

    public static void loadData(Library library) {

        createDataFolder();

        loadBooks(library);
        loadStudents(library);
    }

    // ==================== LOAD BOOKS ====================

    private static void loadBooks(Library library) {

        File file = new File(BOOK_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|", -1);

                if (parts.length != 4) {
                    continue;
                }

                String bookId = parts[0];
                String title = parts[1];
                String author = parts[2];

                boolean available =
                        Boolean.parseBoolean(parts[3]);

                Book book =
                        new Book(bookId, title, author);

                book.setAvailable(available);

                library.loadBook(book);
            }

        } catch (IOException e) {

            System.out.println(
                    "Error while loading books: " +
                    e.getMessage()
            );
        }
    }

    // ==================== LOAD STUDENTS ====================

    private static void loadStudents(Library library) {

        File file = new File(STUDENT_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|", -1);

                if (parts.length != 4) {
                    continue;
                }

                String studentId = parts[0];
                String name = parts[1];
                String branch = parts[2];
                String issuedBooks = parts[3];

                Student student =
                        new Student(
                                studentId,
                                name,
                                branch
                        );

                if (!issuedBooks.isEmpty()) {

                    String[] bookIds =
                            issuedBooks.split(",");

                    for (String bookId : bookIds) {

                        if (!bookId.trim().isEmpty()) {

                            student.issueBook(
                                    bookId.trim()
                            );
                        }
                    }
                }

                library.loadStudent(student);
            }

        } catch (IOException e) {

            System.out.println(
                    "Error while loading students: " +
                    e.getMessage()
            );
        }
    }

    // ==================== CREATE DATA FOLDER ====================

    private static void createDataFolder() {

        File folder = new File(DATA_FOLDER);

        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}