package gameplay;

import java.util.Scanner;

public class Gameplay {
    protected int moves;
    public int points;
    private boolean daytime;
    protected Player player;
    private Scanner stdin;
    private ProgressBar progress;
    protected int maxPoints;

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

    public void endOfGameStats(){
        System.out.print("\nGame Stats:");
        System.out.printf("\n\tProgres Points (MAX %d):.%d",maxPoints,points);
        System.out.printf("\n\tDays Traveled:............%.2f",moves/20.0);
        System.out.printf("\n\tEnemies Fought............%d",0);
        System.out.printf("\n\tBattles Won...............%d",0);
        System.out.printf("\n\tChests opened.............%d\n",0);

    }
    
    private String timeOfDay(){
        if(daytime){
            return "Daytime";
        }else{
            return "Nighttime";
        }
    }


}
