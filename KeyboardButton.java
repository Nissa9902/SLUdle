import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyboardButton extends JButton{
    protected String status;
    
    public KeyboardButton(String text, String status){
        super(text);
        this.status = status;
    }

    public KeyboardButton(String text){
        this(text, "unchecked");
    }

    public void setStatus(String status){
        this.status = status;
        if(status.equals("incorrect")){
            this.setBackground(new Color(120, 124, 127)); //dark gray
            this.setForeground(Color.WHITE);

        } else if (status.equals("correct")){
            this.setBackground(new Color(108, 169, 101)); //green
            this.setForeground(Color.WHITE);

        } else if(status.equals("contains")){
            this.setBackground(new Color(200, 182, 83)); //yellow
            this.setForeground(Color.WHITE);

        } else if(status.equals("unchecked")){
            this.setBackground(new Color(204, 204, 204));
            this.setForeground(new Color(64, 64, 64));
        }
    }

    public String getStatus(){
        return status;
    }

}
