import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class ExpenseTracker {
    private JFrame frame;
    private JTextField amountField;
    private JTextField categoryField;
    private JTextArea reportArea;
    private JButton addButton, generateReportButton, resetButton, showAllButton;
    private final List<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        // Create the ExpenseTracker instance and initialize it directly
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.initialize();
    }

    public void initialize() {
        // Frame setup
        frame = new JFrame("Expense Tracker");
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Input panel for entering expenses
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        frame.add(inputPanel, BorderLayout.NORTH);

        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);

        addButton = new JButton("Add Expense");
        inputPanel.add(addButton);

        // Report area to display expenses
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        frame.add(new JScrollPane(reportArea), BorderLayout.CENTER);

        // Buttons for generating report, resetting data and showing all expenses
        JPanel buttonPanel = new JPanel();
        generateReportButton = new JButton("Generate Report");
        resetButton = new JButton("Reset Tracker");
        showAllButton = new JButton("Show All Expenses");
        buttonPanel.add(generateReportButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(showAllButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners
        addButton.addActionListener(this::addExpense);
        generateReportButton.addActionListener(this::generateReport);
        resetButton.addActionListener(this::resetTracker);
        showAllButton.addActionListener(this::showAllExpenses);

        frame.setVisible(true);
    }

    private void addExpense(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String category = categoryField.getText();
            if (amount <= 0 || category.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter valid amount and category", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            expenses.add(new Expense(amount, category));
            amountField.setText("");
            categoryField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateReport(ActionEvent e) {
        // Ask for confirmation before generating the report
        int choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to generate the report?", 
                "Confirm Report Generation", JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            StringBuilder report = new StringBuilder();
            double totalAmount = 0;

            // Generate the report text from expenses
            for (Expense expense : expenses) {
                report.append("Category: ").append(expense.getCategory())
                        .append(", Amount: ₹").append(expense.getAmount()).append("\n");
                totalAmount += expense.getAmount();
            }

            report.append("\nTotal Expenses: ₹").append(totalAmount);
            JOptionPane.showMessageDialog(frame, report.toString(), "Expense Report", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void resetTracker(ActionEvent e) {
        // Clear the list of expenses and reset the report area
        expenses.clear();
        reportArea.setText("");
        amountField.setText("");
        categoryField.setText("");
        JOptionPane.showMessageDialog(frame, "Expense Tracker has been reset!", "Tracker Reset", JOptionPane.INFORMATION_MESSAGE);
    }

    // Show all expenses in the report area
    private void showAllExpenses(ActionEvent e) {
        StringBuilder report = new StringBuilder();
        if (expenses.isEmpty()) {
            report.append("No expenses recorded.");
        } else {
            for (Expense expense : expenses) {
                report.append("Category: ").append(expense.getCategory())
                        .append(", Amount: ₹").append(expense.getAmount()).append("\n");
            }
        }
        reportArea.setText(report.toString());
    }

    // Expense class to store expense details
    class Expense {
        private double amount;
        private String category;

        public Expense(double amount, String category) {
            this.amount = amount;
            this.category = category;
        }

        public double getAmount() {
            return amount;
        }

        public String getCategory() {
            return category;
        }
    }
}
