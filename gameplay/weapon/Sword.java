package gameplay.weapon;


public class Sword extends Weapon{
    public Sword(){
        uses = 1000000000;
        chanceOfKill = 0.75;
        chanceOfAppear = 0.25;
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
        return "sword";
    }

}
