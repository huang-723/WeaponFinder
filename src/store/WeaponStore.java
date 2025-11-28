package store;
import model.Weapon;
import java.util.ArrayList;
import java.util.List;

public class WeaponStore {
    private List<Weapon> weaponList = new ArrayList<>();
    //A private member variable that uses Arraylist to
    //implement the list interface for storing weapon inventory data

    public void addWeapon(Weapon weapon) {
        boolean isValid = true;

        if (weapon.getId().isEmpty() == true) {
            System.out.println("Unable to add Weapon: ID is empty...");
            isValid = false;
        }

        if (weapon.getId().length() < 3 == true) {
            System.out.println("Unable to add Weapon: ID is too short...");
            isValid = false;
        }

        if (weapon.getType().equals("Sea") == false) {
            if (weapon.getType().equals("Land") == false) {
                if (weapon.getType().equals("Air") == false) {
                    System.out.println("Unable to add Weapon: Type must be Sea/Land/Air...");
                    isValid = false;
                }
            }
        }  //Determine whether the weapon type is valid

        if (weapon.getPower() < 1 == true) {
            System.out.println("Unable to add Weapon: Power is too low...");
            isValid = false;
        }

        if (weapon.getPower() > 100 == true) {
            System.out.println("Unable to add Weapon: Power is too high...");
            isValid = false;
        }

        if (weapon.getName().isEmpty() == true) {
            System.out.println("Unable to add Weapon: Name is empty...");
            isValid = false;
        }

        if (isValid == true) {
            weaponList.add(weapon);
            System.out.println("Weapon added successfully!");
        }
    }  //verify the validity of the weapon
       //if valid,add it to the inventory
       //if invalid,prompt the error messages

    public void deleteWeapon(String id) {
        boolean isDeleted = false;
        for (int i = 0; i < weaponList.size(); i++) {
            Weapon currentWeapon = weaponList.get(i);
            if (currentWeapon.getId().equals(id) == true) {
                weaponList.remove(i);
                isDeleted = true;
                break;
            }
        }
        if (isDeleted == true) {
            System.out.println("Weapon deleted successfully!");
        } else {
            System.out.println("Unable to delete Weapon: ID not found...");
        }
    } //delete the corresponding weapon from the inventory based on the weapon ID

    public void listWeapons() {
        if (weaponList.isEmpty() == true) {
            System.out.println("No weapons in store...");
            return;
        }
        System.out.println("----------------------------");
        System.out.println("  Weapon Data               ");
        System.out.println("----------------------------");
        for (int i = 0; i < weaponList.size(); i++) {
            Weapon currentWeapon = weaponList.get(i);
            System.out.println(currentWeapon);
        }
    } //display all weapon information in a formatted manner

    public List<Weapon> getWeaponList() {
        return weaponList;
    }
}