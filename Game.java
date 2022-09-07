package bricker_beaker_game;
import javax.swing.JFrame;

public class Game {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Gameplay game  = new Gameplay();

        obj.setBounds(10,10,709,660);
        obj.setTitle("Brick Breaker Game");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(game);
    }
}
