package ortho.be.ggames.ortho;

import javax.swing.*;

import ortho.be.ggames.ortho.windows.PatientSearchWindow;
import ortho.be.ggames.ortho.windows.AddPatientWindow;
import ortho.be.ggames.ortho.windows.CalendarWindow;
import ortho.be.ggames.ortho.windows.MainWindow;
import ortho.be.ggames.ortho.windows.SecondWindow;

public class MenuUtil {
    public static JMenuBar createMenuBar() {
        WindowRegistry registry = new WindowRegistry();
        registry.registerWindow("Dossiers", PatientSearchWindow.class);
        registry.registerWindow("Add", AddPatientWindow.class);
        registry.registerWindow("Calendar", CalendarWindow.class);
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Fichier");
        JMenu helpMenu = new JMenu("Aide");

        JMenuItem newItem = new JMenuItem("Nouveau");
        JMenuItem openItem = new JMenuItem("Ouvrir");
        JMenuItem exitItem = new JMenuItem("Quitter");
        
        JMenuItem dossiersWindowItem = new JMenuItem("Ouvrir la fenêtre des recherchs dossiers");
        JMenuItem addWindowItem = new JMenuItem("Ouvrir la fenetre de création");
        JMenuItem calendarWindowItem = new JMenuItem("Ouvrir la fenetre du calendrier");

        dossiersWindowItem.addActionListener(e -> registry.openWindow("Dossiers"));
        addWindowItem.addActionListener(e -> registry.openWindow("Add"));
        calendarWindowItem.addActionListener(e -> registry.openWindow("Calendar"));

        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(dossiersWindowItem);
        fileMenu.add(addWindowItem);
        fileMenu.add(calendarWindowItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }
}

