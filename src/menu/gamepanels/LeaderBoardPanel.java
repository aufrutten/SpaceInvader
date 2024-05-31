package menu.gamepanels;

import fileio.Score;
import menu.MainFrame;

import javax.swing.*;
import java.awt.*;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class LeaderBoardPanel extends JPanel {
    public LeaderBoardPanel() {
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
    }
}
