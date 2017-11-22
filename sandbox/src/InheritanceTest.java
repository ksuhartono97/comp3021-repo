public class InheritanceTest {
    public static void main (String[] args) {
        C c1 = new D();
        C c2 = new C();
        D d1 = new D();

//        c1.pocky(); error, private method in c
//        c2.pocky(); error private method in c
        d1.pocky();
    }
}

class C {
    private void pocky () {
        System.out.println("pocky in super");
    }
    public void pocko () {
        System.out.println("pocky in super");
    }
}

class D extends C {
//    @Override error method doesn't override due to private
    public void pocky () {
        System.out.println("pocky in sub");
    }
}
