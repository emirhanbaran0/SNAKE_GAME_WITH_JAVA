import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;

public class makeSnake extends JLabel {

	public int mGenislik = 10;

	makeSnake() {
		setBounds(200, 100, 10, 10);
		setOpaque(true);
		setBackground(Color.BLACK);

	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D rect = new Rectangle2D.Double(0, 0, mGenislik - 2, mGenislik - 2);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.draw(rect);
	}

}
