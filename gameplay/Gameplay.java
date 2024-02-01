package gameplay;

import java.util.*;

import gameplay.weapon.Weapon;

import gameplay.weapon.*;

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
        this.random = new Random();
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
        try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        if(daytime){
            daytimeMove();

        }else{
            nighttimeMove();
            nightEvents();
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

    
    private void daytimeEvents() {
        int eventChance = random.nextInt(100);

        if (eventChance < 25) {
            // 25% chance of finding food
            System.out.println("You've found some food (animals/berries/etc)!");
            player.hunger += 5;

        } else if (eventChance < 50) {
            // Additional 25% chance (cumulative 50%) of finding water
            System.out.println("You've found a river with fresh water!");
            player.thirst += 5;
        } else if (eventChance < 70) {
            // Additional 20% chance (cumulative 70%) of finding a chest
            System.out.println("You've stumbled upon a mysterious chest!");
            // Method for the chest 
        } else {
            // Remaining 30%
            System.out.println("You find nothing of interest.");
        }
    }

    private void nightEvents() {
        
            if (random.nextInt(100) < 70) {
                System.out.println("You encounter an enemy!");
                handleEnemyEncounter();
            } 
   
}

private void handleEnemyEncounter() {
    if (player.backpack.isEmpty()) {
        System.out.println("Your backpack is empty! You have no weapons to fight.");
        points -= 10;
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
    public Weapon getRandomItem(){
        double randItem = Math.random();
        Bomb bomb = new Bomb();
        Sword sword = new Sword();
        WoodenSpear spear = new WoodenSpear();
        Rock rock = new Rock();

        if(randItem <= bomb.chanceOfAppear){
            return bomb;
        }else if(randItem <= sword.chanceOfAppear){
            return sword;
        }else if(randItem <= spear.chanceOfAppear){
            return spear;
        }else{
            return rock;
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
