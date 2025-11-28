package search;
import store.WeaponStore;
import model.Weapon;
import java.util.List;

public class WeaponSearch {
    //Holds an instance of WeaponStore to access the weapon list
    private WeaponStore weaponStore;

    //Constructor:Intializes the WeaponSearch with a WeaponStore instance
    public WeaponSearch(WeaponStore weaponStore) {
        this.weaponStore = weaponStore;
    }
    //Searches for a Weapon by its ID and returns the matching Weapon

    public Weapon searchById(String id) {
        //Get the list of all weapons from WeaponStore
        List<Weapon> weaponList = weaponStore.getWeaponList();
        //Variable to store the found Weapon (null by default)
        Weapon result = null;
        //Iterate through the weapon list to find the matching ID
        for (int i = 0; i < weaponList.size(); i++) {
            Weapon currentWeapon = weaponList.get(i);
            // Check if the current weapon's ID matches the input ID
            if (currentWeapon.getId().equals(id) == true) {
                result = currentWeapon;
                // Assign the matching weapon to result
            }
        }
        // Return the found weapon, or print a message and return null if not found
        if (result != null) {
            return result;
        } else {
            System.out.println("Weapon not found with ID: " + id);
            return null;
        }
    }
    //Filters weapons by type and prints all matching weapons
    public void filterByType(String type) {
        //Get the list of all weapons from WeaponStore
        List<Weapon> weaponList = weaponStore.getWeaponList();
        //Flag to check if any weapons of the input type are found
        boolean hasResult = false;
        // Print header for the filtered weapon type
        System.out.println("----------------------------");
        System.out.println("  " + type + " Type Weapons       ");
        System.out.println("----------------------------");
        // Iterate through the weapon list to find weapons of the input type
        for (int i = 0; i < weaponList.size(); i++) {
            Weapon currentWeapon = weaponList.get(i);
            //Check if the current weapon's type matches the input type
            if (currentWeapon.getType().equals(type) == true) {
                System.out.println(currentWeapon);
                // Print the matching weapon
                hasResult = true;
                // Set flag to true (weapons found)
            }
        }
        // Print a message if no weapons of the input type are found
        if (hasResult == false) {
            System.out.println("No " + type + " type weapons found...");
        }
    }
}

