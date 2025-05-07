package com.java_revision;

public abstract class Shape {

    public abstract void calculateArea();
}

class Circle extends Shape{

    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }


    @Override
    public void calculateArea() {
        System.out.println("Area of circle " +  Math.PI * radius * radius);
    }
}

class Rectangle extends Shape{

    double breadth, length;

    public Rectangle(double length, double breadth) {
       this.breadth = breadth;
       this.length = length;
    }
    @Override
    public void calculateArea() {
        System.out.println("Area of Rectangle " +  length * breadth);
    }
}


class Triangle extends Shape{

    double breadth, length;

    public Triangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
     }

    @Override
    public void calculateArea() {
        System.out.println("Area of Triangle " +  0.5 * length * breadth);
    }
}

class TestShape{
    
    public static void main(String[] args) {
        Shape shape = new Circle(123.45);
        shape.calculateArea();

        shape = new Rectangle(10, 20.9876);
        shape.calculateArea();

        shape = new Triangle(36.78, 43.45);
        shape.calculateArea();
    }
}