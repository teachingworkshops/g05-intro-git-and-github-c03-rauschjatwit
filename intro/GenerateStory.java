package intro;
import java.util.ArrayList;
import java.util.Arrays;

public class GenerateStory {
    protected static String[] traits = {
        "Shadowy",
        "Tech-savvy",
        "Elemental",
        "Beastly",
        "Cybernetic",
        "Temporal",
        "Psionic",
        "Mystic",
        "Gun-slinging",
        "Alchemical",
        "Charismatic",
        "Adaptable",
        "Stoic",
        "Eccentric",
        "Witty",
        "Analytical",
        "Resilient",
        "Ambitious",
        "Altruistic",
        "Mysterious"
    };

    protected static String[] characterTypes = {
        "Human",
        "Fish",
        "Robot",
        "Android",
        "Cyborg",
        "Mutant",
        "Humanoid",
        "Illusionist",
        "Acrobat",
        "Sniper",
        "Medic",
        "Engineer",
        "Martial Artist",
        "Archaeologist",
        "Nomad",
        "Brawler",
        "Diplomat"
    };
    
    protected static ArrayList<String> locations = new ArrayList<String>(Arrays.asList(
        "Urban Megacity",
        "Enchanted Forest",
        "Subterranean Cavern",
        "Space Colony",
        "Uninhabited Island",
        "Arctic Outpost",
        "Quantum Junction",
        "Psychedelic Wasteland",
        "Floating Archipelago",
        "Bioluminescent Abyss",
        "Clockwork Wonderland",
        "Interdimensional Nexus",
        "Celestial Bazaar",
        "Nebula Outpost",
        "Dreamscape Oasis",
        "Bioelectronic Hive"));
    

    public static void randomize(){
        int randomIndex = (int)(Math.random() * traits.length);
        StartGame.trait = traits[randomIndex];

        randomIndex = (int)(Math.random() * characterTypes.length);
        StartGame.character = characterTypes[randomIndex];

        ArrayList<String> temp = new ArrayList<String>(locations);
        randomIndex = (int)(Math.random() * temp.size());
        StartGame.location1 = temp.get(randomIndex);
        temp.remove(randomIndex);
        randomIndex = (int)(Math.random() * temp.size());
        StartGame.location2 = temp.get(randomIndex);
    }


}