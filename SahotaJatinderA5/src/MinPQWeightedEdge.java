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

        int currIndex = size;
        minheap[size++] = edge;

        boolean needsSwap = true;
        while (currIndex>0 && needsSwap){
            int parentIndex = (currIndex-1)/2;

            WeightedEdge parent = minheap[parentIndex];
            WeightedEdge curr = minheap[currIndex];

            if (curr.compareTo(parent)>=0){
                needsSwap = false;
            } else {
                minheap[currIndex] = parent;
                minheap[parentIndex] = curr;

                currIndex = parentIndex;
            }
        }
    }

    public WeightedEdge retrieveMin(){
        WeightedEdge min = null;

        if (!isEmpty()){
            min = minheap[0]; //grab the root (smallest) element
            minheap[0] = minheap[--size]; //decrement the size and grab the last element
            minheap[size] = null; //set the previous last element to null

            boolean swap = true;
            int index = 0;

            while (swap){
                int leftChild = 2*index + 1;
                int rightChild = 2*index + 2;
                int currIndex = index;

                //important to use 2 if statements as if the right child is smaller than its sibling, we can
                //switch the indices to ensure that we are taking the smallest child to maintain the min heap structure
                if (leftChild<size && minheap[leftChild].compareTo(minheap[currIndex])<0){
                    currIndex = leftChild;
                }

                if (rightChild < size && minheap[rightChild].compareTo(minheap[currIndex])<0){
                    currIndex = rightChild;
                }

                if (currIndex!=index){
                    WeightedEdge temp = minheap[index];
                    minheap[index] = minheap[currIndex];
                    minheap[currIndex] = temp;
                    index = currIndex;

                } else {
                    swap = false;
                }

            }
        }

        return min;
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
