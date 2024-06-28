package ortho.be.ggames.ortho;

import java.io.File;

import javax.swing.*;

import ortho.be.ggames.ortho.windows.AddPatientWindow;
import ortho.be.ggames.ortho.windows.MainWindow;
import ortho.be.ggames.ortho.windows.SecondWindow;

public class OrthoBE {
    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Ma Application");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowMainWindow();
            }
        });
    }

    private static void createAndShowMainWindow() {
    	MainWindow mainWindow = new MainWindow();
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setVisible(true);
    }
    
    public static String getAppDataDirectory() {
        String os = System.getProperty("os.name").toLowerCase();
        String appDataDir = "";

        if (os.contains("mac")) {
            appDataDir = System.getProperty("user.home") + "/Library/Application Support/OrthoBE";
        } else if (os.contains("win")) {
            appDataDir = System.getenv("APPDATA") + "/OrthoBE";
        } else {
            appDataDir = System.getProperty("user.home") + "/OrthoBE";
        }

        File directory = new File(appDataDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        return appDataDir;
    }
}
