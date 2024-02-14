package gameplay;

import java.util.*;

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

    public boolean containsSame(Weapon weapon){
        int numItems=0;
        for(int i=0;i<backpack.size();i++){
            if(weapon.equals(backpack.get(i))){
                numItems++;
            }
        }
        return numItems==3;
    }
    public void chooseBackpackItem(Weapon foundItem) {
        System.out.println("You found a " + foundItem.toString() + " in the chest!");
        System.out.println("However, your backpack is full.");

        displayBackpack();

        Scanner action = new Scanner(System.in);
        System.out.println("Enter the corresponding item number to swap the newly found item for an item in your backpack.");
        System.out.println("Enter 0 to keep all items in your backpack.");
        
        int itemNumber = stdin.nextInt();

        boolean input_is_valid = false;
        
        while(input_is_valid == false) {  
            
            if(itemNumber == 0){
                System.out.println("You keep all your items\n");
                input_is_valid = true;
                }
            if(itemNumber == 1){
                System.out.println("You switch out item 1\n");
                backpack.remove(1);
                backpack.add(foundItem);
                input_is_valid = true;
            }
            else if(itemNumber == 2){
                System.out.println("You switch out item 2\n");
                backpack.remove(2);
                backpack.add(foundItem);
                input_is_valid = true;
            }
            else if(itemNumber == 3){
                System.out.println("You switch out item 3\n");
                backpack.remove(3);
                backpack.add(foundItem);
                input_is_valid = true;
            }
            else{
                System.out.println("Invalid selection. Please choose a valid inventory number.");
            }
         }

        action.close();
        
    }
}
