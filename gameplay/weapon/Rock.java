package gameplay.weapon;


public class Rock extends Weapon{
    public Rock(){
        uses = 1;
        chanceOfKill = 0.2;
        chanceOfAppear = 0.5;
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
}
