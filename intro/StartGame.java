package intro;
import java.util.Scanner;

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
            startGame(stdin);
            

            stdin.close();
        }

        public static void startGame(Scanner stdin){
            GenerateStory.randomize();
            System.out.printf("\nYou are trapped in the %s! \nYou would do anything to escape and journey to the one place you've always dreamed of, the %s...\nAs a %s %s, you bring with you a <Starting Item>\n", location1, location2, trait, character);
            System.out.print("\nAre you ready to begin your journey?\n\t> Yes (y)\n\t> Regenerate story (r)\n\t> Quit (q)\n> ");
            String startYN = stdin.next();
            
            if(startYN.toLowerCase().equals("y")){
                // start gameplay
            }else if(startYN.toLowerCase().equals("r")){
                startGame(stdin);
            }else if(startYN.toLowerCase().equals("q")){
                System.out.println("Bye!");
                System.exit(0);
            }
            else{
                System.out.println("Invalid input");
                startGame(stdin);
            }
        }
}