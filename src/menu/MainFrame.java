package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Scrolling Images");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1000);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(600, 1000));

        String projectDir = System.getProperty("user.dir");

        // Carica le immagini
        BufferedImage image1 = ImageLoader.loadImage(projectDir + "/Sprite/backgroud/backgroundFrame1.png");
        BufferedImage image2 = ImageLoader.loadImage(projectDir + "/Sprite/backgroud/backgroundFrame2.png");
        BufferedImage image3 = ImageLoader.loadImage(projectDir + "/Sprite/backgroud/backgroundFrame3.png");
        BufferedImage titleImage = ImageLoader.loadImage(projectDir + "/Sprite/title.png");
        BufferedImage bestScoreImage = ImageLoader.loadImage(projectDir + "/Sprite/best_score.png");
        BufferedImage startButtonImage = ImageLoader.loadImage(projectDir + "/Sprite/start_button.png");
        BufferedImage leaderboardButtonImage = ImageLoader.loadImage(projectDir + "/Sprite/leaderboard_button.png");

        // Crea il pannello di scrolling
        ScrollingImagesPanel scrollingPanel = new ScrollingImagesPanel(image1, image2, image3);
        scrollingPanel.setBounds(0, 0, 600, 1000);
        layeredPane.add(scrollingPanel, Integer.valueOf(1));

        // Crea il pannello di overlay
        OverlayPanel overlayPanel = new OverlayPanel(titleImage, bestScoreImage, startButtonImage, leaderboardButtonImage);
        overlayPanel.setBounds(0, 0, 600, 1000);
        layeredPane.add(overlayPanel, Integer.valueOf(2));

        frame.add(layeredPane);
        frame.pack();
        frame.setVisible(true);
    }
}
