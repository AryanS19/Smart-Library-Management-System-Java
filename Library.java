import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;
    private ArrayList<Student> students;

    public Library() {
        books = new ArrayList<>();
        students = new ArrayList<>();
    }

    // ==================== BOOK METHODS ====================

    public boolean addBook(Book book) {

        if (searchBook(book.getBookId()) != null) {
            System.out.println("A book with this ID already exists.");
            return false;
        }

        books.add(book);
        System.out.println("Book added successfully.");
        return true;
    }

    public void displayAllBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }

        System.out.println("\n========== ALL BOOKS ==========");

        for (Book book : books) {
            book.displayBook();
        }
    }

    public Book searchBook(String bookId) {

        for (Book book : books) {

            if (book.getBookId().equalsIgnoreCase(bookId)) {
                return book;
            }
        }

        return null;
    }
    public ArrayList<Book> searchBooksByTitle(String keyword) {

    ArrayList<Book> results = new ArrayList<>();

    for (Book book : books) {

        if (book.getTitle().toLowerCase()
                .contains(keyword.toLowerCase())) {

            results.add(book);
        }
    }

    return results;
}
    // ==================== STUDENT METHODS ====================

    public boolean registerStudent(Student student) {

        if (searchStudent(student.getStudentId()) != null) {
            System.out.println("A student with this ID already exists.");
            return false;
        }

        students.add(student);
        System.out.println("Student registered successfully.");
        return true;
    }

    public void displayAllStudents() {

        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }

        System.out.println("\n========== ALL STUDENTS ==========");

        for (Student student : students) {
            student.displayStudent();
        }
    }

    public Student searchStudent(String studentId) {

        for (Student student : students) {

            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }

        return null;
    }

    // ==================== ISSUE BOOK ====================

    public void issueBook(String bookId, String studentId) {

        Book book = searchBook(bookId);
        Student student = searchStudent(studentId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is already issued.");
            return;
        }

        book.setAvailable(false);
        student.issueBook(bookId);

        System.out.println("Book issued successfully.");
    }

    // ==================== RETURN BOOK ====================

    public void returnBook(String bookId, String studentId) {

        Book book = searchBook(bookId);
        Student student = searchStudent(studentId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        if (!student.hasBook(bookId)) {
            System.out.println("This student does not have this book.");
            return;
        }

        book.setAvailable(true);
        student.returnBook(bookId);

        System.out.println("Book returned successfully.");
    }
    // ==================== DATA ACCESS METHODS ====================

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

// ==================== INTERNAL DATA LOADING ====================

    void loadBook(Book book) {
        books.add(book);
    }

    void loadStudent(Student student) {
        students.add(student);
    }    
    
// ==================== LIBRARY STATISTICS ====================

public void displayStatistics() {

    int totalBooks = books.size();
    int availableBooks = 0;

    for (Book book : books) {

        if (book.isAvailable()) {
            availableBooks++;
        }
    }

    int issuedBooks = totalBooks - availableBooks;
    int totalStudents = students.size();

    System.out.println("\n========== LIBRARY STATISTICS ==========");
    System.out.println("Total Books         : " + totalBooks);
    System.out.println("Available Books     : " + availableBooks);
    System.out.println("Issued Books        : " + issuedBooks);
    System.out.println("Registered Students : " + totalStudents);
    System.out.println("=========================================");
}

}