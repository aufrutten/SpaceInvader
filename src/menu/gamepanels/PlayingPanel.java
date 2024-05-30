package menu.gamepanels;

import menu.MainFrame;
import menu.ScrollingImagesPanel;
import units.Alien;
import units.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class PlayingPanel extends JPanel implements ActionListener {
    private final Player player;
    private final Action rightAction;
    private final Action leftAction;
    private final Timer timer;

    public PlayingPanel() {
        setFocusable(true);
        setLayout(null);
        setBounds(new Rectangle(PANEL_WIDTH, PANEL_HEIGHT));
        setOpaque(false);
        rightAction = new RightAction();
        leftAction = new LeftAction();
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        getActionMap().put("rightAction", rightAction);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        getActionMap().put("leftAction", leftAction);
        Alien.spawnAliens(5);
        player = new Player();
        timer = new Timer(20, this);
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
        if(player.checkCollision() || Alien.borderCollision()) {
            ScrollingImagesPanel.timer.stop();
            ScrollingImagesPanel.timer.removeActionListener(ScrollingImagesPanel.timer.getActionListeners()[0]);
            Alien.removeAliens();
            timer.stop();
            this.removeAll();
            MainFrame.layeredPane.removeAll();
            MainFrame.layeredPane.revalidate();
            MainFrame.layeredPane.add(new ScrollingImagesPanel(), Integer.valueOf(1));
            MainFrame.layeredPane.add(new HomePanel(), Integer.valueOf(2));
            MainFrame.layeredPane.repaint();
            return;
        }
        repaint();
    }

    public class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            player.moveRight();
            repaint();
        }
    }

    public class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            player.moveLeft();
            repaint();
        }
    }
}
