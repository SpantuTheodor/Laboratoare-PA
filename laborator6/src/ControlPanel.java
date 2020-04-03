import com.sun.xml.internal.bind.v2.TODO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");
    File saved;

    //create all buttons (Load, Reset, Exit)
    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));
        //add all buttons ...TODO
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);

        //configure listeners for all buttons
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);

    }

    private void save(ActionEvent e) {
        try {
            ImageIO.write(frame.canvas.image, "PNG", saved = new File("d:/test.png"));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void load(ActionEvent e) {
        try {

            BufferedImage originalImage = ImageIO.read(saved);
            this.frame.canvas.createOffscreenImage(originalImage);
            this.frame.canvas.repaint();

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void reset(ActionEvent e) {
        this.frame.canvas.createOffscreenImage();
    }

    private void exit(ActionEvent e) {
        System.exit(0);
    }
}
