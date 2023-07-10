import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a graph from the input file
        Graph graph = createGraphFromFile("data.txt");

        // Completely execute program if there is no graph
        if (graph == null){ return; }

        // Set all list of bus stations into Stations Array
        String[] Stations = setBusStation(graph.getNumVertices());

        // print out Stations list
        printStationsList(Stations);

        // Bus Object declaration
        Bus busA = new Bus("RouteA.txt");
        Bus busAC = new Bus("RouteAC.txt");
        Bus busB = new Bus("RouteB.txt");
        Bus busC = new Bus("RouteC.txt");
        Bus busD = new Bus("RouteD.txt");

        // Get user input for source and destination vertices
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();
        System.out.print("Enter the destination vertex: ");
        int destination = scanner.nextInt();
        scanner.close();

        // compare bus route with source and destination
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Find the path using DFS
        DFS dfs = new DFS();
        List<Integer> path = dfs.findPathDFS(graph, source, destination);

        // Display the path
        System.out.println("Path from " + source + " to " + destination + ":");
        if (!path.isEmpty()) {
          setPath(path, Stations);
        }

        System.out.println("Available Bus Route: ");
        if (busA.isAvailable(source, destination)){
          busA.printBusRoute(Stations);
        }
        if (busAC.isAvailable(source, destination)){
          busAC.printBusRoute(Stations);
        }
        if (busB.isAvailable(source, destination)){
          busB.printBusRoute(Stations);
        }
        if (busC.isAvailable(source, destination)){
          busC.printBusRoute(Stations);
        }
        if (busD.isAvailable(source, destination)){
          busD.printBusRoute(Stations);
        }
    }

  private static Graph createGraphFromFile(String fileName) {
    // set graph object to null
    Graph graph = null;
    try {
        // Check the existence of the file 
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            return null;
        }
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextInt()) {
            int numVertices = scanner.nextInt();
            graph = new Graph(numVertices);
            scanner.nextLine(); // Skip the rest of the first line
            for (int i = 0; i < numVertices; i++) {
                if (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] neighbors = line.split(" ");
                    int vertex = Integer.parseInt(neighbors[0]);
                    for (int j = 1; j < neighbors.length; j++) {
                        int neighbor = Integer.parseInt(neighbors[j]);
                        graph.addEdge(vertex, neighbor, 1);
                    }
                } else {
                    System.out.println("Invalid input format in the file.");
                    return null;
                }
            }
        } else {
            System.out.println("Invalid input format in the file.");
            return null;
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    return graph;
  }

  private static void setPath(List<Integer> path, String[] Stations){
    for (int  i = 0; i < path.size(); i++) {
      System.out.print(Stations[path.get(i)]);
      if(i < path.size() - 1) System.out.print(" ->> ");
    }
    System.out.println();
  }

  private static String[] setBusStation (int numVertices){
    String[] stations = new String[numVertices];

    try {
        File file = new File("Stations.txt");
        Scanner scanner = new Scanner(file);

        for (int i = 0; i < numVertices; i++) {
            if (scanner.hasNextLine()) {
                String station = scanner.nextLine();
                stations[i] = station;
            } else {
                System.out.println("Insufficient data in the file.");
                return null;
            }
        }

        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return stations;
  }

  private static void printStationsList(String[] Stations){
    System.out.println("Stations list: ");
    for (int i = 0; i < Stations.length; i++){
      System.out.println(i + " " + Stations[i]);
    }
  }
}
