/**
 * The playing board for Connect Four
 *
 * @author Sean DeZurik
 */
public class Board {
    /** 2-D array of pieces to go into the board */
    private Piece[][] gamePieces;
    /** How many rows on the board */
    private int numRows;
    /** How many columns on the board */
    private int numColumns;
    /** Array tracking for each column which row will get the next piece */
    private int[] lowestAvailableRow;
    
    /**
     * Constructor for Board
     *
     * @param numRows rows on the board
     * @param numColumns columns on the board
     */
    public Board(int numRows, int numColumns) {
        gamePieces = new Piece[numRows][numColumns];
        
        for (int i = 0; i < gamePieces.length; i++) {
        
           for (int j = 0; j < gamePieces[i].length; j++) {
              gamePieces[i][j] = new Piece();
           }
        }
        
        this.numRows = numRows;
        this.numColumns = numColumns;
        lowestAvailableRow = new int[numColumns];
        
        for (int i = 0; i < numColumns; i++) {
            lowestAvailableRow[i] = numRows - 1;
        }
    }
    
    /**
     * Piece being dropped into the board. Set the characteristics and
     * location for the piece.
     *
     * @param column which column the piece is dropped into
     * @param color the color of the playing piece
     * @return Piece the Piece object just dropped in
     */
    public Piece dropPiece(int column, String color) {
        int row = getRow(column);
        
        if (row >= 0) {
            gamePieces[row][column].setColor(color);
            gamePieces[row][column].setFilled(true);
            gamePieces[row][column].setRow(row);
            gamePieces[row][column].setColumn(column);
            pieceAdded(column);
            return gamePieces[row][column];
        } else {
            return null;
        }
    }
    
    /**
     * Get row piece will occupy in a column
     *
     * @param column the column where the piece is dropped
     * @return int the number of the row where the piece will occupy
     */
    private int getRow(int column) {
        return lowestAvailableRow[column];
    }
    
    /**
     * Modify the next available row because a piece just occupied the
     * previously available row
     *
     * @param column the column where the last piece was dropped into
     */
    private void pieceAdded(int column) {
        lowestAvailableRow[column]--;
    }
    
    /**
     * Determine the maximum number of consecutive pieces of a given
     * color. It only needs to check the vicinity of the piece just
     * dropped into the game to see if it exceeds the previous max value.
     *
     * @param justDropped the Piece just dropped into the game
     * @return an int with the max number of consecutive pieces
     */
    public int determineMaxConsecutive(Piece justDropped) {
        int maxHorizontal = getMaxHorizontal(justDropped);
        int maxVertical = getMaxVertical(justDropped);
        int maxDiagonal = getMaxDiagonal(justDropped);
        int max = maxHorizontal;
        
        if (max < maxVertical) {
            max = maxVertical;
        }
        
        if (max < maxDiagonal) {
            max = maxDiagonal;
        }
        
        return max;
    }
    
    /**
     * Find max consecutive pieces in left-right direction
     *
     * @param justDropped the Piece just dropped in
     * @return int max number of consecutive pieces left to right
     */
    private int getMaxHorizontal(Piece justDropped) {
        String color = justDropped.getColor();
        int row = justDropped.getRow();
        int column = justDropped.getColumn();
        int count = 1; // Count the piece just dropped
        int reverseCount = -1;
        
        // Look to the right
        while (column + count < numColumns && gamePieces[row][column + count].getFilled() && gamePieces[row][column + count].getColor() == justDropped.getColor()) {
            count++;
        }
        
        // Look to the left
        while (column + reverseCount >= 0 && gamePieces[row][column + reverseCount].getFilled() && gamePieces[row][column + reverseCount].getColor() == justDropped.getColor()) {
            reverseCount--;
        }
        
        // Add count to right (includes just played piece) to the absolute value of the count to the left.
        // But, we must subtract one because we end up counting the just played piece twice
        return count + Math.abs(reverseCount) - 1;
    }
    
    /**
     * Find max number of consecutive pieces up and down
     *
     * @param justDropped the Piece just dropped into the game
     * @return an int with the max number of pieces up and down
     */
    private int getMaxVertical(Piece justDropped) {
        String color = justDropped.getColor();
        int row = justDropped.getRow();
        int column = justDropped.getColumn();
        int count = 1; // Count the piece just dropped
        int reverseCount = -1;
        
        // Look down
        while (row + count < numRows && gamePieces[row + count][column].getFilled() && gamePieces[row + count][column].getColor() == justDropped.getColor()) {
            count++;
        }
        
        // Look up
        while (row + reverseCount >= 0 && gamePieces[row + reverseCount][column].getFilled() && gamePieces[row + reverseCount][column].getColor() == justDropped.getColor()) {
            reverseCount--;
        }
        
        return count + Math.abs(reverseCount) - 1;
    }
    
    /**
     * Find max number of consecutive pieces in diagonal directions
     *
     * @param justDropped the Piece just dropped into the game
     * @return an int with the max number of consecutive diagonal pieces
     */
    private int getMaxDiagonal(Piece justDropped) {
        String color = justDropped.getColor();
        int row = justDropped.getRow();
        int column = justDropped.getColumn();
        int count = 1; // Count the piece just dropped
        int reverseCount = -1;
        int down = 1;
        int left = -1;
        int up = -1;
        int right = 1;

        // Look down and right
        while (column + count < numColumns && row + count < numRows && gamePieces[row + count][column + count].getFilled() && gamePieces[row + count][column + count].getColor() == justDropped.getColor()) {
            count++;
        }
        
        // Look up and left
        while (column + reverseCount >= 0 && row + reverseCount >= 0 && gamePieces[row + reverseCount][column + reverseCount].getFilled() && gamePieces[row + reverseCount][column + reverseCount].getColor() == justDropped.getColor()) {
            reverseCount--;
        }
        
        // Look down and left
        while (row + down < numRows && column + left >= 0 && gamePieces[row + down][column + left].getFilled() && gamePieces[row + down][column + left].getColor() == justDropped.getColor()) {
            down++;
            left--;
        }
        
        // Look up and right
        while (row + up >= 0 && column + right < numColumns && gamePieces[row + up][column + right].getFilled() && gamePieces[row + up][column + right].getColor() == justDropped.getColor()) {
            right++;
            up--;
        }
        
        int backSlash = count + Math.abs(reverseCount) - 1;
        int forwardSlash = down + right - 1;
        
        if (backSlash > forwardSlash) {
            return backSlash;
        } else {
            return forwardSlash;
        }
    }
}