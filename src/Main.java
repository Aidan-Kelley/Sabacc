public class Main {

    public static void main(String[] args) {
        Game game = new Game(5);
        int winner = game.runGame();
        System.out.println(game);
        System.out.println(winner + 1);

        // Tester t = new Tester(10);
    }

}