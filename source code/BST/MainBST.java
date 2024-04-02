import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class implements tasks related to binary search trees (BST) to verify the time complexity
 * of basic operations in relation to the height of the tree.
 * 
 * The program performs the following tasks:
 * 1. Generates random integers and inserts them into a BST until a specified number of distinct nodes are inserted.
 * 2. Removes all odd values from the BST, resulting in a new tree containing only even numbers.
 * 3. Inserts new random integers into the modified tree and measures the time needed for insertion.
 * 4. Plots the height of the tree against the time taken for insertion, using the XChart library.
 * 5. Compares the experimental results with the expected time complexity for inserting elements into a BST.
 */
public class MainBST {
	
	// Create a binary search tree instance
	static BinarySearchTree<Integer> bst = new BinarySearchTree<>();

    /**
     * Main method to run the program.
     *
     * @param args command line arguments (not used)
     */
	public static void main(String[] args) {
		MainBST main = new MainBST();
		main.checkTree(300, 100);
		removeOddValues(bst); // Remove odd values from the tree
		bst.inorderTraversal(); // Print the tree after removing odd values
		System.out.println(bst.getRootValue());

		// Step 3: Insert new random integers into T1
		List<Integer> heights = insertRandomIntegers(bst, 50, 150);

		// Create and customize the chart
		XYChart chart = createChart(heights);

		// Display the chart
		new SwingWrapper<>(chart).displayChart();
	}

	// Function g(n) to generate random integers between 0 and n
	public static int g(int n) {
		Random rand = new Random();
		int upperbound = n + 1;
		int r_i = rand.nextInt(upperbound);
		return r_i;
	}
	
    /**
     * Populates the tree with random values.
     *
     * @param n the upper bound for random integer generation
     * @param m the number of random values to add to the tree
     */
	public static void checkTree(int n, int m) {
		int count = 0;
		while (count < m) {
			int r_i = g(n);
			if (!bst.contains(r_i)) { // Check if r_i is not already in the tree
				bst.add(r_i); // Add r_i to the tree if it's not already present
				System.out.println("Added " + r_i + " to the tree.");
				count++;
			} else {
				System.out.println(r_i + " is already in the tree.");
			}
		}
	}

	/**
	 * Removes all odd values from the binary search tree.
	 *
	 * @param bst the binary search tree instance
	 */
	private static void removeOddValues(BinarySearchTree<Integer> bst) {
		ArrayList<Integer> oddValues = new ArrayList<>();  // List to store odd values
		bst.inorderTraversal(); // Display the original tree before removing odd values
		findAndRemoveOddValues(bst.root, oddValues); // Find and remove odd values from the tree
	}
	
	/**
	 * Recursively finds and removes odd values from the binary search tree.
	 *
	 * @param node      the current node being evaluated
	 * @param oddValues list to store odd values found in the tree
	 */

	private static void findAndRemoveOddValues(BinarySearchTree<Integer>.Node node, ArrayList<Integer> oddValues) {
		if (node == null) {
			return;  // Base case: if node is null, return
		}
		findAndRemoveOddValues(node.left, oddValues); // Traverse left subtree
		if (node.data % 2 != 0) {
			oddValues.add(node.data); // Add odd value to the list
		}
		findAndRemoveOddValues(node.right, oddValues); // Traverse right subtree
		
		// Remove odd values from the tree
		for (Integer value : oddValues) {
			bst.remove(value);
		}
	}
	
    /**
     * Inserts random integers into the tree and records the height after each insertion.
     *
     * @param bst  the binary search tree instance
     * @param minK the minimum value for random integer generation
     * @param maxK the maximum value for random integer generation
     * @return a list containing heights of the tree after each insertion
     */
	public static List<Integer> insertRandomIntegers(BinarySearchTree<Integer> bst, int minK, int maxK) {
		List<Integer> heights = new ArrayList<>();
		for (int k = 0; k < 128; k++) {
			int ri = g(300);
			int heightBefore = bst.height(); // Get height before insertion
			bst.add(ri);
			int heightAfter = bst.height(); // Get height after insertion
			heights.add(heightAfter);
			//System.out.println("Insertion " + (k + 1) + ": Height = " + heightAfter + ", Time = " + "nanoseconds"); // "nanoseconds"

		}
		return heights;
	}

	/**
	 * Creates a chart plotting height against time for insertion operations.
	 *
	 * @param heights list containing heights of the tree after each insertion
	 * @return the created XYChart
	 */
	private static XYChart createChart(List<Integer> heights) {
		// Create and customize the chart
		XYChart chart = new XYChart(600, 400);
		chart.setTitle("Height vs. Time for Insertion Operations");
		chart.setXAxisTitle("Time (nanoseconds)");
		chart.setYAxisTitle("Height");
		chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);

		// Add data to the chart
		List<Double> times = new ArrayList<>();
		for (int i = 0; i < heights.size(); i++) {
			times.add((double) (i + 1)); // X-axis: Time (insertion number)
		}
		chart.addSeries("Height vs. Time", times, heights);
		return chart;
	}

}
