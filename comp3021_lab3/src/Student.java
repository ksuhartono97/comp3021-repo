public class Student {
    private int id;
    private String name;
    private BirthDate birthDate;

    /**
     * Constructor for the class
     * @param birthDate the birth date object of the student
     * @param id the id of the student
     * @param name the name of the student
     */
    public Student (BirthDate birthDate, int id, String name) {
        this.birthDate = birthDate;
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for ID
     * @return int value of the id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for name
     * @return String name value of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the BirthDate object
     * @return the BirthDate object of the student
     */
    public BirthDate getBirthDate() {
        return birthDate;
    }

}
