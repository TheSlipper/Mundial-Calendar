import GUI.LoginFrame;

import java.awt.*;

public class Calendar {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // new AdminMainFrame();
                new LoginFrame();
            }
        });
    }
}
