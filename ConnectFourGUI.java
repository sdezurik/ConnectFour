import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConnectFourGUI extends JFrame implements ActionListener {
    private int gameNumber;
    private JButton[] columnButtons;
    private JButton[][] grid;
    private JPanel panel;
    private JPanel gridPanel;
    private JPanel playerPanel;
    private JLabel turn;
    private JLabel player1Max;
    private JLabel player2Max;
    private JLabel player1Total;
    private JLabel player2Total;
    private Game g;
    private String name1;
    private String name2;
    
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLUMNS = 7;
    private static final String MAX_LABEL = "Max Consecutive Pieces: ";
    private static final String TOTAL_LABEL = "Total Pieces Played: ";

    public ConnectFourGUI() {
        name1 = JOptionPane.showInputDialog("Enter name of player 1");
        name2 = JOptionPane.showInputDialog("Enter name of player 2");
        gameNumber = 1;
        
        //do {
            setSize(900, 700);
            setLocation(100, 100);
            setTitle("Connect Four");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            Container c = getContentPane();
            g = new Game(name1, name2, gameNumber, NUM_ROWS, NUM_COLUMNS);
            columnButtons = new JButton[NUM_COLUMNS];
            panel = new JPanel();
            panel.setLayout(new GridLayout(1, NUM_COLUMNS));
            columnButtons = new JButton[NUM_COLUMNS];
            
            for (int i = 0; i < NUM_COLUMNS; i++) {
                columnButtons[i] = new JButton("Column " + (i + 1));
                columnButtons[i].addActionListener(this);
                panel.add(columnButtons[i]);
            }
            
            gridPanel = new JPanel();
            gridPanel.setLayout(new GridLayout(NUM_ROWS, NUM_COLUMNS));
            grid = new JButton[NUM_ROWS][NUM_COLUMNS];
            
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLUMNS; j++) {
                    grid[i][j] = new JButton();
                    grid[i][j].addActionListener(this);
                    grid[i][j].setEnabled(false);
                    gridPanel.add(grid[i][j]);
                }
            }
            
            playerPanel = new JPanel();
            playerPanel.setLayout(new GridLayout(3, 3));
            JLabel player1 = new JLabel("Player 1 (Red): " + name1);
            JLabel player2 = new JLabel("Player 2 (Black): " + name2);
            player1Max = new JLabel(MAX_LABEL + "0");
            player2Max = new JLabel(MAX_LABEL + "0");
            player1Total = new JLabel(TOTAL_LABEL + "0");
            player2Total = new JLabel(TOTAL_LABEL + "0");
            playerPanel.add(player1);
            playerPanel.add(player1Max);
            playerPanel.add(player1Total);
            playerPanel.add(player2);
            playerPanel.add(player2Max);
            playerPanel.add(player2Total);
            String turnName = g.getTurnPlayerName();
            turn = new JLabel("Turn: " + turnName);
            playerPanel.add(turn);
            
            c.add(panel, BorderLayout.NORTH);
            c.add(gridPanel, BorderLayout.CENTER);
            c.add(playerPanel, BorderLayout.SOUTH);
            setVisible(true);
            //break;
        //} while (true);
    }
    
    public void actionPerformed(ActionEvent e) {
        int row = 0;
        
        for (int i = 0; i < columnButtons.length; i++) {
            if (e.getSource() == columnButtons[i]) {
                row = g.dropColumn(i);
                
                if (row >= 0 && row < NUM_ROWS) {
                    String color = g.getPlayerColor();
                    
                    if (color.equals("red")) {
                        grid[row][i].setBackground(Color.RED);
                        player1Max.setText(MAX_LABEL + g.getPlayerConsecutive());
                        player1Total.setText(TOTAL_LABEL + g.getPlayerTotal());
                    } else if (color.equals("black")) {
                        grid[row][i].setBackground(Color.BLACK);
                        player2Max.setText(MAX_LABEL + g.getPlayerConsecutive());
                        player2Total.setText(TOTAL_LABEL + g.getPlayerTotal());
                    }
                    
                    if (row == 0) {
                        columnButtons[i].setEnabled(false);
                    }
                    
                    if (g.winner()) {
                        grid[row][i].setText(g.getTurnPlayerName() + " wins!");
                        int answer = JOptionPane.showConfirmDialog(null, "Do you wish to play another game?", "Another game?", JOptionPane.YES_NO_OPTION);
                        
                        if (answer == JOptionPane.NO_OPTION) {
                            System.exit(0);
                        } else if (answer == JOptionPane.YES_OPTION) {
                            //Clear everything and increment game number
                            gameNumber++;
                            
                            for (int j = 0; j < NUM_ROWS; j++) {
                                for (int k = 0; k < NUM_COLUMNS; k++) {
                                    grid[j][k].setBackground(null);
                                    grid[j][k].setText(null);
                                    player1Max.setText(MAX_LABEL + "0");
                                    player1Total.setText(MAX_LABEL + "0");
                                    player2Max.setText(MAX_LABEL + "0");
                                    player2Total.setText(MAX_LABEL + "0");
                                }
                            }
                            
                            for (int j = 0; j < NUM_COLUMNS; j++) {
                                columnButtons[j].setEnabled(true);
                            }

                            g = new Game(name1, name2, gameNumber, NUM_ROWS, NUM_COLUMNS); 
                            turn.setText("Turn: " + g.getTurnPlayerName());  
                        }
                        
                    } else {
                        turn.setText("Turn: " + g.getTurnPlayerName());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ConnectFourGUI gui = new ConnectFourGUI();
    }
}