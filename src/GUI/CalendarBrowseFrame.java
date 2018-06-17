package GUI;

import CalendarData.EventQueryProcessor;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class CalendarBrowseFrame extends JFrame implements ActionListener{

    private DefaultTableModel model;
    private Calendar cal = new GregorianCalendar();
    private JLabel label;
    private JButton b1, b2;
    private  JButton infoButton;
    private JPanel panel;
    private JTable table;
    private JScrollPane pane;
    private final String[] columns = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    private void _updateMonth() {
        cal.set(Calendar.DAY_OF_MONTH, 1);

        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        int year = cal.get(Calendar.YEAR);
        label.setText(month + " " + year);

        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = 6;

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
        this.setTitle("The Calendar");
        this.setSize(300,200);
        this.setLocation(1300, 300);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
    }

    private void _initialiseObjects() {
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);

        b1 = new JButton("<-");
        b1.addActionListener(this);

        b2 = new JButton("->");
        b2.addActionListener(this);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        model = new DefaultTableModel(null, columns);
        table = new JTable(model);
        pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(300, 102));

        infoButton = new JButton("Get Info");
        infoButton.addActionListener(this);
        infoButton.setPreferredSize(new Dimension(100, 43));
    }

    private void _addObjects() {
        panel.add(b1,BorderLayout.WEST);
        panel.add(label,BorderLayout.CENTER);
        panel.add(b2,BorderLayout.EAST);

        this.add(panel,BorderLayout.NORTH);
        this.add(pane,BorderLayout.CENTER);
        this.add(infoButton, BorderLayout.SOUTH);
    }

    public CalendarBrowseFrame() {
        _setUpMetaData();
        _initialiseObjects();
        _addObjects();
        this._updateMonth();
    }

    private void _displayInfo() {
        // Get the day
        try {
            int row = table.getSelectedRow();
            int column = table.getSelectedColumn();
            int day = (int)table.getValueAt(row, column);
            // Get the month
            int month = (cal.get(Calendar.MONTH)+1);
            // Get the year
            int year = (cal.get(Calendar.YEAR));

            ArrayList eventsList = EventQueryProcessor.getEventsByDate(day, month, year);
            if (eventsList.isEmpty())
                throw new Exception();
            String message = "";
            for (int i=0; i < eventsList.size(); i++)
                message = message + eventsList.get(i).toString()+"\r\n\r\n";
            JOptionPane.showMessageDialog(null, message);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "No events found during that day");
        }

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
        } else if (obj == infoButton) {
            _displayInfo();
        }
    }
}
