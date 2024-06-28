package ortho.be.ggames.ortho.event;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import ortho.be.ggames.ortho.windows.CalendarWindow;

public class SearchPatientDialog extends JDialog {

    private JTextField searchField;
    private JList<Patient> patientList;
    private JButton selectButton;
    private List<Patient> patients;
    private int hour;

    public SearchPatientDialog(Frame parent, List<Patient> patients, int hour) {
        super(parent, "Rechercher un patient", true);
        this.patients = patients;
        this.hour = hour;

        JPanel panel = new JPanel(new BorderLayout());

        searchField = new JTextField();
        panel.add(searchField, BorderLayout.NORTH);

        patientList = new JList<>(patients.toArray(new Patient[0]));
        panel.add(new JScrollPane(patientList), BorderLayout.CENTER);

        selectButton = new JButton("Sélectionner");
        selectButton.addActionListener(e -> {
            Patient selectedPatient = patientList.getSelectedValue();
            if (selectedPatient != null) {
                ((CalendarWindow) parent).updateCalendar(hour, 0, selectedPatient);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez un patient.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(selectButton, BorderLayout.SOUTH);

        add(panel);
        pack();
        setLocationRelativeTo(parent);
    }
}

