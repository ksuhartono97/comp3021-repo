public class TestLab6 {
    public static void display(Person p) {
        if(p instanceof Student) {
            System.out.println("Student: ");
        } else if(p instanceof Teacher) {
            System.out.println("Teacher: ");
        } else if(p instanceof Person) {
            System.out.println("Person:");
        }
        System.out.println(p);
    }

    public static void main(String[] args) {
        Person[] persons = new Person[3];
        persons[0] = new Person("Andy", 17);
        persons[1] = new Student("Benny", 18, 1234, "CSE");
        persons[2] = new Teacher("Fanny", 30, "Physics", 30000);
        for (Person p : persons) {
            display(p);
        }
    }

}