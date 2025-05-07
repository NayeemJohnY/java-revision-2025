package com.java_revision;

interface Vehicle {
    public void start();

    public void stop();
}

class Car implements Vehicle {

    @Override
    public void start() {
        System.out.println("Started Car");
    }

    @Override
    public void stop() {
        System.out.println("Stopped Car");
    }

}

class Bike implements Vehicle {

    @Override
    public void start() {
        System.out.println("Started Bike");
    }

    @Override
    public void stop() {
        System.out.println("Stopped Bike");
    }

}

public class Vehicles {
    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        vehicle.start();
        vehicle.stop();

        vehicle = new Bike();
        vehicle.start();
        vehicle.stop();
    }
}
