public class WeightedEdge implements Comparable<WeightedEdge> {
    private int vertex1;
    private int vertex2;
    private int weight;

    public WeightedEdge(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public int compareTo(WeightedEdge otherEdge){
        return this.weight - otherEdge.weight;
    }

    public int getWeight(){
        return this.weight;
    }

    public int getVertex1(){
        return this.vertex1;
    }

    public int getVertex2(){
        return this.vertex2;
    }

    public String toString(){
        return "(" + this.vertex1 + "," + this.vertex2 + "): " + this.weight + ")";
    }
}
