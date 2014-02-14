import java.awt.*;

/**
 * A minimal turtle-graphics turtle.
 * @author Joshua Parker
 */
public class Turtle {
	private double xPos, yPos, prevX, prevY, heading;
	
	/**
	 * Inital position of the turtle
	 * @param x the start x position
	 * @param y the start y position
	 */
	public Turtle(double x, double y) {
		xPos = prevX = x;
		yPos = prevY = y;
		heading = 0.0;
	}
	
	/**
	 * Moves the turtle (and draws the move).
	 * @param g the graphics context
	 * @param steps the number of steps to move
	 */
	public void move(Graphics g, double steps)	{
		prevX = xPos;
		prevY = yPos;
		xPos += steps * Math.cos(heading);
		yPos -= steps * Math.sin(heading);
		draw(g);
	}
	
	/**
	 * Turns the turtle.
	 * @param angle the angle to turn in degrees
	 */
	public void turn(double angle) {
		heading += Math.PI * angle / 180.0;
	}
	
	/**
	 * Draws the line from the previous turtle position
	 * to its new position.
	 * N.B. This works properly even if passed a Graphics2D object.
	 * @param g the graphics context
	 */
	public void draw(Graphics g) {
		g.drawLine((int)prevX, (int)prevY, (int)xPos, (int)yPos);
	}

}
