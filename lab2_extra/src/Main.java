public class Main {
    public static void main(String[] args) {
        System.out.println("--------------------------Construction Phase--------------------------------");
        Animal a = new Animal(100, 130, 80, 70, 4);

        System.out.println("The animal constructed has the following attributes:");
        a.print();
        a.grow(10);
        System.out.println();
        System.out.println();

        Lion b = new Lion(80, 120, 60, 90, 4, 15);
        System.out.println("The Lion constructed has the following attributes:");
        b.print();
        b.grow(15);
        b.move(20);
        System.out.println();
        System.out.println();

        System.out.println("--------------------------Destruction Phase---------------------------------");
        a = b;
        System.out.println("Let's look at the Lion again before destroying it:");
        a.print();
        System.out.println();
    }
}
