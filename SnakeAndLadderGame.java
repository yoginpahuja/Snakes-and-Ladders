import java.util.*;

class SnakeAndLadderGame {
    private static final int WINNING_POSITION = 100;
    private static final int START_POSITION = 0;

    private int currentPlayer;
    private int[] playerPositions;
    private Map<Integer, Integer> board; // Represents the game board with ladder and snake positions

    public SnakeAndLadderGame() {
        this.currentPlayer = 1;
        this.playerPositions = new int[2];
        this.board = new HashMap<>();
        initializeBoard();
    }
    private void printPlayerPositions()
    {
        System.out.println("Player 1 : "+ playerPositions[0] + "Player 2 :"+ playerPositions[1]);
    }

    private void initializeBoard() {
        // Initialize ladder positions
        board.put(1, 38);
        board.put(4, 14);
        board.put(9, 31);
        board.put(21, 42);
        board.put(28, 84);
        board.put(36, 44);
        board.put(51, 67);
        board.put(71, 91);
        board.put(80, 100);
        board.put(88, 92);

        // Initialize snake positions
        board.put(16, 6);
        board.put(47, 26);
        board.put(49, 11);
        board.put(56, 53);
        board.put(62, 19);
        board.put(64, 60);
        board.put(87, 24);
        board.put(93, 73);
        board.put(95, 75);
        board.put(98, 78);
    }
    private void switchPlayer() {
        currentPlayer = 3 - currentPlayer;
    }
    public void printBoard() {
        System.out.println("Snake and Ladder Board:");
        for (Map.Entry<Integer, Integer> entry : board.entrySet()) {
            System.out.println("Position " + entry.getKey() + " -> " + entry.getValue());
        }
    }
    private int rollDice() {
        return new Random().nextInt(6) + 1;
    }

    private void movePlayer(int diceValue) {
        int currentPlayerPosition = playerPositions[currentPlayer - 1];
        int newPosition = currentPlayerPosition + diceValue;

        if (board.containsKey(newPosition)) {
            int newPositionAfterMove = board.get(newPosition);

            if (newPositionAfterMove > newPosition) {
                System.out.println("Player " + currentPlayer + " climbed a ladder!");
            } else {
                System.out.println("Player " + currentPlayer + " encountered a snake!");
            }

            newPosition = newPositionAfterMove;
        }

        if (newPosition <= WINNING_POSITION) {
            playerPositions[currentPlayer - 1] = newPosition;
        }

        if (newPosition == WINNING_POSITION) {
            System.out.println("Player " + currentPlayer + " wins!");
            System.exit(0);
        }

        if (newPosition < 0) {
            System.out.println("Oops! Player " + currentPlayer + " moved below 0. Restarting from 0.");
            playerPositions[currentPlayer - 1] = START_POSITION;
        }
    }

     private boolean isGameOver() {
        return playerPositions[currentPlayer - 1] >= WINNING_POSITION;
    }

   private void ensureExactWinningPosition() {
        int currentPlayerPosition = playerPositions[currentPlayer - 1];
        if (currentPlayerPosition > WINNING_POSITION) {
            System.out.println("Player " + currentPlayer + " moved beyond 100, staying at the previous position.");
            playerPositions[currentPlayer - 1] -= (currentPlayerPosition - WINNING_POSITION);
        }
    }

    //  private void reportDiceRollsAndPositions() {
    //     int diceRollCount = 0;

    //     while (!isGameOver()) {
    //         int diceValue = rollDice();
    //         diceRollCount++;
    //         System.out.println("Player " + currentPlayer + " - Dice Roll #" + diceRollCount + ": " + diceValue);

    //         movePlayer(diceValue);
    //         printPlayerPositions();
    //         switchPlayer();
    //     }

    //     ensureExactWinningPosition();
    //     System.out.println("Player " + currentPlayer + " reached the winning position in " + diceRollCount + " dice rolls.");
    // }

    private boolean isGameWinner() {
        return playerPositions[currentPlayer - 1] == WINNING_POSITION;
    }

    private void determineWinner() {
        int winner = isGameWinner() ? currentPlayer : 3 - currentPlayer;
        System.out.println("Player " + winner + " wins!");
    }

    public void playGameWithTwoPlayers() {
        while (!isGameWinner()) {
            int diceValue = rollDice();
            System.out.println("Player " + currentPlayer + " - Dice Roll: " + diceValue);

            movePlayer(diceValue);
            printPlayerPositions();
            switchPlayer();
        }

        ensureExactWinningPosition();
        determineWinner();
    }

    public static void main(String[] args) {
        SnakeAndLadderGame game = new SnakeAndLadderGame();
        game.printBoard();

        game.playGameWithTwoPlayers();
    }
}
