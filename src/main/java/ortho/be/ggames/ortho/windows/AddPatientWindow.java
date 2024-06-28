package ortho.be.ggames.ortho.windows;

import com.fasterxml.jackson.databind.ObjectMapper;

import ortho.be.ggames.ortho.OrthoBE;
import ortho.be.ggames.ortho.event.FullPatient;
import ortho.be.ggames.ortho.event.Patient;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AddPatientWindow extends JFrame {

    private JTextField nameField;
    private JTextField forenameField;
    private JTextField birthdayField;
    private JTextField adressField;
    private JTextField phoneField;
    private List<Patient> patients;
    private static final String DATA_DIRECTORY = OrthoBE.getAppDataDirectory();
    private static final String PATIENTS_FILE = DATA_DIRECTORY + "/patients.json";

    public AddPatientWindow() {
        setTitle("Ajouter un nouveau patient");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        loadPatientsFromJson(PATIENTS_FILE);
        JPanel addPatientPanel = new JPanel();
        addPatientPanel.setLayout(new GridLayout(3, 2));
        
        //ajouter nom
        JLabel nameLabel = new JLabel("Nom:");
        addPatientPanel.add(nameLabel);
        nameField = new JTextField(20);
        addPatientPanel.add(nameField);
        
        //ajouter prénom
        JLabel forenameLabel = new JLabel("Prénom:");
        addPatientPanel.add(forenameLabel);
        forenameField = new JTextField(1);
        addPatientPanel.add(forenameField);
        
        //ajouter anniversaire
        JLabel birthdayLabel = new JLabel("Date de naissance:");
        addPatientPanel.add(birthdayLabel);

        try {
            MaskFormatter mask = new MaskFormatter("##/##/####");
            mask.setPlaceholderCharacter('_');
            birthdayField = new JFormattedTextField(mask);
        } catch (ParseException e) {
            e.printStackTrace();
            birthdayField = new JFormattedTextField();
        }

        addPatientPanel.add(birthdayField);
        
        //ajouter adresse
        JLabel adressLabel = new JLabel("Adresse:");
        addPatientPanel.add(adressLabel);
        adressField = new JTextField(1);
        addPatientPanel.add(adressField);
        
        //ajouter téléphone
        JLabel phoneLabel = new JLabel("Téléphone:");
        addPatientPanel.add(phoneLabel);
        
        try {
            MaskFormatter mask = new MaskFormatter("####/##.##.##");
            mask.setPlaceholderCharacter('_');
            phoneField = new JFormattedTextField(mask);
        } catch (ParseException e) {
            e.printStackTrace();
            phoneField = new JFormattedTextField();
        }
        addPatientPanel.add(phoneField);

        JButton addButton = new JButton("Ajouter");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPatient();
            }
        });
        addPatientPanel.add(addButton);

        add(addPatientPanel, BorderLayout.NORTH);
    }

    private void loadPatientsFromJson(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filename);

        try {
            if (!file.exists()) {
                createInitialJsonFile(file);
            }

            patients = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Patient.class));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des patients à partir du fichier JSON.", "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void createInitialJsonFile(File file) {
        Patient testPatient = new Patient("Test", "Test", "0");

        patients = new ArrayList<>();
        patients.add(testPatient);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, patients);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la création du fichier JSON initial.", "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void addPatient() {
        String name = nameField.getText().trim();
        String forename = forenameField.getText().trim();
        String birthday = birthdayField.getText().trim();
        String adress = adressField.getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty()|forename.isEmpty()|birthday.isEmpty()|adress.isEmpty()|phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir le champ du nom.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int newId = generateNewPatientId();

        FullPatient newFullPatient = new FullPatient(name, forename, birthday, adress, phone, String.valueOf(newId));
        
        savePatientToJson(newFullPatient);

        Patient newPatient = new Patient(name, forename, String.valueOf(newId));
        
        patients.add(newPatient);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(PATIENTS_FILE), patients);
            JOptionPane.showMessageDialog(this, "Patient ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            nameField.setText("");
            forenameField.setText("");
            birthdayField.setText("");
            adressField.setText("");
            phoneField.setText("");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde du patient.", "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void savePatientToJson(FullPatient patient) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String patientFileName = DATA_DIRECTORY + "/patients/" + patient.getId() + ".json";
            objectMapper.writeValue(new File(patientFileName), patient);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde du patient.", "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private int generateNewPatientId() {
        int maxId = 0;
        for (Patient patient : patients) {
            int id = Integer.parseInt(patient.getId());
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId + 1;
    }
}
