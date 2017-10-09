public class Teacher extends Person {
    private String subject;
    private double salary;

    public Teacher (String name, int age, String subject, double salary) {
        super(name, age);
        this.subject = subject;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Name: " +
                this.getName() + "\nAge: " +
                this.getAge() + "\nSubject: " +
                this.getSubject() + "\nSalary: " +
                this.getSalary() + "\n" ;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
