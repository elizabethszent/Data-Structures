//Taken from CPSC 331 WINTER 2024 Lecture slides
import java.util.LinkedList;


/**
 * Hash table implementation using separate chaining collision resolution.
 *
 * @param <T> the type of elements stored in the hash table, must extend Student
 */
public class HashTableSC<T extends Student> implements HashTableInterface<T> {
    private LinkedList<T>[] hashTable;
    private HashFunction f;

    /**
     * Constructs a hash table with the specified hash function and size.
     *
     * @param f the hash function to use
     * @param m the size of the hash table
     */
    public HashTableSC(HashFunction f, int m) {
        hashTable = new LinkedList[m]; // Create an array of LinkedLists
        this.f = f;  // Assign the hash function
    }
    
    /**
     * Clears all elements from the hash table.
     */
    @Override
    public void clear() {
        for (int i = 0; i < hashTable.length; i++)  // Iterate through the array
            hashTable[i] = null; // Set each element to null
    }

    /**
     * Adds an item to the hash table.
     *
     * @param item the item to add
     */
    @Override
    public void add(T item) {
        int index = f.hash(item.key(), hashTable.length); // Compute hash index
        if (hashTable[index] == null) { // If no list exists at index
            hashTable[index] = new LinkedList<>(); // Create new linked list
        }
        // Remove existing entry with the same ID, if any
        hashTable[index].removeIf(entry -> entry.key().equals(item.key()));
        // Add the new entry
        hashTable[index].add(item);
    }
    
    /**
     * Removes an item from the hash table.
     *
     * @param item the item to remove
     */
    @Override
    public void remove(T item) {
        int index = f.hash(item.key(), hashTable.length); // Compute hash index
        if (hashTable[index] != null) // If list exists at index
            hashTable[index].remove(item); // Remove item from list
    }

    /**
     * Checks if an item is contained in the hash table.
     *
     * @param item the item to search for
     * @return true if the item is found, false otherwise
     */
    @Override
    public boolean contains(T item) {
        int index = f.hash(item.key(), hashTable.length); // Compute hash index
        if (hashTable[index] != null) { // If list exists at index
            for (T entry : hashTable[index]) { // Iterate through list
                if (entry.key().equals(item.key())) { // If key matches
                    return true;  // Item found
                }
            }
        }
        return false; // Item not found
    }

    /**
     * Returns a string representation of the hash table.
     *
     * @return a string representation of the hash table
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashTable.length; i++) {  // Iterate through the array
            sb.append(i).append(": ["); // Append index
            if (hashTable[i] != null) {  // If list exists at index
                for (T item : hashTable[i]) {  // Iterate through list
                    sb.append(item.getId()).append(":").append(item.getName()).append(","); // Append item details
                }
            }
            sb.append("]\n");  // Close list
        }
        return sb.toString();  // Return string representation
    }
    
    /**
     * Inserts a new item with the given ID and name into the hash table.
     *
     * @param ID   the ID of the item
     * @param name the name of the item
     */
    @Override
    public void insert(int ID, String name) {
        T student = (T) new Student(ID, name);  // Create new student object
        add(student);  // Add student to hash table
    }


    /**
     * Deletes the item with the given ID from the hash table.
     *
     * @param ID the ID of the item to delete
     */
    @Override
    public void delete(int ID) {
        boolean removed = false; // Flag to track if item is removed
        for (LinkedList<T> bucket : hashTable) { // Iterate through the array
            if (bucket != null) { // If list exists at index
                for (T item : bucket) { // Iterate through list
                    if (item.getId() == ID) { // If ID matches
                        bucket.remove(item); // Remove item from list
                        removed = true; // Set flag to true
                        break;  // Exit loop
                    }
                }
                if (removed) {  // If item is removed
                    break; // Exit loop
                }
            }
        }
        if (!removed) { // If item is not removed
            System.out.println("No student with ID " + ID + " found in the hash table.");
        }
    }

    /**
     * Searches for the item with the given ID in the hash table.
     *
     * @param ID the ID of the item to search for
     * @return true if the item is found, false otherwise
     */
    @Override
    public boolean search(int ID) {
        for (LinkedList<T> bucket : hashTable) { // Iterate through the array
            if (bucket != null) { // If list exists at index
                for (T item : bucket) { // Iterate through list
                    if (item.getId() == ID) { // If ID matches
                        return true; // Item found
                    }
                }
            }
        }
        return false; // Item not found
    }

    /**
     * Retrieves the name of the item with the given ID from the hash table.
     *
     * @param ID the ID of the item to retrieve
     * @return the name of the item
     */
    @Override
    public String retrieve(int ID) {
        for (LinkedList<T> bucket : hashTable) {  // Iterate through the array
            if (bucket != null) { // If list exists at index
                for (T item : bucket) {// Iterate through list
                    if (item.getId() == ID) { // If ID matches
                        return item.getName(); // Return name
                    }
                }
            }
        }
        System.out.println("No student with ID " + ID + " found in the hash table.");
        return null;// Return null if item not found
    }

	
}
