/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holycodegame;

import gameserver.GameThread;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Joe
 */
public class HolyCode {

    static Scanner scanner = new Scanner(System.in);
    static String name;
    static String[] outcomes = {
        "You pull the Lever,and the floor opens up from underneath your feet, and you fall to your death ",
        "You died.....alone in a cave",
        "You put the key in the keyhole, and the door opens. infront of you is a long corridor that leads to freedom!"
    };
    static String[] story = {"You wake up in a dark room, and you can barely see. \nYou can 'look around', or 'call out for help' \n"
        + "What will you do?",
        "You look around, and see a see a pile of hay with something shiny in it, and you see a lever on the wall next to you. \nYou may 'search the pile' or 'pull the lever'\n",
        "Inside the pile, and you find a key would you like to put it in the keyhole? \n'yes' or 'no'",
        "You can 'pull the lever' or 'die of boredom' ",
        "You call out for help, and a man screams from the room next to you. He says 'you will never leave. I have been here for years!!'\nYou may 'look around' or 'die of boredom'",
        "You look around, and see a see a pile of hay with something shiny in it, and you see a lever on the wall next to you. \nYou may 'search the pile' or 'pull the lever'",
        "Inside the pile, and you find a key would you like to put it in the keyhole? \n'yes' or 'no'",
        "You can 'pull the lever' or 'die of boredom' "
    };
    static String[] options = {"look around", "call out for help", "search the pile", "pull the lever", "yes", "no", "pull the lever", "die of boredom",
        "look around", "die of boredom", "pull the lever", "search the pile", "yes", "no", "pull the lever", "die of boredom"

    };

    public HolyCode(GameThread game_thread) throws IOException {
        String input;
        System.out.println("          Welcome to THE QUEST FOR THE HOLY CODE    ");
        System.out.println("Enter your name: ");
        System.out.print(">");
        String name = scanner.nextLine();

        System.out.println("Welcome " + name + " lets start our journey together");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        int count = 0;

        while (true) {

            System.out.println(story[count]);
            System.out.print("> ");
            input = scanner.nextLine();
            if (input.equals(options[2 * count])) {
                if (input.equals("yes")) {
                    System.out.println(outcomes[2]);
                    break;
                } else if (input.equals("pull the lever")) {
                    System.out.println(outcomes[0]);
                    break;
                } else if (input.equals("die of boredom")) {
                    System.out.println(outcomes[1]);
                    break;
                }
                count++;

            } else if (input.equals(options[2 * count + 1])) {
                if (input.equals("call out for help")) {
                    count = 4;
                } else if (input.equals("yes")) {
                    System.out.println(outcomes[2]);
                    break;
                } else if (input.equals("pull the lever")) {
                    System.out.println(outcomes[0]);
                    break;
                } else if (input.equals("die of boredom")) {
                    System.out.println(outcomes[1]);
                    break;
                } else {

                    count++;
                }

            } else {
                System.out.println("please make a valid selection");
            }

        }
    }
}
