package ortho.be.ggames.ortho.windows;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ortho.be.ggames.ortho.MenuUtil;
import ortho.be.ggames.ortho.OrthoBE;
import ortho.be.ggames.ortho.event.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PatientSearchWindow extends JFrame {

    private List<Patient> patients;

    private JTextField searchField;
    private JTextArea resultArea;

    private static final String DATA_DIRECTORY = OrthoBE.getAppDataDirectory();

    public PatientSearchWindow() {
        setTitle("Recherche de dossiers de patients");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        loadPatientsFromJson(DATA_DIRECTORY + "/patients.json");

        JMenuBar menuBar = MenuUtil.createMenuBar();
        setJMenuBar(menuBar);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        JLabel searchLabel = new JLabel("Rechercher un patient par nom :");
        searchPanel.add(searchLabel);

        searchField = new JTextField(20);
        searchPanel.add(searchField);

        JButton searchButton = new JButton("Rechercher");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);
        searchField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performSearch();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    private void loadPatientsFromJson(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filename);

        try {
            if (!file.exists()) {
                createInitialJsonFile(file);
            }

            patients = objectMapper.readValue(file, new TypeReference<List<Patient>>() {});
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des patients à partir du fichier JSON.", "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void createInitialJsonFile(File file) {
        Patient testPatient = new Patient("Test", "Test", "0");

        List<Patient> initialPatients = new ArrayList<>();
        initialPatients.add(testPatient);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, initialPatients);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la création du fichier JSON initial.", "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void performSearch() {
        String query = searchField.getText().trim().toLowerCase();
        resultArea.setText("");

        if (query.isEmpty()) {
            resultArea.append("Veuillez entrer un nom pour la recherche.");
            return;
        }

        boolean found = false;
        for (Patient patient : patients) {
            if (patient.getName().toLowerCase().contains(query)) {
                resultArea.append(patient.toString() + "\n");
                found = true;
            }
        }

        if (!found) {
            resultArea.append("Aucun patient trouvé pour : " + query);
        }
    }
}
