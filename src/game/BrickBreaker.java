package game;

import javax.swing.*;
import java.awt.EventQueue;
import java.io.IOException;

public class BrickBreaker extends JFrame {

    public BrickBreaker() throws IOException {

        initUI();
    }

    private void initUI() throws IOException {

        add(new MenuScreen());
        setTitle("java Brick breaker");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        //It must be done after the pack() that the frame is in the center of the page
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            BrickBreaker game = null;
            try {
                game = new BrickBreaker();
            } catch (IOException e) {
                e.printStackTrace();
            }
            game.setVisible(true);
        });
    }
}