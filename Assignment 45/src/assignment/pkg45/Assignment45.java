/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg45;

import java.util.Random;

/**
 *
 * @author mike
 */
public class Assignment45{

    /**
     * @param args the command line arguments
     */
    static int nums[] = {75, -18, 216, 21, -6, 352, 39, -68, -318, 182};

    public static void main(String[] args) {
        Random rand = new Random();
        rand.setSeed(3350);
        int small = nums[0], large = nums[0];
        double avg = 0;
        System.out.print("Numbers printed from NONRAND: ");
        for (int i = 0; i < nums.length; i++) {
            System.out.printf("%d, ", NONRAND(i));
            if (nums[i] < small) {
                small = nums[i];
            }
            if (nums[i] > large) {
                large = nums[i];
            }
            avg = avg + nums[i];
        }
        avg = avg / nums.length;
        System.out.printf("\nValues for NONRAND:\n"
                + "Smallest: %d\n"
                + "Largest: %d\n"
                + "Average: %.2f\n", small, large, avg);
        small = 999;
        large = -999;
        System.out.println("Number printed for RANDOM:");
        for (int i = 0; i < 10; i++) {
            int rand_num = RANDOM(rand);
            System.out.printf("%d, ", rand_num);
            if (rand_num < small) {
                small = rand_num;
            }
            if (rand_num > large) {
                large = rand_num;
            }
            avg = avg + rand_num;
        }
        avg = avg / 10;
        System.out.printf("\nValues for RANDOM:\n"
                + "Smallest: %d\n"
                + "Largest: %d\n"
                + "Average: %.2f\n", small, large, avg);
    }

    public static int NONRAND(int num) {
        return nums[num];
    }

    public static int RANDOM(Random ran) {
        return (-999 + ran.nextInt(1999));
    }

}
