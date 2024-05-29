package menu.gamepanels;

import menu.ImageLoader;
import menu.MainFrame;
import menu.ScrollingImagesPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class HomePanel extends JPanel {
    public HomePanel() {
        BufferedImage titleImage = ImageLoader.loadImage("./Sprite/title.png");
        BufferedImage bestScoreImage = ImageLoader.loadImage("./Sprite/best_score.png");
        BufferedImage startButtonImage = ImageLoader.loadImage("./Sprite/start_button.png");
        BufferedImage leaderboardButtonImage = ImageLoader.loadImage("./Sprite/leaderboard_button.png");

        setLayout(null);
        setOpaque(false); // Rendi il pannello trasparente
        setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setFocusable(true);

        JLabel titleLabel = new JLabel(new ImageIcon(titleImage));
        titleLabel.setBounds((PANEL_WIDTH - titleImage.getWidth()) / 2, 150, titleImage.getWidth(), titleImage.getHeight());
        add(titleLabel);

        JLabel bestScoreLabel = new JLabel(new ImageIcon(bestScoreImage));
        bestScoreLabel.setBounds((PANEL_WIDTH - bestScoreImage.getWidth()) / 2, 250, bestScoreImage.getWidth(), bestScoreImage.getHeight());
        add(bestScoreLabel);

        JButton startButton = new JButton(new ImageIcon(startButtonImage));
        startButton.setBounds((PANEL_WIDTH - startButtonImage.getWidth()) / 2, 450, startButtonImage.getWidth(), startButtonImage.getHeight());
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == startButton) {
                    MainFrame.layeredPane.remove(startButton.getParent());
                    MainFrame.layeredPane.add(new PlayingPanel(), Integer.valueOf(2));
                    MainFrame.layeredPane.repaint();
                }
            }
        });
        add(startButton);

        JButton leaderboardButton = new JButton(new ImageIcon(leaderboardButtonImage));
        leaderboardButton.setBounds((PANEL_WIDTH - leaderboardButtonImage.getWidth()) / 2, 550, leaderboardButtonImage.getWidth(), leaderboardButtonImage.getHeight());
        leaderboardButton.setBorderPainted(false);
        leaderboardButton.setContentAreaFilled(false);
        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.layeredPane.remove(leaderboardButton.getParent());
                MainFrame.layeredPane.add(new ScrollingImagesPanel(), Integer.valueOf(2));
                MainFrame.layeredPane.repaint();
            }
        });
        add(leaderboardButton);
    }
}
