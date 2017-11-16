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
public class GameThread implements Runnable{
    private Socket Cli_socket;
    public GameThread(Socket cli_sock){
        Cli_socket = cli_sock;
        Thread game_thread = new Thread(this);
        game_thread.start();
    }
    
    public void run(){ // will run the game and call input 
        
    }
}
