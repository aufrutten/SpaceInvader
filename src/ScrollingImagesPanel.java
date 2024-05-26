import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ScrollingImagesPanel extends JPanel implements ActionListener {
    private static final int PANEL_WIDTH = 500;
    private static final int PANEL_HEIGHT = 1000;
    private static final int TIMER_DELAY = 5; // Riduci il delay del timer per uno scorrimento più fluido

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
            image1 = ImageIO.read(new File(projectDir+"/Sprite/backgroud/Background frame 1.png"));
            image2 = ImageIO.read(new File(projectDir+"/Sprite/backgroud/Background frame 2.png"));
            image3 = ImageIO.read(new File(projectDir+"/Sprite/backgroud/Background frame 3.png"));

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
    protected void paintComponent(Graphics g) {
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Scrolling Images");
        ScrollingImagesPanel panel = new ScrollingImagesPanel();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}