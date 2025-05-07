package com.java_revision;

public class Manager extends Employee {

    private int bonus;

    public Manager(String name, int id, int salary, int bonus) {
        super(name, id, salary);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public int calculateTotalSalary() {
        return this.getSalary() + this.getBonus();
    }
}
