// TODO 7:
// - Define a GraphicsCanvasUtilities class, which contains three static generic methods as described below.
// - A generic static method print, which accepts a parameter of type GraphicsCanvas bounded to classes that implements Printable
//   It invokes print method for EACH object in the GraphicsCanvas
// - A generic static method show, which accepts a parameter of type GraphicsCanvas bounded to classes that implements Showable
//   It invokes show method for EACH object in the GraphicsCanvas
// - A generic static method draw, which accepts a parameter of type GraphicsCanvas bounded to classes that implements Drawable
//   It invokes draw method for EACH object in the GraphicsCanvas

/* Define GraphicsCanvasUtilities class here */

class GraphicsCanvasUtilities {
  public static void print(GraphicsCanvas<? extends Printable> gc) {
    for(int i=0; i<gc.size(); i++)
      gc.get(i).print();
  }
  
  public static void show(GraphicsCanvas<? extends Showable> gc) {
    for(int i=0; i<gc.size(); i++)
      gc.get(i).show();
  }

  public static void draw(GraphicsCanvas<? extends Drawable> gc) {
    for(int i=0; i<gc.size(); i++)
      gc.get(i).draw();
  }
}