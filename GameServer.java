package gameserver;

import java.awt.BorderLayout;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;

public class GameServer {

    private static int max = 100;
    private static ClientThread[] Clients_arr = new ClientThread[max];
    private static int connected = 0;
    // GUI Variables for the server
    protected static JPanel north;
    protected static JTextArea chat_area;
    protected static JFrame Server_GUI = new JFrame();
    public static void main(String[] args) throws Exception {
        String client_nm = "";
        ServerSocket ssock = new ServerSocket(1234);
        System.out.println("Listening...");
        Serv_GUI(400, 500, "Server Testing");
        while (true) {
            if (connected <= max) {
                Socket Cli_sock = ssock.accept();
                Clients_arr[connected] = new ClientThread(Cli_sock, client_nm);
                connected++;
            }
        }
    }
     public static void Serv_GUI(int height, int width, String title) {
        Server_GUI.setSize(width, height);
        Server_GUI.setResizable(false);
       Server_GUI.setLayout(new BorderLayout());
       Server_GUI.setTitle(title);
       Server_GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       chat_area = new JTextArea(15, 30);
       chat_area.setEditable(false);
       north = new JPanel();
       north.add(chat_area);
       Server_GUI.add(north, BorderLayout.NORTH);
       Server_GUI.setVisible(true);
              
    }

    public static void echo_chat(ClientThread client, String message, String joined_chat) throws IOException {
       chat_area.append(client.get_usernm() + ": " + message);
        for (int i = 0; i < connected; i++) {
            if (!client.equals(Clients_arr[i])) {
                try {
                    DataOutputStream to_client = new DataOutputStream(Clients_arr[i].get_sock().getOutputStream());
                    if(joined_chat.equals("c")){
                    to_client.writeBytes(client.get_usernm() + ": " + message);
                    }else{
                       to_client.writeBytes(message); 
                    }
                } catch (Exception e) {
                    //System.out.println(e);
                }
            }
        }
    }

}
