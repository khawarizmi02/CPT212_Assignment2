import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a graph
        Graph graph = new Graph(6);

        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);

        // Draw the graph
        graph.drawGraph();

        // Find a path from source to destination using DFS
        DFS dfs = new DFS();
        List<Integer> path = dfs.findPathDFS(graph, 0, 5);
        System.out.println("Path: " + path);

        // Print the path
        if (path != null) {
            System.out.println("Path from source to destination: " + path);
        } else {
            System.out.println("No path from source to destination");
        }
    }
}
