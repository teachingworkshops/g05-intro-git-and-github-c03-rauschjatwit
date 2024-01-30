package gameplay;

import java.util.ArrayList;

import gameplay.weapon.Weapon;
import gameplay.weapon.Rock;

public class Player {
    int hunger;
    int thirst;
    ArrayList<Weapon> backpack = new ArrayList<Weapon>();

    public Player(){
        hunger=0;
        thirst=0;
        backpack.add(new Rock());
    }
}
