package shutthebox;

import java.util.Arrays;


public class Function {	
	/**
	 * Executes a full turn for a player, rolling dice and allowing tile selections
	 * until no valid moves remain or the player shuts the box.
	 *
	 * @param tiles      the current state of the board tiles
	 * @param playerName the name of the player taking the turn
	 * @param dice       array of two Dice objects used to roll values
	 * @return the updated board state after the turn ends
	 */
	 public static int[] turn(int[] tiles, String playerName, Dice[] dice) {
	        System.out.println("\n--- " + playerName + "'s turn ---");
	        int[] board = tiles.clone();  // Work on copy to avoid mutating original prematurely
	        
	        while (true) {
	            missingTiles(board);  // Show current board
	            
	            // Roll dice
	            int[] values = Dice.rollTwoValues(dice[0], dice[1]);
	            int total = values[0] + values[1];
	            System.out.println("You rolled: " + values[0] + " + " + values[1] + " = " + total);
	            
	            // Check if win
	            if (getScore(board) == 0) {
	                System.out.println(playerName + " SHUT THE BOX! You win immediately!");
	                return board;
	            }
	            
	            // Check if valid move possible
	            if (!hasValidMove(board, total)) {
	                System.out.println("No valid move. Turn ends.");
	                return board;
	            }
	            
	            // Make move
	            board = selectAndPlay(board, total);
	        }
	    }

	 /**
	  * Determines whether there is at least one valid combination of open tiles
	  * that sums to the given target value.
	  *
	  * @param board  the current board state, where 0 indicates a closed tile
	  * @param target the value that must be matched by a subset of open tiles
	  * @return {@code true} if a valid move exists, {@code false} otherwise
	  */
	    private static boolean hasValidMove(int[] board, int target) {
	        // Recursive backtracking: try all subsets summing to target
	        return subsetSum(board, target, 0, 0);
	    }
	    
	    /**
	     * Recursively checks whether any subset of tiles from the given index onward
	     * sums to the target value using backtracking.
	     *
	     * @param board      the current board state, where 0 indicates a closed tile
	     * @param target     the desired sum to reach
	     * @param index      the current index in the board being evaluated
	     * @param currentSum the accumulated sum of tiles selected so far
	     * @return {@code true} if a valid subset summing to target exists, {@code false} otherwise
	     */
	    private static boolean subsetSum(int[] board, int target, int index, int currentSum) {
	        if (currentSum == target) return true;
	        if (currentSum > target || index >= board.length) return false;
	        
	        // Skip this tile
	        if (subsetSum(board, target, index + 1, currentSum)) return true;
	        
	        // Use this tile if available
	        if (board[index] != 0 && (subsetSum(board, target, index + 1, currentSum + board[index]))) { return true;
	        }
	        
	        return false;
	    }

	    /**
	     * Prompts the player to select tiles to close and validates the selection.
	     * Recursively retries on invalid input or selection until a valid move is made.
	     *
	     * @param board the current board state, where 0 indicates a closed tile
	     * @param total the required sum that the selected tiles must add up to
	     * @return the updated board state after closing the selected tiles
	     */
	    private static int[] selectAndPlay(int[] board, int total) {
	        System.out.print("Enter tiles to close (space-separated, e.g., '3 5' for 8): ");
	        try {
	            String[] input = Main.reader.nextLine().trim().split("\\s+");
	            int[] selected = new int[input.length];
	            
	            for (int i = 0; i < input.length; i++) {
	                selected[i] = Integer.parseInt(input[i]);
	            }
	            
	            // Validate: tiles exist, unique, available, sum correct
	            if (validateSelection(board, selected, total)) {
	                return closeTiles(board.clone(), selected);
	            } else {
	                System.out.println("Invalid selection! Try again.");
	                return selectAndPlay(board, total);  // Retry
	            }
	        } catch (Exception e) {
	            System.out.println("Invalid input. Try again.");
	            return selectAndPlay(board, total);
	        }
	    }
	    
	    /**
	     * Validates that the player's tile selection is legal: the tiles must exist,
	     * be currently open, be unique, and their sum must equal the target total.
	     *
	     * @param board    the current board state, where 0 indicates a closed tile
	     * @param selected array of tile numbers chosen by the player
	     * @param total    the required sum that the selected tiles must match
	     * @return {@code true} if the selection is valid, {@code false} otherwise
	     */
	    private static boolean validateSelection(int[] board, int[] selected, int total) {
	        int sum = Arrays.stream(selected).sum();
	        if (sum != total) return false;
	        
	        // Check all selected tiles exist, are available, and unique
	        boolean[] used = new boolean[13];  // Index 1-12
	        for (int tile : selected) {
	            if (tile < 1 || tile > 12 || used[tile] || board[tile - 1] != tile) {
	                return false;
	            }
	            used[tile] = true;
	        }
	        return true;
	    }
	    
	    /**
	     * Closes the specified tiles on the board by setting their positions to 0.
	     *
	     * @param board    the board state to modify (should be a clone to avoid side effects)
	     * @param selected array of tile numbers to close
	     * @return the updated board state with the selected tiles closed
	     */
	    private static int[] closeTiles(int[] board, int[] selected) {
	        for (int tile : selected) {
	            board[tile - 1] = 0;
	        }
	        return board;
	    }
	    
	    /**
	     * Prints the current state of the board, displaying open tile numbers
	     * and dashes for closed tiles.
	     *
	     * @param board the current board state, where 0 indicates a closed tile
	     */
	    private static void missingTiles(int[] board) {
	        System.out.print("Board: ");
	        for (int tile : board) {
	            System.out.print(tile == 0 ? "- " : tile + " ");
	        }
	        System.out.println();
	    }

	    
	    /**
	     * Calculates and displays the final score for a player at the end of their turn.
	     * The score is the sum of all remaining open tiles.
	     *
	     * @param tiles      the final board state after the player's turn
	     * @param playerName the name of the player whose score is being calculated
	     * @return the player's final score
	     */
	    public static int endTurn(int[] tiles, String playerName) {
	        int score = getScore(tiles);
	        System.out.println(playerName + " final score: " + score);
	        return score;
	    }


	    /**
	     * Calculates the total score by summing all remaining open tiles on the board.
	     * A score of 0 means the player has shut the box.
	     *
	     * @param tiles the current board state
	     * @return the sum of all open (non-zero) tiles
	     */
	    public static int getScore(int[] tiles) {
	        int score = 0;
	        for (int tile : tiles) {
	            score += tile;
	        }
	        return score;
	    }
}