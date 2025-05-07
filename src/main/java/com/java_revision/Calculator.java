package com.java_revision;

public class Calculator {

    public void add(int a, int b){
        System.out.println("Add 2 Integers: " + (a+b));
    }

    public void add(int a, int b, int c){
        System.out.println("Add 3 Integers: " + (a+b+c));
    }

    public void add(double a, double b){
        System.out.println("Add 2 doubles: " + (a+b));
    }

    public void add(String a, int b){
        System.out.println("Add a string and a number: " + (a+b));
    }

    public static void main(String[] args) {
        Calculator cal = new Calculator();
        cal.add(10, 20);
        cal.add(10.20, 10.45);
        cal.add(20, 30, 60);
        cal.add("DEWSQA", 167);
    }
}
