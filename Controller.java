import java.util.ArrayList;

public class Controller{
    private ArrayList<Observer> observersArr; //BoardPanel, Keyboard
    private SecretWord secretWord;

    public Controller(SecretWord secretWord){
        this.observersArr = new ArrayList<Observer>();
        this.secretWord = secretWord;
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

    public void notifyObservers(){
        for(Observer o: observersArr){
            o.update();
        }
    }
}