package menu;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import menu.gamepanels.HomePanel;
import static menu.ScrollingImagesPanel.PANEL_HEIGHT;
import static menu.ScrollingImagesPanel.PANEL_WIDTH;

public class MainFrame {
    public static JLayeredPane layeredPane;

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Space Invader");
        frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // Crea il pannello di scrolling
        layeredPane.add(new ScrollingImagesPanel(), Integer.valueOf(1));
        // Crea il pannello di overlay
        layeredPane.add(new HomePanel(), Integer.valueOf(2));

        frame.add(layeredPane);
        frame.pack();
        frame.setVisible(true);
    }
}
