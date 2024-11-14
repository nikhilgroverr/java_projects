Bubble Sort Visualizer

This project consists of two Java applications that visualize the Bubble Sort algorithm. The first implementation (testBubble.java) provides a basic visualization of the sorting process in a simple graphical user interface (GUI), while the second implementation (BubbleSortVisualizer.java) offers a more feature-rich GUI with additional controls for customizing the array size, sorting speed, and more.


Files:

- testBubble.java
A basic Bubble Sort visualizer with static array size and automatic sorting.

- BubbleSortVisualizer.java
A more advanced version with interactive controls for array size, sorting speed, and additional visual features.

Features:

-testBubble.java

Generates a random array of integers.
Visually displays the array as bars on the screen.
Uses Bubble Sort to sort the array, with a visual update after each swap.
A simple visualization of the sorting process.

-BubbleSortVisualizer.java

Generates an array of integers with user-defined size (between 1 and 100).
Allows the user to adjust the sorting speed using a slider.
Displays the number of comparisons and swaps made during the sorting process.
Buttons for generating a new array, randomizing the current array, or resetting it.
Displays the progress of the sorting in a JProgressBar.
Interactive and visually detailed with color coding for sorted, unsorted, and currently comparing elements.

Requirements:

Java 8 or higher.
A graphical environment (GUI components are used in the visualization).

How It Works

-testBubble.java

A random array is generated with a default size.
The paintComponent method of the JPanel is overridden to display the array as blue bars.
The sorting process is visualized by swapping adjacent elements in the array, with the repaint method called after each swap to update the display.
A 100ms delay is used to make the sorting visible.

-BubbleSortVisualizer.java

Similar to testBubble, a random array is generated.
An array is displayed with bars colored in:
Red: Unsorted elements
Blue: Elements currently being compared
Green: Sorted elements
The array size is customizable using the text field.
The sorting process is handled in a separate thread, and the GUI is updated using repaint() after each comparison and swap.
The number of comparisons and swaps are displayed in real-time.
A progress bar shows the progress of the sorting.

Controls 

-testBubble
Automatic Sorting: Automatically sorts a random array using the Bubble Sort algorithm without manual control.
Array Display: Displays the array as a set of blue bars that change position as the elements are sorted.
Sorting Process: Swaps adjacent elements, with visual updates after each swap to show the current state of the array.
Speed: A fixed delay of 100ms is used between swaps to make the sorting process visually observable.

-BubbleSortVisualizer
Generate Array: Generates a new random array based on the size input.
Randomize Array: Randomizes the current array.
Reset Array: Resets the array back to its original state.
Start Sorting: Starts the sorting process with the current array.
Sorting Speed: A slider that adjusts the delay between sorting steps.
Comparison and Swap Count: Displays the number of comparisons and swaps performed during the sort.

Visuals
Red bars represent unsorted elements.
Blue bars represent the elements currently being compared.
Green bars represent sorted elements.



