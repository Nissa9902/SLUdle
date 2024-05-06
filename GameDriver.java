import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameDriver {
    private Game currentGame;
    private ActionListener resetListener;
    private Calculator scoreCalculator;

    public GameDriver(){
        this.resetListener = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("reseting game");
                currentGame.close();
                currentGame = new Game(this, scoreCalculator);
            }
        };
        
        scoreCalculator = new Calculator();
        currentGame = new Game(resetListener, scoreCalculator);
    }

    public static void main(String[] args){
        GameDriver g = new GameDriver();
    }
}
