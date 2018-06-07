package CalendarData;

import GUI.CalendarDeleteFrame;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class EventQueryProcessor {

    public static int EVENT_AMOUNT;

    public  static void setEventAmount() {
        EventQueryProcessor.EVENT_AMOUNT = 0;
        try {
            FileReader fr = new FileReader("assets/events");
            BufferedReader br = new BufferedReader(fr);
            String helper;
            while ((helper = br.readLine()) != null) {
                if (helper.isEmpty())
                    continue;
                else if (helper.charAt(0) == '<')
                    EventQueryProcessor.EVENT_AMOUNT = Integer.parseInt(helper.substring(1,3));
            }
            br.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void addEvent(CalendarEvent event) {
        try {
            FileWriter fw = new FileWriter("assets/events", true);
            BufferedWriter bw = new BufferedWriter(fw);

            if (event.getId() > 9)
                bw.write('<' + String.valueOf(event.getId()) + '>' + "\r\n");
            else
                bw.write("<0" + String.valueOf(event.getId()) + '>' + "\r\n");

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
            JOptionPane.showMessageDialog(null, "Error: " + exc.getMessage());
        }
    }

    public static void editEvent(CalendarEvent event) {
        try {
            // Strings used to transfer data from old file to the new file and the object indexNo
            String helper;
            StringBuffer inputBuffer = new StringBuffer();
            int indexNo = 0, stageNo = 0;

            //Reading part
            FileReader fr = new FileReader("assets/events");
            BufferedReader br = new BufferedReader(fr);


            while ((helper = br.readLine()) != null) {
                if (helper.isEmpty() || helper.charAt(0) == '#')
                    inputBuffer.append(helper + "\r\n");

                else {
                    if (helper.charAt(0) == '<')
                        indexNo = Integer.parseInt(helper.substring(1,3));

                    if (event.getId() == indexNo) {
                     if (stageNo == 0) {
                            if (event.getId() > 9)
                                inputBuffer.append('<' + String.valueOf(event.getId()) + '>' + "\r\n");
                            else
                                inputBuffer.append("<0" + String.valueOf(event.getId()) + '>' + "\r\n");
                            stageNo++;
                        }
                        else if (stageNo == 1) {
                            // Reading
                            inputBuffer.append('[' + event.getName() + ']' + "\r\n");
                            stageNo++;
                        } else if (stageNo == 2) {
                            if(event.getDay() > 9)
                                inputBuffer.append("Day =  " + event.getDay() + "\r\n");
                            else
                                inputBuffer.append("Day = 0" + event.getDay() + "\r\n");
                            stageNo++;
                        } else if (stageNo == 3) {
                            if(event.getMonth() > 9)
                                inputBuffer.append("Month = " + event.getMonth() + "\r\n");
                            else
                                inputBuffer.append("Month = 0" + event.getMonth() + "\r\n");
                            stageNo++;
                        } else if (stageNo == 4) {
                            inputBuffer.append("Year = " + event.getYear() + "\r\n");
                            stageNo++;
                        } else if (stageNo == 5) {
                            if (event.getStartTimeMinute() > 9 && event.getStartTimeHour() > 9)
                                inputBuffer.append("StartTime = " + event.getStartTimeHour() + ":" + event.getStartTimeMinute() + "\r\n");
                            else if (event.getStartTimeMinute() > 9 && event.getStartTimeHour() < 9)
                                inputBuffer.append("StartTime = 0" + event.getStartTimeHour() + ":" + event.getStartTimeMinute() + "\r\n");
                            else if (event.getStartTimeMinute() < 9 && event.getStartTimeHour() > 9)
                                inputBuffer.append("StartTime = " + event.getStartTimeHour() + ":0" + event.getStartTimeMinute() + "\r\n");
                            else if (event.getStartTimeMinute() < 9 && event.getStartTimeHour() < 9)
                                inputBuffer.append("StartTime = 0" + event.getStartTimeHour() + ":0" + event.getStartTimeMinute() + "\r\n");
                            stageNo++;
                        } else if (stageNo == 6) {
                            if (event.getEndTimeMinute() > 9 && event.getEndTimeHour()  > 9)
                                inputBuffer.append("EndTime = " + event.getEndTimeHour()  + ":" + event.getEndTimeMinute() + "\r\n");
                            else if (event.getEndTimeMinute() > 9 && event.getEndTimeHour()  < 9)
                                inputBuffer.append("EndTime = 0" + event.getEndTimeHour()  + ":" + event.getEndTimeMinute() + "\r\n");
                            else if (event.getEndTimeMinute() < 9 && event.getEndTimeHour()  > 9)
                                inputBuffer.append("EndTime = " + event.getEndTimeHour()  + ":0" + event.getEndTimeMinute() + "\r\n");
                            else if (event.getEndTimeMinute() < 9 && event.getEndTimeHour()  < 9)
                                inputBuffer.append("EndTime = 0" + event.getEndTimeHour()  + ":0" + event.getEndTimeMinute() + "\r\n");
                            stageNo++;
                        } else if (stageNo == 7) {
                            inputBuffer.append("TeamA-Squad = " + event.getTeamASquad() + "\r\n");
                            stageNo++;
                        } else if (stageNo == 8) {
                            inputBuffer.append("TeamB-Squad = " + event.getTeamBSquad() + "\r\n");
                            stageNo++;
                        } else if (stageNo == 9) {
                            inputBuffer.append("TicketPrice = " + event.getTicketPrice() + "\r\n");
                            stageNo++;
                        } else if (stageNo == 10) {
                            inputBuffer.append("Stadium = " + event.getStadium() + "\r\n");
                            stageNo++;
                        } else if (stageNo == 11) {
                            inputBuffer.append("Description = " + event.getDescription() + "\r\n\r\n");
                            stageNo = 0;
                        }
                    } else
                        inputBuffer.append(helper + "\r\n");
                }
            }
            br.close();

            FileWriter fw = new FileWriter("assets/events");
            BufferedWriter bw = new BufferedWriter(fw);
            String outputFileString = inputBuffer.toString();
            bw.write(outputFileString);
            bw.close();

            } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void deleteEvent(CalendarEvent event) {

        try {
            // Strings used to transfer data from old file to the new file and the object indexNo
            String helper;
            StringBuffer inputBuffer = new StringBuffer();
            int indexNo = 0, stageNo = 0;

            //Reading part
            FileReader fr = new FileReader("assets/events");
            BufferedReader br = new BufferedReader(fr);


            while ((helper = br.readLine()) != null) {
                if (helper.isEmpty() || helper.charAt(0) == '#')
                    inputBuffer.append(helper + "\r\n");

                else {
                    if (helper.charAt(0) == '<')
                        indexNo = Integer.parseInt(helper.substring(1,3));

                    if (event.getId() == indexNo) {
                        inputBuffer.append("");
                    } else
                        inputBuffer.append(helper + "\r\n");
                }
            }
            br.close();

            FileWriter fw = new FileWriter("assets/events");
            BufferedWriter bw = new BufferedWriter(fw);
            String outputFileString = inputBuffer.toString();
            bw.write(outputFileString);
            bw.close();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
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
                if (stringHelper.isEmpty() || stringHelper.charAt(0) == '#')
                    continue;
                else if (counter == 0) {
                    eventHelper = new CalendarEvent();
                    eventHelper.setId(Integer.parseInt(stringHelper.substring(1,3)));
                    counter++;
                }

                else if (counter == 1) {
                    // Reading
                    eventHelper.setName(stringHelper.substring(1, stringHelper.length()-1));
                    counter++;
                } else if (counter == 2) {
                    eventHelper.setDay(Integer.parseInt(stringHelper.substring(7, stringHelper.length())));
                    counter++;
                } else if (counter == 3) {
                    eventHelper.setMonth(Integer.parseInt(stringHelper.substring(8, stringHelper.length())));
                    counter++;
                } else if (counter == 4) {
                    eventHelper.setYear(Integer.parseInt(stringHelper.substring(7, stringHelper.length())));
                    counter++;
                } else if (counter == 5) {
                    eventHelper.setStartTimeHour(Integer.parseInt(stringHelper.substring(12, 14)));
                    eventHelper.setStartTimeMinute(Integer.parseInt(stringHelper.substring(15, 17)));
                    counter++;
                } else if (counter == 6) {
                    eventHelper.setEndTimeHour(Integer.parseInt(stringHelper.substring(10, 12)));
                    eventHelper.setEndTimeMinute(Integer.parseInt(stringHelper.substring(13, 15)));
                    counter++;
                } else if (counter == 7) {
                    eventHelper.setTeamASquad(stringHelper.substring(14, stringHelper.length()));
                    counter++;
                } else if (counter == 8) {
                    eventHelper.setTeamBSquad(stringHelper.substring(14, stringHelper.length()));
                    counter++;
                } else if (counter == 9) {
                    eventHelper.setTicketPrice(Double.parseDouble(stringHelper.substring(14, stringHelper.length())));
                    counter++;
                } else if (counter == 10) {
                    eventHelper.setStadium(stringHelper.substring(10, stringHelper.length()));
                    counter++;
                } else if (counter == 11) {
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
