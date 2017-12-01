package terrain;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import pa1.GameApplication;

// TODO: Also setup the Images for Water Animation.
// Use "static {}" to initialize static fields.
// You may change ANIM_TIME_PER_FRAME to your own preference.
// Refer to Lab 12.

public class Water extends Terrain {
    private static final Image IMAGE_WATER;
    public static final int NUM_ANIM_FRAMES;
    private static final Image[] ANIM_FRAMES = new Image[4];
    static
    {
        NUM_ANIM_FRAMES = 4;
        ANIM_FRAMES[0] = new Image("terrain_images/water1.png",  GameApplication.TILE_WIDTH, GameApplication.TILE_HEIGHT, true, true);
        ANIM_FRAMES[1] = new Image("terrain_images/water2.png",  GameApplication.TILE_WIDTH, GameApplication.TILE_HEIGHT, true, true);
        ANIM_FRAMES[2] = new Image("terrain_images/water3.png",  GameApplication.TILE_WIDTH, GameApplication.TILE_HEIGHT, true, true);
        ANIM_FRAMES[3] =  new Image("terrain_images/water4.png",  GameApplication.TILE_WIDTH, GameApplication.TILE_HEIGHT, true, true);
        IMAGE_WATER = ANIM_FRAMES[0];
    }
    public static final int ANIM_TIME_PER_FRAME = 1000;

    public Water() {
        super(-1);
    }

    @Override
    public Image getImage() {
        return IMAGE_WATER;
    }

    public Image getAnimFrame(int index) {
        return ANIM_FRAMES[index];
    }
}