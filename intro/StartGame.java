package intro;
import java.util.Scanner;

import gameplay.Gameplay;
import gameplay.Player;

public class StartGame {
    public static String location1;
    public static String location2;
    public static String trait;
    public static String character;
    protected static String asciiArt =
            "____    __    ____  _______  __        ______   ______   .___  ___.  _______                \n" +
            "\\   \\  /  \\  /   / |   ____||  |      /      | /  __  \\  |   \\/   | |   ____|               \n" +
            " \\   \\/    \\/   /  |  |__   |  |     |  ,----'|  |  |  | |  \\  /  | |  |__                  \n" +
            "  \\            /   |   __|  |  |     |  |     |  |  |  | |  |\\/|  | |   __|                 \n" +
            "   \\    /\\    /    |  |____ |  `----.|  `----.|  `--'  | |  |  |  | |  |____                \n" +
            "    \\__/  \\__/     |_______||_______| \\______| \\______/  |__|  |__| |_______|               \n" +
            ".___________. ______                                                                         \n" +
            "|           |/  __  \\                                                                        \n" +
            "`---|  |----|  |  |  |                                                                       \n" +
            "    |  |    |  |  |  |                                                                       \n" +
            "    |  |    |  `--'  |                                                                       \n" +
            "    |__|     \\______/                                                                        \n" +
            " __    __  .__   __.   ______  _______ .______     .___________.    ___       __  .__   __. \n" +
            "|  |  |  | |  \\ |  |  /      ||   ____||   _  \\    |           |   /   \\     |  | |  \\ |  | \n" +
            "|  |  |  | |   \\|  | |  ,----'|  |__   |  |_)  |   `---|  |----`  /  ^  \\    |  | |   \\|  | \n" +
            "|  |  |  | |  . `  | |  |     |   __|  |      /        |  |      /  /_\\  \\   |  | |  . `  | \n" +
            "|  `--'  | |  |\\   | |  `----.|  |____ |  |\\  \\----.   |  |     /  _____  \\  |  | |  |\\   | \n" +
            " \\______/  |__| \\__|  \\______||_______|| _| `._____|   |__|    /__/     \\__\\ |__| |__| \\__| \n" +
            ".___________.______          ___       __   __          _______.                           \n" +
            "|           |   _  \\        /   \\     |  | |  |        /       |                           \n" +
            "`---|  |----|  |_)  |      /  ^  \\    |  | |  |       |   (----`                           \n" +
            "    |  |    |      /      /  /_\\  \\   |  | |  |        \\   \\                               \n" +
            "    |  |    |  |\\  \\----./  _____  \\  |  | |  `----.----)   |                              \n" +
            "    |__|    | _| `._____/__/     \\__\\ |__| |_______|_______/                               \n";

    	public static void main(String[] args) {
            Scanner stdin = new Scanner(System.in);

            System.out.print(asciiArt);
            gameIntro(stdin);
            

            stdin.close();
        }

        public static void gameIntro(Scanner stdin){
            GenerateStory.randomize();
            System.out.printf("\nYou are trapped in the %s! \nYou would do anything to escape and journey to the one place you've always dreamed of, the %s...\nAs a %s %s, you bring with you a <Starting Item>\n", location1, location2, trait, character);
            System.out.print("\nAre you ready to begin your journey?\n\t> Yes (y)\n\t> Regenerate story (r)\n\t> Quit (q)\n> ");
            String startYN = stdin.next();
            
            if(startYN.toLowerCase().equals("y")){
                startGame(stdin);
            }else if(startYN.toLowerCase().equals("r")){
                gameIntro(stdin);
            }else if(startYN.toLowerCase().equals("q")){
                System.out.println("Bye!");
                System.exit(0);
            }
            else{
                System.out.println("Invalid input");
                gameIntro(stdin);
            }
        }

        public static void startGame(Scanner stdin){
            int gameLength = 100;
            Player player = new Player();
            Gameplay game = new Gameplay(player, gameLength, stdin);

            while(game.points < gameLength){
                if(!player.alive){
                    System.out.println("\nYou died... Better luck next time!");

                    break;
                }
                game.move();
            }
            if(game.points >= gameLength){
                System.out.printf("\nCongratulations! You have finally reached the %s!", location2);

            }else if(game.points <= 0){
                System.out.printf("\nEek! All those lost battles really set you back...\nBetter luck next time!");

            }
            game.endOfGameStats();
        }
}