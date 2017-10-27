public class Fraction
{
  private int numerator;
  private int denominator;
  
  public static int gcd(final int a, final int b) {
    if(b == 0)
      return a;
    return gcd(b, a%b);
  }
  
  public Fraction() {
    setFraction(0, 1);
  }
  
  public Fraction(final int numerator, final int denominator) {
    setFraction(numerator, denominator);
  }
  
  public int getNumerator() {
    return numerator;
  }
  
  public int getDenominator() {
    return denominator;
  }
  
  public boolean getSign() {
    return numerator < 0;   
  }
  
  public void setFraction(final int n, final int d) {
    if(d == 0) {
      System.out.println("Error! Denominator cannot be zero.");
      setFraction(1, 1);
      return;      
    }
    int sign = ((n<0?1:0) + (d<0?1:0))%2;
    int normal = gcd(Math.abs(n), Math.abs(d));
    numerator = Math.abs(n) / normal*(sign==0?1:-1);
    denominator = Math.abs(d) / normal;
  }
  
  public Fraction add(Fraction f) {
    return new Fraction(numerator * f.denominator + f.numerator * denominator, denominator * f.denominator);
  }

  public Fraction subtract(Fraction f) {
    return new Fraction(numerator * f.denominator - f.numerator * denominator, denominator * f.denominator);
  }
  
  public Fraction multiply(Fraction f) {
    return new Fraction(numerator * f.numerator, denominator * f.denominator);
  }
  
  public Fraction divide(Fraction f) {
    return new Fraction(numerator * f.denominator, f.numerator * denominator);
  }
  
  @Override
  public boolean equals(Object o) {
    Fraction f = (Fraction)o;
    return (numerator==f.numerator)&&(denominator==f.denominator);
  }
    
  public void print() {
    if(denominator==1)
      System.out.print("(" + numerator + ")");
    System.out.print("(" + numerator + "/" + denominator + ")");
  }
}