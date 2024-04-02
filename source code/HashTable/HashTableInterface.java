/**
 * Interface for a hash table.
 *
 * @param <T> the type of objects stored in the hash table, must implement Hashable interface
 */
public interface HashTableInterface<T extends Hashable> {
	

    /**
     * Clears all items from the hash table.
     */
	public void clear();
	
    /**
     * Adds an item to the hash table.
     *
     * @param item the item to add
     */
	public void add(T item);
	
    /**
     * Removes an item from the hash table.
     *
     * @param item the item to remove
     */
	public void remove(T item);
	
    /**
     * Checks if an item is contained in the hash table.
     *
     * @param item the item to search for
     * @return true if the item is found, false otherwise
     */
	public boolean contains(T item);
	
    /**
     * Inserts an item with the given ID and name into the hash table.
     *
     * @param ID   the ID of the item
     * @param name the name of the item
     */
	public void insert(int ID, String name);

	
    /**
     * Deletes the item with the given ID from the hash table.
     *
     * @param ID the ID of the item to delete
     */
	public void delete(int ID);
	
    /**
     * Retrieves the name of the item with the given ID from the hash table.
     *
     * @param ID the ID of the item to retrieve
     * @return the name of the item
     */
	public String retrieve(int ID);

	
    /**
     * Searches for the item with the given ID in the hash table.
     *
     * @param ID the ID of the item to search for
     * @return true if the item is found, false otherwise
     */
	public boolean search(int ID);


}