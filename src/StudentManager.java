import java.io.*;
import java.util.*;

public class StudentManager {

    private final List<Student> students = new ArrayList<>();
    private final String fileName = "src/students.txt";


    public void loadStudents() {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");

                if (parts.length != 4) {
                    System.out.println("Invalid data format: " + line);
                    continue;
                }

                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                int age = Integer.parseInt(parts[2].trim());
                double score = Double.parseDouble(parts[3].trim());

                students.add(new Student(id, name, age, score));
            }

            if (students.isEmpty()) {
                System.out.println("File is empty.");
            } else {
                System.out.println("Loaded " + students.size() + " students from the file.");
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    public void displayAll() {
        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }


    public Student getTopStudent() {
        return Collections.max(students, Comparator.comparingDouble(Student::getScore));
    }


    public Student getLowestStudent() {
        return Collections.min(students, Comparator.comparingDouble(Student::getScore));
    }


    public double getAverageScore() {
        double total = 0;
        for (Student s : students) {
            total += s.getScore();
        }
        return students.isEmpty() ? 0 : total / students.size();
    }


    public void displayPassed() {
        System.out.println("\nStudents who passed:");
        boolean found = false;
        for (Student s : students) {
            if (s.getScore() >= 60) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) System.out.println("None");
    }


    public void displayFailed() {
        System.out.println("\nStudents who failed:");
        boolean found = false;
        for (Student s : students) {
            if (s.getScore() < 60) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) System.out.println("None");
    }


    public void addStudent(Student student) {
        students.add(student);
    }


    public void updateStudentScore(int id, double newScore) {
        for (Student s : students) {
            if (s.getStudentId() == id) {
                s.setScore(newScore);
                System.out.println("Score updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }


    public void saveStudents() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Student s : students) {
                bw.write(s.toFileFormat());
                bw.newLine();
            }
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}