//Taken from CPSC 331 WINTER 2024 Lecture slides
/**
 * Class for objects that can be hashed.
 */
public class HashableObject implements Hashable {
	private String key;

    /**
     * Constructs a HashableObject with the given key.
     *
     * @param s the key of the object
     */
	public HashableObject(String s) {
		key = s;
	}
	
	  /**
     * Returns the key of the object.
     *
     * @return the key
     */
	@Override
	public String key() {
		return key;
	}


}