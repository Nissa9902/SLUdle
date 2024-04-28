import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class ConfigFrame extends JFrame{

    private JToggleButton modeButton;
    private JToggleButton difficultyButton;
    private JButton startButton;
    
    public ConfigFrame(String title, ActionListener startListener){
        super(title);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 500));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout(3, 1, 4, 4));

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
        startButton.addActionListener(startListener);


        modeButton.addItemListener(modeListener);
        difficultyButton.addItemListener(difficultyListener);
        panel.add(modeButton);
        panel.add(difficultyButton);
        panel.add(startButton);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
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
