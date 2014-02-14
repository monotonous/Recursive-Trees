import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Draws a recursive "tree" from the input given by the user.
 * User gives the angle and number of branches to draw
 *
 * @author Joshua Parker
 * @version 0.9
 */
public class Tree2 extends JPanel {

	private static final int CENTRE_X = 300;
	private static final int CENTRE_Y = 300;
	private static final int ANGLE = 40;
	private static final double SHRINK = 0.8;
	private static final Color BROWN = new Color(120, 90, 60);
	private static final Color GREEN = new Color(80, 240, 60);

	private Turtle turtle;
	private int clicks, userBranch;
	private double userAngle;

	public Tree2(double angle, int branch) {
		userAngle = angle;
		userBranch = branch;
		setBackground(Color.WHITE);
		setPreferredSize(new java.awt.Dimension(CENTRE_X * 2, CENTRE_Y * 2));
		clicks = 0;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clicks += 1;
				repaint();
			}
		});
	}

	/**
	 * Recursively draws a tree.
	 *
	 * @param g
	 *            the graphics context
	 * @param level
	 *            the recursion level
	 * @param steps
	 *            the size of the edge
	 */
	private void drawTree(Graphics g, int level, double steps) {
		turtle.move(g, steps);
		if(level >= 1){
			turtle.turn(-userAngle / 2);
			drawTree(g, level - 1, steps * SHRINK);
			drawTree(g, level - level, -steps * SHRINK);
			for(int i = 0; i < userBranch - 1; i++){
				turtle.turn(userAngle / (userBranch - 1));
				drawTree(g, level - 1, steps * SHRINK);
				drawTree(g, level - level, -steps * SHRINK);
			}
			turtle.turn(-userAngle / 2);
		}
	}

	/*
	 * Draws the tree in the component.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		turtle = new Turtle(CENTRE_X, CENTRE_Y * 1.9);
		turtle.turn(90);
		drawTree(g, clicks, CENTRE_X / 3);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Recursive Tree");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try{
			Tree2 treePanel = new Tree2(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			System.out.print("User input: \nAngle of " + Integer.parseInt(args[0]));
			System.out.println(" with " + Integer.parseInt(args[1]) + " branches being used");
			frame.add(treePanel);
		}catch(Exception e){
			System.out.println("Incorrect / No input given defaults being used:\nAngle of " + ANGLE + " with 2 branches");
			Tree2 treePanel = new Tree2(ANGLE, 2);
			frame.add(treePanel);
		}finally{
		frame.pack();
		frame.setVisible(true);
	}
	}

}
