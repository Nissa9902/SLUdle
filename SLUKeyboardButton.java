import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SLUKeyboardButton extends KeyboardButton {
    
    public SLUKeyboardButton(String text){
        super(text);
    }

    @Override
    public void setStatus(String status){
        super.status = status;
        if(status.equals("incorrect")){
            this.setBackground(new Color(120, 124, 127)); //dark gray
            this.setForeground(Color.WHITE);

        } else if (status.equals("correct")){
            this.setBackground(new Color(0, 61, 165)); //SLU Blue
            this.setForeground(Color.WHITE);

        } else if(status.equals("contains")){
            this.setBackground(new Color(237, 139, 0)); //Oriflamme Orange
            this.setForeground(Color.WHITE);

        } else if(status.equals("unchecked")){
            this.setBackground(new Color(204, 204, 204));
            this.setForeground(new Color(64, 64, 64));
        }
    }
}
