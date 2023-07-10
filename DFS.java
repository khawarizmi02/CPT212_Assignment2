import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class DFS {
    public List<Integer> findPathDFS(Graph graph, int source, int destination) {
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[graph.getNumVertices()];
        Stack<Integer> pathStack = new Stack<>();
        pathStack.push(source);
        boolean pathFound = performDFS(graph, source, destination, visited, pathStack, path);
        if (!pathFound) {
            System.out.println("No path found from source to destination.");
        }
        return path;
    }

    private boolean performDFS(Graph graph, int currentVertex, int destination, boolean[] visited,
                               Stack<Integer> pathStack, List<Integer> path) {
        visited[currentVertex] = true; // check the vertex ihas been visited 
        if (currentVertex == destination) {
            path.addAll(pathStack); // add all path 
            return true;
        } else {
            for (Edge edge : graph.getAdjacencyList().get(currentVertex)) {
                int neighbor = edge.getDestination();
                if (!visited[neighbor]) {
                    pathStack.push(neighbor); // Add the neighbor to the current path
                    if (performDFS(graph, neighbor, destination, visited, pathStack, path)) {
                        return true;
                    }
                    pathStack.pop(); // Remove the neighbor from the current path
                }
            }
        }
        visited[currentVertex] = false;
        return false;
    }
}

