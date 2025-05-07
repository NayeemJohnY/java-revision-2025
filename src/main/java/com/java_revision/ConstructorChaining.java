package com.java_revision;

class Person {
    protected int age;
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this(name);
        this.age = age;
    }
}

class StudentPerson extends Person {
    private int grade;

    public StudentPerson(String name, int age, int grade) {
        super(name, age);
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", Grade=" + grade + '}';
    }
}

public class ConstructorChaining {
    public static void main(String[] args) {
        StudentPerson student = new StudentPerson("John", 10, 6);
        System.out.println(student.toString());
    }
}
