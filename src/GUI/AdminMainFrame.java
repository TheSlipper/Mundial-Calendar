package GUI;

import Settings.SettingsLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainFrame extends JFrame implements ActionListener {

    private JButton[] buttons;
    private MainFrameLogo logo;

    private void _setUpMetaData() {
        this.setSize(SettingsLoader.getAdminFrameDimension());
        this.setDefaultCloseOperation(AdminMainFrame.EXIT_ON_CLOSE);
        this.setTitle("2018 Mundial Calendar (admin)");
        this.setLocation(350, 150);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
    }

    private void _initialiseObjects() {
        this.logo = new MainFrameLogo();

        this.buttons = new JButton[7];

        this.buttons[0] = new JButton("Search By...");
        buttons[0].setFont(new Font("assets/fonts/sfdr.otf", SettingsLoader.getFontStyle(), SettingsLoader.getFontSize()));
        this.buttons[0].addActionListener(this);

        this.buttons[1] = new JButton("Browse the Calendar");
        buttons[1].setFont(new Font("assets/fonts/sfdr.otf", SettingsLoader.getFontStyle(), SettingsLoader.getFontSize()));
        this.buttons[1].addActionListener(this);

        this.buttons[2] = new JButton("Add an event");
        buttons[2].setFont(new Font("assets/fonts/sfdr.otf", SettingsLoader.getFontStyle(), SettingsLoader.getFontSize()));
        this.buttons[2].addActionListener(this);

        this.buttons[3] = new JButton("Edit an Event");
        buttons[3].setFont(new Font("assets/fonts/sfdr.otf", SettingsLoader.getFontStyle(), SettingsLoader.getFontSize()));
        this.buttons[3].addActionListener(this);

        this.buttons[4] = new JButton("Delete an Event");
        buttons[4].setFont(new Font("assets/fonts/sfdr.otf", SettingsLoader.getFontStyle(), SettingsLoader.getFontSize()));
        this.buttons[4].addActionListener(this);

        this.buttons[5] = new JButton("Edit Calendar Settings");
        buttons[5].setFont(new Font("assets/fonts/sfdr.otf", SettingsLoader.getFontStyle(), SettingsLoader.getFontSize()));
        this.buttons[5].addActionListener(this);

        this.buttons[6] = new JButton("Log out");
        buttons[6].setFont(new Font("assets/fonts/sfdr.otf", SettingsLoader.getFontStyle(), SettingsLoader.getFontSize()));
        this.buttons[6].addActionListener(this);
    }

    private void _addObjects() {
        this.add(logo);
        this.add(buttons[0]);
        this.add(buttons[1]);
        this.add(buttons[2]);
        this.add(buttons[3]);
        this.add(buttons[4]);
        this.add(buttons[5]);
    }

    private void _adminWarning() {
        JOptionPane.showMessageDialog(null, "You just opened the administrator panel. We trust you have received the usual lecture from the local System\r\n" +
                "Administrator. It usually boils down to these three things:\r\n" +
                "\r\n" +
                "    #1) Respect the privacy of others.\r\n" +
                "    #2) Think before you type.\n" +
                "    #3) With great power comes great responsibility.\n" +
                "\n");
    }

    public AdminMainFrame() {
        this._setUpMetaData();
        this._initialiseObjects();
        this._addObjects();
        this._adminWarning();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == buttons[0]) {
            new SearchByFrame();
        } else if (obj == buttons[1]) {
            new CalendarBrowseFrame();
        } else if (obj == buttons[2]) {
            new CalendarAddFrame();
        } else if (obj == buttons[3]) {
            new CalendarEditFrame();
        } else if (obj == buttons[4]) {
            new CalendarDeleteFrame();
        } else if (obj == buttons[5]) {
            new AdminSettingsFrame();
        } else if (obj == buttons[6]) {
            new LoginFrame();
            this.dispose();
        }
    }
}
