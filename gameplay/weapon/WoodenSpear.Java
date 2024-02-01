package gameplay.weapon;


public class WoodenSpear extends Weapon{
    public WoodenSpear(){
        uses = 3;
        chanceOfKill = 0.5;
        chanceOfAppear = 0.75;
    }

    @Override
    public boolean use(){
        uses--;
        double kill = Math.random();
        if(kill <= chanceOfKill){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "wooden spear";
    }
}