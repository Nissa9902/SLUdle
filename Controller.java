import java.util.ArrayList;

public class Controller{
    private ArrayList<Observer> observersArr; //BoardPanel, Keyboard
    private SecretWord secretWord;
    private boolean isHard;

    public Controller(SecretWord secretWord, boolean isHard){
        this.observersArr = new ArrayList<Observer>();
        this.secretWord = secretWord;
        this.isHard = isHard;
    }

    public void register(Observer o){
        this.observersArr.add(o);
    }

    public void unregister(Observer o){
        for (int i = 0; i < observersArr.size(); i++){
            if(observersArr.get(i).equals(o)){
               observersArr.remove(i);
            }
         }
    }

    public void notifyObservers(LetterTile guess[], boolean isHard){
        for(Observer o: observersArr){
            o.update(guess, isHard);
        }
    }
}