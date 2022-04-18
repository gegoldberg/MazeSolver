/**
 * Exception for an out of bounds point in the Maze.
 */

public class PointOutOfBoundsException extends IndexOutOfBoundsException
{
    public PointOutOfBoundsException(String s)
    {
        super(s);
    }
}