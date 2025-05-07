package com.java_revision;

import java.util.Arrays;

public class Student {

    private String name;
    private int rollNo;
    private int[] marks;

    public Student(String name, int rollNo, int... marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public float calculateAverage() {
        if (marks.length == 0)
            return 0;
        return (float) Arrays.stream(marks).sum() / marks.length;
    }

    public String calculateGrade() {
        float average = calculateAverage();
        if (average >= 90)
            return "A+";
        else if (average >= 80)
            return "A";
        else if (average >= 70)
            return "B";
        else if (average >= 60)
            return "C";
        else
            return "Fail";

    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNo=" + rollNo +
                ", marks=" + Arrays.toString(marks) +
                ", average=" + calculateAverage() +
                ", grade='" + calculateGrade() + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Student student = new Student("John", 123, 89, 90, 95, 99);
        System.out.println(student.toString());
        System.out.println(student.calculateGrade());

        student = new Student("Ace", 123, 12, 12, 95, 99);
        System.out.println(student.toString());
        System.out.println(student.calculateGrade());
    }
}
