public class Game {
    private static void printPlayerStats(Player player) {
        System.out.println(player.getName() + " has: ");
        player.getTokenList().forEach(token -> System.out.print(token.getValue() + " "));
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        Board board = new Board(15, 20);
        board.generateTokenList();

        Player player1 = new Player("Player1", board);
        Player player2 = new Player("Player2", board);

        Thread thread1 = new Thread(player1);
        Thread thread2 = new Thread(player2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        printPlayerStats(player1);
        printPlayerStats(player2);
    }
}

