package menu.gamepanels;

import menu.ImageLoader;
import menu.MainFrame;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class LosePanel extends JPanel {
    public LosePanel() {
        BufferedImage retryImage = ImageLoader.loadImage("./Sprite/retry_button.png");
        BufferedImage menuImage = ImageLoader.loadImage("./Sprite/menu_button.png");
        BufferedImage gameOverImage = ImageLoader.loadImage("./Sprite/gameOver.png");

        setLayout(null);
        setFocusable(true);
        setBounds(new Rectangle(PANEL_WIDTH, PANEL_HEIGHT));
        setOpaque(false);

        JLabel titleLabel = new JLabel(new ImageIcon(gameOverImage));
        titleLabel.setBounds((PANEL_WIDTH - gameOverImage.getWidth()) / 2, 20, gameOverImage.getWidth(), gameOverImage.getHeight());
        add(titleLabel);

        JButton retryButton = new JButton(new ImageIcon(retryImage));
        retryButton.setBounds((PANEL_WIDTH - retryImage.getWidth()) / 2, 350, retryImage.getWidth(), retryImage.getHeight());
        retryButton.setBorderPainted(false);
        retryButton.setContentAreaFilled(false);
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == retryButton) {
                    MainFrame.layeredPane.remove(retryButton.getParent());
                    MainFrame.layeredPane.add(new PlayingPanel(), Integer.valueOf(2));
                    MainFrame.layeredPane.repaint();
                }
            }
        });
        add(retryButton);

        JButton menuButton = new JButton(new ImageIcon(menuImage));
        menuButton.setBounds((PANEL_WIDTH - menuImage.getWidth()) / 2, 470, menuImage.getWidth(), menuImage.getHeight());
        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == menuButton) {
                    MainFrame.layeredPane.remove(menuButton.getParent());
                    MainFrame.layeredPane.add(new HomePanel(), Integer.valueOf(2));
                    MainFrame.layeredPane.repaint();
                }
            }
        });
        add(menuButton);
    }
}
