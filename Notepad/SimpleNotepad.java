import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.undo.*;

public class SimpleNotepad {
    private JFrame frame;
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File currentFile;
    private UndoManager undoManager;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleNotepad().initialize());
    }

    private void initialize() {
        frame = new JFrame("Notepad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        textArea = new JTextArea();
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        undoManager = new UndoManager();
        textArea.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));
        
        fileChooser = new JFileChooser();
        
        createMenuBar();
        
        frame.setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
   // File menu
JMenu fileMenu = new JMenu("File");
menuBar.add(fileMenu);

JMenuItem newItem = new JMenuItem("New");
newItem.addActionListener(e -> newFile());
fileMenu.add(newItem);

JMenuItem openItem = new JMenuItem("Open...");
openItem.addActionListener(e -> openFile());
fileMenu.add(openItem);

JMenuItem saveItem = new JMenuItem("Save");
saveItem.addActionListener(e -> saveFile());
fileMenu.add(saveItem);

JMenuItem saveAsItem = new JMenuItem("Save As...");
saveAsItem.addActionListener(e -> saveFileAs());
fileMenu.add(saveAsItem);

fileMenu.addSeparator();

JMenuItem exitItem = new JMenuItem("Exit");
exitItem.addActionListener(e -> System.exit(0));
fileMenu.add(exitItem);

// Edit menu
JMenu editMenu = new JMenu("Edit");
menuBar.add(editMenu);

JMenuItem undoItem = new JMenuItem("Undo");
undoItem.addActionListener(e -> undoAction());
editMenu.add(undoItem);

JMenuItem cutItem = new JMenuItem("Cut");
cutItem.addActionListener(e -> textArea.cut());
editMenu.add(cutItem);

JMenuItem copyItem = new JMenuItem("Copy");
copyItem.addActionListener(e -> textArea.copy());
editMenu.add(copyItem);

JMenuItem pasteItem = new JMenuItem("Paste");
pasteItem.addActionListener(e -> textArea.paste());
editMenu.add(pasteItem);

JMenuItem findItem = new JMenuItem("Find...");
findItem.addActionListener(e -> findText());
editMenu.add(findItem);

frame.setJMenuBar(menuBar);

    }

    private JMenuItem createMenuItem(String text, ActionListener action) {
        JMenuItem item = new JMenuItem(text);
        item.addActionListener(action);
        return item;
    }

    private void newFile() {
        textArea.setText("");
        currentFile = null;
    }

    private void openFile() {
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                currentFile = file;
            } catch (IOException ex) {
                showError("Error opening file");
            }
        }
    }

    private void saveFile() {
        if (currentFile == null) {
            saveFileAs();
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                writer.write(textArea.getText());
            } catch (IOException ex) {
                showError("Error saving file");
            }
        }
    }

    private void saveFileAs() {
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                currentFile = file;
            } catch (IOException ex) {
                showError("Error saving file");
            }
        }
    }

    private void undoAction() {
        if (undoManager.canUndo()) {
            undoManager.undo();
        }
    }

    private void findText() {
        String search = JOptionPane.showInputDialog(frame, "Find text:");
        if (search != null && !search.isEmpty()) {
            String content = textArea.getText();
            int index = content.indexOf(search);
            if (index != -1) {
                textArea.select(index, index + search.length());
            } else {
                JOptionPane.showMessageDialog(frame, "Text not found", "Find", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
