package ui;
import store.WeaponStore;
import search.WeaponSearch;
import model.Weapon;
import javax.swing.JOptionPane;//Import relevant data

//Uses JOptionPane popups + calls persistence methods
public class MenuUI {
    private WeaponStore weaponStore = new WeaponStore();
    private WeaponSearch weaponSearch = new WeaponSearch(weaponStore);

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,
                "------------------------\n" +
                        "  WeaponFinder V2.0     \n" +
                        "------------------------",
                "Welcome", JOptionPane.INFORMATION_MESSAGE);
        MenuUI menu = new MenuUI();
        menu.weaponStore.loadFromFile(); // V2.0: Load data on startup
        menu.runMenu();
    }
    // Main menu loop: Handles user choices until exit
    private void runMenu() {
        while (true) {
            String menuInput = JOptionPane.showInputDialog(null,
                    "WeaponFinder Menu:\n" +
                            "1) Add a Weapon\n" +
                            "2) Display All Weapons\n" +
                            "3) Search Weapon\n" +
                            "4) Delete a Weapon\n" +
                            "0) Exit\n" +
                            "Please enter your choice (0-4):",
                    "Main Menu", JOptionPane.QUESTION_MESSAGE);
            if (menuInput == null) {
                weaponStore.saveToFile(); // V2.0: Save on popup close
                JOptionPane.showMessageDialog(null, "Exiting program...", "Exit",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
            int choice;
            try {
                choice = Integer.parseInt(menuInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Error: Please enter a NUMBER (0-4)!",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            switch (choice) {
                case 1:
                    addWeapon();
                    break;
                case 2:
                    weaponStore.listWeapons();
                    JOptionPane.showMessageDialog(null, "Weapons listed in console!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 3:
                    searchWeapon();
                    break;
                case 4:
                    deleteWeapon();
                    break;
                case 0:
                    weaponStore.saveToFile(); // V2.0: Save on popup close
                    JOptionPane.showMessageDialog(null, "Goodbye!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null,
                            "Error: Invalid option! Please enter 0-4.",
                            "Invalid Choice", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Collects weapon data via popups + validates numeric input
    private void addWeapon() {
        String id = JOptionPane.showInputDialog(null, "Enter Weapon ID (e.g., SEA-001):", "Add Weapon", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;

        String type = JOptionPane.showInputDialog(null, "Enter Weapon Type (Sea/Land/Air):", "Add Weapon", JOptionPane.QUESTION_MESSAGE);
        if (type == null) return;

        String name = JOptionPane.showInputDialog(null, "Enter Weapon Name:", "Add Weapon", JOptionPane.QUESTION_MESSAGE);
        if (name == null) return;

        int power = 0;
        while (true) {
            String powerInput = JOptionPane.showInputDialog(null, "Enter Weapon Power (1-100):", "Add Weapon", JOptionPane.QUESTION_MESSAGE);
            if (powerInput == null) return;
            try {
                power = Integer.parseInt(powerInput);
                if (power >= 1 && power <= 100) break;
                else
                    JOptionPane.showMessageDialog(null, "Power must be 1-100!", "Invalid Power", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a NUMBER!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
        String usage = JOptionPane.showInputDialog(null, "Enter Weapon Usage:", "Add Weapon", JOptionPane.QUESTION_MESSAGE);
        if (usage == null) return;

        Weapon weapon = new Weapon(id, type, name, power, usage);
        weaponStore.addWeapon(weapon);
    }


    // Delete weapon
    private void deleteWeapon() {
        String id = JOptionPane.showInputDialog(null, "Enter Weapon ID to delete:", "Delete Weapon", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return;
        weaponStore.deleteWeapon(id);
    }


    // Routes search requests to WeaponSearch
    private void searchWeapon() {
        String searchChoice = JOptionPane.showInputDialog(null,
                "Search by:\n1) ID (Partial Match)\n2) Type\n3) Sort by Power (Asc/Desc)\nPlease enter 1/2/3:",
                "Search Weapon", JOptionPane.QUESTION_MESSAGE);
        if (searchChoice == null) return;

        int choice;
        try {
            choice = Integer.parseInt(searchChoice);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter 1/2/3!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (choice) {
            case 1:
                String id = JOptionPane.showInputDialog(null, "Enter Weapon ID (partial match, e.g., SEA):", "Search by ID", JOptionPane.QUESTION_MESSAGE);
                if (id == null) return;
                Weapon[] idResult = weaponSearch.searchByIdPartial(id);
                showSearchResult(idResult);
                break;
            case 2:
                String type = JOptionPane.showInputDialog(null, "Enter Weapon Type (Sea/Land/Air):", "Search by Type", JOptionPane.QUESTION_MESSAGE);
                if (type == null) return;
                Weapon[] typeResult = weaponSearch.filterByType(type);
                showSearchResult(typeResult);
                break;
            case 3:
                String sortChoice = JOptionPane.showInputDialog(null, "Sort by Power:\n1) Ascending\n2) Descending\nEnter 1/2:", "Sort Weapons", JOptionPane.QUESTION_MESSAGE);
                if (sortChoice == null) return;
                int sort = Integer.parseInt(sortChoice);
                Weapon[] sortedWeapons = weaponSearch.sortByPower(sort == 1);
                showSearchResult(sortedWeapons);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Displays search results in console + popup notification
    private void showSearchResult(Weapon[] result) {
        if (result == null || result.length == 0) {
            JOptionPane.showMessageDialog(null, "No weapons found!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        System.out.println("------------------------ Search Result ------------------------");
        for (Weapon weapon : result) {
            if (weapon != null) System.out.println(weapon);
        }
        JOptionPane.showMessageDialog(null, "Search result listed in console!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
    }
}
