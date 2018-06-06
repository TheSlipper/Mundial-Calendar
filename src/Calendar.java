import CalendarData.EventQueryProcessor;
import Exceptions.InvalidTextEnteredException;
import GUI.AdminMainFrame;

import java.awt.*;

public class Calendar {
    public static void main(String[] args) throws InvalidTextEnteredException {
        EventQueryProcessor.setEventAmount();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                new LoginFrame();
                new AdminMainFrame();
            }
        });
    }
}
