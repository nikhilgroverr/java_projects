Overview:

This repository contains two different implementations of a Visualization for the Insertion Sort algorithm in Java. The first implementation (InsertionSortVisualizer) provides a GUI-based interactive experience with controls to generate and manipulate arrays, while the second implementation (insertionBasic) offers a simpler, static visualization with minimal interaction. Both visualizations display the sorting process step by step, but they differ in terms of functionality, complexity, and user interaction.

Features:

- InsertionSortVisualizer

GUI Interface: Built using JFrame, JPanel, and other Swing components for a user-friendly interface.

Array Generation: Users can specify the array size (1 to 100) and generate a new array with random values.

Sorting Controls:

Start Sorting: Begins the insertion sort process and visually shows the algorithm's step-by-step progress.

Randomize Array: Randomizes the array values after each sort attempt.

Reset Array: Resets the array to its initial state.

Speed Adjustment: Adjusts the delay between each sorting step using a slider for real-time speed control.

Real-Time Statistics: Displays the number of comparisons and swaps made during the sorting process.

Progress Bar: Indicates the progress of sorting, updating as the array is sorted.

Colors for Visualization:
	
	Red: Unsorted elements.
	Blue: Elements being compared.
	Green: Sorted elements.

Color Legend: Provides a visual guide for the colors used in the sorting process.

Responsive Layout: The program adjusts the layout and size based on user inputs and window size.



- insertionBasic:

Simpler Design: A minimalistic approach with a straightforward bar visualization for the sorting process.

Basic GUI: Utilizes JPanel and Graphics for custom drawing of bars representing array elements.

Sorting Process: Automatically starts sorting when the program is run, without interactive control for array manipulation.

 Sorting Visualization:

	Green Highlight: The current element (key) being moved is highlighted in green.
	Blue Bars: Unsorted elements are represented by blue bars.

No GUI Controls: Lacks interactive features like array size input, speed control, or statistics.

Limited Customization: Offers no option for customizing the array values, sorting speed, or resetting the array.
Comparison


-Usage Instructions:

- InsertionSortVisualizer:
 
Run the Program:
	
	Launch the program, and the GUI will open with buttons and controls.

Generate an Array:
	
	Enter the desired array size (1 to 100) and click the Generate Array button.

Start Sorting:
	
	Click the Start Sorting button to begin the sorting process, which will be visualized in real-time.

Control Speed:

	Use the speed slider to adjust the delay between sorting steps for faster or slower visualization.

Randomize Array:

	Click Randomize Array to shuffle the array after the sorting process.

Reset Array:
	
	Click Reset Array to return to the original array state.


- insertionBasic

Run the Program:

	Launch the program from the main method. The array will be created and sorted automatically.

Visualize Sorting:
	
	The sorting process will begin immediately after the program starts, and the sorting process will be visually displayed with bars.


Requirements:

-Java Development Kit (JDK) 8 or higher
-Basic knowledge of Java Swing and Insertion Sort algorithm


