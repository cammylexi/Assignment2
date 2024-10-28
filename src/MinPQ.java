public class MinPQ<T extends Job> {
    private Job[] heap;
    private int size;

    public MinPQ(int capacity) {
        heap = new Job[capacity + 1]; // Index 0 is unused
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(T job) {
        if (size == heap.length - 1) resize(2 * heap.length); // Resize if full
        heap[++size] = job;
        swim(size);
    }

    public T delMin() {
        if (isEmpty()) {
            System.out.println("Priority queue underflow");
            return null;
        }
        T min = (T) heap[1];
        swap(1, size--);
        heap[size + 1] = null;
        sink(1);

        if (size > 0 && size == (heap.length - 1) / 4) resize(heap.length / 2);
        return min;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        Job temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private boolean greater(int i, int j) {
        return heap[i].compareTo(heap[j]) > 0;
    }

    private void resize(int capacity) {
        Job[] temp = new Job[capacity];
        System.arraycopy(heap, 1, temp, 1, size);
        heap = temp;
    }
}
