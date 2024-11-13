import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverter {

    // Currency conversion rates (example rates for USD to other currencies)
    private static final double USD_TO_EUR = 0.85;
    private static final double USD_TO_GBP = 0.75;
    private static final double USD_TO_INR = 75.0;
    private static final double EUR_TO_USD = 1.18;
    private static final double GBP_TO_USD = 1.33;
    private static final double INR_TO_USD = 0.013;

    public static void main(String[] args) {
        // Create a frame for the application
        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Create panels for user input and output
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // Add components
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField(10);

        JLabel fromLabel = new JLabel("From Currency:");
        JComboBox<String> fromCurrencyBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR"});
        
        JLabel toLabel = new JLabel("To Currency:");
        JComboBox<String> toCurrencyBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR"});
        
        JLabel resultLabel = new JLabel("Converted Amount:");
        JTextField resultField = new JTextField(10);
        resultField.setEditable(false);

        // Add components to the panel
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(fromLabel);
        panel.add(fromCurrencyBox);
        panel.add(toLabel);
        panel.add(toCurrencyBox);
        panel.add(resultLabel);
        panel.add(resultField);

        // Create a Convert button
        JButton convertButton = new JButton("Convert");

        // ActionListener for the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get input values
                    double amount = Double.parseDouble(amountField.getText());
                    String fromCurrency = (String) fromCurrencyBox.getSelectedItem();
                    String toCurrency = (String) toCurrencyBox.getSelectedItem();

                    double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);
                    resultField.setText(String.format("%.2f", convertedAmount));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.");
                }
            }
        });

        // Add components to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(convertButton, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to convert currency
    private static double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double conversionRate = 1.0;

        if (fromCurrency.equals("USD")) {
            if (toCurrency.equals("EUR")) {
                conversionRate = USD_TO_EUR;
            } else if (toCurrency.equals("GBP")) {
                conversionRate = USD_TO_GBP;
            } else if (toCurrency.equals("INR")) {
                conversionRate = USD_TO_INR;
            }
        } else if (fromCurrency.equals("EUR")) {
            if (toCurrency.equals("USD")) {
                conversionRate = EUR_TO_USD;
            }
        } else if (fromCurrency.equals("GBP")) {
            if (toCurrency.equals("USD")) {
                conversionRate = GBP_TO_USD;
            }
        } else if (fromCurrency.equals("INR")) {
            if (toCurrency.equals("USD")) {
                conversionRate = INR_TO_USD;
            }
        }

        return amount * conversionRate;
    }
}
