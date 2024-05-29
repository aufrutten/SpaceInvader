package menu.gamepanels;

import units.Alien;
import units.AlienList;
import units.Enemy;
import units.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class PlayingPanel extends JPanel implements ActionListener {
    private final Player player;
    private ArrayList<Alien> aliens;
    private Action rightAction;
    private Action leftAction;

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
        aliens = new ArrayList<>();
        AlienList.spawnAliens(5);
        player = new Player();
        Timer timer = new Timer(20, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        AlienList.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
