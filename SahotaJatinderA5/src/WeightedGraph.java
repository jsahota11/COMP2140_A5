import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WeightedGraph {
    int vertices;
    int[][] adjacencyMatrix;

    public WeightedGraph(int vertices) {
        this.vertices = vertices;
        //main diagonal will most likely be 0 supposing the graph is simple
        //will be symmetric as edges should not be directed
        //entries will correspond to weight, not sure if weights can be 0
        adjacencyMatrix = new int[vertices][vertices];
    }

    public WeightedGraph(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        vertices = Integer.parseInt(sc.nextLine());
        adjacencyMatrix = new int[vertices][vertices];
        while (sc.hasNextLine()){
            String[] tokens = sc.nextLine().split(" ");
            int vertex1 = Integer.parseInt(tokens[0]);
            int vertex2 = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);

            //maintains symmetry of adjacency
            adjacencyMatrix[vertex1][vertex2] = weight;
            adjacencyMatrix[vertex2][vertex1] = weight;
        }
    }
}
