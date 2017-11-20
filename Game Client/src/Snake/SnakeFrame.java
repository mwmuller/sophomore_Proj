/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

/**
 *
 * @author steve
 */
import javax.swing.JFrame;
import Snake.Screen;

import java.awt.GridLayout;

public class SnakeFrame extends JFrame {
    
    public SnakeFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Snake Game");
        setResizable(false);
        
        init();
    }
    
    public void init() {
        setLayout(new GridLayout(1, 1, 0, 0));
        
        Screen s = new Screen();
        add(s);
        
        pack();
        
        setLocationRelativeTo(null);
        setVisible(true);
        
        
    }
    
    public static void main(String[] args) {
        new SnakeFrame();
    }
    
}
