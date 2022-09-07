package bricker_beaker_game;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
    private boolean play = false;
    private int score = 0;

    private int totalBricks = 30;

    private Timer timer;
    private int delay = 3;

    private int player_x = 310;

    // ball position
    private int ball_position_x = 120;
    private int ball_position_y = 350;

    private int ball_direction_x = -1;
    private int ball_direction_y = -2;

    private map_generator map;

    public Gameplay(){
        map = new map_generator(3, 10);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        timer = new Timer(delay, this);
        timer.start();
    }

    // graphics of game
    public void paint(Graphics g){
        // adding backgroung colour
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 642);

        // drawing thr blocks
        map.draw((Graphics2D)g);

        // borders
        g.setColor(Color.PINK);
        g.fillRect(0, 0, 3, 642);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 642);

        // for paddle
        g.setColor(Color.RED);
        g.fillRect(player_x, 610, 100, 8);

        // for score

        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("SCORE: "+score, 550, 30);

        // for ball

        g.setColor(Color.ORANGE);
        g.fillOval(ball_position_x, ball_position_y, 20, 20);

        // when game gets over

        if(totalBricks == 0){
            play = false;
            ball_direction_x =0;
            ball_direction_y = 0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 35));
            g.drawString("KEDO, YOU WIN! ", 190, 350);

            g.setColor(Color.BLUE);
            g.setFont(new Font("serif1", Font.BOLD, 25));
            g.drawString("Press Enter To Restart Game", 160, 400);
            g.dispose();
        }

        //
        if(ball_position_y > 625){
            play = false;
            ball_direction_x =0;
            ball_direction_y = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 35));
            g.drawString("Game Over ", 230, 350);

            g.setFont(new Font("serif1", Font.BOLD, 25));
            g.drawString("Press Enter To Restart Game", 150, 400);

            g.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(play){
            //creating an intersection point
            if(new Rectangle(ball_position_x, ball_position_y, 20, 20).intersects(new Rectangle(player_x, 610, 100, 8))){
                ball_direction_y = - ball_direction_y;
            }
            //looping on everybrick
            A : for(int i=0; i<map.map.length; i++){
                for(int j=0; j<map.map[0].length; j++){
                    if(map.map[i][j] > 0){
                      //for intersection
                        int brick_x = j * map.Brick_width + 80;
                        int brick_y = i * map.Brick_height +50;
                        int Brick_width = map.Brick_width;
                        int Brick_height = map.Brick_height;

                        Rectangle rect = new Rectangle(brick_x, brick_y, Brick_width, Brick_height);
                        Rectangle ball_rect = new Rectangle(ball_position_x, ball_position_y, 20, 20);
                        Rectangle brick_rect = rect;

                        //checking for intersection
                        if(ball_rect.intersects(brick_rect)){
                            map.set_brick_value(0, i, j);
                            totalBricks--;
                            score += 5;

                            if(ball_position_x + 19 <= brick_rect.x || ball_position_x + 1 >= brick_rect.x
                            + brick_rect.width){
                                ball_direction_x = -ball_direction_x;
                            }
                            else{
                                ball_direction_y = -ball_direction_y;
                            }
                            break A;
                        }
                    }
                }
            }

            ball_position_x += ball_direction_x;
            ball_position_y  += ball_direction_y;

            if(ball_position_x < 0){
                ball_direction_x = -ball_direction_x;
            }
            if(ball_position_y < 0){
                ball_direction_y = -ball_direction_y;
            }
            if(ball_position_x > 670){
                ball_direction_x = -ball_direction_x;
            }
        }

        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(player_x >= 587){
                player_x = 587;
            }
            else{
                move_right();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(player_x <= 10){
                player_x = 10;
            }
            else{
                move_left();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = false;
                ball_direction_x = -1;
                ball_direction_y = -2;
                ball_position_x = 120;
                ball_position_y = 350;
                score = 0;
                player_x = 310;
                totalBricks = 21;
                map = new map_generator(3, 10);

                repaint();

            }
        }
    }
    // creating method for right and left
    public void move_left(){
        play = true;
        player_x -= 20;
    }
    public void move_right(){
        play = true;
        player_x += 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
