This is a Currency Converter application built in Java using Swing for the graphical user interface (GUI). It allows users to convert an amount of money from one currency to another. The application supports conversions between USD, EUR, GBP, and INR.

Features:

Allows the user to input an amount.
Select the currency to convert from and to.
Displays the converted amount based on predefined exchange rates.
Simple, easy-to-use GUI created with Java Swing.
Error handling for invalid inputs.

Currency Conversion Rates (Example):

USD to EUR: 1 USD = 0.85 EUR
USD to GBP: 1 USD = 0.75 GBP
USD to INR: 1 USD = 75.0 INR
EUR to USD: 1 EUR = 1.18 USD
GBP to USD: 1 GBP = 1.33 USD
INR to USD: 1 INR = 0.013 USD
Note: These are example exchange rates and may not reflect current real-world rates.


How to Use:
Step 1: Enter an amount in the "Amount" text field.
Step 2: Select the currency you want to convert from and to, using the "From Currency" and "To Currency" dropdowns.
Step 3: Click the "Convert" button.
Step 4: The converted amount will be displayed in the "Converted Amount" field.

Example:
Input:
Amount: 100
From Currency: USD
To Currency: EUR
Output:
Converted Amount: 85.00 EUR

Error Handling:

If the user enters a non-numeric value in the "Amount" field, an error message will appear, prompting the user to enter a valid number.

Code Explanation:

Main Components:

JFrame: The main window of the application.
JComboBox: Dropdown lists for selecting the currencies (From and To).
JTextField: Text fields for user input (amount) and output (converted amount).
JButton: Button that triggers the currency conversion when clicked.
ActionListener: Handles the event when the "Convert" button is clicked and performs the conversion.

License:
This project is open-source and available for modification and redistribution. Feel free to contribute!

