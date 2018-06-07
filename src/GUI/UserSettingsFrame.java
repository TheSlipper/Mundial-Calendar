package GUI;

import Settings.SettingsLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class UserSettingsFrame extends JFrame implements ActionListener {

    private JTextField[] textFields;
    private JLabel[] labels;
    private JButton[] buttons;

    // Set uppers
    private void _setUpMetaData() {
        this.setDefaultCloseOperation(CalendarBrowseFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Settings (User)");
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

        labels = new JLabel[4];
        labels[0] = new JLabel("Height of the user's Panel: ");
        labels[1] = new JLabel("Width of the user's Panel: ");
        labels[2] = new JLabel("Font size: ");
        labels[3] = new JLabel("Font style(plain = 0, bold = 1, italic = 2): ");

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
        add(buttons[0]);
        add(buttons[1]);
    }

    // Constructor
    public UserSettingsFrame() {
        _setUpMetaData();
        _initialiseObjects();
        _addObjects();
    }

    private void _saveSettings() {
        try {
            FileWriter fw = new FileWriter("assets/settings");
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("###\r\n" +
                    "###\tMUNDIAL CALENDARY SETTINGS FILE\r\n" +
                    "###\r\n" +
                    "\r\n" +
                    "#\r\n" +
                    "#\tWARNING:\r\n" +
                    "#\tIt is highly suggested for the users to use the built in \"settings\" option\r\n" +
                    "#\tto manipulate the settings. Doing otherwise may result in errors.\r\n" +
                    "#\r\n" +
                    "#\r\n" +
                    "\r\n" +
                    "#administrationPanelHeight\r\n" +
                    (int)SettingsLoader.getAdminFrameDimension().getHeight() + "\r\n" +
                    "#administrationPanelWidth \r\n" +
                    (int)SettingsLoader.getAdminFrameDimension().getWidth() + "\r\n" + // Administration pannel width
                    "#userPanelHeight\n" +
                    textFields[0].getText() + "\r\n" +
                    "#userPanelWidth\r\n" +
                    textFields[1].getText() + "\r\n" +
                    "#fontSize\r\n" +
                    textFields[2].getText() + "\r\n" +
                    "#fontStyle (Plain = 0, Bold = 1, Italic = 2)\r\n" +
                    textFields[3].getText());
            bw.close();
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
