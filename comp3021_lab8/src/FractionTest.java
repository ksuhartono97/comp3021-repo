import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class FractionTest extends TestCase {
    Fraction f1,f2,f3,f4;
    ByteArrayOutputStream outContent;

    public FractionTest(String method) {
        super(method);
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }
    @AfterClass
    public static void tearDownBeforeClass() throws Exception {

    }

    @Before
    public void setUp() throws Exception {
        f1 = new Fraction(1, 2);
        f2 = new Fraction(3, 4);
        f3 = new Fraction(-5, 4);
        f4 = new Fraction(10, -3);

    }

    @After
    public void tearDown() throws Exception {
        f1 = f2 = f3 = f4 = null;
    }

    @Test
    public void testGcd() {
        assertEquals(6, Fraction.gcd(54, 24));
        assertEquals(14, Fraction.gcd(42, 56));
    }

    @Test
    public void testGetNumerator() {
        assertEquals(1, f1.getNumerator());
        assertEquals(3, f2.getNumerator());
        assertEquals(-5,f3.getNumerator());
        assertEquals(-10, f4.getNumerator() );
    }

    @Test
    public void testGetDenominator() {
        assertEquals(2,f1.getDenominator());
        assertEquals(4,f2.getDenominator());
        assertEquals( 4,f3.getDenominator());
        assertEquals(3, f4.getDenominator());
    }

    @Test
    public void testGetSign() {
        assertFalse(f1.getSign());
        assertFalse(f2.getSign());
        assertTrue(f3.getSign());
        assertTrue(f4.getSign());
    }

    @Test
    public void testAdd() {
        assertEquals(new Fraction(5,4), f1.add(f2) );
        assertEquals(new Fraction(-55,12), f3.add(f4) );
    }

    @Test
    public void testSubtraction() {
        assertEquals(new Fraction(-1,4), f1.subtract(f2) );
        assertEquals(new Fraction(25,12), f3.subtract(f4) );
    }
    @Test
    public void testMultiply() {
        assertEquals(new Fraction(3,8), f1.multiply(f2) );
        assertEquals(new Fraction(25,6), f3.multiply(f4) );
    }
    @Test
    public void testDivide() {
        assertEquals(new Fraction(2,3), f1.divide(f2) );
        assertEquals(new Fraction(3,8), f3.divide(f4) );
    }
    @Test
    public void testEquals() {
       assertTrue(f1.equals(new Fraction(1,2)));
       assertFalse(f1.equals(f2));
    }
    @Test
    public void testPrint() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        f1.print();
        assertEquals("(1/2)", outContent.toString());
        System.setOut(null);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        f2.print();
        assertEquals("(3/4)", outContent.toString());
        System.setOut(null);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        f3.print();
        assertEquals("(-5/4)", outContent.toString());
        System.setOut(null);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        f4.print();
        assertEquals("(-10/3)", outContent.toString());
        System.setOut(null);
    }

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new FractionTest("testGcd"));
        suite.addTest(new FractionTest("testGetNumerator"));
        suite.addTest(new FractionTest("testGetDenominator"));
        suite.addTest(new FractionTest("testGetSign"));
        suite.addTest(new FractionTest("testAdd"));
        suite.addTest(new FractionTest("testSubtraction"));
        suite.addTest(new FractionTest("testMultiply"));
        suite.addTest(new FractionTest("testDivide"));
        suite.addTest(new FractionTest("testEquals"));
        suite.addTest(new FractionTest("testPrint"));

        return suite;
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}