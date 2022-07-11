package game;

import javax.swing.ImageIcon;
import java.io.IOException;

public class airDropItem extends Sprite {

    private double xdir = 1;
    private double ydir = 1;
    private int item_speed = 1;

    public airDropItem(double x, double y) throws IOException {

        initItem(x, y);
    }

    private void initItem(double x, double y) throws IOException {

        this.x = x +20;
        this.y = y +10;


        loadImage();
        getImageDimensions();


    }

    private void loadImage() throws IOException {

        var ii = new ImageIcon("src/game/images/itemDrop.png");
        image = ii.getImage();
    }

    void move() {
        y += ydir;

    }

    void setXDir(double x) {

        xdir = x;
    }

    void setYDir(double y) {

        ydir = y;
    }

    double getYDir() {

        return ydir;
    }
    private void resetState() {

        x = Configurations.INIT_ITEM_X;
        y = Configurations.INIT_ITEM_Y;
    }
}