/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver;

/**
 *
 * @author mike
 */
import java.io.*;
import java.util.*;

public class Guessgame {

    private int rand_num;
    private Random rand;
    private GameThread game_thread;

    public Guessgame(GameThread game_thread) throws IOException {
        this.game_thread = game_thread;
        rand = new Random();
        start();
    }

    public void start() throws IOException {
        int count = 1, guess;
        boolean valid = false;
        game_thread.send_game_message("Welcome to the Guessing Game!\n"
                + "gYou will be guessing a number between 1 and 100.\n"
                + "gYou want the lowest score possible.\n"
                + "gNumber being generated.");
        try{
        Thread.sleep(1500);
        }catch(Exception er){
            
        }
                game_thread.send_game_message("gA Number has been generated so start guessing!\n"
                + "gGuess " + count + ": ");
        while (!valid) {
            try {
                guess = Integer.parseInt(game_thread.get_from_client());
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (Exception er) {

                }
            }
        }
    }

}
