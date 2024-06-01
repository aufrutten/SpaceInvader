package menu.gamepanels;

import fileio.MyIO;
import fileio.Score;
import menu.MainFrame;
import menu.ScrollingImagesPanel;
import units.Alien;
import units.Bullet;
import units.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Comparator;
import java.util.Random;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class PlayingPanel extends JPanel implements ActionListener {
    public static Player player;
    private final Timer timer;
    private final Timer aliensTimer;
    private Clip clip;

    public PlayingPanel() {
        try {
            File file = new File("./Sprite/music/background-music.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setFocusable(true);
        setLayout(null);
        setBounds(new Rectangle(PANEL_WIDTH, PANEL_HEIGHT));
        setOpaque(false);
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

        aliensTimer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                int number = random.nextInt(4) + 1;
                Alien.spawnAliens(number);
            }
        });
        aliensTimer.start();
        player = new Player();
        timer = new Timer(20, this);
        timer.start();
    }

    public void savePlayerScore() {
        MainFrame.scores = MyIO.readSerializerRecord("scores.bin");
        if(MainFrame.scores.size() <= 2) {
            MainFrame.scores.add(new Score(Player.score, MainFrame.playerName));
            MainFrame.scores.sort(new Comparator<Score>() {
                @Override
                public int compare(Score o1, Score o2) {
                    return Integer.compare(o2.getScore(), o1.getScore());
                }
            });
        } else if (MainFrame.scores.getLast().getScore() < Player.score && MainFrame.scores.size() == 3) {
            MainFrame.scores.removeLast();
            MainFrame.scores.add(new Score(Player.score, MainFrame.playerName));
            MainFrame.scores.sort(new Comparator<Score>() {
                @Override
                public int compare(Score o1, Score o2) {
                    return Integer.compare(o2.getScore(), o1.getScore());
                }
            });
        }
        MyIO.writeSerializerRecord("scores.bin", MainFrame.scores);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        Alien.drawAliens(g);
        Bullet.drawBullets(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        if(player.checkCollision() || Alien.borderCollision()) {
            clip.stop();
            savePlayerScore();
            ScrollingImagesPanel.timer.stop();
            ScrollingImagesPanel.timer.removeActionListener(ScrollingImagesPanel.timer.getActionListeners()[0]);
            Alien.removeAliens();
            Bullet.removeAllBullets();
            aliensTimer.stop();
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
