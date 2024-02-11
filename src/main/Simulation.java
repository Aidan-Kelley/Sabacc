package main;
public class Simulation {
    public Simulation() {
        Game game = new Game(4);
        int winner = game.runGame();
        System.out.println(game);
        System.out.println(winner + 1);
    }
}
