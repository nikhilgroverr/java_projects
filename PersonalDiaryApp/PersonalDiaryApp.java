import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class PersonalDiaryApp {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField searchField;
    private JPasswordField passwordField;
    private boolean isLoggedIn = false;
    private final String PASSWORD = "1234";  // Change this to your preferred password
    private final String FILE_NAME = "diaryEntries.txt";  // File to save entries

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                PersonalDiaryApp window = new PersonalDiaryApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor
    public PersonalDiaryApp() {
        initialize();
    }

    // Initialize the GUI components
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        showPasswordDialog();  // Show password dialog to protect access

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        JLabel lblSearch = new JLabel("Search Entry: ");
        panel.add(lblSearch);

        searchField = new JTextField();
        panel.add(searchField);
        searchField.setColumns(20);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchEntries());
        panel.add(searchButton);

        textArea = new JTextArea();
        textArea.setEditable(true);
        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        JButton saveButton = new JButton("Save Entry");
        saveButton.addActionListener(e -> saveEntry());
        buttonPanel.add(saveButton);

        JButton newEntryButton = new JButton("New Entry");
        newEntryButton.addActionListener(e -> textArea.setText(""));  // Clears the text area for a new entry
        buttonPanel.add(newEntryButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> textArea.setText(""));
        buttonPanel.add(clearButton);

        JButton deleteAllButton = new JButton("Delete All Entries");
        deleteAllButton.addActionListener(e -> deleteAllEntries());
        buttonPanel.add(deleteAllButton);
    }

    // Show password dialog
    private void showPasswordDialog() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter Password: ");
        passwordField = new JPasswordField(10);
        panel.add(label);
        panel.add(passwordField);

        int option = JOptionPane.showConfirmDialog(frame, panel, "Password Required", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            char[] password = passwordField.getPassword();
            if (new String(password).equals(PASSWORD)) {
                isLoggedIn = true;
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect password. The application will now close.");
                System.exit(0);  // Exit the program if password is incorrect
            }
        } else {
            System.exit(0);  // Exit if user cancels the password dialog
        }
    }

    // Save the diary entry with the current date
    private void saveEntry() {
        if (!isLoggedIn) {
            JOptionPane.showMessageDialog(frame, "You need to log in first!");
            return;
        }

        String entryText = textArea.getText();
        if (entryText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please write something to save.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write("Date: " + date + "\n");
            writer.write(entryText + "\n");
            writer.write("-------------------------------------------------\n");
            JOptionPane.showMessageDialog(frame, "Entry saved successfully!");
            textArea.setText("");  // Clear the text area after saving
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "An error occurred while saving the entry.");
        }
    }

    // Delete all entries in the diary
    private void deleteAllEntries() {
        if (!isLoggedIn) {
            JOptionPane.showMessageDialog(frame, "You need to log in first!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete all entries?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                writer.write("");  // Overwrite the file with an empty string
                JOptionPane.showMessageDialog(frame, "All entries deleted successfully!");
                textArea.setText("");  // Clear the text area
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "An error occurred while deleting entries.");
            }
        }
    }

    // Search entries in the diary
    private void searchEntries() {
        if (!isLoggedIn) {
            JOptionPane.showMessageDialog(frame, "You need to log in first!");
            return;
        }

        String searchKeyword = searchField.getText();
        if (searchKeyword.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a keyword to search.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            textArea.setText("");  // Clear the text area before displaying search results
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchKeyword)) {
                    textArea.append(line + "\n");
                }
            }

            if (textArea.getText().isEmpty()) {
                textArea.append("No entries found matching the keyword: " + searchKeyword);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "An error occurred while searching.");
        }
    }
}
