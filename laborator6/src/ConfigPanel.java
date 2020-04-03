import com.sun.xml.internal.bind.v2.TODO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel extends JPanel implements ActionListener {
    final MainFrame frame;
    JLabel label; // we’re drawing regular polygons
    JSpinner sidesField; // number of sides
    JComboBox colorCombo; // the color of the shape


    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //create the label and the spinner
        label = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(6); //default number of sides
        //create the colorCombo, containing the values: Random and Black

        String[] colorStrings = {"Random", "Black"};
        colorCombo = new JComboBox(colorStrings);
        colorCombo.setSelectedIndex(0);
        colorCombo.addActionListener(this);


        add(label); //JPanel uses FlowLayout by default
        add(sidesField);
        add(colorCombo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}