package gameserver;

import java.io.*;
import java.net.*;

public class GameServer {

    private static int max = 100;
    private static ClientThread[] Clients_arr = new ClientThread[max];
    private static int connected = 0;

    public static void main(String[] args) throws Exception {
        String client_nm = "";
        ServerSocket ssock = new ServerSocket(1234);
        System.out.println("Listening...");
        while (true) {
            if (connected <= max) {
                Socket Cli_sock = ssock.accept();
                Clients_arr[connected] = new ClientThread(Cli_sock, client_nm);
                connected++;
            }
        }
    }

    public static void echo_chat(ClientThread client, String message, String joined_chat) throws IOException {
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
