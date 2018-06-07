import CalendarData.EventQueryProcessor;
import Exceptions.InvalidTextEnteredException;
import GUI.AdminMainFrame;
import GUI.LoginFrame;
import GUI.UserMainFrame;
import Settings.SettingsLoader;

import java.awt.*;

public class Calendar {
    public static void main(String[] args) throws InvalidTextEnteredException {
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
