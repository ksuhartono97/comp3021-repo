public class Department {
    private String name;
    private int numStudents;
    private int arraySize;
    private Student[] students = {};

    public Department(String name) {
        this.name = name;
        this.numStudents = 0;
        this.arraySize = 3;
        this.students = new Student[arraySize];
    }

    public String getName() {
        return name;
    }

    boolean createNewStudent (int id, int year, int month, int day, String name) {
        BirthDate newBirthDate = new BirthDate(year, month, day);
        Student newStudent = new Student(newBirthDate, id, name);
        return insertStudent(newStudent);
    }

    boolean insertStudent(Student student) {
        for(Student element : this.students) {
            if(element != null && element.getId() == student.getId()) {
                System.out.println("Creating member " + student.getName() + " failed");
                return false;
            }
        }
        if(numStudents < this.arraySize) {
            this.students[this.numStudents] = student;
            this.numStudents++;
        }
        else {
            Student newStudentsArray[] = new Student[this.arraySize + 1];
            for (int i = 0; i < this.arraySize; i++) {
                newStudentsArray[i] = this.students[i];
            }
            this.arraySize++;
            newStudentsArray[this.numStudents] = student;
            this.numStudents++;
            this.students = newStudentsArray;
        }
        return true;
    }

    public void printMembers () {
        System.out.println(this.name + "'s Student List: ");
        for(int i = 0; i < this.arraySize; i++) {
            System.out.println((i+1) + " " + this.students[i].getName());
        }
    }
}
