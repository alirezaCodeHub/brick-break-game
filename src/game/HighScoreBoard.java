package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class HighScoreBoard extends JPanel{
    JButton rtnMenuButton = new JButton("Return");

    public HighScoreBoard() throws FileNotFoundException {
        initBoard();
    }

    private void initBoard() {
        setFocusable(true);
        setPreferredSize(new Dimension(Configurations.WIDTH, Configurations.HEIGHT));
        setBackground(new Color(90, 28, 114));
        setLayout(null);
        rtnMenuButton.setBounds(10, 10, 75, 20);
        add(rtnMenuButton);
        returnHandler rtnHandler = new returnHandler();
        rtnMenuButton.addActionListener(rtnHandler);
    }

    //Click Listener for High score button
    private class returnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setLayout(new BorderLayout());
            revalidate();
            repaint();
            MenuScreen menu = new MenuScreen();
            add(menu);
            menu.requestFocusInWindow();
            remove(rtnMenuButton);

        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        var g2d = (Graphics2D) g;

        try {
            drawScores(g2d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawScores(Graphics2D g2d) throws IOException {
        HashMap<String,Integer> map =new HashMap<String,Integer>();
        BufferedReader csvReader = new BufferedReader(new FileReader("src/Scores.csv"));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            System.out.println(Arrays.toString(data));
            map.put(data[0], Integer.valueOf(data[1]));
        }
        csvReader.close();
        java.util.List<String> BestPlayersKeys = map.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(5).map(Map.Entry::getKey).toList();
        int y =200;
        if(BestPlayersKeys.size() == 0){
            var font = new Font("Verdana", Font.BOLD, 75);
            g2d.setColor(Color.BLACK);
            g2d.setFont(font);
            g2d.drawString("No Scores yet!", 40, y);
        }else {
            var font = new Font("Verdana", Font.BOLD, 25);
            FontMetrics fontMetrics = this.getFontMetrics(font);

            g2d.setColor(Color.RED);
            g2d.setFont(font);
            g2d.drawString("Top 5 scores",
                    (Configurations.WIDTH - fontMetrics.stringWidth("Top 5 scores")) / 2,
                    50);

            var font2 = new Font("SansSerif Bold Italic", Font.BOLD, 20);
            g2d.setColor(Color.BLACK);
            g2d.setFont(font2);
            for (int i = 1; i <= BestPlayersKeys.size(); i++) {
                g2d.drawString("#" + (i) + ": " +BestPlayersKeys.get(i - 1)+" - " +map.get(BestPlayersKeys.get(i - 1)), 40, y);
                y += 30;
            }
        }


    }
}
