package search;
import store.WeaponStore;
import model.Weapon;
//Works with WeaponStore's array storage

public class WeaponSearch {
    private WeaponStore weaponStore;

    public WeaponSearch(WeaponStore weaponStore) {
        this.weaponStore = weaponStore;
    }

    //Partial ID match search
    //Returns array with no null elements (clean result)
    public Weapon[] searchByIdPartial(String partialld) {
        Weapon[] allWeapons = weaponStore.getWeaponArray();
        int count = weaponStore.getCurrentCount();
        Weapon[] tempResult = new Weapon[count];
        int resultIndex = 0;

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

    // Exact type match filter (Sea/Land/Air, case-sensitive)
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

    //Creates copy of array to avoid modifying original data
    public Weapon[]
    sortByPower(boolean isAscending) {
        Weapon[] allWeapons = weaponStore.getWeaponArray();
        int count = weaponStore.getCurrentCount();
        Weapon[] sortedWeapons = new Weapon[count];
        for (int i = 0; i < count; i++) {
            sortedWeapons[i] = allWeapons[i];
        }

        // Bubble sort core: Outer loop = sort rounds, inner loop = adjacent compare
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

