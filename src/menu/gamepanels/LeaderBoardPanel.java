package menu.gamepanels;

import fileio.Score;
import menu.ImageLoader;
import menu.MainFrame;
import menu.ScrollingImagesPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class LeaderBoardPanel extends JPanel {
    public LeaderBoardPanel() {
        BufferedImage menuImage = ImageLoader.loadImage("./Sprite/menu_button.png");

        setLayout(null);
        setOpaque(false);
        setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

        JLabel leaderboardLabel = new JLabel("Leaderboard");
        leaderboardLabel.setHorizontalAlignment(JLabel.CENTER);
        leaderboardLabel.setVerticalAlignment(JLabel.CENTER);
        leaderboardLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 35));
        leaderboardLabel.setForeground(new Color(229, 184, 11));
        leaderboardLabel.setBounds(0, 175, PANEL_WIDTH, 50);
        add(leaderboardLabel);

        JLabel messageLabel = new JLabel();
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.CENTER);
        messageLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        messageLabel.setBorder(BorderFactory.createLineBorder(new Color(229, 184, 11), 3));
        messageLabel.setForeground(new Color(229, 184, 11));
        messageLabel.setBackground(new Color(31, 40, 45));
        messageLabel.setOpaque(true);
        messageLabel.setBounds((PANEL_WIDTH - PANEL_WIDTH / 2) / 2, (PANEL_HEIGHT - PANEL_HEIGHT / 3) / 2, PANEL_WIDTH / 2, PANEL_HEIGHT / 3);
        if(!MainFrame.scores.isEmpty()) {
            String standings = "<html>";
            for(Score score : MainFrame.scores) {
                standings += score.toString() + "\n";
            }
            standings += "</html>";
            messageLabel.setText(standings);
        }
        add(messageLabel);
        JButton menuButton = new JButton(new ImageIcon(menuImage));
        menuButton.setBounds(30, 30, menuImage.getWidth(), menuImage.getHeight());
        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == menuButton) {
                    ScrollingImagesPanel.timer.stop();
                    ScrollingImagesPanel.timer.removeActionListener(ScrollingImagesPanel.timer.getActionListeners()[0]);
                    MainFrame.layeredPane.removeAll();
                    MainFrame.layeredPane.add(new ScrollingImagesPanel(), Integer.valueOf(1));
                    MainFrame.layeredPane.add(new HomePanel(), Integer.valueOf(2));
                    MainFrame.layeredPane.revalidate();
                    MainFrame.layeredPane.repaint();
                }
            }
        });
        add(menuButton);
    }

}
