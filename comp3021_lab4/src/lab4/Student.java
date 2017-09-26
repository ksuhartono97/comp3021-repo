package lab4;

public class Student {
	// TODO 1: replace ??? with static or delete ???
	private static SIS sis = SIS.getSystem();
	public String depart;
	public String id;
	public String name;
	public static int numOfStudents = 0;

	public Student(String name, String id, String depart) {
		// TODO 2: create a new student and increment the number of students.
		this.depart = depart;
		this.name = name;
		this.id = id;
		System.out.println("Created a student named " + name + " with id " + id + " from department " + depart);
		numOfStudents++;
	}

	public void add(String course) {
		boolean success = sis.addCourse(this, course);
		if(success) {
			System.out.printf("Student %s enrolls in the course %s%n", name, course);
		} else {
			System.err.printf("Student %s failed to enroll in the course %s%n", name, course);
		}
	}

	public void drop(String course) {
		// TODO 3: drop the student from a course. You can refer to the add method.
		boolean success = sis.dropCourse(this, course);
		if(success) {
			System.out.printf("Student %s drops the course %s%n", name, course);
		} else {
			System.err.printf("Student %s failed to drop the course %s%n", name, course);
		}
	}

	public static void printAllEnrolledStudents(String course) {
		// TODO 4: print out the names of all the students enrolled in a course.
		Student courseMember[] = sis.getAllEnrolledStudents(course);
		int numberOfStudents = courseMember.length;

		System.out.print("" + numberOfStudents + " student(s) in course " + course + ": ");

		if(numberOfStudents >= 1) System.out.print(courseMember[0].name);
		for (int i = 1; i < numberOfStudents; i++) {
			System.out.print(", " + courseMember[i].name);
		}
		System.out.print("\n");
	}

	public static void printNumberOfStudents() {
		// TODO 5: print out the total number of students
		System.out.println("The total number of students is " + numOfStudents);
	}
}

