import java.util.Scanner;

//Author: Garrett Goldberg
// MazeSolver.java includes a main method and methods that are used to solve the maze that was input by the user.

public class MazeSolver {

// This main method takes the file, creates a start and finish using the user's input, and returns the solution of the maze. 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String filename = null;
		Maze maze = null;
		boolean success = false;
		while (!success) {
			System.out.println("Please enter the name of your maze file.");
			filename = sc.nextLine();
			maze = Maze.loadMaze(filename);
			if (maze == null) {
				System.out.println("That file doesn't exist, try again.");
			} else {
				success = true;
			}
		}

		System.out.println("Maze:");
		System.out.println(maze.toString());

		boolean goodPoint1 = false;
		boolean goodPoint2 = false;
		Point startPoint = null;
		Point endPoint = null;

		while (!goodPoint1) {
			System.out.println("Please enter the start point of the maze.");
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			startPoint = new Point(x1, y1);

			if (maze.inBounds(startPoint) && (maze.get(startPoint) == TextMaze.EMPTY)) {
				goodPoint1 = true;
			} else {
				System.out.println("That's not an empty space in the maze, try again.");
			}
		}

		while (!goodPoint2) {
			System.out.println("Please enter the end point of the maze.");
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			endPoint = new Point(x2, y2);

			if (maze.inBounds(endPoint) && (maze.get(endPoint) == TextMaze.EMPTY)) {
				goodPoint2 = true;
			} else {
				System.out.println("That point won't work. Please enter a new point.");
			}
		}

		boolean solve = solveMaze(maze, startPoint, endPoint);

		if (solve == true) {
			System.out.println("Solved Maze:");
			Maze.saveMaze(filename + ".solved", maze);
		} else {
			System.out.println("Unsolvable Maze:");
		}

		System.out.println(maze.toString());

	}

	// This method sets the start and finish points, and then uses the
	// solveMazeHelper to find the solution.
	public static boolean solveMaze(Maze maze, Point start, Point end) {
		maze.set(end, TextMaze.GOAL);
		boolean solve = solveMazeHelper(maze, start);
		maze.set(start, TextMaze.START);
		return solve;
	}

	// This method solves the maze by going through each point and it's adjacent
	// points and creating a path for the solution, returning if the maze is solved.
	public static boolean solveMazeHelper(Maze maze, Point point) {
		if (maze.get(point) == TextMaze.GOAL) {
			return true;
		} else if (maze.get(point) != TextMaze.EMPTY) {
			return false;
		} else {
			maze.set(point, TextMaze.PATH);

			Point[] adjacent = point.getAdjacentPoints();
			boolean check = false;

			for (int i = 0; i < adjacent.length; i++) {
				if (maze.inBounds(adjacent[i])) {
					check = solveMazeHelper(maze, adjacent[i]);
					if (check == true) {
						return check;
					}
				}
			}
			if (check == false) {
				maze.set(point, TextMaze.VISITED);
			}
			return check;
		}
	}

}
