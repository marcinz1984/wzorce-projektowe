package org.zieba.observer;

public class Subscriber implements Observer {

    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String book) {
        System.out.println(name + " has been notified about a new book: " + book);
    }
}
