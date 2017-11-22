
public class ExceptionTest {
    private int i,j;
    public void foo()  {
        try { this.i=this.i/this.j; }
        catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        ExceptionTest a = new ExceptionTest();
        a.foo();
        final double Pi = 2;
    }
}
