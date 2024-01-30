package gameplay;

public class ProgressBar {
    private String side = "|";
    private String progress;
    private Gameplay game;
    public ProgressBar(Gameplay game){
        progress = "";
        this.game = game;
    }

    public String printProgressBar(){
        progress = "";
        for(int i=0; i<game.maxPoints; i++){
            if(i <= game.points){
                progress += "=";
            }else{
                progress += " ";
            }
        }
        return side+progress+side;
    }
}
