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
import javax.swing.*;

/**
 *
 * @author Michael Muller
 */
public class ClientThread extends JFrame implements Runnable {

    private Socket Cli_socket;
    private String Username;
    private Thread client_thread;
    private GameServer game_serv = new GameServer();

    ClientThread(Socket sock, String user_nm) { // populated
        this.Cli_socket = sock;
        this.Username = user_nm;
        client_thread = new Thread(this);
        client_thread.start();
    }

    public Socket get_sock() {
        return Cli_socket;
    }

    public String get_usernm() {
        return Username;
    }

    public void run() {
        String c_mess, s_mess;
        try {
            DataOutputStream to_client = new DataOutputStream(Cli_socket.getOutputStream());
            BufferedReader from_client = new BufferedReader(new InputStreamReader(Cli_socket.getInputStream()));
            Username = from_client.readLine();
            System.out.println(Username + " has connected!");
            game_serv.echo_chat(this, Username + " has joined the Chat\n", "j");
            while (true) { // handles the constant chat until they disconnect
                try {
                    c_mess = from_client.readLine();
                    System.out.println(Username + ": " + c_mess);
                    s_mess = c_mess + "\n";
                    game_serv.echo_chat(this, s_mess, "c");
                } catch (Exception e) {
                    System.out.println(Username + " Disconnected");
                    System.out.println(e);
                    break;
                }
            }
        } catch (Exception e) {

        }
    }

}
