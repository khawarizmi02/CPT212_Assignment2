import java.util.ArrayList;
import java.util.List;

class DFS {
    private int[] num;
    private List<Integer> edges;
    private int count;
    private int source;
    private int destination;

    public List<Integer> findPathDFS(Graph graph, int source, int destination) {
        this.source = source;
        this.destination = destination;
        int numVertices = graph.getNumVertices();
        num = new int[numVertices];
        edges = new ArrayList<>();
        count = 0;

        for (int i = 0; i < numVertices; i++) {
            num[i] = 0;
        }

        edges.add(source);
        DFSUtil(graph, source, destination);

        System.out.println(graph.getAdjacencyList().get(source));
        // System.out.println("edges: " + edges);

        if (destination == destination) {
          return edges;
        } else {
          return null;
        }   
    }

    private void DFSUtil(Graph graph, int vertex, int destination) {
        num[vertex] = count++;
        for (int neighbor : graph.getAdjacencyList().get(vertex)) {
            if (num[neighbor] == 0 && vertex != destination) {
                edges.add(neighbor);
                DFSUtil(graph, neighbor, destination);
                if (edges.get(edges.size() - 1) != destination) {
                  edges.remove(edges.size() - 1);
                }
            }
        }
    }
}
