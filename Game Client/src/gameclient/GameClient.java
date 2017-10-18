/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameclient;

import javax.swing.*;
import java.net.*;

class GameClient {

    private static DatagramSocket client_socket, test_sock;
    private static ServerThread serv_thread;
    private static String ip = "";
    static DatagramPacket usernm_send;
    static byte[] buf = new byte[1024];
    static byte[] send = new byte[1024];
    protected static InetAddress serv_inet;

    public static void main(String[] args) throws Exception {
        String username = "";
        int port = 387;
        try {
            while (username.equals("") || username.length() < 3) {
                username = JOptionPane.showInputDialog("Please enter a username for the chat.");
            }
            while (ip.equals("")) {
                ip = JOptionPane.showInputDialog("Please enter the IP Address of the Server you wish to connect to.");
                try {
                    serv_inet = InetAddress.getByName(ip);
                    send = (username).getBytes();
                    usernm_send = new DatagramPacket(send, send.length, serv_inet, port);

                } catch (Exception e) {

                }
            }
            InetAddress my_addr = InetAddress.getLocalHost();
            client_socket = new DatagramSocket(377);
            client_socket.send(usernm_send);
            client_socket.connect(serv_inet, port);
            serv_thread = new ServerThread(client_socket, username, serv_inet, port);
        } catch (Exception e) {
            System.out.println("There was an issue using the current port.");
            System.out.println(e);
            System.exit(-1);
        }
    }

}
