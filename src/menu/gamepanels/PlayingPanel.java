// /mnt/data/PlayingPanel.java
package menu.gamepanels;

import menu.MainFrame;
import menu.ScrollingImagesPanel;
import units.Alien;
import units.Bullet;
import units.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class PlayingPanel extends JPanel implements ActionListener {
    private final Player player;
    private final Timer timer;

    public PlayingPanel() {
        setFocusable(true);
        setLayout(null);
        setBounds(new Rectangle(PANEL_WIDTH, PANEL_HEIGHT));
        setOpaque(false);

        // Associazioni per i tasti di movimento
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed D"), "rightActionPressed");
        getActionMap().put("rightActionPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.startMovingRight();
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released D"), "rightActionReleased");
        getActionMap().put("rightActionReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.stopMovingRight();
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed A"), "leftActionPressed");
        getActionMap().put("leftActionPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.startMovingLeft();
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released A"), "leftActionReleased");
        getActionMap().put("leftActionReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.stopMovingLeft();
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "fireAction");
        getActionMap().put("fireAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.fire();
            }
        });

        Alien.spawnAliens(5);
        player = new Player();

        // Incrementa la frequenza di aggiornamento del timer
        timer = new Timer(5, this); // Aggiorna ogni 5 ms
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        Alien.drawAliens(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        List<Bullet> bullets = player.getBullets();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < Alien.getAliens().size(); j++) {
                Alien alien = Alien.getAliens().get(j);
                if (bullet.getBounds().intersects(alien.getBounds())) {
                    bullets.remove(i);
                    i--;
                    Alien.getAliens().remove(j);
                    j--;
                    break;
                }
            }
        }
        if (player.checkCollision() || Alien.borderCollision()) {
            ScrollingImagesPanel.timer.stop();
            ScrollingImagesPanel.timer.removeActionListener(ScrollingImagesPanel.timer.getActionListeners()[0]);
            Alien.removeAliens();
            timer.stop();
            this.removeAll();
            MainFrame.layeredPane.removeAll();
            MainFrame.layeredPane.revalidate();
            MainFrame.layeredPane.add(new ScrollingImagesPanel(), Integer.valueOf(1));
            MainFrame.layeredPane.add(new LosePanel(), Integer.valueOf(2));
            MainFrame.layeredPane.repaint();
            return;
        }
        repaint();
    }
}
