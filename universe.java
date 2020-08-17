//Hector Felix
//CSC 295-02
//Assignment 2 Problem 4
//20181025

package csc295_02_project1;

import java.util.ArrayList;
import java.util.Scanner;

public class universe {

    Scanner sc = new Scanner(System.in);
    private final ArrayList<Integer> universeSet = new ArrayList<>();

    public universe() {
        universeSet.add(0);
        universeSet.add(1);
        universeSet.add(2);
        universeSet.add(3);
        universeSet.add(4);
        universeSet.add(5);
        universeSet.add(6);
        universeSet.add(7);
        universeSet.add(8);
        universeSet.add(9);
    }

    public boolean checkDup(ArrayList<Integer> arrayList, int num) {
        for (Integer testNumber : arrayList) {
            if (testNumber == num) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> union(ArrayList<Integer> setA, ArrayList<Integer> setB) {
        ArrayList<Integer> union = new ArrayList<>();

        for (Integer num1 : setA) {
            union.add(num1);
        }

        for (Integer num2 : setB) {
            if (checkDup(union, num2) == false) {
                union.add(num2);
            }
        }
        return union;
    }

    public ArrayList<Integer> intersection(ArrayList<Integer> setA, ArrayList<Integer> setB) {
        ArrayList<Integer> intersection = new ArrayList<>();

       
        for (Integer num1 : setA) {
            for (Integer num2 : setB) { //nested loops to check every entry for intersection
                //if (num1.equals(num2)) {
                if (num1 == num2) {
                    intersection.add(num2);
                }
            }
        }
        return intersection;
    }

    public ArrayList<Integer> complement(ArrayList<Integer> setA) { //adding all numbers to universeSet, then removing those in our ArrayList
        ArrayList<Integer> complement = new ArrayList<>();

        for (Integer num1 : universeSet) {
            complement.add(num1);
        }

        for (Integer num1 : setA) {
            complement.remove(num1);
        }
        return complement;
    }

    public void printList(ArrayList<Integer> arrayList) {
        String output = "{";
        for (Integer num : arrayList) {
            output += num + ",";
        }
        System.out.println(removeLastChar(output) + "}");
    }

    private static String removeLastChar(String str) { //method to remove last character in Strings, using to remove ',' from output strings
        return str.substring(0, str.length() - 1);
    }

    public void printProduction(ArrayList<Integer> setA, ArrayList<Integer> setB) {
        String output = "{ ";
        for (Integer num1 : setA) {
            for (Integer num2 : setB) {
                output += "(" + num1 + "," + num2 + ")";
            }
        }
        System.out.println(output + " }");
    }

    public ArrayList<Integer> input() {
        ArrayList<Integer> input = new ArrayList<>();

        System.out.println("Please Enter a Unique Integer [0,9] and enter 99 when finished:");
        
        while (true) {
            int num = sc.nextInt();

            if (num == 99) {
                break;
            }

            if (checkDup(input, num)) {
                System.out.println("Element Already Exists!!!");
            } else if (num > 9 || num < 0) {
                System.out.println("Error: Input must be between [0,9]");
            } else {
                input.add(num);
            }
        }
        return input;
    }

    public static void main(String[] args) {

        universe operation = new universe();

        ArrayList<Integer> set1 = operation.input(); //print set A
        while (set1.isEmpty()) {
            set1 = operation.input();
        }

        System.out.print("Set A: ");
        operation.printList(set1);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        ArrayList<Integer> set2 = operation.input(); //Print set B
        while (set2.isEmpty()) {
            set2 = operation.input();
        }

        System.out.print("Set B: ");
        operation.printList(set2);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.print("Union Set of A and B: "); //Union Set
        operation.printList(operation.union(set1, set2));

        System.out.print("Set B's Complement (universal set is {0,1,2...8,9} ): "); //B's Complement Set
        operation.printList(operation.complement(set2));

        System.out.print("Intersection of Set of A and B: "); //Intersection Set
        ArrayList<Integer> intersection = operation.intersection(set1, set2);
        if (intersection.isEmpty()) {
            System.out.println("No Intersections!");
        } else {
            operation.printList((operation.intersection(set1, set2)));
        }

        System.out.print("Production of Union Set A and Set B: ");
        if (intersection.isEmpty()) {
            System.out.println("No Intersections!");
        } else {
            operation.printProduction(operation.intersection(set1, set2), set1);
        }
    }
}
