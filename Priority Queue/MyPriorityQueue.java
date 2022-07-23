public class MyPriorityQueue<Type extends Comparable<Type>> {
    private MyArrayList<Type> heap;
    public MyPriorityQueue() {
        heap = new MyArrayList<Type>();
    }

    //public static void main(String[] args) {
        //MyPriorityQueue <Integer> A;
    //}
    public void insert(Type item) {
        heap.insert(item, size());
        bubbleUp();
    }
    public Type removeMin() {
        if(size()==0)
            return null;
        Type s = heap.get(0);
        heap.set(0, heap.get(heap.size()-1));
        sinkDown();
        heap.remove(heap.size()-1);
        return s;
    }

    public Type min() {
        if(size()==0){
            return null;
        }
        return heap.get(0);
    }

    public int size() {

        return heap.size();
    }

    public boolean isEmpty() {

        return heap.size() == 0;
    }

    public String toString() {

        return heap.toString();
    }

   private void bubbleUp() {
        int index = size() - 1;
        int parent = parent(index);
        Type temp = heap.get(index);
        while (index > 0 && heap.get(parent).compareTo(temp) > 0) {
            heap.set(index, heap.get(parent));
            index = parent;
            parent = parent(index);
        }
        heap.set(index, temp);
    }

    private void sinkDown() {
        int index = 0;
        int numOfItems = size();
        Type temp = heap.get(index);
        int Child1;
        while (index < numOfItems / 2) {
            int left = left(index);
            int right = right(index);
            if (right < numOfItems && heap.get(left).compareTo(heap.get(right)) > 0)
                Child1 = right;
            else
                Child1 = left;
            if (temp.compareTo(heap.get(Child1)) < 0)
                break;
            heap.set(index, heap.get(Child1));
            index = Child1;
        }
        heap.set(index, temp);
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }
    private int left(int index) {
        return (2 * index) + 1;
    }

    private int right(int index) {
        return (2 * index) + 2;
    }
}