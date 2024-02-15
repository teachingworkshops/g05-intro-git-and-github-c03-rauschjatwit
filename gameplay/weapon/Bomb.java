package gameplay.weapon;


public class Bomb extends Weapon{
    public Bomb(){
        uses = 1;
        chanceOfKill = 1;
        chanceOfAppear = 0.1;
    }

    @Override
    public boolean use(){
        uses--;
        return true;
    }

    @Override
    public String toString(){
        return "bomb";
    }

}
