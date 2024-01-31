package gameplay.weapon;


public class Sword extends Weapon{
    public Sword(){
        uses = 1000000000;
        chanceOfKill = 0.75;
        chanceOfAppear = 0.15;
    }

    public void use(){
        uses--;
        usable = true;
    }
}