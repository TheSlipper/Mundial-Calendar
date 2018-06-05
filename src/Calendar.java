import CalendarData.EventQueryProcessor;
import GUI.AdminMainFrame;
import GUI.LoginFrame;

import java.awt.*;

public class Calendar {
    public static void main(String[] args) {
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
