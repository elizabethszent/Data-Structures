/**
 * Problem 2: Designing a Hash Table for School Database Management
 * This class demonstrates the usage of the HashTableSC implementation for managing a school database.
 */
public class MainHashTable {
	
    /**
     * Main method to test the functionality of the hash table.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create hash table with a hash function and size 8
        HashFunction hashFunction = new SimpleHashFunction();
        HashTableInterface<Student> database = new HashTableSC<>(hashFunction, 8);
        
        // 1. Insert students
        database.insert(20500120, "Bob");
        database.insert(20700200, "Alice");
        database.insert(30100230, "Cathy");
        database.insert(20200156, "Ali");

        // 2. Print entire database
        System.out.println(database.toString());

        // 3. Update Bob's name to Bobby
        database.insert(20500120, "Bobby");

        // 4. Search for student with ID 20500120
        System.out.println("Student with ID 20500120 found: " + database.search(20500120));

        // 5. Retrieve the name associated with ID 20700200
        String name = database.retrieve(20700200);
        if (name != null) {
            System.out.println("Name associated with ID 20700200: " + name);
        } else {
            System.out.println("Name associated with ID 20700200: Not found");
        }

        // 6. Remove student with ID 20700200
        database.delete(20700200);

        // 7. Try to remove again
        database.delete(20700200);

        // 8. Try to retrieve student with ID 20700200
        name = database.retrieve(20700200);
        if (name != null) {
            System.out.println("Name associated with ID 20700200: " + name);
        } else {
            System.out.println("Name associated with ID 20700200: Not found");
        }

        // 9. Print updated database
        System.out.println(database.toString());
    }
}


