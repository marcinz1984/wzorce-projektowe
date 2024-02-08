# Observer (Obserwator)

## Przeznaczenie

Określa zależność jeden do wielu między obiektami. Gdy zmianie ulega jeden z obiektów, cała reszta zależnych od niego
jest automatycznie powiadamiana i aktualizowana.

## Implementacja

Wyobraźmy sobie, że prowadzimy księgarnię iternetową. Mamy listę subskrybentów, którzy chcą być powiadomieni za każdym
razem, kiedy do księgarni trafi nowa książka. Nie chcemy, aby obserwatorzy księgarni sami musieli sprawdzać, kiedy nowa
książka trafia do listy. Lepszym rozwiązaniem jest, aby obiekt obserwowany (księgarnia) sam uruchomił metody wysyłające
powiadomienia dla swoich subskrybentów (obserwatorów) o dodaniu nowej książki. Wystąpienie zdarzenia (dodania książki)
powinno zainicjalizować zmianę stanu obserwatora. W naszym przypadku będzie to powiadomienie go o dostępności nowego
tytułu w bibliotece. Aby rozdzielić te dwa rodzaje interfejsów (dla obiektu obserwatora i obiektu obserwowanego),
stworzyłem dwa interfejsy. Pierwszy z nich dla Obserwatora:

```java
public interface Observer {

    void update(String book);
}
```

Każdy obserwator według kontraktu ustalonego przez interfejs będzie teraz musiał za pomoca metody `update` jakoś
zadziałać, w przypadku wystąpienia zdarzenia (dodania książki). Dla naszego przypadku klasą implementującą będzie klasa
Subskrybenta o nazwie `Subscriber.java`:

```java
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
```

Do logowania informacji będzie służyć wyprintowanie w konsoli jaki użytkownik został powiadomiony oraz o jakiej książce
został powiadomiony.

Interfejs `Observable.java` definiuje metody dla obserwowanego obiektu, takie jak dodawanie, usuwanie obserwatorów i
powiadamianie ich. Wygląda on następująco:

```java
public interface Observable {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(String book);
}
```

Interfejs implemntuje klasa księgarni - `BookStore.java`:

```java
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
```

Widzimy, że od tej pory przy operacji dodawania książki wszyscy subskrybenci zostaną powiadomieni. Możemy to
zaobserwować po uruchomieniu metody głównej z klasy `MainForObserver.java`:

```java
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
```

Rezultatem jest wysłanie notyfikacji o dodaniu książki dla listy Obserwatorów (subskrybentów):

```console
Marcin has been notified about a new book: The Great Gatsby
Piotr has been notified about a new book: The Great Gatsby

Process finished with exit code 0
```

Nasz przykład jest bardzo prosty, ale dobrze obrazuje działanie wzorca Observer. Jest szczególnie użyteczny w
sytuacjach, gdy zmiana stanu jednego obiektu musi automatycznie pociągać za sobą aktualizacje w jednym lub wielu innych
obiektach, bez konieczności twardego kodowania zależności między nimi. Nie chciałem komplikować przykładu, ale łatwo
wyobrazić sobie, że mogłyby być różne listy użytkowników, różne typy książek, sposoby notyfikacji itd. Podsumowując,
wzorzec Obserwator umożliwia obiektom (obserwatorom) subskrybowanie i reagowanie na zdarzenia w innym obiekcie (
obserwowanym) w sposób zorganizowany, elastyczny i efektywny, promując luźne powiązanie i wysoki poziom modularności.
Nowi obserwatorzy mogą być łatwo dodawani do systemu bez zmiany istniejącego kodu obserwowanego obiektu, co ułatwia
rozwój i rozbudowę systemu. Wzorzec ten jest naturalnym wyborem dla aplikacji opartych na zdarzeniach, gdzie zmiany w
jednym komponencie muszą być odzwierciedlane w innych komponentach.