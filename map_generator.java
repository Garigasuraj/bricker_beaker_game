package bricker_beaker_game;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class map_generator{
        public int map[][];
        public int Brick_width;
        public int Brick_height;
        
        public map_generator(int row, int col){
            map = new int[row][col];
            for(int i=0; i<map.length; i++){
                for(int j=0; j<map[0].length; j++){
                    map[i][j] = 1;
                }
            }
            Brick_width = 540/col;
            Brick_height = 150/row;
        }
        // drawing thr blocks
        public void draw(Graphics2D g){
            for(int i=0; i<map.length; i++){
                for(int j=0; j<map[0].length; j++){
                    if(map[i][j] > 0){
                        // setting the color
                        g.setColor(Color.GRAY);
                        g.fillRect(j * Brick_width + 80, i * Brick_height + 50, Brick_width, Brick_height);

                        g.setStroke(new BasicStroke(3));
                        g.setColor(Color.BLACK);
                        g.drawRect(j * Brick_width + 80, i * Brick_height + 50, Brick_width, Brick_height);
                    }
                }
            }
        }
        public void set_brick_value(int value, int row, int col){
            map[row][col] = value;
        }
    }