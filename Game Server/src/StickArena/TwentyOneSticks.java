/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StickArena;

import java.util.Scanner;
import gameserver.*;

/**
 *
 * @author ryman
 */
public class TwentyOneSticks {

    /**
     * @param args the command line arguments
     */
    public GameThread game_thread;

    public TwentyOneSticks(GameThread game) {
        game_thread = game;
        int numSticks = 21;
        System.out.println("Would you like to be first? (y/n)");
        Scanner input = new Scanner(System.in);
        String goFirst = input.nextLine();
        Scanner take = new Scanner(System.in);
        int numToTake = 0;

        while (numSticks > 0) {
            if (goFirst.equals("y") || goFirst.equals("Y")) {
                System.out.println("There are " + numSticks + " stick(s).");
                System.out.println("How many sticks do you want to take? (1 or 2)");
                numToTake = take.nextInt();

                if (numToTake > 2) {
                    numToTake = 2;
                } else if (numToTake < 1) {
                    numToTake = 1;
                }
                numSticks = numSticks - numToTake;

                if (numSticks <= 0) {
                    System.out.println("You Loose!! :( Thanks For Playing!");
                } else {

                    if ((numSticks - 2) % 3 == 0 || numSticks - 2 == 0) {
                        numToTake = 1;
                    } else {
                        numToTake = 2;
                    }
                    System.out.println("Computer takes " + numToTake + " stick(s).");
                    numSticks = numSticks - numToTake;

                    if (numSticks <= 0) {
                        System.out.println("You Win!! Thanks For Playing!");
                    }
                }
            } else {

                if ((numSticks - 2) % 3 == 0 || numSticks - 2 == 0) {
                    numToTake = 1;
                } else {
                    numToTake = 2;
                }
                System.out.println("Computer takes " + numToTake + " stick(s).");
                numSticks = numSticks - numToTake;

                if (numSticks <= 0) {
                    System.out.println("You Win!! Thanks For Playing!");
                } else {
                    System.out.println("There are " + numSticks + " stick(s).");
                    System.out.println("How many sticks do you want to take? (1 or 2)");
                    numToTake = take.nextInt();

                    if (numToTake > 2) {
                        numToTake = 2;
                    } else if (numToTake < 1) {
                        numToTake = 1;
                    }
                    numSticks = numSticks - numToTake;

                    if (numSticks <= 0) {
                        System.out.println("You Lose!! :( Thanks For Playing!");
                    }
                }
            }
        }

    }

}
