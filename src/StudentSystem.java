import java.util.Scanner;

public class StudentSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        manager.loadStudents();
        manager.displayAll();

        System.out.println("\nAverage score: " + manager.getAverageScore());

        if(manager.getTopStudent() != null)  {
            System.out.println("Top student: " +
                    manager.getTopStudent().getName() +
                    " (" + manager.getTopStudent().getScore() + ")");
        }

        System.out.println("Lowest student: " +
                manager.getLowestStudent().getName() +
                " (" + manager.getLowestStudent().getScore() + ")");

        manager.displayPassed();
        manager.displayFailed();


        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Update Score");
            System.out.println("3. Save & Exit");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("Age: ");
                int age = scanner.nextInt();

                System.out.print("Score: ");
                double score = scanner.nextDouble();

                manager.addStudent(new Student(id, name, age, score));
                System.out.println("Student added.");

            } else if (choice == 2) {
                System.out.print("Enter student ID: ");
                int id = scanner.nextInt();
                System.out.print("New score: ");
                double score = scanner.nextDouble();
                manager.updateStudentScore(id, score);

            } else if (choice == 3) {
                manager.saveStudents();
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}
