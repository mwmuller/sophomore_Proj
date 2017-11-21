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
                + "You will be guessing a number between 1 and 100.\n"
                + "You want the lowest score possible."
                + "A Number has been picked so start guessing!\n\n"
                + "Guess " + count + ": ");
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
