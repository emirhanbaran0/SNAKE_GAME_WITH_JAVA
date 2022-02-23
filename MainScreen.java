import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainScreen extends JFrame {

	private static MainScreen frame = null;

	private MainScreen() {
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		JLabel snake = new Snake();
		add(snake);

		getContentPane().setBackground(Color.GREEN);
		setVisible(true);
	}

	public static MainScreen singleScreen() {
		if (frame == null) {
			frame = new MainScreen();
		}
		return frame;

	}

}
