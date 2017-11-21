/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.event.*;

/**
 *
 * @author Michael Muller
 */
public class GameThread implements Runnable {

    private Socket Cli_socket;
    private DataOutputStream to_client;
    private Object game_class;
    private String current_game = "", from_client = "_";
    private boolean game_selected = false;
    private String game_state = "select";
    public String games = "Please make a game Selection: \n"
            + "(1) Snake Game\n"
            + "(2) Quest for the Holy Code\n"
            + "(3) Sticks Arena\n"
            + "(4) Card Game\n"
            + "(5) Guess Game\n"
            + "(6) End\n";

    public GameThread(Socket cli_sock, String game) throws IOException {
        Cli_socket = cli_sock;
        to_client = new DataOutputStream(Cli_socket.getOutputStream());
        Thread game_thread = new Thread(this);
        game_thread.start();
        to_client.writeBytes("g" + games);
    }

    public void select_game(int game_choice) throws IOException {
        switch (game_choice) {
            case 1:
                send_game_message("play_snake\n");
                current_game = "snake";
                game_state = "playing";
                break;
            case 2:
                //go to quest for holy code
                break;
            case 3:
                //go to battlesticks
                break;
            case 4:
                // go to the card game
                break;
            case 5:
                game_class = new Guessgame(this);
                System.out.println("Games");
                current_game = "guess";
                game_state = "playing";
                break;
        }
    }

    public String get_from_client() {
        return from_client;
    }

    public void send_game_message(String msg) throws IOException {
        to_client.writeBytes("g" + msg);
    }

    public void in_message(String msg) {
        from_client = msg;
    }

    public void set_state(String msg) {
        game_state = msg;
    }

    public void run() { // will run the game and call input 
        try {
            while (true) {
                if (from_client.equals("_") || game_state.equals("playing")) {
                    Thread.sleep(1000);
                } else if (game_state.equals("select")) {
                    boolean valid = false;
                    while (!valid) {
                        try {
                            select_game(Integer.parseInt(from_client));
                            from_client = "_";
                            valid = true;
                        } catch (Exception e) {
                            to_client.writeBytes("gInvalid Selection or Input!\n" + games);
                        }
                    }
                } else {

                }
            }
        } catch (Exception e) {

        }
    }
}