/**
 * Player in the Connect Four game
 *
 * @author Sean DeZurik
 */
public class Player {
    /** How many total pieces played by a player */
    private int piecesPlayed;
    /** Max consecutive pieces on the board by a player */
    private int maxConsecutivePieces;
    /** Color of player's pieces */
    private String color;
    /** Name of the player */
    private String name;
    /** Player1 or Player2 */
    private int playerNumber;
    
    /**
     * Constructor for Player
     *
     * @param name the name of the player
     * @param color the color of the player's pieces
     * @param playerNumber whether 1 or 2
     */
    public Player(String name, String color, int playerNumber) {
        this.name = name;
        this.color = color;
        this.playerNumber = playerNumber;
        piecesPlayed = 0;
        maxConsecutivePieces = 0;
    }
    
    /**
     * Get total number of pieces played by player
     * 
     * @return int with number of pieces played
     */
    public int getPiecesPlayed() {
        return piecesPlayed;
    }
    
    /**
     * Get max consecutive pieces on board by player
     *
     * @return int with number of max consecutive pieces
     */
    public int getMaxConsecutivePieces() {
        return maxConsecutivePieces;
    }
    
    /**
     * Increase number of pieces played by one
     */
    public void incrementPiecesPlayed() {
        piecesPlayed++;
    }
    
    /**
     * Set the max number of consecutive pieces
     *
     * @param count the number of consecutive pieces
     */
    public void setMaxConsecutivePieces(int count) {
        if (count > maxConsecutivePieces) {
            maxConsecutivePieces = count;
        }
    }
    
    /**
     * Get the player's color
     *
     * @return String with player's color
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Get the player's name
     *
     * @return String with the player's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the player number, 1 or 2
     *
     * @return int with the player number
     */
    public int getPlayerNumber() {
        return playerNumber;
    }
}