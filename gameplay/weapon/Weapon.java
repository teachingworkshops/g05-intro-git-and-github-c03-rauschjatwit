package gameplay.weapon;

public abstract class Weapon {
    public int uses;
    public double chanceOfKill;
    public double chanceOfAppear;

    public Weapon (){
    }

    
    public boolean use(){
        uses--;
        return false;
    }

    public String toString(){
        return "";
    }

}
