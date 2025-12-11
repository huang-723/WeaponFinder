package search;
import store.WeaponStore;
import model.Weapon;

public class WeaponSearch {
    //Dependent on WeaponStore to get weapon data (array + current count)
    private WeaponStore weaponStore;

    public WeaponSearch(WeaponStore weaponStore) {
        this.weaponStore = weaponStore;
    }

    public Weapon[] searchByIdPartial(String partialld) {
        //Get the list of all weapons from WeaponStore
        Weapon[] allWeapons = weaponStore.getWeaponArray();
        int count = weaponStore.getCurrentCount();
        //Temporary array to store matched weapons
        Weapon[] tempResult = new Weapon[count];
        int resultIndex = 0;

        //Traverse all valid weapons
        for (int i = 0; i < count; i++) {
            if (allWeapons[i].getId().contains(partialld)) {
                tempResult[resultIndex] = allWeapons[i];
                resultIndex++;
            }
        }

        Weapon[] finalResult = new Weapon[resultIndex];
        for (int i = 0; i < resultIndex; i++) {
            finalResult[i] = tempResult[i];
        }
        return finalResult;
    }

    public Weapon[] filterByType(String type) {
        Weapon[] allWeapons = weaponStore.getWeaponArray();
        int count = weaponStore.getCurrentCount();
        Weapon[] tempResult = new Weapon[count];
        int resultIndex = 0;

        for (int i = 0; i < count; i++) {
            if (allWeapons[i].getType().equals(type)) {
                tempResult[resultIndex] = allWeapons[i];
                resultIndex++;
            }
        }

        Weapon[] finalResult = new Weapon[resultIndex];
        for (int i = 0; i < resultIndex; i++) {
            finalResult[i] = tempResult[i];
        }
        return finalResult;
    }

    //V1.1 New Feature: Sort weapons by power (bubble sort)
    public Weapon[]
    sortByPower(boolean isAscending) {
        Weapon[] allWeapons = weaponStore.getWeaponArray();
        int count = weaponStore.getCurrentCount();
        Weapon[] sortedWeapons = new Weapon[count];
        for (int i = 0; i < count; i++) {
            sortedWeapons[i] = allWeapons[i];
        }

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                boolean needSwap = false;
                if (isAscending) {
                    needSwap = sortedWeapons[j].getPower() < sortedWeapons[j + 1].getPower();
                }
                if (!needSwap) {
                    Weapon temp = sortedWeapons[j];
                    sortedWeapons[j] = sortedWeapons[j + 1];
                    sortedWeapons[j + 1] = temp;
                }
            }
        }
        return sortedWeapons;
    }
}

