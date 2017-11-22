public class StringTest {
    public static void main (String[] args) {
        String a = "haha";
        String b = "haha";
        String c  = new String("haha");

        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(b==c);
        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
    }
}
