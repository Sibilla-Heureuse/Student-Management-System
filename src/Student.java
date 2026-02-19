public class Student {
    private int studentId;
    private String name;
    private int age;
    private double score;

    public Student(int studentId, String name, int age, double score) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getScore() { return score; }

    public void setScore(double score) { this.score = score; }

    @Override
    public String toString() {
        return studentId + " " + name + " " + age + " " + score;
    }

    public String toFileFormat() {
        return studentId + "," + name + "," + age + "," + score;
    }

}
