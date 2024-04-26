import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

import javax.swing.*;

public class ButtonPanel extends JPanel{

    private JToggleButton modeButton;
    private JToggleButton difficultyButton;
    private JButton startButton;

    public ButtonPanel(){
        super();

        this.modeButton = new JToggleButton("BASIC Mode");
        ItemListener modeListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent){
                int state = itemEvent.getStateChange();

                if (state == ItemEvent.SELECTED) {
                    modeButton.setText("SLU Mode");
                }
                else {
                    modeButton.setText("BASIC Mode");
                }
            }
        };

        this.difficultyButton = new JToggleButton("NORMAL");
        ItemListener difficultyListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent){
                int state = itemEvent.getStateChange();

                if (state == ItemEvent.SELECTED) {
                    difficultyButton.setText("HARD");
                }
                else {
                    difficultyButton.setText("NORMAL");
                }
            }
        };

        this.startButton = new JButton("Go!");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               System.out.println("Start Button Pressed");
               disableButtons();
            }
         });


        modeButton.addItemListener(modeListener);
        difficultyButton.addItemListener(difficultyListener);
        this.add(modeButton);
        this.add(difficultyButton);
        this.add(startButton);
    }

    public String getMode(){
        if(modeButton.isSelected()){
            return "SLU";
        } else {
            return "BASIC";
        }
    }

    public String getDifficulty(){
        if(difficultyButton.isSelected()){
            return "HARD";
        } else {
            return "NORMAL";
        }
    }
    
    public void disableButtons(){
        modeButton.setEnabled(false);
        difficultyButton.setEnabled(false);
        startButton.setEnabled(false);
    }

    public void enableButtons(){
        modeButton.setEnabled(true);
        difficultyButton.setEnabled(true);
        startButton.setEnabled(true);
    }
}
