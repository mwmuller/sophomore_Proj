/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.Random;
import java.util.*;
import java.io.*;

/**
 *
 * @author Michael Muller
 */
public class Project1 {

    public static void main(String[] args) throws IOException{
        long start, end, time;
        int[] N_size = {1000, 5000, 10000, 20000, 50000, 100000, 1000000, 10000000};
        int[] rand_nums_arr;
        FileWriter file = new FileWriter("Times.txt", true);
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
            rand_nums_arr = fillArray(N_size[i]);
            switch (selection) {
                case 1:
                    start = System.currentTimeMillis();
                    System.out.println(MaxsubSlow(rand_nums_arr));
                    end = System.currentTimeMillis();
                    time = end - start;
                    if( i == 0){
                        file.write("Times for Slow Algorithm:\r\n");
                    }
                    file.write("N = " + N_size[i] + "\r\nComputation Time: " + time + " ms\r\n");
                    break;
                case 2:
                    start = System.currentTimeMillis();
                    System.out.println(MaxsubFaster(rand_nums_arr));
                    end = System.currentTimeMillis();
                    time = end - start;
                    if( i == 0){
                        file.write("Times for Slow Algorithm:\r\n");
                    }
                    file.write("N = " + N_size[i] + "\r\nComputation Time: " + time + " ms\r\n");
                    break;
                case 3:
                    start = System.currentTimeMillis();
                    System.out.println(MaxsubFastest(rand_nums_arr));
                    end = System.currentTimeMillis();
                    time = end - start;
                    if( i == 0){
                        file.write("Times for Fastest Algorithm:\r\n");
                    }
                    file.write("N = " + N_size[i] + "\r\nComputation Time: " + time + " ms\r\n");
                    
                    break;
                default:
                    // nothing
                    break;
            }
        }
        file.close();
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
        for (int j = 0; j < A.length; j++) {
            for (int k = j; k < A.length; k++) {
                s = 0;
                for (int i = j; i < k; i++) {
                    s = s + A[i];
                    if (s > max) {
                        max = s;
                    }
                }
            }
        }
        return max;
    }

    private static int MaxsubFaster(int[] A) {
        int n = A.length;
        int[] S = new int[n];
        int s;
        int m;
        S[0] = 0;
        for (int i = 1; i < n; i++) {
            S[i] = S[i - 1] + A[i];
        }
        m = 0;
        for (int j = 1; j < n; j++) {
            for (int k = j; k < n; k++) {
                s = S[k] - S[j - 1];
                if (s > m) {
                    m = s;
                }
            }
        }
        return m;
    }

    private static int MaxsubFastest(int[] A) {
        int n = A.length;
        int[] M = new int[n];
        int m;
        M[0] = 0;
        for(int t = 1; t < n; t++){
            M[t] = Integer.max(0, M[t-1] + A[t]);
        }
        m = 0;
        for(int t = 1; t < n; t++){
            m = Integer.max(m, M[t]);
        }
        return m;
    }
}
