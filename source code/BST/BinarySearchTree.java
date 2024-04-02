import java.util.ArrayList;

//Code taken from tutorial slides CPSC 331 WINTER 2024
/**
 * Represents a binary search tree.
 *
 * @param <T> the type of elements in the tree, must extend Comparable
 */
public class BinarySearchTree<T extends Comparable<T>> implements BinarySearchTreeInterface<T> {
    /**
     * Represents a node in the binary search tree.
     */
	public class Node {
        T data;
        Node left, right;
        /**
         * Constructs a node with the given data.
         *
         * @param data the data to be stored in the node
         */
        Node(T data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    Node root;
    private ArrayList<T> traversalList;
    private int current;
    
    /**
     * Constructs an empty binary search tree.
     */
    public BinarySearchTree() {
        root = null;
        traversalList = new ArrayList<>();
        current = -1;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right); // Size of the tree rooted at this node
    }

    @Override
    public void clear() {
        root = null; // Clear the root, effectively clearing the tree
    }

    @Override
    public void add(T element) {
        root = addRecursive(root, element); // Add element recursively starting from the root
    }

    private Node addRecursive(Node current, T element) {
        if (current == null) {
            return new Node(element); // Create a new node if the current node is null
        }

        if (element.compareTo(current.data) < 0) {
            current.left = addRecursive(current.left, element);  // Recursively add to the left subtree
        } else if (element.compareTo(current.data) > 0) {
            current.right = addRecursive(current.right, element);  // Recursively add to the right subtree
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    @Override
    public boolean contains(T element) {
        return containsRecursive(root, element);  // Check if element exists recursively
    }

    private boolean containsRecursive(Node current, T element) {
        if (current == null) {
            return false;  // Element not found
        }

        if (element.compareTo(current.data) == 0) {
            return true;  // Element found
        }

        return element.compareTo(current.data) < 0
            ? containsRecursive(current.left, element)  // Search in the left subtree
            : containsRecursive(current.right, element);  // Search in the right subtree
    }

    @Override
    public void remove(T element) {
        root = removeRecursive(root, element);  // Remove element recursively starting from the root
    }

    private Node removeRecursive(Node current, T element) {
        if (current == null) {
            return null;  // Element not found
        }

        if (element.compareTo(current.data) == 0) {
            if (current.left == null && current.right == null) {
                return null;  // Node has no children
            }
            if (current.right == null) {
                return current.left;  // Node has only left child
            }
            if (current.left == null) {
                return current.right;  // Node has only right child
            }
         // Node has both children, replace with the smallest value in the right subtree
            T smallestValue = findSmallestValue(current.right);
            current.data = smallestValue;
            current.right = removeRecursive(current.right, smallestValue); // Remove the replaced node
            return current;
        }

        if (element.compareTo(current.data) < 0) {
            current.left = removeRecursive(current.left, element); // Remove from left subtree
        } else {
            current.right = removeRecursive(current.right, element); // Remove from right subtree
        }
        return current;
    }

    private T findSmallestValue(Node root) {
        return root.left == null ? root.data : findSmallestValue(root.left); // Find the smallest value in the subtree
    }

    @Override
    public void reset(int order) {
        traversalList.clear(); // Clear the traversal list
        current = 0;
        switch (order) {
            case 0: // Inorder
                inorderRecursive(root);  // Perform inorder traversal
                break;
            case 1: // Preorder
                preorderRecursive(root);  // Perform preorder traversal
                break;
            case 2: // Postorder
                postorderRecursive(root);; // Perform postorder traversal
                break;
            default:
                throw new IllegalArgumentException("Invalid order: " + order);  // Unsupported traversal order
        }
    }

    @Override
    public T getNext(int order) {
        if (current >= 0 && current < traversalList.size()) {
            return traversalList.get(current++); // Get the next element in the traversal list
        }
        return null;
    }

    @Override
    public void inorderTraversal() {
        inorderRecursive(root); // Perform inorder traversal
        System.out.println();
    }

    private void inorderRecursive(Node node) {
        if (node != null) {
            inorderRecursive(node.left); // Traverse left subtree
            System.out.print(node.data + " "); // Visit current node
            inorderRecursive(node.right); // Traverse right subtree
        }
    }

    @Override
    public void postorderTraversal() {
        postorderRecursive(root);  // Perform postorder traversal
        System.out.println();
    }

    private void postorderRecursive(Node node) {
        if (node != null) {
            postorderRecursive(node.left);  // Traverse left subtree
            postorderRecursive(node.right); // Traverse right subtree
            System.out.print(node.data + " "); // Visit current node
        }
    }

    @Override
    public void preorderTraversal() {
        preorderRecursive(root); // Perform preorder traversal
        System.out.println();
    }

    private void preorderRecursive(Node node) {
        if (node != null) {
            System.out.print(node.data + " "); // Visit current node
            preorderRecursive(node.left); // Traverse left subtree
            preorderRecursive(node.right); // Traverse right subtree
        }
    }

    /**
     * Finds the maximum value in the tree.
     *
     * @return the maximum value in the tree, or null if the tree is empty
     */
    public T findMax() {
        if (isEmpty()) {
            return null; // or throw an exception, depending on your preference
        }
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    /**
     * Finds the maximum value in the tree.
     *
     * @return the maximum value in the tree, or null if the tree is empty
     */
    public T findMin() {
        if (isEmpty()) {
            return null; // or throw an exception, depending on your preference
        }
        Node current = root;
        while (current.left != null) {
            current = current.left; // Traverse to the rightmost node
        }
        return current.data;
    }

    
    /**
     * Calculates the height of the tree.
     *
     * @return the height of the tree
     */
    public int height() {
        return height(root); // Calculate height starting from the root
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = height(node.left); // Height of the left subtree
            int rightHeight = height(node.right); // Height of the right subtree

            return Math.max(leftHeight, rightHeight) + 1; // Height of the current node
        }
    }
    /**
     * Gets the value stored in the root node of the tree.
     *
     * @return the value stored in the root node, or null if the tree is empty
     */
    public T getRootValue() {
        if (root != null) {
            return root.data; // Return the data of the root node
        }
        return null; // Tree is empty
    }

    /**
     * Main method for testing the BinarySearchTree class.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Adding elements to the BST
        bst.add(50);
        bst.add(30);
        bst.add(20);
        bst.add(40);
        bst.add(70);
        bst.add(60);
        bst.add(80);

        // Display the tree size
        System.out.println("Size of the BST: " + bst.size());

        // Perform different traversals
        System.out.println("Inorder traversal of the BST:");
        bst.inorderTraversal();

        System.out.println("Preorder traversal of the BST:");
        bst.preorderTraversal();

        System.out.println("Postorder traversal of the BST:");
        bst.postorderTraversal();

        System.out.println("Maximum element in the BST: " + bst.findMax());
        System.out.println("Minimum element in the BST: " + bst.findMin());

        // Check if the BST contains a specific element
        System.out.println("Does the BST contain 40? " + bst.contains(40));

        // Remove an element
        System.out.println("Removing 40 from the BST.");
        bst.remove(40);
        System.out.println("Does the BST contain 40 after removal? " + bst.contains(40));

        // Reset and getNext for inorder traversal (order = 0)
        System.out.println("Resetting traversal (inorder) and getting elements:");
        bst.reset(0);
        Integer next;
        while ((next = bst.getNext(0)) != null) {
            System.out.print(next + " ");
        }
        System.out.println();

        // Demonstrating clear
        bst.clear();
        System.out.println("After clearing, is the BST empty? " + bst.isEmpty());
    }
}