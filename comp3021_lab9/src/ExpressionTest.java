import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExpressionTest {
    Parser parser = new Parser();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testParser() throws IOException {
        assertEquals("100013", parser.parse("\t  \t  +100013\t\t ").toString());
        assertEquals("-123", parser.parse("\t  \t-123  \t").toString());
        assertEquals("(+ (- (* (/ 100 -2) 3) 4) 5)", parser.parse("(+ 	(- (* 	(/ 100 -2) 3) 4)     5)").toString());
        assertEquals(new BigInteger("21"), parser.parse("(+ (- (* 1 2) (/ 10 2)) 24)").eval());
    }

    @Test
    public void testAdd() throws IOException {
        assertEquals(new BigInteger("100"), parser.parse("(+ 100)").eval());
        assertEquals(new BigInteger("6"), parser.parse("(+ 1 2 3)").eval());
        assertEquals(new BigInteger("-10"), parser.parse("(+ 10 -20)").eval());
    }

    @Test
    public void testSub() throws IOException {
        assertEquals(new BigInteger("-11"), parser.parse("(- 11)").eval());
        assertEquals(new BigInteger("78"), parser.parse("(- 100 22)").eval());
        assertEquals(new BigInteger("7"), parser.parse("(- 10 2 -4 5)").eval());
    }

    @Test
    public void testMult() throws IOException {
        assertEquals(new BigInteger("10"), parser.parse("(* 10)").eval());
        assertEquals(new BigInteger("12"), parser.parse("(* 3 4)").eval());
        assertEquals(new BigInteger("-60"), parser.parse("(* 3 -4 5)").eval());
    }

    @Test
    public void testDiv() throws IOException {
        assertEquals(new BigInteger("3"), parser.parse("(/ 10 3)").eval());
        assertEquals(BigInteger.ONE, parser.parse("(/ 1)").eval());
        assertEquals(BigInteger.ZERO, parser.parse("(/ 2)").eval());
        assertEquals(new BigInteger("-5"), parser.parse("(/ 100 -2 10)").eval());
    }

    @Test(expected = ArithmeticException.class)
    public void testDivByZero() throws IOException {
        parser.parse("(/ 100 -2 0 3)").eval();
    }

    @Test
    public void testExp() throws IOException {
        assertEquals(new BigInteger("1024"), parser.parse("(^ 2 10)").eval());
        assertEquals(new BigInteger("81"), parser.parse("(^ -3 4)").eval());
    }
}
