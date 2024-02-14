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

    public boolean equals(Weapon weapon){
        return weapon.getClass() == Weapon.class;
    }
}
