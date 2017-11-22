import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MultithreadingAnimation extends Application {
    private Canvas canvas;                                    // JavaFX canvas for drawing
    private String[] files = new String[10];                  // List of image file strings
    private int no = 0;                                       // Index of image file

    private int x = 0;                                        // Initial x-coordinate of image
    private int y = 0;                                        // Initial y-coordinate of image
    private int width;                                        // Width of image
    private int height;                                       // Height of image

    private final int SPEED = 10;                             // Walking speed of the character
    private ObjectProperty<Image> image;                      // image (A Property wrapping an Image)

    /**
     * Constructor
     */
    public MultithreadingAnimation() {
        // TODO 1:
        // Initialize "files", "image", "width" and "height"
        // - Store a list of file strings in the array "files"
        //   (The list of file strings are "Walk1.png", "Walk2.png", ..., "Walk10.png")
        // - Get the width of the first image and store it in "width"
        // - Get the height of the first image and store it in "height"
        // - Construct a SimpleObjectProperty object with the first image and make it referenced by "image"
        this.files = new String [] {
                "./images/Walk1.png",
                "./images/Walk2.png",
                "./images/Walk3.png",
                "./images/Walk4.png",
                "./images/Walk5.png",
                "./images/Walk6.png",
                "./images/Walk7.png",
                "./images/Walk8.png",
                "./images/Walk9.png",
                "./images/Walk10.png"
        };
        Image tempImage = new Image(files[0]);
        this.width = (int)tempImage.getWidth();
        this.height = (int)tempImage.getHeight();
        this.image = new SimpleObjectProperty<Image>(tempImage);
    }

    /**
     * start method of GUI application
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Create a thread to work on the task as specified in run method using Lambda expression.
            Thread thread = new Thread(() -> {
                // Add a ChangeListener "changeImage" which will be notified whenever the image is changed.
                this.image.addListener(event -> changeImage(image.get()));

                // TODO 2:
                // Changing image indefinitely by constructing an infinite loop
                // In each iteration, do the followings:
                // - Set a new image (specified by files[no]) to "image" using set method of ObjectProperty
                // - Cause the current thread to suspend execution for 200ms
                // - Update the index of image file
                //   Increase "no" by 1 (Make sure that "no" is within 0 to 9)
                // - Update the x-coordinate of image
                //   Increase "x" by 10 (use the given constant: SPEED) (Make sure it is within 0 to width * 4 - 1)
                // Note: Static method sleep() will throw InterruptedException
                while (true) {
                    try {
                        this.image.set(new Image(this.files[no]));
                        Thread.sleep(200);
                        this.no = (this.no+1)%10;
                        this.x = (this.x + SPEED) % (width * 4);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
            thread.setDaemon(true);                               // Make the thread as a daemon
            thread.start();                                       // Start the thread

            BorderPane root = new BorderPane();                   // Create a BorderPane container and use it as root
            canvas = new Canvas();                                // Create a canvas for drawing
            Pane pane = new Pane();                               // Create a Pane container
            pane.getChildren().add(canvas);                       // Add the canvas object to the Pane container
            canvas.widthProperty().bind(pane.widthProperty());    // Unidirectional bind the width of canvas to width of pane
            canvas.heightProperty().bind(pane.heightProperty());  // Unidirectional bind the height of canvas to height of pane
            root.setCenter(pane);                                 // Put pane to the center of the BorderPane

            Scene scene = new Scene(root, width * 5, height);     // Create a scene with the BorderPane as root and of size (width*5, height)
            primaryStage.setScene(scene);                         // Set the scene of stage
            primaryStage.setTitle("Multithreading Animation");    // Set the title of stage
            primaryStage.show();                                  // Show the stage
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method to be called when the image is changed
     */
    public void changeImage(Image img) {
        // TODO 3:
        // - Run the specified Runnable on the JavaFX Application Thread
        //   The run method should do the followings:
        //   - Get GraphicsContext of canvas and reference it by gc
        //   - Clear the rectangular portion specified by (0, 0, width*5, height) using clearRect of gc
        //   - Draw img into the destination rectangle (x, y, width, height) using drawImage of gc
        // Hint: Refer to the example on P.18 of the "Multithreading" lecture notes on how to
        //       run the Runnable on the JavaFX Application Thread

       new Thread(new Runnable() {
           @Override
           public void run() {
//               System.out.println("boo");
               try{
                   GraphicsContext gc = canvas.getGraphicsContext2D();
                   gc.clearRect(0, 0, width * 5, height);
                   gc.drawImage(img, x, y, width, height);
               }
               catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }).start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}