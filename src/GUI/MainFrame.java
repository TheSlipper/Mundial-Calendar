package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    private JButton[] buttons;
    private MainFrameLogo logo;


    private void _setUpMetaData() {
        // TODO: Implement loading settings from a txt file
        this.setSize(1280, 450);
        this.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
        this.setTitle("2018 Mundial Calendar");
        this.setLocation(350, 150);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
    }

    private void _initialiseObjects() {
        //TODO: Implement a method that will read the font from the assets file and use it in those buttons
        logo = new MainFrameLogo();

        buttons = new JButton[4];

        buttons[0] = new JButton("Browse the Calendar");
        buttons[0].setPreferredSize(new Dimension(300, 100));
        buttons[0].setFont(new Font("assets/fonts/sfdr.otf", Font.PLAIN, 20));
        buttons[0].addActionListener(this);

        buttons[1] = new JButton("Add an Event");
        buttons[1].setPreferredSize(new Dimension(300, 100));
        buttons[1].setFont(new Font("assets/fonts/sfdr.otf", Font.PLAIN, 20));
        buttons[1].addActionListener(this);

        buttons[2] = new JButton("Edit Calendar Settings");
        buttons[2].setPreferredSize(new Dimension(300, 100));
        buttons[2].setFont(new Font("Arial", Font.PLAIN, 20));
        buttons[2].addActionListener(this);

        buttons[3] = new JButton("Exit the program");
        buttons[3].setPreferredSize(new Dimension(300, 100));
        buttons[3].setFont(new Font("assets/fonts/sfdr.otf", Font.PLAIN, 20));
        buttons[3].addActionListener(this);
    }

    private void _addObjects() {
        add(logo);
        add(buttons[0]);
        add(buttons[1]);
        add(buttons[2]);
        add(buttons[3]);
    }

    public MainFrame() {
        _setUpMetaData();
        _initialiseObjects();
        _addObjects();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == buttons[0]) {
            new BrowseCalendarFrame();
        }
    }
}