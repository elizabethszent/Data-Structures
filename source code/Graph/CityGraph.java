import java.util.*;

/**
 * CityGraph class represents a directed weighted graph where each node
 * represents a warehouse or delivery location, and edges represent roads
 * connecting them.
 */
public class CityGraph {
	private int numVertices; // Number of vertices in the graph
	private List<List<Edge>> adjacencyList; // Adjacency list representing the graph

	/**
	 * Constructs a CityGraph with the specified number of vertices.
	 *
	 * @param numVertices the number of vertices in the graph
	 */
	public CityGraph(int numVertices) {
		this.numVertices = numVertices; // Initialize the number of vertices
		this.adjacencyList = new ArrayList<>(); // Initialize the adjacency list
		for (int i = 0; i < numVertices; i++) {
			adjacencyList.add(i, new ArrayList<>()); // Initialize each vertex with an empty list of edges
		}
	}

	/**
	 * Adds a directed edge from source to destination with the given weight.
	 *
	 * @param source      the index of the source vertex
	 * @param destination the index of the destination vertex
	 * @param weight      the weight of the edge (distance between locations)
	 */
	public void addEdge(int source, int destination, int weight) {
		Edge edge = new Edge(destination, weight); // Create a new edge with destination and weight
		adjacencyList.get(source).add(edge); // Add the edge to the adjacency list of the source vertex
	}

	/**
	 * Retrieves the list of edges connected to the specified vertex.
	 *
	 * @param vertex the index of the vertex
	 * @return the list of edges connected to the vertex
	 */
	public List<Edge> getEdges(int vertex) {
		return adjacencyList.get(vertex); // Return the list of edges for the specified vertex
	}

	/**
	 * Gets the number of vertices in the graph.
	 *
	 * @return the number of vertices
	 */
	public int getNumVertices() {
		return numVertices; // Return the number of vertices
	}

	/**
	 * Edge class represents an edge in the CityGraph.
	 */
	static class Edge {
		int destination; // Index of the destination vertex
		int weight; // Weight of the edge

		/**
		 * Constructs an Edge with the specified destination vertex and weight.
		 *
		 * @param destination the index of the destination vertex
		 * @param weight      the weight of the edge
		 */
		public Edge(int destination, int weight) {
			this.destination = destination; // Initialize the destination vertex
			this.weight = weight; // Initialize the weight of the edge
		}
	}
}
