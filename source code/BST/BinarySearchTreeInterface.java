//Code taken from tutorial slides CPSC 331 WINTER 2024
/**
 * Interface for a binary search tree.
 *
 * @param <T> the type of elements in the tree, must extend Comparable
 */
public interface BinarySearchTreeInterface <T extends Comparable<T>>  {
    /**
     * Checks if the tree is empty.
     *
     * @return true if the tree is empty, false otherwise
     */
	boolean isEmpty();
	
    /**
     * Checks if the tree is full.
     *
     * @return always false since the tree is dynamic and can grow as needed
     */
    boolean isFull();
    
    /**
     * Gets the number of elements in the tree.
     *
     * @return the number of elements in the tree
     */
    int size();
    
    /**
     * Clears all elements from the tree.
     */
    void clear();
    
    /**
     * Adds an element to the tree.
     *
     * @param element the element to be added
     */
    void add(T element);
    
    /**
     * Checks if the tree contains the specified element.
     *
     * @param element the element to be checked for existence
     * @return true if the tree contains the element, false otherwise
     */
    boolean contains(T element);
    
    /**
     * Removes the specified element from the tree.
     *
     * @param element the element to be removed
     */
    void remove(T element);
    
    /**
     * Resets the traversal of the tree to the specified order.
     * Order:
     * 0 - Inorder
     * 1 - Preorder
     * 2 - Postorder
     *
     * @param order the order of traversal to be reset to
     */
    void reset(int order);
    
    /**
     * Gets the next element in the traversal order specified.
     *
     * @param order the order of traversal
     * @return the next element in the specified traversal order, or null if there are no more elements
     */
    T getNext(int order);
    
    /**
     * Performs an inorder traversal of the tree.
     */
    void inorderTraversal();
    
    /**
     * Performs a postorder traversal of the tree.
     */
    void postorderTraversal();
    
    /**
     * Performs a preorder traversal of the tree.
     */
    void preorderTraversal();

}
