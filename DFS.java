import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class DFS {
    public List<Integer> findPathDFS(Graph graph, int source, int destination) {
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[graph.getNumVertices()];
        Stack<Integer> pathStack = new Stack<>();
        pathStack.push(source);
        DFS(graph, source, destination, visited, pathStack, path);
        return path;
    }

    private boolean DFS(Graph graph, int currentVertex, int destination, boolean[] visited,
                                Stack<Integer> pathStack, List<Integer> path) {
        visited[currentVertex] = true;
        if (currentVertex == destination) {
            path.addAll(pathStack);
            return true;
        } else {
            for (int neighbor : graph.getAdjacencyList().get(currentVertex)) {
                if (!visited[neighbor]) {
                    pathStack.push(neighbor);
                    if (DFS(graph, neighbor, destination, visited, pathStack, path)) {
                        return true;
                    }
                    pathStack.pop();
                }
            }
        }
        visited[currentVertex] = false;
        return false;
    }
}

