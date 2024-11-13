# Personal Diary App

A simple personal diary application built in Java using Swing for the graphical user interface (GUI). This app allows users to add, save, search, and delete diary entries with password protection for security. Entries are stored in a text file, and users can view and search past entries.

## Features
- **Password Protection**: A password is required to access the diary. The default password is `1234` (you can change this in the code).
- **Create New Entries**: Users can add new diary entries and save them with timestamps.
- **Search Entries**: Users can search for specific entries by keyword.
- **Clear Entries**: Clear the current entry or delete all saved entries.
- **Save Entries**: Each entry is saved with the current date and time.
- **Delete All Entries**: Deletes all saved diary entries from the file.

## Requirements
- Java 8 or higher
- Any text editor or IDE (e.g., IntelliJ IDEA, Eclipse)


Usage
Login
Upon launching the application, you will be prompted to enter a password. The default password is 1234. If the correct password is entered, you will have access to the diary.

Main Interface
Search Entry: Allows you to search through saved entries.
Text Area: The main text area where you can write your diary entries.
Save Entry: Saves the current entry with a timestamp to a text file (diaryEntries.txt).
New Entry: Clears the text area for a new entry.
Clear: Clears the current text area content.
Delete All Entries: Deletes all saved entries in the file.
Diary Entries
The diary entries are saved in a text file named diaryEntries.txt.
Each entry is saved with the current date and time.
