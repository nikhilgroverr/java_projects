# Tic Tac Toe Game

A simple Java-based Tic Tac Toe game with a graphical user interface (GUI) created using the `Swing` library.

## Features
- Two-player game (Player X and Player O)
- Simple graphical user interface using `JButton` components
- Automatic winner detection
- Draw detection when the board is full
- Reset the game after a win or draw
- The game alternates turns between players

## Requirements
- Java (version 8 or above)
- Swing library (included in Java)


How to Play;

The game is played on a 3x3 grid.
Player X goes first, followed by Player O.
Players take turns clicking on the buttons to place their symbol (X or O).
The game ends when either a player wins or the board is full, resulting in a draw.
After the game ends, the board resets automatically.

Code Structure:

TicTacToe.java: The main class for the game logic and GUI components.
The game board is represented by a 2D character array board[][] where each cell holds the symbol of the player ('X', 'O', or ' ' for empty).
The game alternates between two players: 'X' and 'O'.
The board is displayed using JButton components arranged in a GridLayout.
The game logic includes functions to check for a winner, check if the board is full, switch players, and reset the game.
