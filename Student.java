import java.util.ArrayList;

public class Student {

    private String studentId;
    private String name;
    private String branch;
    private ArrayList<String> issuedBooks;

    public Student(String studentId, String name, String branch) {
        this.studentId = studentId;
        this.name = name;
        this.branch = branch;
        this.issuedBooks = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public ArrayList<String> getIssuedBooks() {
        return issuedBooks;
    }

    public void issueBook(String bookId) {
        issuedBooks.add(bookId);
    }

    public void returnBook(String bookId) {
        issuedBooks.remove(bookId);
    }

    public boolean hasBook(String bookId) {
        return issuedBooks.contains(bookId);
    }

    public void displayStudent() {
        System.out.println("----------------------------------------");
        System.out.println("Student ID  : " + studentId);
        System.out.println("Name        : " + name);
        System.out.println("Branch      : " + branch);

        if (issuedBooks.isEmpty()) {
            System.out.println("Issued Books: None");
        } else {
            System.out.println("Issued Books: " + issuedBooks);
        }

        System.out.println("----------------------------------------");
    }
}
