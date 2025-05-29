package com.java_revision;

import java.time.LocalDate;

public class Event {

    String name;
    LocalDate date;
    int participents;

    @Override
    public String toString() {
        return "Event [name=" + name + ", date=" + date + ", participents=" + participents + "]";
    }

    public Event(String name) {
        this.name = name;
        this.date = LocalDate.now();
    }

    public Event(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getParticipents() {
        return participents;
    }

}
