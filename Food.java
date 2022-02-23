import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JLabel;

public class Food extends JLabel {
    
     
    int mGenislik=20;
	Food()
	{
	 setBounds(20,20,mGenislik,mGenislik);   		
	}
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		
		Graphics2D g2=(Graphics2D) g;
		Ellipse2D elipse= new Ellipse2D.Double(1,1,mGenislik-2,mGenislik-2);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.draw(elipse);
	}
	
	public void SetPosition(int PosX,int PosY)
	{
		setBounds(PosX,PosY,mGenislik,mGenislik);
	}
}
