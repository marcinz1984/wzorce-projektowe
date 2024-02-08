package org.zieba.observer;

public class MainForObserver {

    public static void main(String[] args) {
        BookStore bookstore = new BookStore();
        Observer subscriber1 = new Subscriber("Marcin");
        Observer subscriber2 = new Subscriber("Piotr");

        bookstore.addObserver(subscriber1);
        bookstore.addObserver(subscriber2);

        bookstore.addBook("The Great Gatsby");
    }
}
