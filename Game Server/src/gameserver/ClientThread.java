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

    // private DatagramSocket Cli_socket;
    private Socket Cli_socket;
    protected BufferedReader from_client;
    // private DatagramPacket rec_pack;
    protected InetAddress client_ip;
    private String Username;
    private int place;
    private Thread client_thread;
    private GameServer game_serv = new GameServer();

    ClientThread(Socket socket, String user_nm, int index) { // populated
        Cli_socket = socket;
        place = index;
        Username = user_nm;
        client_thread = new Thread(this);
        client_thread.start();
    }

    public Socket get_socket() {
        return Cli_socket;
    }

    public InetAddress get_ip() {
        return client_ip;
    }

    public String get_usernm() {
        return Username;
    }

    public int get_place() {
        return place;
    }

    public void set_place(int index) {
        place = index;
    }

    public void set_usernm(String usernm) {
        Username = usernm;
    }

    @Override
    public void run() {
        String c_mess, s_mess;
        try { // Gets messages from the clients

//            rec_pack = new DatagramPacket(rec_data, rec_data.length);
//            Cli_socket.receive(rec_pack);
//            Username = new String(rec_pack.getData());
            from_client = new BufferedReader(new InputStreamReader(Cli_socket.getInputStream()));
            // reads in the username if they join the chat
            Username = from_client.readLine();
            game_serv.check_nm(this);
            game_serv.echo_chat(this, Username + " has joined the Chat", "j");
            game_serv.update_clients_box('a', this);
            while (true) { // handles the constant chat until they disconnect
                try {
//                    rec_data = new byte[1500];
//                    rec_pack = new DatagramPacket(rec_data, rec_data.length);
//                    Cli_socket.receive(rec_pack);
                    c_mess = from_client.readLine();
                    // c_mess = new String(rec_pack.getData());
                    if (c_mess.charAt(0) == 'g') {
                        game_serv.game_chat(this, c_mess.substring(1));
                    } else {
                        s_mess = c_mess.substring(1);
                        //  s_mess = s_mess + "\n";
                        game_serv.echo_chat(this, s_mess, "c");
                    }
                } catch (Exception e) {
                    game_serv.echo_chat(this, Username + " has disconnected.", "j");
                    game_serv.update_clients_box('r', this);
                    game_serv.remove_client(place);
                    System.out.println(e);
                    break;
                }
            }
        } catch (Exception e) {

        }
    }

}
