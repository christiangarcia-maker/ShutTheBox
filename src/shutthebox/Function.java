package shutthebox;

import java.util.Arrays;


public class Function {
	
	
/**
 * 
 * @param tiles
 * @param playerName
 * @param dice
 * @return
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

	 //QUE OTRO HAGA EL JAVADOC PORFAVOR IDK
	 /**
	  * 
	  * @param board
	  * @param target
	  * @return
	  */
	    private static boolean hasValidMove(int[] board, int target) {
	        // Recursive backtracking: try all subsets summing to target
	        return subsetSum(board, target, 0, 0);
	    }
	    
	  /**
	   * 
	   * @param board
	   * @param target
	   * @param index
	   * @param currentSum
	   * @return
	   */

	    private static boolean subsetSum(int[] board, int target, int index, int currentSum) {
	        if (currentSum == target) return true;
	        if (currentSum > target || index >= board.length) return false;
	        
	        // Skip this tile
	        if (subsetSum(board, target, index + 1, currentSum)) return true;
	        
	        // Use this tile if available
	        if (board[index] != 0) {
	            if (subsetSum(board, target, index + 1, currentSum + board[index])) return true;
	        }
	        
	        return false;
	    }

	    /**
	     * 
	     * @param board
	     * @param total
	     * @return
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
	     * Validates user selection.
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
	     * 
	     * @param board
	     * @param selected
	     * @return
	     */
	    private static int[] closeTiles(int[] board, int[] selected) {
	        for (int tile : selected) {
	            board[tile - 1] = 0;
	        }
	        return board;
	    }
	    
	    /**
	     * 
	     * @param board
	     */
	    private static void missingTiles(int[] board) {
	        System.out.print("Board: ");
	        for (int tile : board) {
	            System.out.print(tile == 0 ? "- " : tile + " ");
	        }
	        System.out.println();
	    }

	    
	    /**
	     * 
	     * @param tiles
	     * @param playerName
	     * @return
	     */
	    public static int endTurn(int[] tiles, String playerName) {
	        int score = getScore(tiles);
	        System.out.println(playerName + " final score: " + score);
	        return score;
	    }


	    /**
	     * 
	     * @param tiles
	     * @return
	     */
	    public static int getScore(int[] tiles) {
	        int score = 0;
	        for (int tile : tiles) {
	            score += tile;
	        }
	        return score;
	    }
}