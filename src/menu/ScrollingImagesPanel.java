package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ScrollingImagesPanel extends JPanel implements ActionListener {
    public static final int PANEL_WIDTH = 500;
    public static final int PANEL_HEIGHT = 800;
    private static final int TIMER_DELAY = 5;

    private final Image scaledImage1;
    private final Image scaledImage2;
    private final Image scaledImage3;
    private final int imageHeight;
    private int yPosition1;
    private int yPosition2;
    private int yPosition3;
    public static Timer timer;

    public ScrollingImagesPanel() {
        BufferedImage image1 = ImageLoader.loadImage("./Sprite/backgroud/backgroundFrame1.png");
        BufferedImage image2 = ImageLoader.loadImage("./Sprite/backgroud/backgroundFrame2.png");
        BufferedImage image3 = ImageLoader.loadImage("./Sprite/backgroud/backgroundFrame3.png");

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
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

        // Imposta il timer per l'animazione
        timer = new Timer(TIMER_DELAY, this);
        timer.start();
        setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setFocusable(true);
    }

    private Image getScaledImage(BufferedImage src, int width) {
        int originalWidth = src.getWidth();
        int originalHeight = src.getHeight();
        int newHeight = (originalHeight * width) / originalWidth;
        return src.getScaledInstance(width, newHeight, Image.SCALE_SMOOTH);
    }

    @Override
    public void paintComponent(Graphics g) {
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
}
