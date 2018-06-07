package GUI;

import Settings.SettingsLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class SettingsFrame extends JFrame implements ActionListener {

    private JTextField[] textFields;
    private JLabel[] labels;
    private JButton[] buttons;

    // Set uppers
    private void _setUpMetaData() {
        this.setDefaultCloseOperation(CalendarBrowseFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Pick the date");
        this.setSize(300,430);
        this.setLocation(1300, 300);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }

    private void _initialiseObjects() {
        textFields = new JTextField[6];
        textFields[0] = new JTextField();
        textFields[0].setColumns(10);
        textFields[1] = new JTextField();
        textFields[1].setColumns(10);
        textFields[2] = new JTextField();
        textFields[2].setColumns(10);
        textFields[3] = new JTextField();
        textFields[3].setColumns(10);
        textFields[4] = new JTextField();
        textFields[4].setColumns(10);
        textFields[5] = new JTextField();
        textFields[5].setColumns(10);

        labels = new JLabel[6];
        labels[0] = new JLabel("Height of the administration Panel: ");
        labels[1] = new JLabel("Width of the administration Panel: ");
        labels[2] = new JLabel("Height of the user's Panel: ");
        labels[3] = new JLabel("Width of the user's Panel: ");
        labels[4] = new JLabel("Font size: ");
        labels[5] = new JLabel("Font style(plain = 0, bold = 1, italic = 2): ");

        buttons = new JButton[2];
        buttons[0] = new JButton("Save");
        buttons[0].addActionListener(this);
        buttons[1] = new JButton("Cancel");
        buttons[1].addActionListener(this);
    }

    private void _addObjects() {
        add(labels[0]);
        add(textFields[0]);
        add(labels[1]);
        add(textFields[1]);
        add(labels[2]);
        add(textFields[2]);
        add(labels[3]);
        add(textFields[3]);
        add(labels[4]);
        add(textFields[4]);
        add(labels[5]);
        add(textFields[5]);
        add(buttons[0]);
        add(buttons[1]);
    }

    // Constructor
    public SettingsFrame() {
        _setUpMetaData();
        _initialiseObjects();
        _addObjects();
    }

    private void _saveSettings() {
        try {
            // TODO: Implement changing the settings
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        SettingsLoader.loadSettings();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == buttons[1])
            this.dispose();
        else if (obj == buttons[0]) {
            _saveSettings();
            JOptionPane.showMessageDialog(null, "You might need to restart the application in order for some changes to take place.");
            this.dispose();
        }
    }
}
