//Taken from CPSC 331 WINTER 2024 Tutorial slides

/**
 * Represents a Max Heap data structure and implements Heap Sort algorithm.
 * This class provides methods to build a max heap, perform heap sort, insert elements,
 * remove the maximum element, check if the heap is full or empty, get the size of the heap,
 * get the maximum element, and count swaps during heap operations.
 * It includes two methods for creating a max heap: heapify and adding elements one by one.
 */
public class MainMaxHeap {
    private int[] Heap; // Array to store heap elements
    private int size;
    private int maxsize;
    private int swaps; // Variable to count swaps

    /**
     * Constructs a MaxHeap object with the specified maximum size.
     *
     * @param maxsize the maximum size of the heap
     */
    public MainMaxHeap(int maxsize) {
        this.maxsize = maxsize;  // Initialize the maximum size of the heap
        this.size = 0;  // Initialize the current size of the heap
        this.swaps = 0;  // Initialize the swap count
        Heap = new int[this.maxsize + 1]; // Initialize the heap array
        Heap[0] = Integer.MAX_VALUE; // Sentinel value at the 0th index to make indexing easier
    }

    /**
     * Returns the index of the parent for the node at index pos.
     *
     * @param pos the index of the node
     * @return the index of the parent node
     */
    private int parent(int pos) { return pos / 2; } // Calculate the parent index for the given node index

    /**
     * Returns the index of the left child for the node at index pos.
     *
     * @param pos the index of the node
     * @return the index of the left child
     */
    private int leftChild(int pos) { return (2 * pos); } // Calculate the left child index for the given node index

    /**
     * Returns the index of the right child for the node at index pos.
     *
     * @param pos the index of the node
     * @return the index of the right child
     */
    private int rightChild(int pos) { return (2 * pos) + 1; } // Calculate the right child index for the given node index

    /**
     * Checks if the node at index pos is a leaf node.
     *
     * @param pos the index of the node
     * @return true if the node is a leaf node, false otherwise
     */
    private boolean isLeaf(int pos) { return pos > (size / 2) && pos <= size; } // Check if the node is a leaf node

    /**
     * Swaps two nodes of the heap at indexes fpos and spos.
     *
     * @param fpos the index of the first node
     * @param spos the index of the second node
     */
    private void swap(int fpos, int spos) {
        int tmp = Heap[fpos]; // Store the value of the first node
        Heap[fpos] = Heap[spos]; // Assign the value of the second node to the first node
        Heap[spos] = tmp; // Assign the stored value of the first node to the second node
        swaps++; // Increment the swap count
    }

    /**
     * Heapifies the node at index pos.
     *
     * @param pos the index of the node
     */
    private void maxHeapify(int pos) {
        int left = leftChild(pos); // Calculate the index of the left child
        int right = rightChild(pos); // Calculate the index of the right child
        int largest = pos; // Initialize the largest element index as the current node
        
        // Check if the left child is larger than the current node
        if (left <= size && Heap[left] > Heap[largest]) {
            largest = left;
        }
        
        // Check if the right child is larger than the current node or the left child
        if (right <= size && Heap[right] > Heap[largest]) {
            largest = right;
        }
        // If the largest element is not the current node, swap and heapify recursively
        if (largest != pos) {
            swap(pos, largest);
            maxHeapify(largest);
        }
    }

    /**
     * Builds a max heap from the input array using heapify method.
     *
     * @param arr the input array
     */
    public void buildHeapify(int[] arr) {
        if (arr.length > maxsize) {
            System.out.println("Input array size exceeds heap size. Truncating...");
        }
        size = Math.min(arr.length, maxsize);  // Set the current size of the heap
        for (int i = 0; i < size; i++) {
            Heap[i + 1] = arr[i];  // Assign elements from the input array to the heap array
        }
        for (int i = (size / 2); i >= 1; i--) {
            maxHeapify(i);  // Heapify starting from the parent nodes
        }
    }


