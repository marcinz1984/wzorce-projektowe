package org.zieba.observer;

import java.util.ArrayList;
import java.util.List;

public class BookStore implements Observable {

    private final List<String> books;
    private final List<Observer> subscribers;

    public BookStore() {
        this.books = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public void addBook(String book) {
        this.books.add(book);
        notifyObservers(book);
    }

    @Override
    public void addObserver(Observer observer) {
        this.subscribers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.subscribers.remove(observer);
    }

    @Override
    public void notifyObservers(String book) {
        for (Observer observer : subscribers) {
            observer.update(book);
        }
    }
}