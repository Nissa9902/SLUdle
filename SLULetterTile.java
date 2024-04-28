import java.awt.Color;

public class SLULetterTile extends LetterTile{
    public SLULetterTile(char letter, String status){
        super(letter, status);
    }

    @Override
    public void setStatus(String status){
        this.status = status;
        if(status.equals("incorrect")){
            this.iconColor = new Color(120, 124, 127); //dark gray
            this.textColor = Color.WHITE;

        } else if (status.equals("correct")){
            this.iconColor = new Color(0, 61, 165); //SLU Blue
            this.textColor = Color.WHITE;

        } else if(status.equals("contains")){
            this.iconColor = new Color(237, 139, 0); //Oriflamme Orange
            this.textColor = Color.WHITE;

        } else if(status.equals("empty")){

            this.iconColor = Color.WHITE;
            this.textColor = Color.WHITE;

        } else if(status.equals("unchecked")){
            this.iconColor = Color.WHITE;
            this.textColor = new Color(120, 124, 127);;
        }
    }

}