import com.sun.xml.internal.bind.v2.TODO;
import javafx.scene.control.ComboBox;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawingPanel extends JPanel {
    final static int W = 800, H = 600;
    final MainFrame frame;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }

    void createOffscreenImage(BufferedImage image) {
        graphics = image.createGraphics();
        this.image = image;
    }

    private void init() {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY());
                repaint();
            } //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }

    private void drawShape(int x, int y) {
        int radius = (int) new Random().nextInt(255); //generate a random number
        int sides = (int) frame.configPanel.sidesField.getValue();//get the value from UI (in ConfigPanel)
        int color = frame.configPanel.colorCombo.getSelectedIndex();

        //create a transparent random Color.

        Color drawingColor;
        if (color == 1) {
            drawingColor = new Color(0, 0, 0, 255);
        } else {
            int randomR = new Random().nextInt(255);
            int RandomG = new Random().nextInt(255);
            int RandomB = new Random().nextInt(255);
            drawingColor = new Color(randomR, RandomG, RandomB, 50);
        }


        graphics.setColor(drawingColor);
        graphics.fill(new RegularPolygon(x, y, radius, sides));


    }

    @Override
    public void update(Graphics g) {
    } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}