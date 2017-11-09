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
import java.util.Locale;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
<<<<<<< HEAD
=======

>>>>>>> 42a3cd292c67ab7a619d7da34967af06909dd79f
/**
 *
 * @author mike
 */
public class ServerThread extends JFrame implements Runnable, ActionListener {

    // variables for the thread
    private Thread server_thread;
//    private DatagramSocket serv_socket;
    private DataOutputStream to_server;
    private Socket serv_socket;
    private String Username = "";
    private BufferedReader from_server;
    private int port;
//    protected DatagramPacket rec_pack, send_pack;
//    protected byte[] send_data = new byte[1500];
//    protected byte[] rec_data = new byte[1500];
    protected InetAddress serv_ip;
    // variables for the GUI
    public String default_game = "Please type Start to select a game: \n";
    protected JTextArea game_text, chat_text, chat_message, game_command;
    protected JButton send_command, send_message;
    protected JLabel game_lbl, chat_lbl, spacer_lbl_recieve, spacer_lbl_send;
    protected JPanel Center, South;
    protected JScrollPane scroll_chat, scroll_game, scroll_send_command, scroll_send_message;

    public ServerThread(Socket sock, String user_nm, InetAddress ip_addr, int port) throws IOException {
        Username = user_nm;
        this.port = port;
        serv_ip = ip_addr;
        server_thread = new Thread(this);
        serv_socket = sock;
        server_thread.start();
        ChatGui(900, 320, "Game and Chat Hub");
        game_text.append(default_game);
    }

    public void ChatGui(int width, int height, String title) {
        DefaultCaret caret_chat_wim;
        // create the window and its properties
        this.setSize(width, height);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setTitle("Game and Chat Hub");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // defining the different compnents of the JFrame
        //Text areas and scrolling capability
        game_text = new JTextArea(15, 42);
        game_text.setEditable(false);
        game_text.setLineWrap(true);
        scroll_game = new JScrollPane(game_text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chat_text = new JTextArea(15, 33);
        chat_text.append("Welcome to the Chat!\n");
        chat_text.setEditable(false);
        chat_text.setLineWrap(true);
        caret_chat_wim = (DefaultCaret) chat_text.getCaret();
        caret_chat_wim.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scroll_chat = new JScrollPane(chat_text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chat_message = new JTextArea(3, 27);
        chat_message.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (!chat_message.getText().equals("")) {
                            e.consume();
                            send_message_func();
                        } else {
                            e.consume();
                            chat_message.setText("");
                        }
                    }
                } catch (Exception er) {

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        scroll_send_message = new JScrollPane(chat_message, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chat_message.setLineWrap(true);
        game_command = new JTextArea(3, 35);
        scroll_send_command = new JScrollPane(game_command, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        game_command.setLineWrap(true);
        game_command.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (!game_command.getText().equals("")) {
                            e.consume();
                            send_command_func();
                        } else {
                            e.consume();
                            game_command.setText("");
                        }
                    }
                } catch (Exception er) {

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        // Spacers
        spacer_lbl_recieve = new JLabel("        ");
        spacer_lbl_send = new JLabel("         ");
        // adding buttons
        send_command = new JButton("Send");
        send_message = new JButton("Send");
        send_command.addActionListener(this);
        send_message.addActionListener(this);
        // Jpanels needed
        South = new JPanel();
        Center = new JPanel();
        // adding components to the Jpanels
        // North components
        Center.add(scroll_game);
        Center.add(spacer_lbl_recieve);
        Center.add(scroll_chat);
        // South panel components
        South.add(send_command);
        South.add(scroll_send_command); // Jscrolling for the game command text area
        South.add(spacer_lbl_send);
        South.add(send_message);
        South.add(scroll_send_message); // JScrolling for the chat message window

        // West.add(chat_lbl);
        // adding the panels to the JFrame
        this.add(South, BorderLayout.SOUTH);
        this.add(Center, BorderLayout.CENTER);
        this.setVisible(true);
        chat_message.requestFocus();
        if (chat_message.isFocusOwner()) {
            this.getRootPane().setDefaultButton(send_message);
        } else if (game_command.isFocusOwner()) {
            this.getRootPane().setDefaultButton(send_command);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message;
        if (e.getSource().equals(send_command)) {
            if (!game_command.getText().equals("")) {
                send_command_func();
            } else {
                game_command.setText("");
            }
        } else if (e.getSource().equals(send_message)) {
            if (!chat_message.getText().equals("")) {
                send_message_func();
            } else {
                chat_message.setText("");
            }
        }
    }

    public void send_message_func() {
        String message;
        //send_data = new byte[1500];
        try {
            message = chat_message.getText();
            to_server.writeBytes("c" + message + "\n");
            //  send_data = message.getBytes();
            chat_text.append(message + "\n");
            // send_pack = new DatagramPacket(send_data, send_data.length, serv_ip, port);
            // serv_socket.send(send_pack);
            chat_message.setText("");
        } catch (Exception e) {
            System.out.println("Oh no! Connection to the server was lost. Please Reconnect.");
            System.out.println(e);
        }
    }

    public void send_command_func() {
        String command;
        try {
            if (game_command.getText().toLowerCase().equals("clear")) {
                game_text.setText(default_game);
            } else {
                command = game_command.getText();
                game_text.append(command + "\n");
                to_server.writeBytes("g" + command + "\n");
                game_command.setText("");
            }

        } catch (Exception er) {
            game_text.append(er.toString());
        }
    }

    private void kicked() {
        this.setVisible(false);
        JOptionPane warning = new JOptionPane("You Have been kicked from the server!", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_OPTION);
        JOptionPane.showMessageDialog(warning, "You have been kicked");
        System.exit(-1);
    }

    @Override
    public void run() {
        String s_mess;

        try { // Send your usern ame to the Server to store it 
            from_server = new BufferedReader(new InputStreamReader(serv_socket.getInputStream()));
            to_server = new DataOutputStream((serv_socket.getOutputStream()));
//            send_data = Username.getBytes();
//            send_pack = new DatagramPacket(send_data, send_data.length, serv_ip, port);
//            serv_socket.send(send_pack);
        } catch (Exception ei) {
            System.out.println("Not wroking");
        }

        while (true) {
            try { // Get the messages from the server or from other users
                s_mess = from_server.readLine();

                if (s_mess == null) {
                    kicked();
                }
                if (s_mess.charAt(0) == 'c') {
                    chat_text.append(s_mess.substring(1) + "\n");
                } else if (s_mess.charAt(0) == 'k') {
                    kicked();
                } else {
                    game_text.append(s_mess.substring(1) + "\n");
                }

//                rec_data = new byte[1500];
//                rec_pack = new DatagramPacket(rec_data, rec_data.length);
//                serv_socket.receive(rec_pack);
//                s_mess = new String(rec_pack.getData());
            } catch (Exception e) {
                System.out.println("Oh no! Connection to the server was lost. Please Reconnect.");
                System.out.println(e);
                System.exit(-1);
            }
        }

    }
}
