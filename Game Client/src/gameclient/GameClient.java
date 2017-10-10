/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameclient;

import javax.swing.*;
import java.net.*;

class GameClient {

    private static Socket socket;
    private static ServerThread serv_thread;

    public static void main(String[] args) throws Exception {
        String username = "";
        try {
            while (username.equals("")) {
                username = JOptionPane.showInputDialog("Please enter a username for the chat.");
            }
            socket = new Socket("localhost", 1234);
            serv_thread = new ServerThread(socket, username);
        } catch (Exception e) {
            System.out.println("There was an issue using the current port.");
            System.out.println(e);
            System.exit(-1);
        }
    }

}
