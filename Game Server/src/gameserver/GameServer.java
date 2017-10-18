package gameserver;

import java.awt.BorderLayout;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

public class GameServer {

    private static int max = 100;
    private static ClientThread[] Clients_arr = new ClientThread[max];
    private static int connected = 0;
    protected static String[] Inet_addr;
    protected static DatagramPacket rec_pack, send_pack;
    protected static DatagramSocket ssock;
    protected static InetAddress client_ip;
    protected Thread game_thread;
    // GUI Variables for the server
    protected static JPanel west, south, east;
    protected static JTextArea chat_area;
    protected static JButton send_message;
    protected static JScrollPane scroll, scroll_box;
    protected static JFrame Server_GUI = new JFrame();
    protected static JTextArea message_box;
    protected static byte[] buffer = new byte[1500];
    protected static byte[] send_data = new byte[1500];

    public static void main(String[] args) throws Exception {
        String client_nm = "";
        Serv_GUI(400, 500, "Server Testing");
        ssock = new DatagramSocket(387);
        Inet_addr = InetAddress.getLocalHost().toString().split("/");
        System.out.print(Inet_addr[1]);
        //ServerSocket ssock = new ServerSocket(387);
        chat_area.append("Hosting at address: " + Inet_addr[1] + "\n");
        chat_area.append("Listening...\n");
        while (true) {
            if (connected <= max) {
                get_username_packet();
                Clients_arr[connected] = new ClientThread(client_ip, client_nm);
                connected++;
            }
        }
    }

    public static void get_username_packet() throws IOException {
        buffer = new byte[1500];
        int count = 2;
        rec_pack = new DatagramPacket(buffer, buffer.length);
        while (count < 3 ) {
            ssock.receive(rec_pack);
        }
        client_ip = rec_pack.getAddress();
        String connected = new String(rec_pack.getData());
        chat_area.append(connected + " has connected to the chat!\n");
    }

    public static void Serv_GUI(int height, int width, String title) {
        DefaultCaret caret;
        Server_GUI.setSize(width, height);
        Server_GUI.setResizable(false);
        Server_GUI.setLayout(new BorderLayout());
        Server_GUI.setTitle(title);
        Server_GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chat_area = new JTextArea(15, 30);
        chat_area.setEditable(false);
        chat_area.setLineWrap(true);
        caret = (DefaultCaret) chat_area.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scroll = new JScrollPane(chat_area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        message_box = new JTextArea(3, 30);
        message_box.setLineWrap(true);
        caret = (DefaultCaret) chat_area.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scroll_box = new JScrollPane(message_box, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        message_box.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        e.consume();
                        //if()
                        echo_chat(null, "Moderator: " + message_box.getText() + "\n", "m");
                        message_box.setText("");
                    }
                } catch (Exception er) {

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        send_message = new JButton("Send");
        Server_GUI.getRootPane().setDefaultButton(send_message);
        send_message.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    echo_chat(null, "Moderator: " + message_box.getText() + "\n", "m");
                    message_box.setText("");
                } catch (Exception er) {
                    System.out.println(er);
                }
            }
        });
        west = new JPanel();
        south = new JPanel();
        west.add(scroll);
        south.add(send_message);
        south.add(scroll_box);
        Server_GUI.add(west, BorderLayout.WEST);
        Server_GUI.add(south, BorderLayout.SOUTH);
        Server_GUI.setVisible(true);

    }

    public static void echo_chat(ClientThread client, String message, String joined_chat) throws IOException {
        if (joined_chat.equals("c")) {
            chat_area.append(client.get_usernm() + ": " + message);
        } else {
            chat_area.append(message);
        }
        if (joined_chat.equals("m")) {
            for (int i = 0; i < connected; i++) {
                send_data = message.getBytes();
                send_pack = new DatagramPacket(send_data, send_data.length, Clients_arr[i].get_ip(), 387);
                ssock.send(send_pack);
                message_box.setText("");
            }
        } else {
            for (int i = 0; i < connected; i++) {
                if (!client.equals(Clients_arr[i])) {
                    try {
                        if (joined_chat.equals("c")) {
                            send_data = (client.get_usernm() + ": " + message).getBytes();
                            send_pack = new DatagramPacket(send_data, send_data.length, Clients_arr[i].get_ip(), 387);
                            ssock.send(send_pack);
                        } else {
                            send_data = message.getBytes();
                            send_pack = new DatagramPacket(send_data, send_data.length, Clients_arr[i].get_ip(), 387);
                            ssock.send(send_pack);
                        }
                    } catch (Exception e) {
                        //System.out.println(e);
                    }
                }
            }
        }
    }
}
