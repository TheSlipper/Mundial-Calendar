package GUI;

import CalendarData.CalendarEvent;
import CalendarData.EventQueryProcessor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarDeleteFrame extends JFrame implements ActionListener {

    private DefaultTableModel model;
    private Calendar cal = new GregorianCalendar();
    private JLabel label1;
    private  JLabel[] inputLabels;
    private JButton b1, b2;
    private  JButton loadEventButton, deleteButton;
    private JPanel topPanel;
    private JTable table;
    private  JTextField[] textFields;
    private JScrollPane pane;
    private  JComboBox eventsComboBox;
    private ArrayList<CalendarEvent> events;
    private final String[] columns = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    private void _updateMonth() {
        cal.set(Calendar.DAY_OF_MONTH, 1);

        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        int year = cal.get(Calendar.YEAR);
        label1.setText(month + " " + year);

        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = 6;

        model.setRowCount(0);
        model.setRowCount(weeks);

        int i = startDay-1;

        for (int day = 1; day <= numberOfDays; day++) {
            model.setValueAt(day, i/7 , i%7 );
            i++;
        }

    }

    private void _setUpMetaData() {
        this.setDefaultCloseOperation(CalendarBrowseFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Pick the date");
        this.setSize(303,429);
        this.setLocation(1300, 300);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }

    private void _initialiseObjects() {
        eventsComboBox = new JComboBox();
        eventsComboBox.addActionListener(this);
        eventsComboBox.setPreferredSize(new Dimension(280, 23));

        loadEventButton = new JButton("Load Events");
        loadEventButton.addActionListener(this);

        textFields = new JTextField[8];
        textFields[0] = new JTextField();
        textFields[0].setColumns(17);
        textFields[0].setEditable(false);
        textFields[1] = new JTextField();
        textFields[1].setColumns(21);
        textFields[1].setEditable(false);
        textFields[2] = new JTextField();
        textFields[2].setColumns(17);
        textFields[2].setEditable(false);
        textFields[3] = new JTextField();
        textFields[3].setColumns(17);
        textFields[3].setEditable(false);
        textFields[4] =  new JTextField();
        textFields[4] .setColumns(20);
        textFields[4].setEditable(false);
        textFields[5] =  new JTextField();
        textFields[5] .setColumns(20);
        textFields[5].setEditable(false);
        textFields[6] =  new JTextField();
        textFields[6] .setColumns(21);
        textFields[6].setEditable(false);
        textFields[7] =  new JTextField();
        textFields[7] .setColumns(23);
        textFields[7].setEditable(false);

        inputLabels = new JLabel[8];
        inputLabels [0] = new JLabel("Name of the Event: ");
        inputLabels [1] = new JLabel("Description: ");
        inputLabels [2] = new JLabel("Start time (HH:MM): ");
        inputLabels [3] = new JLabel("End time (HH:MM): ");
        inputLabels [4] = new JLabel("Team A squad: ");
        inputLabels [5] = new JLabel("Team B squad: ");
        inputLabels [6] = new JLabel("Ticket price: ");
        inputLabels [7] = new JLabel("Stadium: ");

        label1 = new JLabel();
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        b1 = new JButton("<-");
        b1.addActionListener(this);

        b2 = new JButton("->");
        b2.addActionListener(this);

        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        model = new DefaultTableModel(null, columns);
        table = new JTable(model);
        pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(300, 102));

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
    }

    private void _addObjects() {
        topPanel.add(b1,BorderLayout.WEST);
        topPanel.add(label1,BorderLayout.CENTER);
        topPanel.add(b2,BorderLayout.EAST);

        this.add(topPanel);
        this.add(pane);

        this.add(eventsComboBox);

        this.add(inputLabels[0]);
        this.add(textFields[0]);

        this.add(inputLabels[1]);
        this.add(textFields[1]);

        this.add(inputLabels[2]);
        this.add(textFields[2]);

        this.add(inputLabels[3]);
        this.add(textFields[3]);

        this.add(inputLabels[4]);
        this.add(textFields[4]);

        this.add(inputLabels[5]);
        this.add(textFields[5]);

        this.add(inputLabels[6]);
        this.add(textFields[6]);

        this.add(inputLabels[7]);
        this.add(textFields[7]);

        this.add(loadEventButton);
        this.add(deleteButton);
    }

    public CalendarDeleteFrame() {
        _setUpMetaData();
        _initialiseObjects();
        _addObjects();
        this._updateMonth();
    }

    private int _getSelectedDay() {
        int row = table.getSelectedRow();
        int column = table.getSelectedColumn();
        int day = (int)table.getValueAt(row, column);
        return day;
    }

    private void _clearInput() {
        textFields[0].setText("");
        textFields[1].setText("");
        textFields[2].setText("");
        textFields[3].setText("");
        textFields[4].setText("");
        textFields[5].setText("");
        textFields[6].setText("");
        textFields[7].setText("");
        JOptionPane.showMessageDialog(null, "Added to your events!");
    }

    private void _loadEventDataToTextFields(int index) {
        textFields[0].setText(events.get(index).getName());
        textFields[1].setText(events.get(index).getDescription());
        textFields[2].setText(events.get(index).getStartTimeHour() + ":" + events.get(index).getStartTimeMinute());
        textFields[3].setText(events.get(index).getEndTimeHour() + ":" + events.get(index).getEndTimeMinute());
        textFields[4].setText(events.get(index).getTeamASquad());
        textFields[5].setText(events.get(index).getTeamBSquad());
        textFields[6].setText(String.valueOf(events.get(index).getTicketPrice()));
        textFields[7].setText(events.get(index).getStadium());
    }

    private void _loadEvents() {
        eventsComboBox.removeAllItems();

        events = EventQueryProcessor.getEventByDate(_getSelectedDay(), cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
        for(int i=0; i < events.size(); i++)
            eventsComboBox.addItem(events.get(i).getName());

        eventsComboBox.setSelectedIndex(0);
        _loadEventDataToTextFields(0);
        System.gc();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == b1) {
            cal.add(Calendar.MONTH, -1);
            this._updateMonth();
        } else if (obj == b2) {
            cal.add(Calendar.MONTH, +1);
            this._updateMonth();
        } else if (obj == deleteButton) {
//            _submitEdittedEntry();
            EventQueryProcessor.deleteEvent(events.get(eventsComboBox.getSelectedIndex()));
        } else if (obj == loadEventButton) {
            this._loadEvents();
        } else if (obj == eventsComboBox) {
            this._loadEventDataToTextFields(eventsComboBox.getSelectedIndex());
        }
    }

}
