/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        return null;
    }

    // Method to get all possible cells to explore
    public ArrayList<MazeCell> cellsToExplore(MazeCell loc)
    {
        // Arraylist of possible cells to explore
        ArrayList<MazeCell> toExplore = new ArrayList<MazeCell>();

        // Location
        int row = loc.getRow();
        int col = loc.getCol();

        // Checks cell to the North
        if(maze.isValidCell(row-1,col))
        {
            toExplore.add(maze.getCell(row-1, col));
        }
        // Checks cell to the East
        if(maze.isValidCell(row,col+1))
        {
            toExplore.add(maze.getCell(row, col+1));
        }
        // Checks cell to the South
        if(maze.isValidCell(row+1,col))
        {
            toExplore.add(maze.getCell(row+1, col));
        }
        // Checks to the West
        if(maze.isValidCell(row,col-1))
        {
            toExplore.add(maze.getCell(row, col-1));
        }

        return toExplore;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Stack for next moves
        Stack<MazeCell> nextMove = new Stack<MazeCell>();

        ArrayList<MazeCell> visited = new ArrayList<MazeCell>();

        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST

        return null;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        return null;
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
