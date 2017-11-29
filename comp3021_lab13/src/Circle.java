// TODO 4:
// - Define a Circle class, which implements Drawable, Showable and Comparable interface by
//   completing the given code below.
// - Note: You are required to add the annotation for checking the validity of method overriding.

class Circle implements Drawable, Printable, Showable, Comparable<Circle> {
  private double centerX;
  private double centerY;
  private double radius;
    
  public Circle(double centerX, double centerY, double radius) {
    this.centerX = centerX;
    this.centerY = centerY;
    this.radius = radius;
  }
  
  // Implement toString method such that it returns a string representing a circle object
  // Refer to the given output of program for the string format
  
  /* Add your code here */

  @Override
  public String toString() {
    return ("(centerX, centerY) = ("+ centerX + ", " + centerY + "), radius = " + radius);
  }
  // Implement draw method of Drawable interface such that it prints in according to
  // given output of program
  
  /* Add your code here */
  @Override
  public void draw() {
    System.out.println("Draw a circle of radius "+ radius + " at (" + centerX + ", "+ centerY + ")");
  }
  
  
  // Implement show method of Showable interface such that it prints in according to
  // given output of program
  
  /* Add your code here */
  @Override
  public void show() {
    System.out.println("Show a circle of radius "+ radius + " at (" + centerX + ", "+ centerY + ")");
  }
  
  
  // Implement print method of Printable interface such that it prints in according to
  // given output of program
  
  /* Add your code here */
  @Override
  public void print() {
    System.out.println("Print a circle of radius "+ radius + " at (" + centerX + ", "+ centerY + ")");
  }
    
  
  // Implement compareTo method of Comparable interface such that it compares two circle
  // objects based on their radius
  @Override
  public int compareTo (Circle c) {
    if (this.radius == c.radius) return 0;
    else return this.radius < c.radius ? -1:1;
  }

  
  /* Add your code here */  
}
