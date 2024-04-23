import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

import javax.swing.Icon;

public class LetterTile implements Icon{
    private char letter;
    private String status;
    private Color iconColor;
    private int width;
    private int thickness;

    public LetterTile(char letter){
        this.letter = letter;
        setStatus("empty");
        this.width = 60;
        this.thickness = 0;
    }

    public LetterTile(char letter, String status){
        this.letter = letter;
        setStatus(status);
        this.width = 60;
        this.thickness = 0;
    }

    public void setStatus(String status){
        this.status = status;
        if(status.equals("incorrect") || status.equals("unchecked")){
            this.iconColor = new Color(120, 124, 127); //dark gray
        } else if (status.equals("correct")){
            this.iconColor = new Color(108, 169, 101); //green
        } else if(status.equals("contains")){
            this.iconColor = new Color(200, 182, 83); //yellow
        } else if(status.equals("empty")){
            this.iconColor = Color.WHITE;
        }
    }

    public void setLetter(char letter){
        this.letter = letter;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        //get graphics context
		Graphics2D g2 = (Graphics2D) g;

		//set stroke size and color
		g2.setPaint(new Color(189, 189, 189));
		Stroke s = new BasicStroke(thickness);
		g2.setStroke(s);

		Rectangle2D r1 = new Rectangle2D.Float(x, y, width, width);
		g2.draw(r1); //draw rectangle

		g2.setPaint(iconColor);		
		g2.fill(r1); //paint background

        //Add text
		g2.setPaint(Color.WHITE);
        Font f = new Font("Clear Sans", Font.BOLD, 40);
        FontMetrics fm   = g.getFontMetrics(f);
		String l = String.valueOf(letter);
        Rectangle2D rect = fm.getStringBounds(l, g2);

        int textHeight = (int)(rect.getHeight()); 
		int textWidth  = (int)(rect.getWidth());
		int panelHeight= getIconHeight();
		int panelWidth = getIconWidth();

		// Center text horizontally and vertically
		int offsetX = (panelWidth  - textWidth)  / 2;
		int offsetY = (panelHeight - textHeight) / 2  + fm.getAscent();

        // Draw the string.
		g2.setFont(f);
		g2.drawString (l, x + offsetX, y + offsetY);
    }

    @Override
    public int getIconWidth() {
        return this.width + 2*thickness;
    }

    @Override
    public int getIconHeight() {
        return this.width + 2*thickness;
    }

}