// TODO 5:
// - Define a Rectangle class, which implements Drawable, Showable and Comparable interface by
//   completing the given code below.
// - Note: You are required to add the annotation for checking the validity of method overriding.

class Rectangle implements Drawable, Printable, Showable, Comparable<Rectangle>/* Complete this statement so as Rectangle implements all the required interfaces */ {
  private double x;
  private double y;
  private double width;
  private double height;  

  public Rectangle(double x, double y, double width, double height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
  
  // Implement toString method such that it returns a string representing a rectangle object
  // Refer to the given output of program for the string format
  
  /* Add your code here */

  @Override
  public String toString() {
    return ("(x, y) = ("+ x + ", " + y + "), width = " + width + ", height = " + height);
  }
  // Implement draw method of Drawable interface such that it prints in according to
  // given output of program
  
  /* Add your code here */
  @Override
  public void draw() {
    System.out.println("Draw a rectangle of size (" + width + ", " + height + ") at (" + x + ", " + y+ ")");
  }
  
  
  // Implement show method of Showable interface such that it prints in according to
  // given output of program
  
  /* Add your code here */

  @Override
  public void show() {
    System.out.println("Show a rectangle of size (" + width + ", " + height + ") at (" + x + ", " + y+ ")");
  }
  
  // Implement print method of Printable interface such that it prints in according to
  // given output of program
  
  /* Add your code here */
  @Override
  public void print() {
    System.out.println("Print a rectangle of size (" + width + ", " + height + ") at (" + x + ", " + y+ ")");
  }
    
  
  // Implement compareTo method of Comparable interface such that it compares two rectangle
  // objects based on their area
  
  /* Add your code here */

  @Override
  public int compareTo(Rectangle o) {
    if ((this.width*this.height) == (o.width*o.height)) return 0;
    else return (this.width*this.height) < (o.width*o.height) ? -1:1;
  }
}
