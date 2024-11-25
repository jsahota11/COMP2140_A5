public class MinPQWeightedEdge {
    WeightedEdge[] minheap;
    int size;

    public MinPQWeightedEdge() {
        minheap = new WeightedEdge[2];
        size = 0;
    }

    public void insert(WeightedEdge edge){
        if (isFull()){
            resize();
        }

        //index of last value is size-1
        //parent index is floor of (i-1)/2 or if i = 0 then i is the root and there is no parent
        //for last value, do (size-2)/2

        int currIndex = size;
        minheap[size++] = edge;
        //covers if we are adding the first value to the queue
        int parentIndex = Math.max(0,(currIndex-1)/2);

        WeightedEdge parent = minheap[parentIndex];
        WeightedEdge curr = minheap[currIndex];
        while (curr.compareTo(parent) < 0 && curr!=minheap[0]){
            curr = minheap[currIndex];
            parent = minheap[parentIndex];

//            WeightedEdge temp = edge;
//            edge = parent;
//            parent = temp;

            minheap[currIndex] = parent;
            minheap[parentIndex] = curr;

            currIndex = parentIndex;
            parentIndex = (currIndex-1)/2;

        }


        //takes a weighted edge and adds it to the minimum priority queue
        //use the algorithm from the notes to insert it, inc the size
        //return if it was successful
    }

    public WeightedEdge retrieveMin(){
        WeightedEdge temp = minheap[0];
        minheap[0] = minheap[--size];

        //index of children for a parent at index i:
        //2i + 1 = left child
        //2i + 2 = right child
        int index = 0;

        while ((minheap[2*index+1]!=null && minheap[index].compareTo(minheap[2*index+1]) > 0) || (minheap[2*index+2]!=null && minheap[index].compareTo(minheap[2*index+2]) > 0)){
            WeightedEdge curr;

            if (minheap[2*index+1]==null){
                curr = minheap[index];
                minheap[index] = minheap[2*index+2];
                minheap[2*index+2] = curr;
            } else if (minheap[2*index+2]==null){
                curr = minheap[index];
                minheap[index] = minheap[2*index+1];
                minheap[2*index+1] = curr;

            } else {
                if (minheap[2*index+1].compareTo(minheap[2*index+2]) > 0){
                    curr = minheap[2*index+2];
                    minheap[2*index+2] = minheap[2*index+1];
                    minheap[2*index+1] = curr;

                    index = 2*index+1;
                } else {
                    curr = minheap[2*index+1];
                    minheap[2*index+1] = minheap[2*index+2];
                    minheap[2*index+2] = curr;

                    index = 2*index+2;
                }
            }
        }

        return temp;
    }

    public boolean isEmpty(){
        return minheap[0] == null;
    }

    public void print(){
        for (int i = 0; i < size; i++) {
            System.out.println(minheap[i].toString());
        }
    }

    public void resize(){
        WeightedEdge[] newHeap = new WeightedEdge[2 * size];
        System.arraycopy(minheap, 0, newHeap, 0, size);
        minheap = newHeap;
    }

    public boolean isFull(){
        return size == minheap.length;
    }
}
