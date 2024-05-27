package menu.gamepanels;

import javax.swing.*;
import java.awt.*;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class LeaderBoardPanel extends JPanel {
    public LeaderBoardPanel() {
        setLayout(null);
        setOpaque(false);
        setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

        JLabel messageLabel = new JLabel("IMPLEMENTATION NEEDED");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.CENTER);
        messageLabel.setFont(messageLabel.getFont().deriveFont(20f));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        this.add(messageLabel);
    }
}
