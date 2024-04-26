import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

public class SLUdleFrame extends JFrame {
    private BoardPanel boardPanel;
    
    public SLUdleFrame(String title, int wordLength, Controller c, String mode, String difficulty){
        super(title);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 500));
        panel.setBackground(Color.WHITE);

        this.boardPanel = new BoardPanel(wordLength, mode);
        boardPanel.setPreferredSize(new Dimension(60 * wordLength, 60 * (wordLength + 1)));
        boardPanel.setBackground(Color.WHITE);
        c.register(boardPanel);

        //TODO: add keyboard 
        panel.add(boardPanel);
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
    }

}
