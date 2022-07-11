package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ThemeMenu extends JPanel {
    JButton rtnMenuButton = new JButton("Return");
    JButton button = new JButton("Choose a color");
    Color color;
    JRadioButton r1=new JRadioButton("blue ball");
    JRadioButton r2=new JRadioButton("black ball");
    public ThemeMenu() throws IOException {
        initMenu();
    }

    private void initMenu() throws IOException {
        setFocusable(true);
        setPreferredSize(new Dimension(Configurations.WIDTH, Configurations.HEIGHT));
        JPanel colorPanel = new JPanel();
        setLayout(null);
        rtnMenuButton.setBounds(10, 10, 75, 20);
        add(rtnMenuButton);
        add(colorPanel, BorderLayout.CENTER);
        button.setBounds(0, 100, Configurations.WIDTH, 50);
        add(button);
        setSize(425, 150);
        r1.setBounds(70,300,100,30);
        r2.setBounds(70,350,100,30);
        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);bg.add(r2);
        add(r1);add(r2);
        setVisible(true);
        r1.addActionListener(event ->{
            FileWriter fw = null;
            try {
                fw = new FileWriter("src/ballColor.txt");
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("blue");
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        r2.addActionListener(event ->{
            FileWriter fw = null;
            try {
                fw = new FileWriter("src/ballColor.txt");
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("black");
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        button.addActionListener(event -> {
            color = JColorChooser.showDialog(null, "Pick your color", color);
            if(color == null){
                color = (Color.WHITE);
            }
            EditBackgroundFile(color.toString());
            button.setBackground(color);
        });


        add(colorPanel, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
        setSize(425, 150);
        returnHandler rtnHandler = new returnHandler();
        rtnMenuButton.addActionListener(rtnHandler);

    }

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
            remove(button);
            remove(r1);
            remove(r2);
        }
    }

    private void EditBackgroundFile(String colorChosen) {
        FileWriter fw;
        try {
            fw = new FileWriter("src/BackGroundColor.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(colorChosen);
            bw.close();
            fw.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        var g2d = (Graphics2D) g;

        try {
            drawText(g2d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawText(Graphics2D g2d) throws IOException {
        g2d.drawString("Choose background color below:", 70, 50);
        g2d.drawString("Current Background Color", 70, 80);
    }
}
