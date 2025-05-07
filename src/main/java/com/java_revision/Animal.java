package com.java_revision;

public class Animal {

    public void makeSound(){
        System.out.println("Animal Sound");
    }

    public void printSound(Animal animal){
        animal.makeSound();
    }

    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.printSound(animal);

        animal = new Cat();
        animal.printSound(animal);

        animal = new Cow();
        animal.printSound(animal);
    }
}
class Dog extends Animal {
    public void makeSound(){
        System.out.println("Bark Bark");
    }
}

class Cat extends Animal {
    public void makeSound(){
        System.out.println("Meow Meow");
    }
}

class Cow extends Animal {
    public void makeSound(){
        System.out.println("May May");
    }
}