import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.naming.NameNotFoundException;

// Author: Garrett Goldberg
// Maze.java implements the TextMaze, and creates the variables, methods, and constructors that are used to setup and load the maze.

public class Maze implements TextMaze {

	// Instance Variables
	private char[][] maze;
	private int width, height;

	// Maze Constructor
	public Maze(int width, int height) {
		this.width = width;
		this.height = height;
		maze = new char[this.height][this.width];

		for (int iterY = 0; iterY < maze.length; iterY++) {
			for (int iterX = 0; iterX < maze[iterY].length; iterX++) {
				maze[iterY][iterX] = TextMaze.EMPTY;
			}
		}
	}

	// Sets Point in Maze
	public void set(Point p, char c) {
		if (this.inBounds(p)) {
			maze[p.y][p.x] = c;
		} else {
			throw new PointOutOfBoundsException(p.toString());
		}
	}

	// Returns Point in Maze
	public char get(Point p) {
		if (this.inBounds(p)) {
			return maze[p.y][p.x];
		} else {
			throw new PointOutOfBoundsException(p.toString());
		}
	}

	@Override
	public int width() {
		return width;
	}

	@Override
	public int height() {
		return height;
	}

	// Checks if Point is inBounds of the Maze
	public boolean inBounds(Point p) {
		if (p.x >= 0 && p.x < width() && p.y >= 0 && p.y < height()) {
			return true;
		} else {
			return false;
		}
	}

	// Prints the Maze as a string
	public String toString() {
		String print = "";

		for (int x = maze.length - 1; x >= 0; x--) {
			for (int y = 0; y < maze[x].length; y++) {
				print += maze[x][y];
			}
			if (x > 0) {
				print += "\n";
			}
		}
		return print;
	}

	// Read Maze from Text File and return Maze or Null
	public static Maze loadMaze(String fileName) {
		Scanner scanner = new Scanner(System.in);
		File file = new File(fileName);
		Scanner fileInput = null;
		try {
			fileInput = new Scanner(file);
			int width = fileInput.nextInt();
			int height = fileInput.nextInt();
			fileInput.nextLine();
			Maze maz = new Maze(width, height);
			for (int y = height - 1; y >= 0; y--) {
				if (fileInput.hasNextLine()) {
					String line = fileInput.nextLine();
					for (int x = 0; x < width; x++) {
						char c = line.charAt(x);
						Point p = new Point(x, y);
						maz.set(p, c);
					}
				}
			}
			return maz;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	// Saves the maze to a text file
	public static Maze saveMaze(String fileName, Maze maze) {
		try (PrintWriter pw = new PrintWriter(new File(fileName))) {
			pw.print(maze.width);
			pw.print(" ");
			pw.println(maze.height);
			pw.println(maze.toString());
			return maze;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

}
