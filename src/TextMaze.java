
/**
 * @author Zach Kohlberg
 *
 * Interface for a Maze of characters
 */

public interface TextMaze
{
	//Constants for different characters in the maze. Use these constants instead of hard-
	//coding characters. For example, write TextMaze.WALL in your code instead of '#'.
	public static final char
			WALL = '#',
			EMPTY = ' ',
			GOAL = 'G',
			START = 'S',
			PATH = 'P',
			VISITED = '.';

	/**
	 * Change character at a location within the maze if that location is in bounds.
	 * If the location is out of bounds, throw an IndexOutOfBoundsException showing
	 * the point that was out of bounds.
	 */
	public void set(Point p, char c);

	/**
	 * Get character at a location within the maze if that location is in bounds.
	 * If the location is out of bounds, throw an IndexOutOfBoundsException showing
	 * the point that was out of bounds.
	 */
	public char get(Point p);

	/**
	 * Return the maze's dimensions.
	 */
	public int width();
	public int height();

	/**
	 * Returns whether a point is within the bounds of the maze.
	 */
	public boolean inBounds(Point p);
}