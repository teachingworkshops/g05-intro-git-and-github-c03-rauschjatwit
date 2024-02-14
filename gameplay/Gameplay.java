package gameplay;

import java.util.*;

import gameplay.weapon.*;

public class Gameplay {
    private int enemiesFought;
    private int battlesWon;
    private int chestsOpened;

    private int currMoves;
    protected int moves;
    public int points;
    private boolean daytime;
    protected int maxPoints;

    protected Player player;
    private Scanner stdin;
    private ProgressBar progress;
    private Random random;
    

    public Gameplay(Player player, int maxPoints, Scanner stdin){
        chestsOpened = 0;
        battlesWon = 0;
        enemiesFought = 0;

        currMoves = 0;
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
        if(points < 0){
            points = 0;
        }
        if(daytime){
           if(currMoves == 15){
             daytime = false;
             currMoves = 0;
           }
        }else{
            if(currMoves == 5){
                daytime = true;
                currMoves = 0;

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
            points++;
            daytimeEvents();
        }else{
            startNight();
        }
        moves++;
        currMoves++;
        player.updatePlayer();
    }

    private void startNight(){
        if(moves == 15){
            System.out.println("\n On your journey, you will have the option to travel though the night or wait till morning to continue on.\n\n Waiting till morning will make your journey longer.\n If you chose to keep going through the night, you will likely encounter monsters in your path!\n You must fight them with whatever you have in your backpack.\n\n  Falling in battle won't break your stride, but triumphs will swiftly shorten the ride...\n");
        }
        if(currMoves==0){
            System.out.print("\nWould you like to brave the night?\n\t> Keep going (y)\n\t> Sleep till morning (n)\n> ");
            String response = stdin.next();
            if(response.toLowerCase().equals("y")){
                System.out.println("Carry on!");                
            }else if(response.toLowerCase().equals("n")){
                daytime = true;
                currMoves = 0;
                System.out.println("You sleep till morning...");
            }
            else{
                System.out.println("Invalid input");
                startNight();
            }
        }
        if(!daytime){
            nightEvents();
        }
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
            chestsOpened++;
            System.out.println("You've stumbled upon a mysterious chest!");
            Weapon foundItem = getRandomItem();
            if (player.isBackpackFull() && player.containsSame(foundItem)) {
                System.out.println("Your backpack is full. You can't pick up any more items.");
            }
            else if( player.isBackpackFull()){
                // backpack is full but contains different items
                chooseBackpackItem(foundItem);
            }
            else {
                player.addItemToBackpack(foundItem); 
             //   System.out.println("You found a " + foundItem.getClass().getSimpleName() + " in the chest!");
                System.out.println("You found a " + foundItem.toString() + " in the chest!");
    }
        } else {
            // Remaining 30%
            System.out.println("You find nothing of interest.");
        }
    }

    private void nightEvents() {
        points += 2;
            if (random.nextInt(100) < 70) {
                enemiesFought++;
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
            battlesWon++;
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

    public void endOfGameStats(){
        System.out.print("\nGame Stats:");
        System.out.printf("\n\tProgres Points (MAX %d):.%d",maxPoints,points);
        System.out.printf("\n\tDays Traveled:............%.2f",moves/20.0);
        System.out.printf("\n\tEnemies Fought............%d",enemiesFought);
        System.out.printf("\n\tBattles Won...............%d",battlesWon);
        System.out.printf("\n\tChests opened.............%d\n",chestsOpened);

    }
    
    private String timeOfDay(){
        if(daytime){
            return "Daytime";
        }else{
            return "Nighttime";
        }
    }

    private void chooseBackpackItem(Weapon foundItem) {
        System.out.println("You found a " + foundItem.toString() + " in the chest!");
        System.out.println("However, your backpack is full.");

        player.displayBackpack();

        System.out.println("Enter the corresponding item number to swap the newly found item for an item in your backpack.");
        System.out.println("Enter 0 to keep all items in your backpack.");
        
        int itemNumber = stdin.nextInt();

        boolean input_is_valid = false;
        
        while(input_is_valid == false) {  
            
            if(itemNumber == 0){
                System.out.println("You keep all your items\n");
                input_is_valid = true;
                }
            else if(itemNumber == 1){
                System.out.println("You switch out item 1\n");
                player.remItemFromBackpack(0);
                player.addItemToBackpack(foundItem);
                input_is_valid = true;
            }
            else if(itemNumber == 2){
                System.out.println("You switch out item 2\n");
                player.remItemFromBackpack(1);
                player.addItemToBackpack(foundItem);
                input_is_valid = true;
            }
            else if(itemNumber == 3){
                System.out.println("You switch out item 3\n");
                player.remItemFromBackpack(2);
                player.addItemToBackpack(foundItem);
                input_is_valid = true;
            }
            else{
                System.out.println("Invalid selection. Please choose a valid inventory number.");
            }
         }
    }
}
