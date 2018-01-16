/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.Random;
import java.util.*;

/**
 *
 * @author Michael Muller
 */
public class Project1 {

    public static void main(String[] args) {
        int[] N_size = {1000, 5000, 10000, 20000, 50000, 100000};
        int[] rand_nums_arr;
        Scanner in = new Scanner(System.in);
        int selection = 0;
        System.out.println("Which Program would you like to run?\n"
                + "(1) Slow Sub Array\n"
                + "(2) Fast Sub Array\n"
                + "(3) Fastest Sub Array\n"
                + "(4) End\n"
                + "Selection: ");
        selection = Integer.parseInt(in.nextLine());
        for (int i = 0; i < N_size.length; i++) {
            rand_nums_arr = fillArray(N_size[1]);
            switch (selection) {
                case 1:
                    
                    System.out.println(MaxsubSlow(rand_nums_arr));
                    break;
                case 2:
                    MaxsubFaster(rand_nums_arr);
                    break;
                case 3:
                    MaxsubFastest(rand_nums_arr);
                    break;
                default:
                    // nothing
                    break;
            }
        }
    }

    private static int[] fillArray(int n) {
        int[] temp_arr = new int[n];
        Random rand = new Random();
        rand.setSeed(3630);
        for (int i = 0; i < n; i++) {
            temp_arr[i] = (int) (-25 + Math.random() * (25 + 25));
        }
        return temp_arr;
    }

    private static int MaxsubSlow(int[] A) {
        int max = 0;
        int s;
        for(int j = 0; j < A.length; j++){
            for(int k = j; k < A.length; k++){
                s = 0;
                for(int i = j; i < k; i++){
                    s = s + A[i];
                    if(s > max){
                        max = s;
                    }
                }
            }
        }
        return max;
    }

    private static int MaxsubFaster(int[] A) {
        int[] pre_sum = new int[A.length];
        for(int i = 0; i < A.length; i++){
            
        }
        return 0;
    }

    private static int MaxsubFastest(int[] A) {

        return 0;
    }
}
