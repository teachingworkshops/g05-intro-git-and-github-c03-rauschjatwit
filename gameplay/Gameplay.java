package gameplay;

import java.util.*;

import gameplay.weapon.Weapon;

public class Gameplay {
    protected int moves;
    public int points;
    private boolean daytime;
    protected Player player;
    private Scanner stdin;
    private ProgressBar progress;
    protected int maxPoints;
    private Random random;

    public Gameplay(Player player, int maxPoints, Scanner stdin){
        moves = 0;
        points = 0;
        daytime = true;
        this.player = player;
        this.stdin = stdin;
        progress = new ProgressBar(this);
        this.maxPoints = maxPoints;
    }

    public void move(){
        if(daytime){
            if(moves % 15 == 0 && moves != 0){
                daytime = false;
            }  
        }else{
            if((moves-5) % 15 == 0 && moves != 0){
                daytime = true;
            }
        }
        System.out.printf("\nIt is %s", timeOfDay());
        System.out.printf("\nHunger: %d\nThirst: %d\n", player.hunger, player.thirst);
        System.out.println(progress.printProgressBar());
        
        if(daytime){
            daytimeMove();
        }else{
            nighttimeMove();
        }
        moves++;
        player.updatePlayer();
    }
    
    private void daytimeMove(){
        points++;
    }

    private void nighttimeMove(){
        if(moves%15==0){
            if(moves == 15){
                System.out.println("\n On your journey, you will have the option to travel though the night or wait till morning to continue on.\n\n Waiting till morning will make your journey longer.\n If you chose to keep going through the night, you will likely encounter monsters in your path!\n You must fight them with whatever you have in your backpack.\n\n  Falling in battle won't break your stride, but triumphs will swiftly shorten the ride...\n");
            }
            System.out.print("\nWould you like to brave the night?\n\t> Keep going (y)\n\t> Sleep till morning (n)\n> ");
            String response = stdin.next();
            if(response.toLowerCase().equals("y")){
                System.out.println("Carry on!");
                nightEvents();
            }else if(response.toLowerCase().equals("n")){
                daytime = true;
                System.out.println("You sleep till morning...");

            }
            else{
                System.out.println("Invalid input");
                nighttimeMove();
            }
        } 
        points += 2;
    }

    private void nightEvents() {

        //for (int i = 0; i < 5; i++) {
            if (random.nextInt(100) < 70) {
                System.out.println("You encounter an enemy!");
                handleEnemyEncounter();
            } 
   // }
}

private void handleEnemyEncounter() {
    if (player.backpack.isEmpty()) {
        System.out.println("Your backpack is empty! You have no weapons to fight.");
        // To Do
        return;
    }
    player.displayBackpack();

    System.out.println("Please choose a weapon from your backpack (1, 2 or 3)");

    int input = stdin.nextInt();

  if (input >= 1 && input <= player.backpack.size()) {
        Weapon selectedWeapon = player.backpack.get(input - 1); 
        boolean isEnemyDefeated = selectedWeapon.use();

        if (isEnemyDefeated) {
            System.out.println("You defeated the enemy!");
            points+=15;
        } else {
            System.out.println("You failed to defeat the enemy.");
            points-=10;
        }
    } else {
        System.out.println("Invalid selection. Please choose a valid weapon number.");
    }
   
}
    
    private String timeOfDay(){
        if(daytime){
            return "Daytime";
        }else{
            return "Nighttime";
        }
    }


}
