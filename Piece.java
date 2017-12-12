/**
 * Characteristics and location of pieces in the Connect Four game
 *
 * @author Sean DeZurik
 */
public class Piece {
    /** Color of the piece */
    private String color;
    /** Whether a spot on the board is occupied by a piece or not */
    private boolean filled;
    /** Rows on the board */
    private int row;
    /** Columns on the board */
    private int column;
    
    /**
     * Constructor for Piece
     */
    public Piece() {
        color = "";
        filled = false;
    }
    
    /**
     * Get color of a piece
     *
     * @return String with color of the piece
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Set the color of the piece
     *
     * @param color a String with the name of a color
     */
    public void setColor(String color) {
        this.color = color;
    }
    
    /**
     * Find out if piece is filling a spot on the board
     *
     * @return boolean true if piece is filling the spot on the board
     *         and false if it is not
     */
    public boolean getFilled() {
        return filled;
    }
    
    /**
     * Mark a spot on the board as occupied by a piece
     *
     * @param filled a boolean that is true if the spot is filled
     *        and false if it is not
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    
    /**
     * Set the row the piece is in
     *
     * @param row the row number
     */
    public void setRow(int row) {
        this.row = row;
    }
    
    /**
     * Set the column the piece is in
     *
     * @param column the column number
     */
    public void setColumn(int column) {
        this.column = column;
    }
    
    /**
     * Get the row the piece is in
     *
     * @return int with row number
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Get the column the piece is in
     *
     * @return int with column number
     */
    public int getColumn() {
        return column;
    }
}