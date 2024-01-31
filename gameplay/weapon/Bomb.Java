package gameplay.weapon;


public class Bomb extends Weapon{
    public Bomb(){
        uses = 1;
        chanceOfKill = 1;
        chanceOfAppear = 0.1;
    }

    public void use(){
        uses--;
        usable = false;
    }
}
