package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainFrame extends JFrame implements ActionListener {

    private JButton[] buttons;
    private MainFrameLogo logo;

    private void _setUpMetaData() {
        // TODO: Implement loading settings from a txt file
        this.setSize(920, 330);
        this.setDefaultCloseOperation(AdminMainFrame.EXIT_ON_CLOSE);
        this.setTitle("2018 Mundial Calendar");
        this.setLocation(350, 150);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
    }

    private void _initialiseObjects() {
        //TODO: Implement a method that will read the font from the assets file and use it in those buttons
        logo = new MainFrameLogo();

        buttons = new JButton[6];

        buttons[0] = new JButton("Browse the Calendar");
//        buttons[0].setPreferredSize(new Dimension(300, 100));
//        buttons[0].setFont(new Font("assets/fonts/sfdr.otf", Font.PLAIN, 20));
        buttons[0].addActionListener(this);

        buttons[1] = new JButton("Add an event");
//        buttons[1].setPreferredSize(new Dimension(300, 80));
//        buttons[1].setFont(new Font("assets/fonts/sfdr.otf", Font.PLAIN, 20));
        buttons[1].addActionListener(this);

        buttons[2] = new JButton("Edit an Event");
//        buttons[2].setPreferredSize(new Dimension(300, 80));
//        buttons[2].setFont(new Font("assets/fonts/sfdr.otf", Font.PLAIN, 20));
        buttons[2].addActionListener(this);

        buttons[3] = new JButton("Delete an Event");
//        buttons[3].setPreferredSize(new Dimension(300, 80));
//        buttons[3].setFont(new Font("assets/fonts/sfdr.otf", Font.PLAIN, 20));
        buttons[3].addActionListener(this);

        buttons[4] = new JButton("Edit Calendar Settings");
//        buttons[4].setPreferredSize(new Dimension(300, 80));
//        buttons[4].setFont(new Font("Arial", Font.PLAIN, 20));
        buttons[4].addActionListener(this);

        buttons[5] = new JButton("Exit the program");
//        buttons[5].setPreferredSize(new Dimension(300, 50));
//        buttons[5].setFont(new Font("assets/fonts/sfdr.otf", Font.PLAIN, 20));
        buttons[5].addActionListener(this);
    }

    private void _addObjects() {
        add(logo);
        add(buttons[0]);
        add(buttons[1]);
        add(buttons[2]);
        add(buttons[3]);
        add(buttons[4]);
        add(buttons[5]);
    }

    public AdminMainFrame() {
        _setUpMetaData();
        _initialiseObjects();
        _addObjects();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == buttons[0]) {
            new CalendarBrowseFrame();
        } else if (obj == buttons[1]) {
            new CalendarAddFrame();
        } else if (obj == buttons[2]) {

        } else if (obj == buttons[3]) {

        } else if (obj == buttons[4]) {

        } else if (obj == buttons[5]) {
            System.exit(0);
        }
    }
}
