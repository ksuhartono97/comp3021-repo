public class Lab3 {

    public static void main(String args[]){

        Department cse = new Department("Computer Science and Engineering");
        boolean b;
        int numSucceed = 0;
        b = cse.createNewStudent(001, 2000, 1, 1, "Andy");
        if (b) numSucceed++;
        b = cse.createNewStudent(002, 2000, 3, 3, "Benny");
        if (b) numSucceed++;
        b = cse.createNewStudent(002, 1990, 6, 6, "Benny");
        if (b) numSucceed++;

        b = cse.createNewStudent(003, 1990, 2, 2, "Fanny");
        if (b) numSucceed++;
        b = cse.createNewStudent(003, 1990, 6, 6, "Gilbert");
        if (b) numSucceed++;
        b = cse.createNewStudent(004, 1990, 6, 6, "Harry");
        if (b) numSucceed++;
        b = cse.createNewStudent(005, 1990, 6, 6, "Ivy");
        if (b) numSucceed++;

        System.out.println("Successfully created " + numSucceed + " members");
        cse.printMembers();
    }
}