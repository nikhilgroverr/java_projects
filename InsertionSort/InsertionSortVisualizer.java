import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class InsertionSortVisualizer {
    private static int[] array;
    private static boolean[] sorted;
    private static int currentIndex1 = -1; // For the current element
    private static int currentIndex2 = -1; // For the element being compared
    private static int delay = 100; // Default sorting speed
    private static JButton startSortBtn, generateBtn, randomizeBtn, resetBtn;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Insertion Sort Visualizer");
        JPanel panel = new JPanel(new BorderLayout());
        array = new int[0]; // Default size
        sorted = new boolean[50]; // To track sorted state
        int[] statistics = {0, 0}; // {comparisons, swaps}
        JLabel comparisonLabel = new JLabel("Comparisons: 0");
        JLabel swapLabel = new JLabel("Swaps: 0");
        JProgressBar progressBar = new JProgressBar(0, 50);
        JTextField sizeInput = new JTextField(5);

        startSortBtn = new JButton("Start Sorting");
        generateBtn = new JButton("Generate Array");
        randomizeBtn = new JButton("Randomize Array");
        resetBtn = new JButton("Reset Array");

        // Initially disable the start sorting button
        startSortBtn.setEnabled(false);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Enter Array Size (1-100):"));
        controlPanel.add(sizeInput);
        controlPanel.add(generateBtn);
        controlPanel.add(randomizeBtn);
        controlPanel.add(resetBtn);
        controlPanel.add(startSortBtn);
        controlPanel.add(comparisonLabel);
        controlPanel.add(swapLabel);
        controlPanel.add(progressBar);

        // Slider for speed adjustment
        JSlider speedSlider = new JSlider(10, 1000, 100);
        speedSlider.setMajorTickSpacing(100);
        speedSlider.setMinorTickSpacing(10);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(false); // Disable default labels
        speedSlider.addChangeListener(e -> {
            delay = speedSlider.getValue(); // Update the delay based on the slider
        });

        controlPanel.add(new JLabel("Sorting Speed (ms):"));
        controlPanel.add(speedSlider);

        panel.add(controlPanel, BorderLayout.NORTH);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        generateArray(50); // Initialize with default size

        generateBtn.addActionListener(e -> {
            String input = sizeInput.getText();
            try {
                int size = Integer.parseInt(input);
                if (size < 1 || size > 100) {
                    JOptionPane.showMessageDialog(frame, "Size must be between 1 and 100.");
                    return;
                }
                generateArray(size);
                statistics[0] = statistics[1] = 0; // Reset comparisons and swaps
                comparisonLabel.setText("Comparisons: 0");
                swapLabel.setText("Swaps: 0");
                progressBar.setValue(0);
                panel.repaint();
                startSortBtn.setEnabled(true); // Enable the sorting button
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
            }
        });

        randomizeBtn.addActionListener(e -> {
            Random rand = new Random();
            for (int i = 0; i < array.length; i++) {
                array[i] = rand.nextInt(400) + 10;
            }
            statistics[0] = statistics[1] = 0; // Reset comparisons and swaps
            comparisonLabel.setText("Comparisons: 0");
            swapLabel.setText("Swaps: 0");
            panel.repaint();
        });

        resetBtn.addActionListener(e -> {
            String input = sizeInput.getText();
            try {
                int size = Integer.parseInt(input);
                if (size < 1 || size > 100) {
                    JOptionPane.showMessageDialog(frame, "Size must be between 1 and 100.");
                    return;
                }
                generateArray(size);
                statistics[0] = statistics[1] = 0; // Reset comparisons and swaps
                comparisonLabel.setText("Comparisons: 0");
                swapLabel.setText("Swaps: 0");
                progressBar.setValue(0);
                panel.repaint();
                startSortBtn.setEnabled(true); // Enable the sorting button
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
            }
        });

        startSortBtn.addActionListener(e -> {
            disableButtons(true); // Disable buttons during sorting
            new Thread(() -> {
                for (int i = 1; i < array.length; i++) {
                    int key = array[i];
                    int j = i - 1;

                    // Mark the current element being compared
                    currentIndex1 = i;
                    currentIndex2 = j;

                    while (j >= 0 && array[j] > key) {
                        statistics[0]++; // Increment comparisons
                        array[j + 1] = array[j];
                        j--;

                        currentIndex2 = j; // Update current comparison
                        panel.repaint();
                        try {
                            Thread.sleep(delay); // Use the adjustable delay
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    array[j + 1] = key;
                    statistics[1]++; // Increment swaps
                    sorted[i] = true; // Mark as sorted

                    progressBar.setValue((i * 100) / array.length);
                    panel.repaint();
                }
                comparisonLabel.setText("Comparisons: " + statistics[0]);
                swapLabel.setText("Swaps: " + statistics[1]);
                disableButtons(false); // Re-enable buttons after sorting
            }).start();
        });

        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < array.length; i++) {
                    if (sorted[i]) {
                        g.setColor(Color.GREEN); // Sorted elements
                    } else if (i == currentIndex1 || i == currentIndex2) {
                        g.setColor(Color.BLUE); // Currently comparing elements
                    } else {
                        g.setColor(Color.RED); // Unsorted elements
                    }
                    // Increase the gap and adjust the bar width
                    g.fillRect(i * 20, getHeight() - array[i], 15, array[i]); // Increased dimensions
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(array[i]), i * 20, getHeight() - array[i] - 5);
                }
            }
        };

        panel.add(drawingPanel, BorderLayout.CENTER);

        // Legend Panel
        JPanel legendPanel = new JPanel();
        legendPanel.add(createColorBox(Color.RED, "Unsorted"));
        legendPanel.add(createColorBox(Color.GREEN, "Sorted"));
        legendPanel.add(createColorBox(Color.BLUE, "Currently Comparing"));
        panel.add(legendPanel, BorderLayout.SOUTH);
    }

    private static void generateArray(int size) {
        array = new int[size]; // Resize the array based on user input
        sorted = new boolean[size]; // Resize the sorted array
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(400) + 10;
            sorted[i] = false; // Reset sorted states
        }
    }

    private static void disableButtons(boolean disable) {
        startSortBtn.setEnabled(!disable);
        generateBtn.setEnabled(!disable);
        randomizeBtn.setEnabled(!disable);
        resetBtn.setEnabled(!disable);
    }

    private static JPanel createColorBox(Color color, String label) {
        JPanel box = new JPanel();
        box.setBackground(color);
        box.setPreferredSize(new Dimension(120, 40));
        box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        box.add(new JLabel(label));
        return box;
    }
}
