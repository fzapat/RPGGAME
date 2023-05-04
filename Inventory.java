import java.util.ArrayList;

public class Inventory{
    String weapon;
    String supportItem;
    String potion;

    public Inventory(String weapon, String supportItem, String potion){
        this.weapon = weapon;
        this.supportItem = supportItem;
        this.potion = potion;
    }

    public void setWeapon(String weapon){
        this.weapon = weapon;
    }

    public String getWeapon(){
        return this.weapon;
    }

    public void setSupportItem(String supportItem){
        this.supportItem = supportItem;
    }

    public String getSupportItem(){
        return this.supportItem;
    }

    public void setPotion(String potion){
        this.potion = potion;
    }

    public String getPotion(){
        return this.potion;
    }

    public String toString(){
        String stringInv;

        stringInv = "Inventory:\n";
        stringInv += "Weapon: " + getWeapon()+"\n";
        stringInv += "Support Item: " + getSupportItem() +"\n";
        stringInv += "Potion: " + getPotion() +"\n";
        
        return stringInv;

    }

}
