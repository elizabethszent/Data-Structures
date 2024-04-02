import java.util.ArrayList; // Import ArrayList class from java.util package for dynamic array
import java.util.InputMismatchException; // Import InputMismatchException class from java.util package for handling input mismatch errors
import java.util.List; // Import List interface from java.util package for representing a list data structure
import java.util.Scanner; // Import Scanner class from java.util package for reading user input

/**
 * MainDirectedGraph class implements the main program for finding the shortest delivery routes
 * in a directed graph representing a city's road network.
 */
public class MainDirectedGraph {
    
    /**
     * Main method to execute the program.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create Scanner object for reading user input

        int numWarehouses, numLocations; // Declare variables for number of warehouses and delivery locations
        try {
            // Input: Number of warehouses and delivery locations
            numWarehouses = scanner.nextInt(); // Read the number of warehouses from user input
            numLocations = scanner.nextInt(); // Read the number of delivery locations from user input
        } catch (InputMismatchException e) {
            // Handle invalid input format
            System.out.println("Invalid input format: Expected integer values for number of warehouses and delivery locations.");
            return; // Exit the program
        }

        // Validate input values
        if (numWarehouses != 1 || numLocations < 1) {
            System.out.println("Invalid input format: Number of warehouses should be 1 and number of delivery locations should be positive.");
            return; // Exit the program
        }

        // Create a city graph
        CityGraph cityGraph = new CityGraph(numLocations + 1); // Include central warehouse (0) and delivery locations

        // Input: Number of roads connecting locations
        int numRoads = scanner.nextInt(); // Read the number of roads from user input

        // Input: Roads connecting locations
        for (int i = 0; i < numRoads; i++) {
            int start = scanner.nextInt(); // Read the starting location of the road from user input
            int end = scanner.nextInt(); // Read the ending location of the road from user input
            int distance = scanner.nextInt(); // Read the distance of the road from user input

            // Validate vertex numbers and distances
            if (start < 0 || end < 0 || distance < 0) {
                System.out.println("Invalid input format: Vertex numbers and distances should be non-negative.");
                return; // Exit the program
            }

            // Add edges to the graph
            cityGraph.addEdge(start, end, distance); // Add the road to the city graph
        }

        // Perform Dijkstra's algorithm for each delivery location
        for (int location = 1; location <= numLocations; location++) {
            int[] shortestDistances = DijkstraAlgorithm.dijkstra(cityGraph, 0); // Central warehouse at node 0
            int distance = shortestDistances[location]; // Get the shortest distance to the current location
            String shortestRoute = getShortestRoute(shortestDistances, location); // Get the shortest route to the current location
            System.out.println("Delivery Location " + location + " - Shortest Route: " + shortestRoute + ", Distance: " + (distance == Integer.MAX_VALUE ? "Infinity : (Location " + location + " is unreachable from the central warehouse)" : distance)); // Print the shortest route and distance to the current location
        }

        scanner.close(); // Close the scanner
    }

    /**
     * Gets the shortest route from the central warehouse to the destination location.
     *
     * @param distances   the array of shortest distances from the central warehouse to each location
     * @param destination the index of the destination location
     * @return the shortest route from the central warehouse to the destination location
     */
    private static String getShortestRoute(int[] distances, int destination) {
        StringBuilder shortestRoute = new StringBuilder(); // Create StringBuilder object to store the shortest route
        int currentLocation = destination; // Initialize the current location as the destination
        if (distances[currentLocation] == Integer.MAX_VALUE) {
            return "Unreachable"; // Indicate unreachable location if the distance is maximum value
        }
        while (currentLocation != 0) { // Loop until reaching the central warehouse
            shortestRoute.insert(0, currentLocation + " -> "); // Add the current location to the beginning of the route
            currentLocation = getPredecessor(distances, currentLocation); // Get the predecessor location in the shortest route
        }
        shortestRoute.insert(0, "0 -> "); // Add central warehouse to the beginning of the route
        shortestRoute.deleteCharAt(shortestRoute.length() - 2); // Remove extra arrow before central warehouse
        return shortestRoute.toString(); // Return the shortest route as a string
    }

    /**
     * Helper method to get the predecessor in the shortest route.
     *
     * @param distances the array of shortest distances from the central warehouse to each location
     * @param location  the index of the current location
     * @return the index of the predecessor in the shortest route
     */
    private static int getPredecessor(int[] distances, int location) {
        int minDistance = Integer.MAX_VALUE; // Initialize minimum distance as maximum value
        int predecessor = 0; // Initialize predecessor index as 0
        for (int i = 0; i < distances.length; i++) { // Loop through all locations
            if (distances[i] < minDistance && distances[i] != Integer.MAX_VALUE && distances[i] < distances[location]) {
                minDistance = distances[i]; // Update minimum distance
                predecessor = i; // Update predecessor index
            }
        }
        return predecessor; // Return the predecessor index
    }
}

