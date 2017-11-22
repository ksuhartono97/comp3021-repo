public class TestStatic {
    public static void main (String[] args) {
        B b = new B();
        B.scream();
        B.shout();
        System.out.println(B.cga);
    }
}

class A {
    static double cga = 3.7; // Static field initializer
    static { cga = 4.0; }
    
    public static void shout() {
        System.out.println("SHOUT");
    }
}

class B extends A {

    public static void scream() {
        System.out.println("scream");
    }
}
