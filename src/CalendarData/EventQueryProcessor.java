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
                bw.write("EndTime = " + event.getEndTimeHour()  + ":" + event.getEndTimeMinute() + "\r\n");
            else if (event.getEndTimeMinute() > 9 && event.getEndTimeHour()  < 9)
                bw.write("EndTime = 0" + event.getEndTimeHour()  + ":" + event.getEndTimeMinute() + "\r\n");
            else if (event.getEndTimeMinute() < 9 && event.getEndTimeHour()  > 9)
                bw.write("EndTime = " + event.getEndTimeHour()  + ":0" + event.getEndTimeMinute() + "\r\n");
            else if (event.getEndTimeMinute() < 9 && event.getEndTimeHour()  < 9)
                bw.write("EndTime = 0" + event.getEndTimeHour()  + ":0" + event.getEndTimeMinute() + "\r\n");

            // Here add: TeamA-Squad, TeamB-Squad, TicketPrice, MatchPlace
            bw.write("TeamA-Squad = " + event.getTeamASquad() + "\r\n");
            bw.write("TeamB-Squad = " + event.getTeamBSquad() + "\r\n");
            bw.write("TicketPrice = " + event.getTicketPrice() + "\r\n");
            bw.write("Stadium = " + event.getStadium() + "\r\n");

            bw.write("Description = " + event.getDescription() + "\r\n\r\n");
            bw.close();

        } catch (IOException exc) {
            JOptionPane.showMessageDialog(null, "Error: " + exc.getMessage());
        } catch (Exception exc) {
            // TODO: Handle this error by printing out the err.println in some text file later
            JOptionPane.showMessageDialog(null, "Error: Unknown error has occured");
        }
    }

    public static ArrayList<CalendarEvent> getEventByDate(int day, int month, int year) {
        ArrayList<CalendarEvent> events = new ArrayList<CalendarEvent>();

        try {
            FileReader fr = new FileReader("assets/events");
            BufferedReader br = new BufferedReader(fr);
            CalendarEvent eventHelper = new CalendarEvent();
            String stringHelper;
            int counter = 0;

            while((stringHelper = br.readLine()) != null) {
                // TODO: Do some stuff
                // 10 lines
                if (stringHelper.isEmpty() || stringHelper.charAt(0) == '#')
                    continue;
                else if (counter == 0) {
                    // Reading
                    eventHelper = new CalendarEvent();
                    eventHelper.setName(stringHelper.substring(1, stringHelper.length()-1));
                    counter++;
                } else if (counter == 1) {
                    // TODO: Make sure that days are two numbered or if they work like that:
                    eventHelper.setDay(Integer.parseInt(stringHelper.substring(7, stringHelper.length())));
                    counter++;
                } else if (counter == 2) {
                    eventHelper.setMonth(Integer.parseInt(stringHelper.substring(8, stringHelper.length())));
                    counter++;
                } else if (counter == 3) {
                    eventHelper.setYear(Integer.parseInt(stringHelper.substring(7, stringHelper.length())));
                    counter++;
                } else if (counter == 4) {
                    eventHelper.setStartTimeHour(Integer.parseInt(stringHelper.substring(12, 14)));
                    eventHelper.setStartTimeMinute(Integer.parseInt(stringHelper.substring(15, 17)));
                    counter++;
                } else if (counter == 5) {
                    eventHelper.setEndTimeHour(Integer.parseInt(stringHelper.substring(10, 12)));
                    eventHelper.setEndTimeMinute(Integer.parseInt(stringHelper.substring(13, 15)));
                    counter++;
                } else if (counter == 6) {
                    eventHelper.setTeamASquad(stringHelper.substring(14, stringHelper.length()));
                    counter++;
                } else if (counter == 7) {
                    eventHelper.setTeamBSquad(stringHelper.substring(14, stringHelper.length()));
                    counter++;
                } else if (counter == 8) {
                    eventHelper.setTicketPrice(Double.parseDouble(stringHelper.substring(14, stringHelper.length())));
                    counter++;
                } else if (counter == 9) {
                    eventHelper.setStadium(stringHelper.substring(10, stringHelper.length()));
                    counter++;
                } else if (counter == 10) {
                    eventHelper.setDescription(stringHelper.substring(14, stringHelper.length()));
                    counter = 0;
                    if (eventHelper.getYear() == year && eventHelper.getDay() == day && eventHelper.getMonth() == month) {
                        events.add(eventHelper);
                    }
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return events;
    }

}
