package Settings;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class SettingsLoader {

    private static Dimension adminFrameDimension, userFrameDimension;
    private static int fontSize, fontStyle;
    private final static String confFilePath = "assets/settings";

    // Getters and setters
    public static Dimension getAdminFrameDimension() {
        return adminFrameDimension;
    }

    public static void setAdminFrameDimension(Dimension frameDimension) {
        SettingsLoader.adminFrameDimension = frameDimension;
    }

    public static Dimension getUserFrameDimension() {
        return userFrameDimension;
    }

    public static void setUserFrameDimension(Dimension userFrameDimension) {
        SettingsLoader.userFrameDimension = userFrameDimension;
    }

    public static int getFontSize() {
        return fontSize;
    }

    public static void setFontSize(int fontSize) {
        SettingsLoader.fontSize = fontSize;
    }

    public static String getConfFilePath() {
        return confFilePath;
    }

    public static int getFontStyle() {
        return fontStyle;
    }

    public static void setFontStyle(int fontStyle) {
        SettingsLoader.fontStyle = fontStyle;
    }

    // Misc
    public static void loadSettings() {
        try {
            FileReader fr = new FileReader(confFilePath);
            BufferedReader br = new BufferedReader(fr);
            String helper;
            int settingIndex = 0;
            int adminHeight = 0, adminWidth = 0;
            int userHeight = 0, userWidth = 0;

            while ((helper = br.readLine()) != null) {
                if (helper.isEmpty() || helper.charAt(0) == '#')
                    continue;
                else if (settingIndex == 0) {
                    adminHeight = Integer.parseInt(helper);
                    settingIndex++;
                } else if (settingIndex == 1) {
                    adminWidth = Integer.parseInt(helper);
                    settingIndex++;
                } else if (settingIndex == 2) {
                    userHeight = Integer.parseInt(helper);
                    settingIndex++;
                } else if (settingIndex == 3) {
                    userWidth = Integer.parseInt(helper);
                    settingIndex++;
                } else if (settingIndex == 4){
                    fontSize = Integer.parseInt(helper);
                    settingIndex++;
                } else if (settingIndex == 5) {
                    fontStyle = Integer.parseInt(helper);
                }
            }
            br.close();
            SettingsLoader.setAdminFrameDimension(new Dimension(adminWidth, adminHeight));
            SettingsLoader.setUserFrameDimension(new Dimension(userWidth, userHeight));
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error:" + exc.getMessage(), "SETTINGS ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
