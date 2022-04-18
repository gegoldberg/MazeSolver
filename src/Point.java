
public class Point
{
	//These can't be modified, so they're public for convenience
	public final int x, y;

	//Constructor
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	//Return all of the points orthogonally adjacent to this point
	public Point[] getAdjacentPoints()
	{
		return new Point[]
		{
			new Point(x + 1, y),
			new Point(x, y + 1),
			new Point(x - 1, y),
			new Point(x, y - 1)
		};
	}

	@Override
	public String toString()
	{
		return String.format("(%d, %d)", x, y);
	}
}