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

    private DatagramSocket Cli_socket;
    private DatagramPacket rec_pack;
    protected InetAddress client_ip;
    protected byte[] rec_data = new byte[1500];
    private String Username;
    private Thread client_thread;
    private GameServer game_serv = new GameServer();

    ClientThread(InetAddress ip, String user_nm) { // populated
        client_ip = ip;
        Username = user_nm;
        client_thread = new Thread(this);
        client_thread.start();
    }

    public InetAddress get_ip() {
        return client_ip;
    }

    public String get_usernm() {
        return Username;
    }
    @Override
    public void run() {
        String c_mess, s_mess;
        try {
            rec_pack = new DatagramPacket(rec_data, rec_data.length);
            Cli_socket.receive(rec_pack);
            Username = new String(rec_pack.getData());
            System.out.println(Username + " has connected!");
            game_serv.echo_chat(this, Username + " has joined the Chat\n", "j");
            while (true) { // handles the constant chat until they disconnect
                try {
                    rec_data = new byte[1500];
                    rec_pack = new DatagramPacket(rec_data, rec_data.length);
                    Cli_socket.receive(rec_pack);
                    c_mess = new String(rec_pack.getData());
                    System.out.println(Username + ": " + c_mess);
                    s_mess = c_mess + "\n";
                    game_serv.echo_chat(this, s_mess, "c");
                } catch (Exception e) {
                    System.out.println(Username + " Disconnected");
                    game_serv.echo_chat(this, Username + " has disconnected.\n", "j");
                    System.out.println(e);
                    break;
                }
            }
        } catch (Exception e) {

        }
    }

}
