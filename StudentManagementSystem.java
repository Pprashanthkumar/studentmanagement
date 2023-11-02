import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String studentId;
    private String name;
    private int age;

    public Student(String studentId, String name, int age) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println("Student ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println();
        }
    }

    public Student findStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void updateStudent(String studentId, String name, int age) {
        Student student = findStudent(studentId);
        if (student != null) {
            student.name = name;
            student.age = age;
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void saveStudentsToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.println(student.getStudentId() + "," + student.getName() + "," + student.getAge());
            }
            System.out.println("Student data saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error while saving student data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search for Student");
            System.out.println("4. Update Student Info");
            System.out.println("5. Save Student Data to File");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.next();
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();

                    Student newStudent = new Student(studentId, name, age);
                    sms.addStudent(newStudent);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    System.out.println("All Students:");
                    sms.displayAllStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID to search: ");
                    studentId = scanner.next();
                    Student foundStudent = sms.findStudent(studentId);
                    if (foundStudent != null) {
                        System.out.println("Student found:");
                        System.out.println("Student ID: " + foundStudent.getStudentId());
                        System.out.println("Name: " + foundStudent.getName());
                        System.out.println("Age: " + foundStudent.getAge());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID to update: ");
                    studentId = scanner.next();
                    System.out.print("Enter New Name: ");
                    name = scanner.next();
                    System.out.print("Enter New Age: ");
                    age = scanner.nextInt();

                    sms.updateStudent(studentId, name, age);
                    break;

                case 5:
                    System.out.print("Enter the filename to save student data: ");
                    String fileName = scanner.next();
                    sms.saveStudentsToFile(fileName);
                    break;

                case 6:
                    System.out.println("Exiting Student Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
