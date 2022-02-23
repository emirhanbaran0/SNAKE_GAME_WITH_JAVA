import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Snake extends JLabel {

	public Snake head = new Snake();
	Random rand= new Random(System.currentTimeMillis());
	public Food mFood=new Food();
	public int direction = Direction.right;
	public Timer mTimer = null;
	public int mGenislik = 10;
	public ArrayList<Snake> list = new ArrayList<Snake>();
	public TimerTask task = new TimerTask() {

		@Override
		public void run() {
			MovementAll();
		}

	};

	Snake() {

		addKeyListener(new KeyboardControl());
		setFocusable(true);
		mTimer = new Timer();
		mTimer.schedule(task, 0, 100);
		if (Crash()) {
			task.cancel();
		}
		list.add(head);
		for (int i = 1; i < 10; i++) {
			addTail();
		}
		add(mFood);
		add(head);
	}

	public boolean Crash() {
		int kalem=10;
		int width=getWidth();
		int height=getHeight();
		if(head.getX()<kalem && head.getX()+head.mGenislik>width-kalem && head.getY()<kalem && head.getY()+head.mGenislik>height-kalem)
		{
			return true;
		}
		for(int i=1;i<list.size();i++)
		{
			int X=list.get(i).getX();
			int Y=list.get(i).getY();
			if(head.getX()==X  && head.getY()==Y)
				return true;
		}
		if(mFood.getX()==head.getX() && mFood.getY()==head.getY())
		{
			addTail();
			makeFood();
		}
		
		return false;
		
		
	}

	public void MovementAll() {
		for (int i = list.size() - 1; i > 0; i++) {
			Snake before = list.get(i - 1);
			Snake after = list.get(i);
			list.get(i).Movement();
			before.direction = after.direction;
		}
		head.Movement();
	}

	public void addTail() {
		Snake K = list.get(list.size() - 1).makeTail();
		list.add(K);
		add(K);
	}

	public Snake makeTail() {
		Snake tail = new Snake();
		int X = getX();
		int Y = getY();
		setBounds(X, Y, head.mGenislik, head.mGenislik);
		tail.direction = -direction;
		tail.Movement();
		tail.direction = direction;

		return tail;
	}

	public void Movement() {
		if (direction == Direction.right) {
			head.TurnRight();
		}
		if (direction == Direction.left) {
			head.TurnLeft();
		}
		if (direction == Direction.up) {
			head.TurnUp();
		}
		if (direction == Direction.down) {
			head.TurnDown();
		}
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
		g2.setColor(Color.gray);
		g2.setStroke(new BasicStroke(10));
		g2.draw(rect);

	}

	public void TurnLeft() {
		int PosX = head.getX();
		int PosY = head.getY();

		PosX -= head.mGenislik;
		head.setBounds(PosX, PosY, head.mGenislik, head.mGenislik);
	}

	public void TurnRight() {
		int PosX = head.getX();
		int PosY = head.getY();

		PosX += head.mGenislik;
		head.setBounds(PosX, PosY, head.mGenislik, head.mGenislik);
	}

	public void TurnUp() {
		int PosX = head.getX();
		int PosY = head.getY();

		PosY -= head.mGenislik;
		head.setBounds(PosX, PosY, head.mGenislik, head.mGenislik);
	}

	public void TurnDown() {
		int PosX = head.getX();
		int PosY = head.getY();

		PosY += head.mGenislik;
		head.setBounds(PosX, PosY, head.mGenislik, head.mGenislik);
	}

	public void makeFood()
	{
		int width=getWidth()-30-mFood.WIDTH;
		int height=getHeight()-30-mFood.HEIGHT;
		
		int PosX=Math.abs(rand.nextInt(width))+10;
		int PosY=Math.abs(rand.nextInt(height))+10;
		
		int XMod=PosX%20;
		int YMod=PosY%20;
		
		PosX-=XMod;
		PosY-=YMod;
		
		for(int i=0;i<list.size();i++)
		{
			int X=list.get(i).getX();
			int Y=list.get(i).getY();
			
			if(X==mFood.getX() && Y==mFood.getY())
			{
				makeFood();
				return;
			}
		}
		
		
		mFood.SetPosition(PosX, PosY);
	}

	class KeyboardControl implements KeyListener

	
	{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (head.direction != Direction.right)
					direction = Direction.left;
			}

			if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (head.direction != Direction.left)
					direction = Direction.right;
			}

			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
				if (head.direction != Direction.down)
					direction = Direction.up;
			}

			if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (head.direction != Direction.up)
					direction = Direction.down;
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
