import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class insertionBasic extends JPanel {

    int[] array;
    int delay = 500;
    int currentKeyIndex = -1; // To track the current key being sorted
    final int barWidth = 15; // Width of each bar
    final int barGap = 5; // Gap between bars

    public insertionBasic(int size) {
        array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(150);
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < array.length; i++) {
            if (i == currentKeyIndex) {
                g.setColor(Color.GREEN); // Highlight the current key being sorted
            } else {
                g.setColor(Color.BLUE); // Default color for the bars
            }
            int x = i * (barWidth + barGap); // Position with gap
            g.fillRect(x, getHeight() - array[i], barWidth, array[i]); // Draw the bar
            g.setColor(Color.RED); // Changes the drawing color to red for the text
            g.drawString(String.valueOf(array[i]), x, getHeight() - array[i] - 5); // Draw the value above the bar
        }
    }

    private void insertionSort() throws InterruptedException {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            currentKeyIndex = i; // Update the current key index
            int j = i - 1;

            // Move elements of the array that are greater than `key` to one position ahead of their current position
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;

                // Repaint and delay to visualize the sorting process
                repaint();
                Thread.sleep(delay);
            }
            array[j + 1] = key;

            // Repaint and delay to visualize the placement of the key
            repaint();
            Thread.sleep(delay);
        }
        currentKeyIndex = -1; // Reset the current key index after sorting
        repaint();
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements for the array: ");
        int size = scanner.nextInt();

        JFrame frame = new JFrame();
        insertionBasic visualizer = new insertionBasic(size);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.add(visualizer);
        frame.setVisible(true);

        // Start Sorting
        visualizer.insertionSort();

        scanner.close();
    }
}
