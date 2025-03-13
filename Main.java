import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    
    // Student class
    static class Student {
        private String name;
        private int rollNo;
        private String address;
        private String course;
        private double grade;

        public Student(String name, int rollNo, String address, String course, double grade) {
            this.name = name;
            this.rollNo = rollNo;
            this.address = address;
            this.course = course;
            this.grade = grade;
        }

        public String getName() { return name; }
        public int getRollNo() { return rollNo; }
        public String getAddress() { return address; }
        public String getCourse() { return course; }
        public double getGrade() { return grade; }

        public void setName(String name) { this.name = name; }
        public void setAddress(String address) { this.address = address; }
        public void setCourse(String course) { this.course = course; }
        public void setGrade(double grade) { this.grade = grade; }

        @Override
        public String toString() {
            return "Student [Name: " + name + ", Roll No: " + rollNo + ", Address: " + address + ", Course: " + course + ", Grade: " + grade + "]";
        }
    }

    // StudentManager class
    static class StudentManager {
        private List<Student> students;

        public StudentManager() {
            students = new ArrayList<>();
        }

        public void addStudent(Student student) {
            students.add(student);
        }

        public boolean editStudent(int rollNo, String name, String address, String course, double grade) {
            Optional<Student> studentOptional = students.stream().filter(student -> student.getRollNo() == rollNo).findFirst();
            if (studentOptional.isPresent()) {
                Student student = studentOptional.get();
                student.setName(name);
                student.setAddress(address);
                student.setCourse(course);
                student.setGrade(grade);
                return true;
            }
            return false;
        }

        public boolean deleteStudent(int rollNo) {
            return students.removeIf(student -> student.getRollNo() == rollNo);
        }

        public Student viewStudent(int rollNo) {
            return students.stream().filter(student -> student.getRollNo() == rollNo).findFirst().orElse(null);
        }

        public void listStudents() {
            students.forEach(System.out::println);
        }

        public Student searchByName(String name) {
            return students.stream().filter(student -> student.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        }
    }

    // Main method to interact with user
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager studentManager = new StudentManager();
        
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View Student");
            System.out.println("5. List All Students");
            System.out.println("6. Search Student");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Roll Number: ");
                    int rollNo = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Course: ");
                    String course = scanner.nextLine();
                    System.out.print("Enter Grade: ");
                    double grade = scanner.nextDouble();
                    studentManager.addStudent(new Student(name, rollNo, address, course, grade));
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Roll Number of student to edit: ");
                    int editRollNo = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Address: ");
                    String newAddress = scanner.nextLine();
                    System.out.print("Enter New Course: ");
                    String newCourse = scanner.nextLine();
                    System.out.print("Enter New Grade: ");
                    double newGrade = scanner.nextDouble();
                    if (studentManager.editStudent(editRollNo, newName, newAddress, newCourse, newGrade)) {
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Roll Number of student to delete: ");
                    int deleteRollNo = scanner.nextInt();
                    if (studentManager.deleteStudent(deleteRollNo)) {
                        System.out.println("Student deleted successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Roll Number to view: ");
                    int viewRollNo = scanner.nextInt();
                    Student student = studentManager.viewStudent(viewRollNo);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    studentManager.listStudents();
                    break;

                case 6:
                    System.out.print("Enter Name to search: ");
                    String searchName = scanner.nextLine();
                    Student searchedStudent = studentManager.searchByName(searchName);
                    if (searchedStudent != null) {
                        System.out.println(searchedStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
