package GUI;

import CalendarData.CalendarEvent;
import CalendarData.EventQueryProcessor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarAddFrame extends JFrame implements ActionListener {

    private DefaultTableModel model;
    private Calendar cal = new GregorianCalendar();
    private JLabel label1;
    private  JLabel[] inputLabels;
    private JButton b1, b2;
    private  JButton submitButton;
    private JPanel topPanel;
    private JTable table;
    private  JTextField[] textFields;
    private JScrollPane pane;
    private final String[] columns = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    private void _updateMonth() {
        cal.set(Calendar.DAY_OF_MONTH, 1);

        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        int year = cal.get(Calendar.YEAR);
        label1.setText(month + " " + year);

        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

        model.setRowCount(0);
        model.setRowCount(weeks);

        int i = startDay-1;

        for (int day = 1;day <= numberOfDays; day++) {
            model.setValueAt(day, i/7 , i%7 );
            i++;
        }

    }

    private void _setUpMetaData() {
        this.setDefaultCloseOperation(CalendarBrowseFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Pick the date");
        this.setSize(303,300);
        this.setLocation(1300, 300);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }

    private void _initialiseObjects() {
        textFields = new JTextField[4];
        textFields[0] = new JTextField();
        textFields[0].setColumns(10);
        textFields[1] = new JTextField();
        textFields[1].setColumns(10);
        textFields[2] = new JTextField();
        textFields[2].setColumns(10);
        textFields[3] = new JTextField();
        textFields[3].setColumns(10);

        inputLabels = new JLabel[4];
        inputLabels [0] = new JLabel("Name of the Event: ");
        inputLabels [1] = new JLabel("Description: ");
        inputLabels [2] = new JLabel("Start time (HH:MM): ");
        inputLabels [3] = new JLabel("End time (HH:MM): ");

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
        pane.setPreferredSize(new Dimension(300, 86));

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setPreferredSize(new Dimension(100, 43));
    }

    private void _addObjects() {
        topPanel.add(b1,BorderLayout.WEST);
        topPanel.add(label1,BorderLayout.CENTER);
        topPanel.add(b2,BorderLayout.EAST);

        this.add(topPanel);
        this.add(pane);

        this.add(inputLabels[0]);
        this.add(textFields[0]);

        this.add(inputLabels[1]);
        this.add(textFields[1]);

        this.add(inputLabels[2]);
        this.add(textFields[2]);

        this.add(inputLabels[3]);
        this.add(textFields[3]);

        this.add(submitButton);
    }

    public CalendarAddFrame() {
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

    private void  _addEvent() {
        // TODO: Add Exceptions and check for typos when entering this data!!!
        CalendarEvent calEvent = new CalendarEvent();
        calEvent.setName(textFields[0].getText());
        calEvent.setDescription(textFields[1].getText());

        String startTime = textFields[2].getText();

        calEvent.setStartTimeHour(Integer.parseInt(startTime.substring(0, 2)));
        calEvent.setStartTimeMinute(Integer.parseInt(startTime.substring(3, 5)));

        String endTime = textFields[3].getText();

        calEvent.setEndTimeHour(Integer.parseInt(endTime.substring(0, 2)));
        calEvent.setEndTimeMinute(Integer.parseInt(endTime.substring(3, 5)));

        calEvent.setDay(_getSelectedDay());
        calEvent.setMonth((cal.get(Calendar.MONTH)+1));
        calEvent.setYear(cal.get(Calendar.YEAR));
        EventQueryProcessor.addEvent(calEvent);
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
        } else if (obj == submitButton) {
            this._addEvent();
        }
    }
}
