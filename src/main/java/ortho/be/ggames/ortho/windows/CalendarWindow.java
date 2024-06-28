package ortho.be.ggames.ortho.windows;

import javax.swing.*;

import ortho.be.ggames.ortho.event.Patient;
import ortho.be.ggames.ortho.event.SearchPatientDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CalendarWindow extends JFrame {

    private JButton[][] calendarButtons;
    private List<Patient> patients;

    public CalendarWindow() {
        setTitle("Calendrier des rendez-vous");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialisation du calendrier
        calendarButtons = new JButton[24][7]; // 24 heures x 7 jours

        JPanel calendarPanel = new JPanel(new GridLayout(24, 7));
        for (int hour = 0; hour < 24; hour++) {
            for (int day = 0; day < 7; day++) {
                JButton button = new JButton(hour + ":00");
                int finalHour = hour;
                button.addActionListener(e -> openSearchPatientDialog(finalHour));
                calendarButtons[hour][day] = button;
                calendarPanel.add(button);
            }
        }
        add(new JScrollPane(calendarPanel), BorderLayout.CENTER);

        // Chargement des patients depuis le fichier JSON
        patients = loadPatientsFromJson("patients.json");
    }

    private List<Patient> loadPatientsFromJson(String jsonFileName) {
        // Code pour charger les patients depuis un fichier JSON
        // Utilisation de Jackson pour la désérialisation JSON
        // Exemple simplifié :
        List<Patient> patients = new ArrayList<>();
        // Charger depuis le fichier JSON et remplir la liste de patients
        return patients;
    }

    private void openSearchPatientDialog(int hour) {
        SearchPatientDialog dialog = new SearchPatientDialog(this, patients, hour);
        dialog.setVisible(true);
    }

    public void updateCalendar(int hour, int day, Patient patient) {
        JButton button = calendarButtons[hour][day];
        button.setText(patient.toString());
        button.setToolTipText("ID du patient: " + patient.getId());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalendarWindow calendar = new CalendarWindow();
            calendar.setVisible(true);
        });
    }
}
