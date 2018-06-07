package GUI;

import Settings.SettingsLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMainFrame extends JFrame implements ActionListener {

    private JButton[] buttons;
    private MainFrameLogo logo;

    private void _setUpMetaData() {
        // TODO: Implement loading settings from a txt file
        this.setSize(SettingsLoader.getUserFrameDimension());
        this.setDefaultCloseOperation(AdminMainFrame.EXIT_ON_CLOSE);
        this.setTitle("2018 Mundial Calendar");
        this.setLocation(350, 150);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
    }

    private void _initialiseObjects() {
        //TODO: Implement a method that will read the font from the assets file and use it in those buttons
        this.logo = new MainFrameLogo("assets/mainFrameLogo/logo_user.png");

        this.buttons = new JButton[3];

        this.buttons[0] = new JButton("Browse the Calendar");
        buttons[0].setFont(new Font("assets/fonts/sfdr.otf", SettingsLoader.getFontStyle(), SettingsLoader.getFontSize()));
        this.buttons[0].addActionListener(this);

        this.buttons[1] = new JButton("Edit Calendar Settings");
        this.buttons[1].setFont(new Font("assets/fonts/sfdr.otf", SettingsLoader.getFontStyle(), SettingsLoader.getFontSize()));
        this.buttons[1].addActionListener(this);

        this.buttons[2] = new JButton("Log out");
        this.buttons[1].setFont(new Font("assets/fonts/sfdr.otf", SettingsLoader.getFontStyle(), SettingsLoader.getFontSize()));
        this.buttons[2].addActionListener(this);
    }

    private void _addObjects() {
        this.add(logo);
        this.add(buttons[0]);
        this.add(buttons[1]);
        this.add(buttons[2]);
    }

    public UserMainFrame() {
        this._setUpMetaData();
        this._initialiseObjects();
        this._addObjects();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == buttons[0]) {
            new CalendarBrowseFrame();
        } else if (obj == buttons[1]) {
            // TODO: Settings
        } else if (obj == buttons[2]) {
            new LoginFrame();
            this.dispose();
        }
    }

}
