/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameclient;

import java.net.*;

class GameClient {

    private static Socket socket;
    private static ServerThread serv_thread;

    public static void main(String[] args) throws Exception {

        try {
            socket = new Socket("localhost", 1234);
            serv_thread = new ServerThread(socket, "");
        } catch (Exception e) {
            System.out.println("There was an issue using the current port.");
            System.out.println(e);
            System.exit(-1);
        }
    }

}
