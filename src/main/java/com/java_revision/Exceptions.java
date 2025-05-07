package com.java_revision;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;



public class Exceptions {

    public void arrayIndexBoundException(int index) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        try {
            System.out.println(array[index]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void arithemticException(int num1, int num2) {
        try {
            System.out.println(num1/num2);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    public void useFinallyBlock(int num1, int num2) {
        try {
            System.out.println("Open Connection");
            arithemticException(num1, num2);
        }
        finally {
            System.out.println("Close Connection");
        }
    }

    public void validateAge(int age) throws InvalidAgeException {
        if (age < 18)
            throw new InvalidAgeException("Age below 18");
    }

    public void multipleCatchBlock(int index1, int index2) {
        int[] array  =  new int[5];
        try{
               System.out.println(index1 / index2);
                array[index1] = array[index2];
        } catch (ArithmeticException  e) {
            System.out.println(e.getLocalizedMessage());
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void readFile(String filename) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(filename)) {
            outputStream.write("Sample content".getBytes());
        }
    }

    public void nestedTryCatch(int num){
        try {
            int[] array = new int[5];
            try {
                System.out.println(array.length/ num);
            } catch (ArithmeticException e) {
                System.out.println("Inner Catch Block" + e.getMessage());
            }
            System.out.println(array[array.length+num]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Outer Catch Block" + e.getMessage());
        }
    }

    public void fileException(String filename) throws FileNotFoundException, IOException{
        FileOutputStream outputStream = new FileOutputStream(filename);
        outputStream.write("Sample content".getBytes());
    }

    public void nullException(String filename) {
        try {
            System.out.println(filename.length());
        } catch (NullPointerException e) {
           System.out.println(e.getMessage());
        }
        
    }

    public void numberFormatException(String str){
        try {
            int num = Integer.parseInt(str);
            System.out.println("Number " + num);
        } catch (NumberFormatException e) {
            System.out.println("Exception " + str);
        }
    }

    public void illegalArgumentException(int age){
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Age must be between 0 and 150");
        }
    }

    public void inputMismatchException(){
        try {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Integer Input" + scanner.nextInt());
            }
        } catch (InputMismatchException e) {
            System.out.println("Input Mismatch" + e.getMessage());
        }
    }

    public void classCastException(Object obj){
        try {
            String str = (String) obj;
            System.out.println("Input" + str);
        } catch (ClassCastException e) {
            System.out.println("Input Mismatch" + e.getMessage());
        }
    }

    public void recursiveCall(){
        recursiveCall();
    }
}
