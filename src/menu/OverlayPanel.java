package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class OverlayPanel extends JPanel {
    public OverlayPanel(BufferedImage titleImage, BufferedImage bestScoreImage, BufferedImage startButtonImage, BufferedImage leaderboardButtonImage) {
        setLayout(null);
        setOpaque(false); // Rendi il pannello trasparente

        JLabel titleLabel = new JLabel(new ImageIcon(titleImage));
        titleLabel.setBounds((PANEL_WIDTH - titleImage.getWidth()) / 2, 150, titleImage.getWidth(), titleImage.getHeight());
        add(titleLabel);

        JLabel bestScoreLabel = new JLabel(new ImageIcon(bestScoreImage));
        bestScoreLabel.setBounds((PANEL_WIDTH - bestScoreImage.getWidth()) / 2, 250, bestScoreImage.getWidth(), bestScoreImage.getHeight());
        add(bestScoreLabel);

        JButton startButton = new JButton(new ImageIcon(startButtonImage));
        startButton.setBounds((PANEL_WIDTH - startButtonImage.getWidth()) / 2, 650, startButtonImage.getWidth(), startButtonImage.getHeight());
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.addActionListener(e -> System.out.println("start"));
        add(startButton);

        JButton leaderboardButton = new JButton(new ImageIcon(leaderboardButtonImage));
        leaderboardButton.setBounds((PANEL_WIDTH - leaderboardButtonImage.getWidth()) / 2, 750, leaderboardButtonImage.getWidth(), leaderboardButtonImage.getHeight());
        leaderboardButton.setBorderPainted(false);
        leaderboardButton.setContentAreaFilled(false);
        leaderboardButton.addActionListener(e -> System.out.println("leaderboard"));
        add(leaderboardButton);
    }
}
