package gameplay.weapon;


public class Rock extends Weapon{
    public Rock(){
        uses = 1;
        chanceOfKill = 0.2;
        chanceOfAppear = 1;
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
        return "rock";
    }

    @Override
    public boolean equals(Weapon weapon){
        return weapon.getClass() == Rock.class;
    }
}
