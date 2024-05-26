package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class ScrollingImagesPanel extends JPanel implements ActionListener {
    private static final int PANEL_WIDTH = 600;
    private static final int PANEL_HEIGHT = 1000;
    private static final int TIMER_DELAY = 5;

    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage image3;

    private Image scaledImage1;
    private Image scaledImage2;
    private Image scaledImage3;

    private int imageHeight;
    private int yPosition1;
    private int yPosition2;
    private int yPosition3;

    private Timer timer;


    public ScrollingImagesPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        try {
            String projectDir = System.getProperty("user.dir");
            // Carica le immagini
            image1 = read(new File(projectDir + "/Sprite/backgroud/Background frame 1.png"));
            image2 = read(new File(projectDir + "/Sprite/backgroud/Background frame 2.png"));
            image3 = read(new File(projectDir + "/Sprite/backgroud/Background frame 3.png"));

            // Ridimensiona le immagini mantenendo le proporzioni
            scaledImage1 = getScaledImage(image1, PANEL_WIDTH);
            scaledImage2 = getScaledImage(image2, PANEL_WIDTH);
            scaledImage3 = getScaledImage(image3, PANEL_WIDTH);

            // Calcola l'altezza delle immagini ridimensionate
            imageHeight = scaledImage1.getHeight(this);

            // Imposta le posizioni iniziali delle immagini in modo che siano visibili a schermo intero
            yPosition1 = 0;
            yPosition2 = yPosition1 + imageHeight;
            yPosition3 = yPosition2 + imageHeight;
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imposta il timer per l'animazione
        timer = new Timer(TIMER_DELAY, this);
        timer.start();
    }

    private Image getScaledImage(BufferedImage src, int width) {
        int originalWidth = src.getWidth();
        int originalHeight = src.getHeight();
        int newHeight = (originalHeight * width) / originalWidth;
        return src.getScaledInstance(width, newHeight, Image.SCALE_SMOOTH);
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);

        // Disegna le immagini alle rispettive posizioni
        g.drawImage(scaledImage1, 0, yPosition1, this);
        g.drawImage(scaledImage2, 0, yPosition2, this);
        g.drawImage(scaledImage3, 0, yPosition3, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Aggiorna le posizioni delle immagini
        yPosition1 += 1;
        yPosition2 += 1;
        yPosition3 += 1;

        // Riposiziona le immagini quando escono dal pannello
        if (yPosition1 >= PANEL_HEIGHT) {
            yPosition1 = yPosition3 - imageHeight;
        }
        if (yPosition2 >= PANEL_HEIGHT) {
            yPosition2 = yPosition1 - imageHeight;
        }
        if (yPosition3 >= PANEL_HEIGHT) {
            yPosition3 = yPosition2 - imageHeight;
        }

        // Richiedi il ridisegno del pannello
        repaint();
    }

    private void resizeImages(int width) {
        scaledImage1 = getScaledImage(image1, width);
        scaledImage2 = getScaledImage(image2, width);
        scaledImage3 = getScaledImage(image3, width);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Scrolling Images");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);

        // Usa JLayeredPane per gestire i livelli dei pannelli
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // Crea il pannello di scrolling
        ScrollingImagesPanel scrollingPanel = new ScrollingImagesPanel();
        scrollingPanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        layeredPane.add(scrollingPanel, Integer.valueOf(1));

        // Crea il pannello di overlay
        JPanel overlayPanel = new JPanel(null);
        overlayPanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        overlayPanel.setOpaque(false); // Rendi il pannello trasparente

        try {
            String projectDir = System.getProperty("user.dir");

            // Carica e aggiungi l'immagine del titolo
            BufferedImage titleImage = read(new File(projectDir + "/Sprite/title.png"));
            JLabel titleLabel = new JLabel(new ImageIcon(titleImage));
            titleLabel.setBounds((PANEL_WIDTH - titleImage.getWidth()) / 2, 150, titleImage.getWidth(), titleImage.getHeight());
            overlayPanel.add(titleLabel);

            // Carica e aggiungi l'immagine del best score
            BufferedImage bestScoreImage = read(new File(projectDir + "/Sprite/best_score.png"));
            JLabel bestScoreLabel = new JLabel(new ImageIcon(bestScoreImage));
            bestScoreLabel.setBounds((PANEL_WIDTH - bestScoreImage.getWidth()) / 2, 250, bestScoreImage.getWidth(), bestScoreImage.getHeight());
            overlayPanel.add(bestScoreLabel);

            // Carica e aggiungi il bottone start
            BufferedImage startButtonImage = read(new File(projectDir + "/Sprite/start_button.png"));
            JButton startButton = new JButton(new ImageIcon(startButtonImage));
            startButton.setBounds((PANEL_WIDTH - startButtonImage.getWidth()) / 2, 650, startButtonImage.getWidth(), startButtonImage.getHeight());
            startButton.setBorderPainted(false);
            startButton.setContentAreaFilled(false);
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("start");
                }
            });
            overlayPanel.add(startButton);

            // Carica e aggiungi il bottone leaderboard
            BufferedImage leaderboardButtonImage = read(new File(projectDir + "/Sprite/leaderboard_button.png"));
            JButton leaderboardButton = new JButton(new ImageIcon(leaderboardButtonImage));
            leaderboardButton.setBounds((PANEL_WIDTH - leaderboardButtonImage.getWidth()) / 2, 750, leaderboardButtonImage.getWidth(), leaderboardButtonImage.getHeight());
            leaderboardButton.setBorderPainted(false);
            leaderboardButton.setContentAreaFilled(false);
            overlayPanel.add(leaderboardButton);

            leaderboardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("leaderboard");
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }

        // Aggiungi il pannello di overlay sopra il pannello di scrolling
        layeredPane.add(overlayPanel, Integer.valueOf(2));
        frame.add(layeredPane);
        frame.pack();
        frame.setVisible(true);
    }
}
