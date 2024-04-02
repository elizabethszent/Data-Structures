import java.util.Arrays;
import java.util.List;

/**
 * DijkstraAlgorithm class implements Dijkstra's algorithm to find the shortest
 * delivery routes from the central warehouse to all delivery locations based on
 * distance.
 */
public class DijkstraAlgorithm {
	/**
	 * Finds the shortest delivery routes from the central warehouse to all delivery
	 * locations using Dijkstra's algorithm.
	 *
	 * @param graph  the city graph representing warehouses, delivery locations, and
	 *               roads
	 * @param source the index of the central warehouse
	 * @return an array containing the shortest distances from the central warehouse
	 *         to all locations
	 */
	public static int[] dijkstra(CityGraph graph, int source) {
		int numVertices = graph.getNumVertices();// Get the number of vertices in the graph

		// Initialize arrays to store distances and visited status of vertices
		int[] distances = new int[numVertices];
		boolean[] visited = new boolean[numVertices];

		Arrays.fill(distances, Integer.MAX_VALUE);// Set all distances to infinity initially
		distances[source] = 0;// Distance from the source vertex to itself is 0

		// Iterate through all vertices
		for (int i = 0; i < numVertices - 1; i++) {
			int minDistance = Integer.MAX_VALUE;
			int minIndex = -1;

			// Find the vertex with the minimum distance among the unvisited vertices
			for (int v = 0; v < numVertices; v++) {
				if (!visited[v] && distances[v] < minDistance) {
					minDistance = distances[v];
					minIndex = v;
				}
			}

			visited[minIndex] = true; // Mark the selected vertex as visited
			List<CityGraph.Edge> edges = graph.getEdges(minIndex);// Get the edges connected to the selected vertex
			// Update distances to adjacent vertices if a shorter path is found
			for (CityGraph.Edge edge : edges) {
				int newDistance = distances[minIndex] + edge.weight;
				if (!visited[edge.destination] && newDistance < distances[edge.destination]) {
					distances[edge.destination] = newDistance;
				}
			}
		}

		return distances; // Return the array containing the shortest distances from the central warehouse to all locations
	}

}