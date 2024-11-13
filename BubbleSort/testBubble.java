import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testBubble extends JPanel {

    int[] array;
    int delay = 500;

    public testBubble(int size){
        array = new int[size];
        Random rand = new Random();
        for(int i = 0; i < array.length; i++){
            array[i] = rand.nextInt(150);
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < array.length; i++){
            g.setColor(Color.BLUE);                                           // Sets color for the bars
            g.fillRect(i * 15, getHeight() - array[i], 15, array[i]);          // Draws a filled rectangle (bar) for each element in the array
            g.setColor(Color.RED);                                             // Changes the drawing color to red for the text
            g.drawString(String.valueOf(array[i]), i * 15, getHeight() - array[i] - 5); // Draws the value of the array element above the bar
        }
    }

    private void bubbleSort() throws InterruptedException {
        for(int i = 0; i < array.length - 1; i++){
            for(int j = 0; j < array.length - 1 - i; j++){
                if(array[j] > array[j + 1]){
                    //SWAP
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;

                    // Repaint and delay to visualize the sorting process
                    repaint();
                    Thread.sleep(100);
                }
            }
        }

    }


    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        testBubble visualizer = new testBubble(50);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(visualizer);
        frame.setVisible(true);

        //Start Sorting
        visualizer.bubbleSort();

    }

    void selectionSort() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  

}
