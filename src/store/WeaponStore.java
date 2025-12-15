package store;
import model.Weapon;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

//A private member variable that uses Arraylist to
//implement the list interface for storing weapon inventory data
public class WeaponStore {
    private static final int MAX_SIZE=100;
    private Weapon[]weaponArray=new Weapon[MAX_SIZE];
    private int currentCount=0;


//Validates weapon attributes + checks for duplicate ID
    private boolean isWeaponValid(Weapon weapon){
        if(weapon.getId().isEmpty()||weapon.getType().isEmpty()||weapon.getName().isEmpty()){
            return false;
        }

        if (weapon.getPower()<1||weapon.getPower()>100){
            return false;
        }

        for(int i=0;i<currentCount;i++){
            if (weaponArray[i].getId().equals(weapon.getId())){
                System.out.println("Error:WeaponId"+weapon.getId()+"already exists");
                return false;
              }
            }
        return true;
        }


    public void addWeapon(Weapon weapon) {

        //Check if storage has reached maximum capacity
       if (currentCount >= MAX_SIZE){
           System.out.println("Error:Weapon store is full(max 100 weapons)");
           return;
       }

       //Verify weapon attribute validity;if invalid,prompt error and return
       if (!isWeaponValid(weapon)){
           System.out.println("Failed to add weapon:invalid attributes!");
           return;
       }

        //Store the weapon in the array and increment the actual count by 1
       weaponArray[currentCount]=weapon;
       currentCount++;
       System.out.println("Success:Weapon added!Total weapons:"+currentCount);
    }


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

        //If the target weapon is found,execute deletion logic
        if (isDeleted) {
            for (int i = deleteIndex; i < currentCount - 1; i++) {
                weaponArray[i] = weaponArray[i + 1];
            }
            weaponArray[currentCount - 1] = null;
            currentCount--;
            System.out.println("Success:Weapon" + id + "deleted");
        }

        //Target weapon not found,prompt error
        else {
            System.out.println("Error:Weapon Id" + id + "not found");
        }
    }


    //Display information of all weapons in the store
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
    }


    //Saves weapons to TXT
    //Uses try-with-resources to auto-close IO streams
    public void saveToFile() {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter("weapons.txt"))) {
          for (int i=0;i<currentCount;i++){
              Weapon w = weaponArray[i];
              bw.write(w.getId()+","+w.getType()+","+w.getName()+","+w.getPower()+","+w.getUsage());
              bw.newLine();
          }
          System.out.println("Weapons saved to weapons.txt");
      }

      catch (IOException e){
          System.out.println("Save failed: "+e.getMessage());
      }
    }


    //Loads weapons from TXT
    public void loadFromFile() {
        File file = new File("weapons.txt");
        if (!file.exists()){
            System.out.println("No save file found.start with empty storage");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine())!=null){
                String[] parts = line.split(",");
                if(parts.length!=5) continue;
                Weapon w = new Weapon(parts[0],parts[1],parts[2],Integer.parseInt(parts[3]),parts[4]);
                   addWeapon(w);
            }
            System.out.println("Weapons loaded from weapons.txt");
        }

        catch (IOException e){
            System.out.println("Load failed: "+e.getMessage());
        }
    }


    public Weapon[] getWeaponArray(){
        return weaponArray;
    }

    public int getCurrentCount(){
        return currentCount;
    }









}
