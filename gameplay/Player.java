package gameplay;

import java.util.ArrayList;
import java.util.Scanner;

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

    public void  addItemToBackpack(Weapon newWeapon){
        backpack.add(newWeapon);
        
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

    public int chooseBackpackItem(Weapon foundItem) {
        System.out.println("You found a " + foundItem.toString() + " in the chest!");
        System.out.println("However, your backpack is full.");

        displayBackpack();

        Scanner action = new Scanner(System.in);
        System.out.println("Enter the corresponding item number to swap the newly found item for an item in your backpack.");
        System.out.println("Enter 0 to keep all items in your backpack.");
        
        int itemNumber = action.nextInt();

        action.close();

        return itemNumber;
    }
}
