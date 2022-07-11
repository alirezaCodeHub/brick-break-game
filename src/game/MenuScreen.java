package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuScreen extends JPanel {
    JButton StartButton = new JButton("Start Game");
    JButton HSButton = new JButton("High Scores");
    JButton ThemeButton = new JButton("Change Theme");
    boolean isCanceled = false;

    public MenuScreen(){
        initMenu();
    }

    private void initMenu() {
        setFocusable(true);
        setPreferredSize(new Dimension(Configurations.WIDTH, Configurations.HEIGHT));

        setLayout(null);
        StartButton.setBounds((Configurations.WIDTH - 120) / 2, 100, 120, 60);
        HSButton.setBounds((Configurations.WIDTH - 300) / 2, 180, 120, 40);
        ThemeButton.setBounds((Configurations.WIDTH + 60) / 2, 180, 120, 40);
        setBackground(new Color(90, 28, 114));
        add(StartButton);
        add(HSButton);
        add(ThemeButton);
        StartHandler stHandler = new StartHandler();
        StartButton.addActionListener(stHandler);
        HSHandler hsHandler = new HSHandler();
        HSButton.addActionListener(hsHandler);
        ThemeHandler THandler = new ThemeHandler();
        ThemeButton.addActionListener(THandler);
    }

    //Click listener for Start Button
    private class StartHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                while (reqVar.name.length() == 0 || reqVar.name.length() > 35) {
                    String tempInputMemory = JOptionPane.showInputDialog("Enter your name: (maximum 35 char)");
                    if (tempInputMemory == null) {
                        isCanceled = true;
                        break;
                    }
                    else if(tempInputMemory.equals(""))
                        JOptionPane.showConfirmDialog(null,
                                "input canNot be empty type a name","wrong input",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
                    else
                        reqVar.name = tempInputMemory;
                }
                if (!isCanceled) {
                    setLayout(new FlowLayout());
                    revalidate();
                    repaint();
                    GameBoard gameBoard = new GameBoard();
                    add(gameBoard);
                    gameBoard.requestFocusInWindow();
                    remove(HSButton);
                    remove(StartButton);
                    remove(ThemeButton);
                    }
                } catch(IOException ex){
                    ex.printStackTrace();

            }
        }
    }

    //Click Listener for High score button
    private class HSHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                setLayout(new BorderLayout());
                revalidate();
                repaint();
                HighScoreBoard hsBoard = new HighScoreBoard();
                add(hsBoard);
                hsBoard.requestFocusInWindow();
                setBackground(Color.white);
                remove(HSButton);
                remove(StartButton);
                remove(ThemeButton);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

        }
    }

    //Click Listener for High score button
    private class ThemeHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                setLayout(new BorderLayout());
                revalidate();
                repaint();
                ThemeMenu TMenu = new ThemeMenu();
                add(TMenu);
                TMenu.requestFocusInWindow();
                remove(HSButton);
                remove(StartButton);
                remove(ThemeButton);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        var g2d = (Graphics2D) g;

        drawTitle(g2d);
    }

    private void drawTitle(Graphics2D g2d) {
        var font = new Font("Serif", Font.ITALIC, 50);
        FontMetrics fontMetrics = this.getFontMetrics(font);
        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        g2d.drawString( Configurations.WelcomeMassageOnMenu,
                ((Configurations.WIDTH - fontMetrics.stringWidth(Configurations.WelcomeMassageOnMenu)) / 2),
                62);
    }}
