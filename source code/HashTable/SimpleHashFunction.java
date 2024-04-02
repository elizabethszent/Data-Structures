/**
 * Simple hash function implementation.
 */
class SimpleHashFunction extends HashFunction {
    
	/**
     * Computes the hash value for the given key and table size.
     *
     * @param key       the key to be hashed
     * @param tableSize the size of the hash table
     * @return the hash value computed for the key
     */
	@Override
	public int hash(String key, int tableSize) {
		// Extract numeric part of the key (assuming format "ID:Name")
		String numericPart = key.split(":")[0];
		// Convert numeric part to integer and hash it
		return Integer.parseInt(numericPart) % tableSize;
	}

}