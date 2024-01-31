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

    public ArrayList<Weapon> addItemToBackpack(Weapon newWeapon){
        backpack.add(newWeapon);
        return backpack;
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
}
