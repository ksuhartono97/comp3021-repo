public class TestGraphicsCanvas
{
  public static void main(String[] args)
  {
    System.out.println("Test Circle class and GraphicsCanvas Class");
    System.out.println("==========================================");
    
    Circle circle1 = new Circle(2, 4, 6);
    Circle circle2 = new Circle(3, 7, 8);
    System.out.println("Circle1: " + circle1);
    System.out.println("Circle2: " + circle2);
    
    GraphicsCanvas<Circle> circleGC = new GraphicsCanvas<>();    
    circleGC.add(circle1);
    circleGC.add(circle2);
    
    GraphicsCanvasUtilities.print(circleGC);
    GraphicsCanvasUtilities.show(circleGC);
    GraphicsCanvasUtilities.draw(circleGC);
    
    int compCircleResult = circle1.compareTo(circle2);
    if(compCircleResult > 0)
      System.out.println("Circle1 is bigger than Circle2");
    else if(compCircleResult < 0)
      System.out.println("Circle1 is smaller than Circle2");
    else
      System.out.println("Circle1 is same size as Cirlc2");
    
    System.out.println();

    System.out.println("Test Rectangle class and GraphicsCanvas Class");
    System.out.println("=============================================");
    
    Rectangle rectangle1 = new Rectangle(12, 10, 5, 3);
    Rectangle rectangle2 = new Rectangle(1, 7, 42, 12);
    System.out.println("Rectangle1: " + rectangle1);
    System.out.println("Rectangle2: " + rectangle2);
    
    GraphicsCanvas<Rectangle> rectangleGC = new GraphicsCanvas<>();
    rectangleGC.add(rectangle1);
    rectangleGC.add(rectangle2);
    
    int compRectangleResult = rectangle1.compareTo(rectangle2);
    if(compRectangleResult > 0)
      System.out.println("Rectangle1 is bigger than Rectangle2");
    else if(compRectangleResult < 0)
      System.out.println("Rectangle1 is smaller than Rectangle2");
    else
      System.out.println("Rectangle1 is same size as Rectangle2");    
    
    GraphicsCanvasUtilities.print(rectangleGC);
    GraphicsCanvasUtilities.show(rectangleGC);
    GraphicsCanvasUtilities.draw(rectangleGC);
  }
}