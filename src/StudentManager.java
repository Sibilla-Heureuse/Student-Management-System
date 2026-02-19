import java.io.*;
import java.util.*;

public class StudentManager {

    private final List<Student> students = new ArrayList<>();
    private final String fileName = "src/students.txt";

    // 1️⃣ Load from file
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

    // 2️⃣ Display
    public void displayAll() {
        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // 3️⃣ Highest Score
    public Student getTopStudent() {
        return Collections.max(students, Comparator.comparingDouble(Student::getScore));
    }

    // 4️⃣ Lowest Score
    public Student getLowestStudent() {
        return Collections.min(students, Comparator.comparingDouble(Student::getScore));
    }

    // 5️⃣ Average
    public double getAverageScore() {
        double total = 0;
        for (Student s : students) {
            total += s.getScore();
        }
        return students.isEmpty() ? 0 : total / students.size();
    }

    // 6️⃣ Passed Students
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

    // 7️⃣ Failed Students
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

    // 8️⃣ Add Student
    public void addStudent(Student student) {
        students.add(student);
    }

    // 9️⃣ Update Score
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

    // 🔟 Save to File
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