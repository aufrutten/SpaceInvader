package menu.gamepanels;

import javax.swing.*;

import java.awt.*;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class PlayingPanel extends JPanel {
    private Image playerSpaceShip;
    public PlayingPanel() {
        setLayout(null);
        setOpaque(false);
        setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
    }
}
