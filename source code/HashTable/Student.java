/**
 * Represents a student entity with an ID and a name.
 */
public class Student implements Hashable<Integer> {
    private int id;
    private String name;

    /**
     * Constructs a new Student object with the given ID and name.
     *
     * @param id   the ID of the student
     * @param name the name of the student
     */
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Retrieves the ID of the student.
     *
     * @return the ID of the student
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the name of the student.
     *
     * @return the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Generates the key for hashing, which is the ID of the student.
     *
     * @return the key for hashing
     */
    @Override
    public String key() {
        return String.valueOf(id);
    }
   
    /**
     * Returns a string representation of the student.
     *
     * @return a string representation of the student in the format "ID:Name"
     */
    @Override
    public String toString() {
        return id + ":" + name;
    }
    
    /**
     * Interface representing an object that can be hashed.
     *
     * @param <T> the type of the key used for hashing
     */
    public interface Hashable<T> {
        T key();
    }
}
