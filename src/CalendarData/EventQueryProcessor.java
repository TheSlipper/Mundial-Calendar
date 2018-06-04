package CalendarData;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class EventQueryProcessor {

    public static void addEvent(CalendarEvent event) {
        try {
            FileWriter fw = new FileWriter("assets/events", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write('[' + event.getName() + ']' + "\r\n");
            if(event.getDay() > 9)
                bw.write("Day =  " + event.getDay() + "\r\n");
            else
                bw.write("Day = 0" + event.getDay() + "\r\n");
            if(event.getMonth() > 9)
                bw.write("Month = " + event.getMonth() + "\r\n");
            else
                bw.write("Month = 0" + event.getMonth() + "\r\n");
            bw.write("Year = " + event.getYear() + "\r\n");
            if (event.getStartTimeMinute() > 9 && event.getStartTimeHour() > 9)
                bw.write("StartTime = " + event.getStartTimeHour() + ":" + event.getStartTimeMinute() + "\r\n");
            else if (event.getStartTimeMinute() > 9 && event.getStartTimeHour() < 9)
                bw.write("StartTime = 0" + event.getStartTimeHour() + ":" + event.getStartTimeMinute() + "\r\n");
            else if (event.getStartTimeMinute() < 9 && event.getStartTimeHour() > 9)
                bw.write("StartTime = " + event.getStartTimeHour() + ":0" + event.getStartTimeMinute() + "\r\n");
            else if (event.getStartTimeMinute() < 9 && event.getStartTimeHour() < 9)
                bw.write("StartTime = 0" + event.getStartTimeHour() + ":0" + event.getStartTimeMinute() + "\r\n");

            if (event.getEndTimeMinute() > 9 && event.getEndTimeHour()  > 9)
                bw.write("StartTime = " + event.getEndTimeHour()  + ":" + event.getEndTimeMinute() + "\r\n");
            else if (event.getEndTimeMinute() > 9 && event.getEndTimeHour()  < 9)
                bw.write("StartTime = 0" + event.getEndTimeHour()  + ":" + event.getEndTimeMinute() + "\r\n");
            else if (event.getEndTimeMinute() < 9 && event.getEndTimeHour()  > 9)
                bw.write("StartTime = " + event.getEndTimeHour()  + ":0" + event.getEndTimeMinute() + "\r\n");
            else if (event.getEndTimeMinute() < 9 && event.getEndTimeHour()  < 9)
                bw.write("StartTime = 0" + event.getEndTimeHour()  + ":0" + event.getEndTimeMinute() + "\r\n");

//            bw.write("EndTime = " + event.getEndTimeHour() + ":" + event.getEndTimeMinute() + "\r\n");
            bw.write("Description = " + event.getDescription() + "\r\n\r\n");
            bw.close();

        } catch (IOException exc) {
            JOptionPane.showMessageDialog(null, "Error: " + exc.getMessage());
        } catch (Exception exc) {
            // TODO: Handle this error by printing out the err.println in some text file later
            JOptionPane.showMessageDialog(null, "Error: Unknown error has occured");
        }
    }

    public static ArrayList<CalendarEvent> getEventByDate(byte day, byte month, short year) {
        ArrayList<CalendarEvent> events = new ArrayList<CalendarEvent>();

        try {
            FileReader fr = new FileReader("assets/events");
            BufferedReader br = new BufferedReader(fr);
            CalendarEvent eventHelper;
            String stringHelper;
            int counter = 0;

            while((stringHelper = br.readLine()) != null) {
                // TODO: Do some stuff

            }
            br.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: File not found exception");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return events;
    }

}
