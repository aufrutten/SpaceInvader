package menu;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

import fileio.MyIO;
import fileio.Score;
import menu.gamepanels.HomePanel;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class MainFrame {
    public static String playerName;
    public static ArrayList<Score> scores = new ArrayList<>();
    public static JLayeredPane layeredPane;
    public static JFrame frame;

    public static void main(String[] args) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("Space Invader");
        frame.setLayout(null);
        frame.pack();
        frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2);
        frame.setIconImage(new ImageIcon("./Sprite/Icon.png").getImage());

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(new Rectangle(PANEL_WIDTH, PANEL_HEIGHT));
        layeredPane.setFocusable(true);


        layeredPane.add(new ScrollingImagesPanel(), Integer.valueOf(1));

        scores = MyIO.readSerializerRecord("scores.bin");
        scores.sort(new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return Integer.compare(o2.getScore(), o1.getScore());
            }
        });
        layeredPane.add(new HomePanel(), Integer.valueOf(2));

        frame.add(layeredPane);
        frame.setVisible(true);
    }
}