/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameclient;

import java.io.*;
import java.net.*;
import javax.swing.text.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author mike
 */
public class ServerThread extends JFrame implements Runnable, ActionListener {

    // variables for the thread
    private Thread server_thread;
    private Socket serv_socket;
    private String Username = "";
    private BufferedReader from_server;
    private DataOutput to_server;
    // variables for the GUI
    protected JTextArea game_text, chat_text, chat_message, game_command;
    protected JButton send_command, send_message;
    protected JLabel game_lbl, chat_lbl, spacer_lbl_recieve, spacer_lbl_send;
    protected JPanel Center, South;
    protected JScrollPane scroll_chat, scroll_game, scroll_send_command, scroll_send_message;

    public ServerThread(Socket sock, String user_nm) throws IOException {
        Username = user_nm;
        server_thread = new Thread(this);
        server_thread.start();
        serv_socket = sock;
        ChatGui(900, 320, "Game and Chat Hub");
    }

    public void ChatGui(int width, int height, String title) {
        DefaultCaret caret;
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
        caret = (DefaultCaret) game_text.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scroll_game = new JScrollPane(game_text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chat_text = new JTextArea(15, 33);
        chat_text.append("Welcome to the Chat!");
        chat_text.setEditable(false);
        chat_text.setLineWrap(true);
        caret = (DefaultCaret) chat_text.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scroll_chat = new JScrollPane(chat_text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chat_message = new JTextArea(3, 27);
        caret = (DefaultCaret) chat_message.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scroll_send_message = new JScrollPane(chat_message, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chat_message.setLineWrap(true);
        game_command = new JTextArea(3, 35);
        caret = (DefaultCaret) game_command.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scroll_send_command = new JScrollPane(game_command, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        game_command.setLineWrap(true);
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
        System.out.println(e.getActionCommand());
        if (e.getSource().equals(send_command)) {

        } else if (e.getSource().equals(send_message)) {
            send_message_func();
        }
    }

    public void send_message_func() {
        String message;
        try {
            if (chat_message.getText().equals("END")) {
                serv_socket.close();
            }
            message = chat_message.getText();
            chat_text.append("\n" + message);
            to_server.writeBytes(message + "\n");
            chat_message.setText("");
        } catch (Exception e) {
            System.out.println("Oh no! Connection to the server was lost. Please Reconnect.");
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        String s_mess;
        try {
            to_server = new DataOutputStream(serv_socket.getOutputStream());
            from_server = new BufferedReader(new InputStreamReader(serv_socket.getInputStream()));
            to_server.writeBytes(Username + "\n");
        } catch (Exception ei) {
            System.out.println("Not wroking");
        }

        while (true) {
            try {
                s_mess = from_server.readLine();
                chat_text.append("\n" + s_mess);
                System.out.println(s_mess);
            } catch (Exception e) {
                System.out.println("Oh no! Connection to the server was lost. Please Reconnect.");
                System.out.println(e);
                System.exit(-1);
            }
        }

    }
}
