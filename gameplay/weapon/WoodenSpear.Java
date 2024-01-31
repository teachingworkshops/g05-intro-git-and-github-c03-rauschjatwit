package gameplay.weapon;


public class WoodenSpear extends Weapon{
    public WoodenSpear(){
        uses = 3;
        chanceOfKill = 0.5;
        chanceOfAppear = 0.25;
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