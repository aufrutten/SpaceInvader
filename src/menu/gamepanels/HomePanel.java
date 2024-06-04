package menu.gamepanels;

import menu.ImageLoader;
import menu.MainFrame;
import menu.ScrollingImagesPanel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class HomePanel extends JPanel {
    public static Clip clip;

    public HomePanel() {
        BufferedImage titleImage = ImageLoader.loadImage("./Sprite/title.png");
        BufferedImage bestScoreImage = ImageLoader.loadImage("./Sprite/best_score.png");
        BufferedImage startButtonImage = ImageLoader.loadImage("./Sprite/start_button.png");
        BufferedImage leaderboardButtonImage = ImageLoader.loadImage("./Sprite/leaderboard_button.png");

        setLayout(null);
        setOpaque(false);
        setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setFocusable(true);

        JLabel titleLabel = new JLabel(new ImageIcon(titleImage));
        titleLabel.setBounds((PANEL_WIDTH - titleImage.getWidth()) / 2, 50, titleImage.getWidth(), titleImage.getHeight());
        add(titleLabel);

        JLabel nameLabel = new JLabel("Insert your name:");
        nameLabel.setForeground(Color.ORANGE);
        nameLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        nameLabel.setBounds((PANEL_WIDTH - 200) / 2, 200, 200, 30);
        add(nameLabel);

        JTextField nameTextField = new JTextField("Name");
        nameTextField.setBounds((PANEL_WIDTH - 200) / 2, 240, 200, 40);
        nameTextField.setBackground(new Color(31, 40, 45));
        nameTextField.setForeground(new Color(229, 184, 11));
        nameTextField.setBorder(BorderFactory.createLineBorder(new Color(229, 184, 11), 3));
        nameTextField.setHorizontalAlignment(JTextField.CENTER);
        nameTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        add(nameTextField);

        JLabel bestScoreLabel = new JLabel(new ImageIcon(bestScoreImage));
        bestScoreLabel.setBounds((PANEL_WIDTH - bestScoreImage.getWidth()) / 2, 325, bestScoreImage.getWidth(), bestScoreImage.getHeight());
        add(bestScoreLabel);

        JLabel bestScoreValueLabel = new JLabel();
        bestScoreValueLabel.setForeground(new Color(229, 184,11));
        bestScoreValueLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
        if(!MainFrame.scores.isEmpty())
            bestScoreValueLabel.setText(String.valueOf(MainFrame.scores.getFirst().getScore()));
        bestScoreValueLabel.setBounds((PANEL_WIDTH - 200) / 2, 370, 200, 30);
        bestScoreValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(bestScoreValueLabel);

        JButton startButton = new JButton(new ImageIcon(startButtonImage));
        startButton.setBounds((PANEL_WIDTH - startButtonImage.getWidth()) / 2, 475, startButtonImage.getWidth(), startButtonImage.getHeight());
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == startButton) {
                    MainFrame.playerName = nameTextField.getText();
                    ScrollingImagesPanel.timer.stop();
                    ScrollingImagesPanel.timer.removeActionListener(ScrollingImagesPanel.timer.getActionListeners()[0]);
                    MainFrame.layeredPane.removeAll();
                    MainFrame.layeredPane.add(new ScrollingImagesPanel(), Integer.valueOf(1));
                    MainFrame.layeredPane.add(new PlayingPanel(), Integer.valueOf(2));
                    MainFrame.layeredPane.revalidate();
                    MainFrame.layeredPane.repaint();
                }
            }
        });
        add(startButton);

        JButton leaderboardButton = new JButton(new ImageIcon(leaderboardButtonImage));
        leaderboardButton.setBounds((PANEL_WIDTH - leaderboardButtonImage.getWidth()) / 2, 575, leaderboardButtonImage.getWidth(), leaderboardButtonImage.getHeight());
        leaderboardButton.setBorderPainted(false);
        leaderboardButton.setContentAreaFilled(false);
        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == leaderboardButton) {
                    ScrollingImagesPanel.timer.stop();
                    ScrollingImagesPanel.timer.removeActionListener(ScrollingImagesPanel.timer.getActionListeners()[0]);
                    MainFrame.layeredPane.removeAll();
                    MainFrame.layeredPane.add(new ScrollingImagesPanel(), Integer.valueOf(1));
                    MainFrame.layeredPane.add(new LeaderBoardPanel(), Integer.valueOf(2));
                    MainFrame.layeredPane.revalidate();
                    MainFrame.layeredPane.repaint();
                }
            }
        });
        add(leaderboardButton);

        JLabel creditsLabel = new JLabel("<html>Credits: Atti Akram, Dragoni Luca, Semykopenko Ihor,<br>Vergara Diego - 4X a.s. 2023-2024</html>");
        creditsLabel.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
        creditsLabel.setForeground(Color.WHITE);
        creditsLabel.setBounds(0,PANEL_HEIGHT - 100, PANEL_WIDTH,  40);
        creditsLabel.setHorizontalAlignment(JLabel.CENTER);
        add(creditsLabel);

        if (clip != null) {
            clip.stop();
        }
        try {
            File file = new File("./Sprite/music/loop-menu-preview.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            HomePanel.clip = AudioSystem.getClip();
            HomePanel.clip.open(audioInputStream);
            HomePanel.clip.start();
            HomePanel.clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
