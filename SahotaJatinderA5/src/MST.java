import java.io.File;
import java.io.FileNotFoundException;

public class MST {
    public static void main(String[] args) {
        String filename = "GraphExample3.txt";

        try {
            WeightedGraph wg = new WeightedGraph(filename);
            int[][] wgMST = buildMST(wg);
            int totalWeight = 0;

            System.out.println("Here is a minimal spanning tree:");
            for (int i = 0; i < wg.vertices; i++) {
                for(int j = i+1; j < wg.vertices; j++) {
                    if (wgMST[i][j] != 0) {
                        int weight = wgMST[i][j];
                        totalWeight += weight;

                        System.out.println("Edge (" + i + ", " + j + ") ,weight " + weight);
                    }
                }
            }

            System.out.println("Total weight: " + totalWeight);

        } catch (FileNotFoundException fnf){
            System.out.println("File not found.");
            System.exit(1);
        }

    }


    public static int[][] buildMST (WeightedGraph graph){
        int[][] mst = new int[graph.vertices][graph.vertices];
        boolean[] vertexInMST = new boolean[graph.vertices];
        MinPQWeightedEdge pq = new MinPQWeightedEdge();

        //start with an arbitrary vertex to add to the MST
        vertexInMST[0] = true;

        //add all incident edges to a min priority queue
        for (int i = 0; i < graph.vertices; i++) {
            if (graph.adjacencyMatrix[0][i]!=0){
                pq.insert(new WeightedEdge(0,i,graph.adjacencyMatrix[0][i]));
            }
        }

        int edgesInMST = 0;

        //we can stop once number of edges = number of vertices -1 since were working with a tree
        //global priority queue so we can consider edges incident to previously considered vertices
        while (!pq.isEmpty() && edgesInMST < graph.vertices) {

            //get the smallest edge in the priority queue
            WeightedEdge nextEdge = pq.retrieveMin();
            int vertex1 = nextEdge.getVertex1();
            int vertex2 = nextEdge.getVertex2();

            //if we already added the neighbouring vertex2 to the MST, ignore it
            if (!vertexInMST[vertex2]){

                //maintains symmetry (not sure if this is necessary)
                mst[vertex1][vertex2] = nextEdge.getWeight();
                mst[vertex2][vertex1] = nextEdge.getWeight();
                vertexInMST[vertex2] = true;
                edgesInMST++;

                //get all incident edges with vertex2 into a priority queue
                for (int j = 0; j < graph.vertices; j++){
                    if (!vertexInMST[j] && graph.adjacencyMatrix[vertex2][j]!=0){
                        pq.insert(new WeightedEdge(vertex2, j, graph.adjacencyMatrix[vertex2][j]));
                    }
                }

            }
        }


        //simple graph, so this is equivalent to just adding a vertex
        //mst[0][0] = graph.adjacencyMatrix[0][0];


        //start at vertex 0
        //go through all other vertices and check if the edge has weight
        //if yes, add it to a min priority queue
        //once all edges hae been traversed, retrieve the min from the priority queue
        //this will be the cheapest edge, so add it to the mst
        //do this for each vertex
        //however, we do not need to look back on vertices, only in order increasing by symmetry
//        for (int i = 0; i < graph.vertices; i++) {
//            MinPQWeightedEdge pq = new MinPQWeightedEdge();
//            for (int j = 0; j < graph.vertices; j++) {
//                if (graph.adjacencyMatrix[i][j] != 0) {
//                    System.out.println("Adding ("+i+","+j+") to pq");
//                    pq.insert(new WeightedEdge(i, j, graph.adjacencyMatrix[i][j]));
//                }
//            }
//
//            WeightedEdge nextEdge = pq.retrieveMin();
//                int vertex1 = nextEdge.getVertex1();
//                int vertex2 = nextEdge.getVertex2();
//
//
//                if (mst[vertex2][vertex1]==0 || (mst[vertex2][vertex1]!=0 && mst[vertex1][vertex2]<mst[vertex2][vertex1])){
//                    System.out.println("Adding ("+vertex1+","+vertex2+") to mst");
//                    mst[vertex1][vertex2] = nextEdge.getWeight();
//                }
//            //mst[vertex2][vertex1] = nextEdge.getWeight();
//        }

        return mst;
    }
}
