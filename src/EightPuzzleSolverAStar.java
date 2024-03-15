import java.util.*;

public class EightPuzzleSolverAStar {
    static final int[][] goalState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    static final int[][] initialState = {{2, 8, 3}, {1, 6, 4}, {7, 0, 5}};

    public static void main(String[] args) {
        System.out.println("Initial State:");
        printState(initialState);

        List<int[][]> solutionPath = solvePuzzleAStar(initialState);

        if (solutionPath != null) {
            System.out.println("\nSolution Path:");
            for (int[][] state : solutionPath) {
                printState(state);
            }
        } else {
            System.out.println("Solution not found.");
        }
    }

    // A* search algorithm with Manhattan distance heuristic
    public static List<int[][]> solvePuzzleAStar(int[][] initialState) {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        Map<String, Integer> gScore = new HashMap<>();
        Map<String, int[][]> cameFrom = new HashMap<>();

        gScore.put(Arrays.deepToString(initialState), 0);
        openSet.add(new Node(initialState, 0, calculateManhattanDistance(initialState)));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (Arrays.deepEquals(current.state, goalState)) {
                return reconstructPath(cameFrom, current.state);
            }

            List<int[][]> successors = generateSuccessors(current.state);
            for (int[][] successor : successors) {
                int tentativeGScore = gScore.get(Arrays.deepToString(current.state)) + 1;
                if (!gScore.containsKey(Arrays.deepToString(successor)) || tentativeGScore < gScore.get(Arrays.deepToString(successor))) {
                    gScore.put(Arrays.deepToString(successor), tentativeGScore);
                    int fScore = tentativeGScore + calculateManhattanDistance(successor);
                    openSet.add(new Node(successor, fScore, calculateManhattanDistance(successor)));
                    cameFrom.put(Arrays.deepToString(successor), current.state);
                }
            }
        }
        return null; // No solution found
    }

    // Calculate Manhattan distance heuristic
    public static int calculateManhattanDistance(int[][] state) {
        int distance = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = state[i][j];
                if (value != 0) {
                    int goalX = (value - 1) / 3;
                    int goalY = (value - 1) % 3;
                    distance += Math.abs(goalX - i) + Math.abs(goalY - j);
                }
            }
        }
        return distance;
    }

    // Generate successor states by moving the blank tile
    public static List<int[][]> generateSuccessors(int[][] state) {
        List<int[][]> successors = new ArrayList<>();
        int[] blankPosition = findBlankPosition(state);

        // Possible moves: left, right, up, down
        int[][] moves = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        for (int[] move : moves) {
            int newX = blankPosition[0] + move[0];
            int newY = blankPosition[1] + move[1];
            if (newX >= 0 && newX < 3 && newY >= 0 && newY < 3) {
                int[][] successor = Arrays.stream(state).map(int[]::clone).toArray(int[][]::new);
                successor[blankPosition[0]][blankPosition[1]] = state[newX][newY];
                successor[newX][newY] = 0;
                successors.add(successor);
            }
        }
        return successors;
    }

    // Find the position of the blank tile in the puzzle
    public static int[] findBlankPosition(int[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    // Reconstruct the solution path
    public static List<int[][]> reconstructPath(Map<String, int[][]> cameFrom, int[][] currentState) {
        List<int[][]> path = new ArrayList<>();
        String currentStateString = Arrays.deepToString(currentState);
        while (cameFrom.containsKey(currentStateString)) {
            path.add(0, currentState);
            currentState = cameFrom.get(currentStateString);
            currentStateString = Arrays.deepToString(currentState);
        }
        path.add(0, currentState);
        return path;
    }

    // Print the state of the puzzle
    public static void printState(int[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Node class to represent states in A* search
    static class Node {
        int[][] state;
        int cost;

        Node(int[][] state, int cost, int heuristic) {
            this.state = state;
            this.cost = cost + heuristic; // Total cost = gScore + heuristic
        }

        public int getCost() {
            return cost;
        }
    }
}
