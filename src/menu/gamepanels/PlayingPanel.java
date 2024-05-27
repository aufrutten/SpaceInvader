package menu.gamepanels;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class PlayingPanel extends JPanel implements KeyListener {
    private Image playerSkin;

    public PlayingPanel() {
        this.setBounds(new Rectangle(PANEL_WIDTH, PANEL_HEIGHT));
        this.setOpaque(false);
        playerSkin = new ImageIcon("./Sprite/player-skins/player.png").getImage();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playerSkin, (PANEL_WIDTH - playerSkin.getWidth(null)) / 2, PANEL_HEIGHT, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
