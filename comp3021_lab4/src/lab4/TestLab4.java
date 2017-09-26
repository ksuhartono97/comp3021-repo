package lab4;

public class TestLab4 {

	public static void main(String[] args) {
		System.out.println("Main method starts");
		Student.printNumberOfStudents();
		Student alex = new Student("Alex", "1234", "CSE");
		Student andy = new Student("Andy", "2345", "CPEG");
		Student herry = new Student("Herry", "3456", "CSE");
		alex.add("COMP3021");
		andy.add("COMP3711");
		andy.add("COMP3021");
		herry.add("COMP3111");
		herry.add("COMP3021");
		Student.printAllEnrolledStudents("COMP3021");
		alex.drop("COMP3021");
		Student.printAllEnrolledStudents("COMP3021");
		Student.printAllEnrolledStudents("COMP3711");
		Student.printAllEnrolledStudents("COMP3111");
		Student.printAllEnrolledStudents("COMP3511");
		herry.drop("COMP3711");
		andy.add("COMP3021");
		Student.printAllEnrolledStudents("COMP3021");
		alex.add("COMP3711");
		Student.printAllEnrolledStudents("COMP3711");
		Student.printNumberOfStudents();
	}

}