    /**
     * Builds a max heap from the input array by adding elements one by one.
     *
     * @param arr the input array
     */
    public void buildOneByOne(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            insert(arr[i]); // Insert elements one by one into the heap
        }
    }

    /**
     * Performs heap sort on the heap.
     */
    public void heapSort() {
        int originalSize = size; // Store the original size of the heap
        for (int i = originalSize; i > 1; i--) {
            swap(1, i); // Swap the maximum element with the last element
            size--; // Reduce the size of the heap
            maxHeapify(1); // Heapify the root node
        }
        size = originalSize; // Restore the size of the heap
    }

    /**
     * Inserts a new element into the heap.
     *
     * @param element the element to be inserted
     */
    public void insert(int element) {
        if (size >= maxsize) {
            return; // Heap is full, no more insertion allowed
        }
        Heap[++size] = element; // Increment size and insert the new element at the end of the heap
        int current = size; // Initialize the current index as the newly inserted element index

        // Move the new element up to maintain the heap property
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current)); // Swap the element with its parent if necessary
            current = parent(current); // Update the current index
        }
    }

    /**
     * Removes and returns the maximum element from the heap.
     *
     * @return the maximum element in the heap
     */
    public int remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty"); // Throw exception if heap is empty
        }
        int popped = Heap[1]; // Store the maximum element
        Heap[1] = Heap[size--]; // Replace the root with the last element
        maxHeapify(1); // Heapify the root node
        return popped; // Return the maximum element
    }

    /**
     * Checks if the heap is full.
     *
     * @return true if the heap is full, false otherwise
     */
    public boolean isFull() { return size == maxsize; } // Check if the current size equals the maximum size

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() { return size == 0; } // Check if the current size is zero

    /**
     * Returns the size of the heap.
     *
     * @return the size of the heap
     */
    public int size() { 
    	return size;  // Return the current size of the heap
    	}

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element in the heap
     * @throws IllegalStateException if the heap is empty
     */
    public int getMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty"); // Throw exception if heap is empty
        }
        return Heap[1];  // Return the root element which is the maximum element
    }

    /**
     * Returns the number of swaps performed during heap operations.
     *
     * @return the number of swaps
     */
    public int getSwaps() { 
    	return swaps;  // Return the total number of swaps
    	}

    /**
     * Main method to demonstrate the usage of MainMaxHeap class and perform Heap Sort.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        MainMaxHeap maxHeap = new MainMaxHeap(1000); // Create a new MainMaxHeap object

        // Example usage
        int[] randomArray = new int[1000]; // Create an array for random values
        for (int i = 0; i < 1000; i++) {
            randomArray[i] = (int) (Math.random() * 1000); // Random array with values between 0 and 999
        }

        int[] sortedArray = new int[1000]; // Create an array for sorted values
        for (int i = 0; i < 1000; i++) {
            sortedArray[i] = i; // Sorted array [0, 1, 2, ..., 999]
        }

        // Heapify method
        maxHeap.buildHeapify(randomArray);// Build max heap using heapify method with random array
        int heapifySwapsRandom = maxHeap.getSwaps(); // Get the number of swaps
        maxHeap.heapSort(); // Perform heap sort
        int heapifySortSwapsRandom = maxHeap.getSwaps(); // Get the number of swaps after sorting

        maxHeap.buildHeapify(sortedArray); // Build max heap using heapify method with sorted array
        int heapifySwapsSorted = maxHeap.getSwaps(); // Get the number of swaps
        maxHeap.heapSort(); // Perform heap sort
        int heapifySortSwapsSorted = maxHeap.getSwaps(); // Get the number of swaps after sorting

        // OneByOne method
        maxHeap.buildOneByOne(randomArray); // Build max heap using one by one method with random array
        int oneByOneSwapsRandom = maxHeap.getSwaps(); // Get the number of swaps
        maxHeap.heapSort(); // Perform heap sort
        int oneByOneSortSwapsRandom = maxHeap.getSwaps(); // Get the number of swaps after sorting

        maxHeap.buildOneByOne(sortedArray); // Build max heap using one by one method with sorted array
        int oneByOneSwapsSorted = maxHeap.getSwaps(); // Get the number of swaps
        maxHeap.heapSort(); // Perform heap sort
        int oneByOneSortSwapsSorted = maxHeap.getSwaps(); // Get the number of swaps after sorting

        // Output
        System.out.println("For part 1 in Section (b):");
        System.out.println("Heapify method - Random Array: " + heapifySwapsRandom);
        System.out.println("OneByOne method - Random Array: " + oneByOneSwapsRandom);
        System.out.println("Heapify method - Sorted Array: " + heapifySwapsSorted);
        System.out.println("OneByOne method - Sorted Array: " + oneByOneSwapsSorted);

        System.out.println("For part 2 in Section (b):");
        System.out.println("Heapify method - Random Array: " + (heapifySwapsRandom + heapifySortSwapsRandom));
        System.out.println("OneByOne method - Random Array: " + (oneByOneSwapsRandom + oneByOneSortSwapsRandom));
        System.out.println("Heapify method - Sorted Array: " + (heapifySwapsSorted + heapifySortSwapsSorted));
        System.out.println("OneByOne method - Sorted Array: " + (oneByOneSwapsSorted + oneByOneSortSwapsSorted));
    }
}
