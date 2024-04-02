//Taken from CPSC 331 WINTER 2024 Lecture slides
/**
 * Abstract class representing a hash function.
 */
public abstract class HashFunction {
    /**
     * Computes the hash value for the given key.
     *
     * @param key       the key to be hashed
     * @param tableSize the size of the hash table
     * @return the hash value
     */
	public abstract int hash(String key, int tableSize);
}
