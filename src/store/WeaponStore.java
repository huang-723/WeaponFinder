package store;
import model.Weapon;
import java.util.ArrayList;
import java.util.List;

public class WeaponStore {
    private static final int MAX_SIZE=100;
    private Weapon[]weaponArray=new Weapon[MAX_SIZE];
    private int currentCount=0;
    //A private member variable that uses Arraylist to
    //implement the list interface for storing weapon inventory data

    private boolean isWeaponValid(Weapon weapon){
        if(weapon.getId().isEmpty()||weapon.getType().isEmpty()||weapon.getName().isEmpty()){
            return false;
        } //Check if Id,type,or name is an empty string

        if (weapon.getPower()<1||weapon.getPower()>100){
            return false;
        } //Check if power is within the valid range [1,100]

        for(int i=0;i<currentCount;i++){
            if (weaponArray[i].getId().equals(weapon.getId())){
                System.out.println("Error:WeaponId"+weapon.getId()+"already exists");
                return false;
              }
            }
        return true;  //all validation rules passed,weapon is valid
        }


    public void addWeapon(Weapon weapon) {
       if (currentCount >= MAX_SIZE){
           System.out.println("Error:Weapon store is full(max 100 weapons)");
           return;
       } //Check if storage has reached maximum capacity

       if (!isWeaponValid(weapon)){
           System.out.println("Failed to add weapon:invalid attributes!");
           return;
       } //Verify weapon attribute validity;if invalid,prompt error and return

       weaponArray[currentCount]=weapon;
       currentCount++;
       System.out.println("Success:Weapon added!Total weapons:"+currentCount);
    } //Store the weapon in the array and increment the actual count by 1


    public void deleteWeapon(String id) {
        boolean isDeleted = false;
        int deleteIndex = -1;

        for (int i = 0; i < currentCount; i++) {
            if (weaponArray[i].getId().equals(id)) {
                deleteIndex = i;
                isDeleted = true;
                break;
            }
        }

        if (isDeleted) {
            for (int i = deleteIndex; i < currentCount - 1; i++) {
                weaponArray[i] = weaponArray[i + 1];
            }
            weaponArray[currentCount - 1] = null;
            currentCount--;
            System.out.println("Success:Weapon" + id + "deleted");
        }  //If the target weapon is found,execute deletion logic
           else {
            System.out.println("Error:Weapon Id" + id + "not found");
        } //Target weapon not found,prompt error
    }

    public void listWeapons() {
        if (currentCount==0){
            System.out.println("No weapons in storage");
            return;
      }
        System.out.println("------------------------ All Weapons ------------------------");
        for (int i=0;i<currentCount;i++){
            System.out.println("Weapon"+(i+1)+":");
            System.out.println(weaponArray[i]);
        }
    } //Display information of all weapons in the store


    public Weapon[] getWeaponArray(){
       return weaponArray;
    }


    public int getCurrentCount(){
       return currentCount;
    }
}