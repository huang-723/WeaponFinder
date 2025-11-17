package model;

//Data model class for weapon equipment, used to store core information of weapons.
//This class stores information like ID, type, name, power, and usage of a weapon.
public class Weapon {
    private String id;
    private String type;
    private String name;
    private int power;
    private String usage;

    public Weapon(String id, String type, String name, int power, String usage) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.power = power;
        this.usage = usage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    //Return a formatted string that displays all details of the weapon.
    public String toString() {
        return "====== Weapon Details ======\n" +
                "id: " + this.id + "\n" +
                "type: " + this.type + "\n" +
                "name: " + this.name + "\n" +
                "power: " + this.power + "\n" +
                "usage: " + this.usage + "\n" +
                "============================";
    }
}