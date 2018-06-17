import CalendarData.EventQueryProcessor;
import GUI.LoginFrame;
import Settings.SettingsLoader;

import java.awt.*;

public class Calendar {
    public static void main(String[] args) {

        EventQueryProcessor.setEventAmount();
        SettingsLoader.loadSettings();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame();
            }
        });
    }
}
