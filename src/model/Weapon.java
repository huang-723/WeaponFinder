package model;

//Data model class for weapon equipment, used to store core information of weapons.
//This class stores information like ID, type, name, power, and usage of a weapon.
//All validation logic is in Model layer to reduce coupling with Store layer.

public class Weapon {
    private String id = "";
    private String type = "";
    private String name = "";
    private int power = 1;
    private String usage = "";

    //Default constructor
    public Weapon(){}

    public Weapon(String id, String type, String name, int power, String usage) {
        setId(id);
        setType(type);
        setName(name);
        setPower(power);
        setUsage(usage);
    }

    //Set weapon ID with validation
    public void setId(String id){
        if (id.isEmpty()){
            System.out.println("Error: Weapon ID cannot be empty!");
            return;
        }
        if(id.length()<3){
            System.out.println("Error: Weapon ID must be at least 3 characters!");
            return;
        }
        this.id = id;
    }

    //Set weapon type with validation
    public void setType(String type){
        if(!type.equals("Sea")&&!type.equals("Air")&&!type.equals("Land")){
            System.out.println("Error: Weapon type must be Sea/Land/Air!");
            return;
        }
        this.type = type;
    }

    //Set weapon name with validation: not empty.
    public void setName(String name){
        if(!name.isEmpty()){
            System.out.println("Error: Weapon name cannot be empty!");
            return;
        }
        this.name = name;
    }

    //Set weapon power with validation
    public void setPower(int power){
        if(power < 1 || power > 100){
            System.out.println("Error: Power must be between 1 and 100!");
            return;
        }
        this.power = power;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public String getUsage() {
        return usage;
    }

    //Return a formatted string that displays all details of the weapon.
    //Now it's more easier to read!
    public String toString() {
        return "====== Weapon Details ======\n" +
                "id: " + id + "\n" +
                "type: " + type + "\n" +
                "name: " + name + "\n" +
                "power: " + power + "\n" +
                "usage: " + usage + "\n" +
                "============================";
    }
}