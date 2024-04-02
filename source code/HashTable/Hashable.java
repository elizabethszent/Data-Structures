//Taken from CPSC 331 WINTER 2024 Lecture slides
/**
 * Interface for objects that can be used as keys in hash-based data structures.
 *
 * @param <T> the type of objects that can be hashed
 */
public interface Hashable<T extends Comparable> {
	
    /**
     * Returns the key of the object.
     *
     * @return the key
     */
	public String key();
}
