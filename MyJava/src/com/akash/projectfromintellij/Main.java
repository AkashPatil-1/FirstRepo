package com.akash.projectfromintellij;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Your Name: ");
        String name = scanner.next();
        System.out.println("Enter Your Age: ");
        int age = scanner.nextInt();
        if(age>=18) {
            System.out.println("Hi " + name + " you are " + age + " years old and you are eligible for voting.");
        }
        else {
            System.out.println("Hi " + name + " you are " + age + " years old but not 18 so you are not eligible.");
        }

        System.out.println("Are you apply for voter id?");
        boolean isApplied = scanner.hasNext();
        if(isApplied==true)
            System.out.println("Track your application");
        else
            System.out.println("Apply for voter id");

    }
}
