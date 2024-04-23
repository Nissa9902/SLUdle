import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

public class SLUdleFrame extends JFrame {
    
    public SLUdleFrame(String title, int wordLength){
        super(title);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 500));
        panel.setBackground(Color.WHITE);

        BoardPanel boardPanel = new BoardPanel(wordLength);
        boardPanel.setPreferredSize(new Dimension(60 * wordLength, 60 * (wordLength + 1)));
        boardPanel.setBackground(Color.WHITE);

        panel.add(boardPanel);
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
    }

}
