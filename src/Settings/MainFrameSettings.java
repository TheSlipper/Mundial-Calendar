package Settings;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class MainFrameSettings {

    private static Dimension frameDimension;
    private static int fontSize;
    private static String confFilePath;
    private static String fontName;

    public static void loadSettings() {
        try {
            FileReader fr = new FileReader(confFilePath);
            BufferedReader br = new BufferedReader(fr);
            String helper;

            while ((helper = br.readLine()) != null) {

                // TODO: if helper contains do this:

//                for (int i=0; i < helper.length(); i++) {
//
//                }
            }

        } catch (Exception exc) {
            System.out.println("Dicke en bollz");
        }
    }
}
