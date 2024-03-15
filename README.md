# 8 Puzzle Solver using A* Search

This project implements an A* search algorithm to solve the classic 8-puzzle problem. The 8-puzzle is a sliding puzzle that consists of a 3x3 grid of numbered tiles, with one tile missing. The objective is to rearrange the tiles into their correct order by sliding them into the empty space, typically using the least number of moves.

# Problem Description

In the 8-puzzle problem, you are given an initial configuration of the puzzle, which is a scrambled arrangement of the tiles. The goal is to reach a specified final configuration, typically the solved state where the tiles are arranged in ascending order from left to right, top to bottom, with the empty space in the bottom-right corner.

# A* Search Algorithm

A* search is an informed search algorithm that efficiently finds the shortest path from a start state to a goal state in a graph or state space. It combines the benefits of both breadth-first search and greedy best-first search by using a heuristic function to guide the search towards the goal while also considering the cost of reaching each state from the start.

# Implementation Details

This project implements the A* search algorithm to solve the 8-puzzle problem. The algorithm uses a priority queue to explore states in order of their estimated cost from the start plus a heuristic estimate of the cost to reach the goal. The heuristic function used is the Manhattan distance, which calculates the sum of the distances between each tile's current position and its goal position.

# Usage

1. **Input**: Provide the initial configuration of the puzzle as a 3x3 grid of numbers, with 0 representing the empty space.

2. **Output**: The program will output the sequence of moves required to solve the puzzle and reach the goal state. Each move represents sliding a tile into the empty space, indicated by the direction of the movement (left, right, up, down).

# How to Run

To run the program, follow these steps:

1. Clone the repository to your local machine.
2. Compile the source code.
3. Execute the compiled program, providing the initial configuration of the puzzle as input.
4. The program will display the solution path, showing each move required to solve the puzzle.

# Conclusion

The A* search algorithm efficiently solves the 8-puzzle problem by intelligently exploring the state space and finding the shortest path to the goal. By leveraging heuristics to guide the search, A* achieves optimal solutions while minimizing computational effort.

Feel free to explore the code and experiment with different initial configurations to test the algorithm's performance and effectiveness. Happy puzzling!



