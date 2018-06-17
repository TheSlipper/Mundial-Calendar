package GUI;

import CalendarData.CalendarEvent;
import CalendarData.EventQueryProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchByFrame extends JFrame implements ActionListener {

   private JButton submitQueryButton;
   private JLabel label;
   private JTextField textFields;
   private JComboBox categoryComboBox, sortComboBox;
   private final String[] categoryStrings = {"Team / Event Name", "Player/s Name", "Stadium"};
   private final String[] sortStrings = {"Alphabetically Ascending", "Alphabetically Descending"};

   private void _setUpMetaData() {
       this.setDefaultCloseOperation(CalendarBrowseFrame.DISPOSE_ON_CLOSE);
       this.setTitle("Pick the date");
       this.setSize(270,125);
       this.setLocation(1300, 300);
       this.setLayout(new FlowLayout());
       this.setVisible(true);
   }

   private void _initialiseObjects() {
        submitQueryButton = new JButton("Search");
        submitQueryButton.addActionListener(this);

        label = new JLabel("Team / Event name: ");

        textFields = new JTextField();
        textFields.setColumns(13);

        categoryComboBox = new JComboBox(categoryStrings);
        categoryComboBox.setPreferredSize(new Dimension(250, 25));
        categoryComboBox.setSelectedIndex(0);
        categoryComboBox.addActionListener(this);

        sortComboBox = new JComboBox(sortStrings);
        sortComboBox.setSelectedIndex(0);
   }

   private void _addObjects() {
       add(categoryComboBox);
       add(label);
       add(textFields);
       add(sortComboBox);
       add(submitQueryButton);
   }

   public SearchByFrame() {
       _setUpMetaData();
       _initialiseObjects();
       _addObjects();
   }

   private void _updateFrame() {
       this.sortComboBox.enable();;
       if (categoryComboBox.getSelectedIndex() == 0) {
           this.label .setText("Team / Event Name:");
           this.textFields.setText("");
       } else if (categoryComboBox.getSelectedIndex() == 1) {
           this.label .setText("Player/s Name:");
           this.textFields.setText("");
           this.sortComboBox.disable();
       } else if (categoryComboBox.getSelectedIndex() == 2) {
           this.label .setText("Stadium:");
       }
   }

   private void _showEvents() {
       ArrayList<CalendarEvent> events;
       String helper = "";
       if (categoryComboBox.getSelectedIndex() == 0) {
           events = EventQueryProcessor.getEventsByName(textFields.getText());

           if (sortComboBox.getSelectedIndex() == 0) {
               EventQueryProcessor.sortByName(events, true);
               for (int i=0; i < events.size(); i++)
                   helper += events.get(i).toString() + "\r\n\r\n";
            } else {
               EventQueryProcessor.sortByName(events, false);
               for (int i=0; i < events.size(); i++)
                   helper += events.get(i).toString() + "\r\n\r\n";
           }
       } else if (categoryComboBox.getSelectedIndex() == 1) {
           events = EventQueryProcessor.getEventsByPlayerName(textFields.getText());
           for (int i=0; i < events.size(); i++)
               helper += events.get(i).toString() + "\r\n\r\n";
       } else {
           events =  EventQueryProcessor.getEventsByStadium(textFields.getName());
           if (sortComboBox.getSelectedIndex() == 0) {
               EventQueryProcessor.sortByStadium(events, true);
               for (int i=0; i < events.size(); i++)
                   helper += events.get(i).toString() + "\r\n\r\n";
           } else {
               EventQueryProcessor.sortByStadium(events, false);
               for (int i=0; i < events.size(); i++)
                   helper += events.get(i).toString() + "\r\n\r\n";
           }
       }

           JOptionPane.showMessageDialog(null,  helper);
       }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == submitQueryButton) {
            _showEvents();
        } else if (obj == categoryComboBox) {
            _updateFrame();
        }
    }
}
