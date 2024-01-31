package gameplay.weapon;

public abstract class Weapon {
    public int uses;
    public double chanceOfKill;
    public double chanceOfAppear;
    public boolean usable;

    public Weapon (){
    }

    public void use(){
        uses--;
        if(uses != 0 && uses > 0){
            usable = true;
        }
        else{
            usable = false;
        }
    }
}
