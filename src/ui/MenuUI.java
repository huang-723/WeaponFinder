package ui;
import store.WeaponStore;
import search.WeaponSearch;
import model.Weapon;
import java.util.Scanner;//Import relevant data


public class MenuUI {
    private WeaponStore weaponStore = new WeaponStore();
    private WeaponSearch weaponSearch = new WeaponSearch(weaponStore);
    private Scanner input = new Scanner(System.in);//初始化相关数据

    public static void main(String[] args) {
        System.out.println();
        System.out.println("----------------------------");
        System.out.println("  Weapon Finder V1.0        ");
        System.out.println("----------------------------");
        System.out.println();
        MenuUI menu = new MenuUI();//Create a menu

        menu.runMenu();//Start menu

    }

    // Get menu option
    private int getMenuOption() {//打印菜单（可读）
        System.out.print("""
                Weapon Menu                ---------                   1) Add a Weapon                   2) Display All Weapons                   3) Search Weapon                   4) Delete a Weapon                   0) Exit                ==>> """);
        int option = input.nextInt();
        return option;
    }

    // Run main menu loop
    private void runMenu() {
        int option = getMenuOption();
        while (option != 0) {
            switch (option) {
                case 1 -> addWeapon();
                case 2 -> weaponStore.listWeapons();
                case 3 -> searchWeapon();
                case 4 -> deleteWeapon();
                default -> System.out.println("Invalid option entered: " + option);//Remove residues

            }
            // Fix Scanner bug
            System.out.println("\nPress enter key to continue...");
            input.nextLine();
            input.nextLine();
            option = getMenuOption();//Retrieve the menu again

        }
        System.out.println("Exiting program, goodbye!");//Exit prompt

    }

    // Add weapon
    private void addWeapon() {
        input.nextLine();
        System.out.print("Enter Weapon ID (e.g., SEA-001): ");
        String id = input.nextLine();
        System.out.print("Enter Weapon Type (Sea/Land/Air): ");
        String type = input.nextLine();
        System.out.print("Enter Weapon Name: ");
        String name = input.nextLine();
        System.out.print("Enter Weapon Power (1-100): ");
        int power = input.nextInt();
        input.nextLine();
        System.out.print("Enter Weapon Usage: ");
        String usage = input.nextLine();//Weapon-related attributes


        Weapon weapon = new Weapon(id, type, name, power, usage);//Create and save

        weaponStore.addWeapon(weapon);
    }

    // Delete weapon
    private void deleteWeapon() {
        input.nextLine();//清除残留
        System.out.print("Enter Weapon ID to delete: ");
        String id = input.nextLine();
        weaponStore.deleteWeapon(id);//Call the deletion method

    }

    // Search weapon
    private void searchWeapon() {
        input.nextLine();
        System.out.print("Search by 1) ID 2) Type: ");
        int choice = input.nextInt();//Search method

        input.nextLine();

        if (choice == 1) {//Search precisely by ID

            System.out.print("Enter Weapon ID: ");
            String id = input.nextLine();
            Weapon result = weaponSearch.searchById(id);
            if (result != null) {
                System.out.println("----------------------------");
                System.out.println("  Search Result             ");
                System.out.println("----------------------------");
                System.out.println(result);
            }
        } else if (choice == 2) {//Fuzzy matching

            System.out.print("Enter Weapon Type (Sea/Land/Air): ");
            String type = input.nextLine();
            weaponSearch.filterByType(type);
        } else {
            System.out.println("Invalid search option!");//Invalid search method prompt

        }
    }
}