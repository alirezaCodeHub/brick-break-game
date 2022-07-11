package game;

import javax.swing.ImageIcon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Ball extends Sprite {

    private double xdir;
    private double ydir;

    public Ball() throws IOException {

        initBall();
    }

    private void initBall() throws IOException {
        //OFC player have more fun with random Starting move
        List<Integer> nums = Arrays.asList(-2,1,-1,2);
        xdir = nums.get(new Random().nextInt(nums.size())); //1
        ydir = nums.get(new Random().nextInt(nums.size())); //-2

        loadImage();
        getImageDimensions();
        resetState();
    }

    private void loadImage() throws IOException {
        FileReader fr = new FileReader("src/ballColor.txt");
        BufferedReader br = new BufferedReader(fr);
        String color = br.readLine();
        ImageIcon ii = null;
        if(Objects.equals(color, "black")) {
             ii = new ImageIcon("src/game/images/ball.png");
        } else {
             ii = new ImageIcon("src/game/images/ball-Blue.png");
        }
        image = ii.getImage();
    }

    void move() {

        x += xdir;
        y += ydir;


        if(x >= (Configurations.WIDTH)){
            xdir*=-1;
            setXDir(xdir);
        }
        else if(x <= 0){
            xdir *= -1;
            x = xdir;
        }
        else if(y <= 0){
            ydir *= -1;
            y = ydir;
        }
    }

    private void resetState() {

        x = Configurations.INIT_BALL_X;
        y = Configurations.INIT_BALL_Y;
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
}