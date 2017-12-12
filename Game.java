/**
 * Game logic for Connect Four
 *
 * @author Sean DeZurik
 */
public class Game {
    /** Array of Player objects for the two players */
    private Player[] players;
    /** The game board */
    private Board grid;
    /** The player whose turn it is */
    private Player playerTurn;
    /** How many games have been played since the program started */
    private int gameNumber;
    /** Number of rows on the board */
    private int numRows;
    /** Number of columns on the board */
    private int numColumns;
  
    /** Piece color for player1 */
    private static final String PLAYER1_COLOR = "red";
    /** Piece color for player2 */
    private static final String PLAYER2_COLOR = "black";
    /** How many consecutive pieces are needed to win */
    private static final int WINNING_NUMBER_PIECES = 4;
    
    /**
     * Constructor for Game
     *
     * @param firstPlayerName name of player one
     * @param secondPlayerName name of player two
     * @param gameNumber which game number this is
     * @param numRows how many rows on the board
     * @param numColumns how many columns on the board
     *
     */
    public Game(String firstPlayerName, String secondPlayerName, int gameNumber, int numRows, int numColumns) {
        players = new Player[2];
        players[0] = new Player(firstPlayerName, PLAYER1_COLOR, 1);
        players[1] = new Player(secondPlayerName, PLAYER2_COLOR, 2);
        grid = new Board(numRows, numColumns);
        this.gameNumber = gameNumber;
        this.numRows = numRows;
        this.numColumns = numColumns;
        
        if (gameNumber % 2 == 0) {
            setPlayerTurn(players[1]);
        } else {
            setPlayerTurn(players[0]);
        }
    }
    
    /**
     * Dropping a playing piece into a column on the board
     *
     * @param column column number
     * @return int with the row number the piece dropped to
     */
    public int dropColumn(int column) {
        Player current = getPlayerTurn();
        String color = current.getColor();
        Piece justDropped = grid.dropPiece(column, color);
        
        if (justDropped != null) {
            int maxConsecutive = grid.determineMaxConsecutive(justDropped);
            current.setMaxConsecutivePieces(maxConsecutive);
            current.incrementPiecesPlayed();
            return justDropped.getRow();
        } else {
            return -1;
        }
    }
    
    /**
     * Determine if a winner can be declared
     *
     * @return boolean true if player won and false if not
     */
    public boolean winner() {
        Player current = getPlayerTurn();
        
        if (current.getMaxConsecutivePieces() == WINNING_NUMBER_PIECES) {
            return true;
        } else {
            changeTurn(current);
            return false;
        }
    }
    
    /**
     * Get the player whose turn it is
     *
     * @return Player the player whose turn it is
     */
    private Player getPlayerTurn() {
        return playerTurn;
    }
    
    /**
     * Set the player with the first turn
     *
     * @param playerNumber player going first
     */
    private void setPlayerTurn(Player playerNumber) {
        playerTurn = playerNumber;
    }
    
    /**
     * Change the player whose turn it is
     *
     * @param current player who took the last turn
     */
    private void changeTurn(Player current) {
        int playerIndex = current.getPlayerNumber() - 1;
        
        if (playerIndex == 0) {
            setPlayerTurn(players[1]);
        } else {
            setPlayerTurn(players[0]);
        }
    }
    
    public String getPlayerColor() {
        return playerTurn.getColor();
    }
    
    public String getTurnPlayerName() {
        Player current = getPlayerTurn();
        return current.getName();
    }
    
    public int getPlayerConsecutive() {
        Player current = getPlayerTurn();
        return current.getMaxConsecutivePieces();
    }
    
    public int getPlayerTotal() {
        Player current = getPlayerTurn();
        return current.getPiecesPlayed();
    }
}