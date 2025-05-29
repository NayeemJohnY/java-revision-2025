package com.java_revision;

public class SimplePerson {

    String name;
    int age;
    String gender;

    public SimplePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public SimplePerson(String name, int age, String gender) {
        this(name, age);
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "SimplePerson [name=" + name + ", age=" + age + ", gender=" + (gender == null ? "Unknown" : gender)
                + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

}
