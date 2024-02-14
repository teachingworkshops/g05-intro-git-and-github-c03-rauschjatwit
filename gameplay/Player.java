package gameplay;

import java.util.ArrayList;

import gameplay.weapon.Weapon;

public class Player {
    public boolean alive;
    protected int hunger;
    protected int thirst;
    public ArrayList<Weapon> backpack = new ArrayList<Weapon>();

    public Player(){
        alive=true;
        hunger=25;
        thirst=25;
    }

    public void addItemToBackpack(Weapon newWeapon){
        backpack.add(newWeapon);
    }

    public void remItemFromBackpack(int index){
        backpack.remove(index);
    }

    public void updatePlayer(){
        hunger--;
        thirst--;
        // Remove items from backpack with no more uses available
        for(int i=0; i<backpack.size(); i++){
            if(backpack.get(i).uses <=0){
                backpack.remove(i);
            }
        }

        if(hunger <= 0 || thirst <= 0){
            alive = false;
        }
    }
    public void displayBackpack() {
        System.out.println("Items in your backpack:");
        for (int i = 0; i < backpack.size(); i++) {
           // System.out.println((i + 1) + ". " + backpack.get(i).getClass().getSimpleName());
            System.out.println((i + 1) + ". " + backpack.get(i).toString());
        }
    }
    public boolean isBackpackFull() {
        return backpack.size() >= 3; 
    }

    public boolean containsSame(Weapon weapon){
        for(int i=0; i<backpack.size(); i++){
            if(!(weapon.toString().equals(backpack.get(i).toString()))){
                return false;
            }
        }
        return true;
    }
}
