/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam & Neil Hutton
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

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

        // Arraylist of moves to get to end
        ArrayList<MazeCell> path = new ArrayList<MazeCell>();

        // Trace from finish back to start
        MazeCell current = maze.getEndCell();

        // While still going backwards
        while (!(current == null))
        {
            // Adds the move leading up to the end to the arraylist
            path.add(0,current);
            // Sets current to the location before
            current = current.getParent();
        }


        return path;
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

        // Start and end cells from the maze
        MazeCell start = maze.getStartCell();
        MazeCell end = maze.getEndCell();

        // First move is start position and it has been explored
        nextMove.push(start);
        start.setExplored(true);

        // While there are still places to explore
        while(!nextMove.isEmpty()) {
            // Move to next move
            MazeCell current = nextMove.pop();
            // If program is at the end find solution
            if (current == end) {
                return getSolution();
            }
            // Find all neighbours and next moves
            ArrayList<MazeCell> moves = cellsToExplore(current);
            // Explore every move in the possible moves list
            for(MazeCell move: moves)
            {
                if(!move.isExplored())
                {
                    // Make sure don't revisit same place
                    move.setExplored(true);
                    // Register pathway to end
                    move.setParent(current);
                    // Add move to nextMove list
                    nextMove.push(move);
                }
            }
        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze

        // Queue instead of stack because visit all possible locations
        Queue<MazeCell> nextMove = new LinkedList<MazeCell>();
        // Start and end cells from the maze
        MazeCell start = maze.getStartCell();
        MazeCell end = maze.getEndCell();

        // First move is start position and it has been explored
        nextMove.add(start);
        start.setExplored(true);

        // While there are still places to explore
        while(!nextMove.isEmpty()) {
            // Move to next move
            MazeCell current = nextMove.remove();
            // If program is at the end find solution
            if (current == end) {
                return getSolution();
            }
            // Find all neighbours and next moves
            ArrayList<MazeCell> moves = cellsToExplore(current);
            // Explore every move in the possible moves list
            for(MazeCell move: moves)
            {
                if(!move.isExplored())
                {
                    // Make sure don't revisit same place
                    move.setExplored(true);
                    // Register pathway to end
                    move.setParent(current);
                    // Add move to nextMove list
                    nextMove.add(move);
                }
            }
        }
        return getSolution();
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
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
