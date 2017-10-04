/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gc1;

import java.net.*;

class Gc1 {

    private static Socket socket;
    private static ServerThread serv_thread;

    public static void main(String[] args) throws Exception {

        try {
            socket = new Socket("localhost", 1234);
            serv_thread = new ServerThread(socket, "");
        } catch (Exception e) {
            System.out.println("There was an issue using the current port.");
            System.exit(-1);
        }
    }

}
