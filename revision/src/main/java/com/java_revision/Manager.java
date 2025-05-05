package com.java_revision;

public class Manager extends Employee{

    private int bonus;


    public int getBonus() {
        return bonus;
    }


    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public int calculateTotalSalary(){
        return this.getSalary() + this.getBonus();
    }
}
