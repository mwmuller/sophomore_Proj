/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameclient;

import java.io.*;
import java.net.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author mike
 */
public class ServerThread extends JFrame implements Runnable {

    // variables for the thread
    private Thread server_thread;
    private Socket serv_socket;
    private String Username = "";
    private BufferedReader in, from_server;
    private DataOutput to_server;
    // variables for the GUI
    protected JTextArea game_text, chat_text;
    protected JTextField game_command, chat_message;
    protected JButton send_command, send_message;
    protected JLabel game_lbl, chat_lbl;
    protected JPanel West, South, North, East;

    public ServerThread(Socket sock, String user_nm) {
        server_thread = new Thread(this);
        serv_socket = sock;
        Messages();
    }

    public ServerThread(int width, int height, String title) {
        // create the window and its properties
        this.setSize(width, height);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setTitle("Game and Chat Hub");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // defining the different compnents of the JFrame
        game_text = new JTextArea(15, 40);
        game_text.setEditable(false);
        chat_text = new JTextArea(15, 30);
        chat_text.setEditable(false);
        chat_lbl = new JLabel("I AM HERE");
        // Jpanels needed
        West = new JPanel();
        South = new JPanel();
        North = new JPanel();
        East = new JPanel();
        // adding components to the Jpanels
        West.add(game_text);
        East.add(chat_text);

        // West.add(chat_lbl);
        // adding the panels to the JFrame
        this.add(West, BorderLayout.WEST);
        this.add(East, BorderLayout.EAST);
        this.setVisible(true);
    }

    public void Messages() {
        String message;
        try {
            to_server = new DataOutputStream(serv_socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(System.in));

            while (Username.equals("")) {
                System.out.println("Please enter a username for the chat: ");
                Username = in.readLine();
            }
            to_server.writeBytes(Username + "\n");
            server_thread.start();
            System.out.println("Connected!");
        } catch (Exception e) {
            // possible issue with socket
            System.out.println(e);
        }
        System.out.println("Welcome to the Chat!");
        while (true) {
            try {
                message = in.readLine();
                if (message.equals("END")) {
                    serv_socket.close();
                    break;
                }
                to_server.writeBytes(message + "\n");
            } catch (Exception e) {
                System.out.println("Oh no! Connection to the server was lost. Please Reconnect.");
                System.out.println(e);
                System.exit(-1);
            }
        }
    }

    public void run() {
        String s_mess;
        try {
            from_server = new BufferedReader(new InputStreamReader(serv_socket.getInputStream()));
        } catch (Exception ei) {

        }

        while (true) {
            try {
                s_mess = from_server.readLine();
                System.out.println(s_mess);
            } catch (Exception e) {
                System.out.println("Oh no! Connection to the server was lost. Please Reconnect.");
                System.out.println(e);
                System.exit(-1);
            }
        }

    }
}
