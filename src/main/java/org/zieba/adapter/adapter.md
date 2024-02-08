# Adapter

## Przeznaczenie

Wzorzec projektowy Adapter (znany również jako Wrapper) jest używany do przekształcania interfejsu jednej klasy na
interfejs oczekiwany przez klienta. Umożliwia współpracę klas, które nie mogłyby tego robić z powodu niekompatybilnych
interfejsów. Adapter działa jako pośrednik, przekształcając wywołania API jednego obiektu na format i interfejs
rozumiany przez inny obiekt. Jest szczególnie przydatny w sytuacjach, gdzie istniejące klasy muszą współpracować, ale
ich kod nie może zostać zmieniony.

## Implementacja

W kodzie pakietu adapter mamy zdefiniowany następujący interfejs kuli(sfery) o nazwie `Sphere`:

```java
public interface Sphere {

    String getName();

    double getRadius();
}
```

Implementuje ten interfejs klasa piłki:

```java

@AllArgsConstructor
public class Ball implements Sphere {

    private String name;
    private double radius;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getRadius() {
        return radius;
    }
}
```

W pakiecie znajduje się klasa pudełka - `Box.java`, która zawiera okrągły otwór o określonym promieniu. Zakładamy, że
pudełko posiada nieskończoną pojemność. Do pudełka możemy bez problemu rzucać piłki, o ile ich promień jest mniejszy
bądź równy promieniowi otworu pudełka. Pudełko mając doczynienia z obiektami typu implementującymi `Sphere.java`, może
bez problemu sprawdzić, czy można do niego wrzucić określoną kulę, po prostu porównując promienie w metodzie **put**:

```java

@AllArgsConstructor
public class Box {

    private double holeRadius;
    private List<Sphere> content;

    public void put(Sphere sphere) {
        if (sphere.getRadius() <= holeRadius) {
            content.add(sphere);
            System.out.println(sphere.getName() + " added added to the box!");
        } else {
            System.out.println(sphere.getName() + " is too large to fit through the box!");
        }
    }
}
```

Jeżeli kula przejdzie przez otwór, jest zapisywana do listy. Jeżeli nie, nie będzie zapisana do listy. Odpowiednia
informacja o powodzeniu lub porażce jest wypisywana do konsoli programu. Możemy teraz zobaczyć, jak działa program.
Metoda główna wygląda następująco:

```java
public static void main(String[] args) {
    List<Sphere> spheres = new ArrayList<>();
    double boxHoleRadius = 10.0;
    Box box = new Box(boxHoleRadius, spheres);

    Sphere ball1 = new Ball("ball1", 8.0);
    Sphere ball2 = new Ball("ball2", 11.0);
    box.put(ball1);
    box.put(ball2);
}
```

Jak możemy się spodziewać, dla otworu pudełka o promieniu 10 możemy włożyć piłkę **ball1** o promieniu 8, ale nie możemy
włożyć **ball2**, bo jej promień(11) jest większy niż promień otworu, co widać w outpucie programu:

```console
ball1 added added to the box!
ball2 is too large to fit through the box!

Process finished with exit code 0
```

I wszystko wydaje się proste do czasu. W pewnym momencie klient żąda, aby do pudełka mogłyby zostać wrzucane
prostopadłościany. Nie da się dla prostopadłościanu zaimplementować interfejsu `Sphere.java`. Wynika to z faktu, że
prostopadłościan nie może być kulą. Jest to niezgodne z założeniami matematycznymi. Poza tym ingerencja w intefejs może
powodować błędy dla całości programu. Jest jednak rozwiązanie dla naszego problemu - wzorzec **Adapter**. Najpierw
przedstawmy klasę prostopadłościanu:

```java

@AllArgsConstructor
@Getter
public class Cuboid {

    private String name;
    private double length;
    private double width;
    private double height;
}
```

Teraz możemy wprowadzić do projektu klasę adaptera `CuboidAdapter.java`, który będzie udawał że prostopadłościan ma
promień(przekrój kuli). Wykorzystamy tu moc matematyki - zakładamy że taki prostopadłościan powinien dać się włożyc do
okrągłego otworu pudełka z każdego boku (nawet tego o największej przekątnej czy powierzchni), aby możliwość włożenia
nie była zależna od strony którą wkładamy (jak przy kuli, nie ważne jak ją odwrócimy). Kluczem do rozwiązania jest
znalezienie minimalnej średnicy otworu, przez który może przejść prostopadłościan. W przypadku prostopadłościanu,
minimalna średnica otworu, przez który może przejść, jest średnicą kuli, w której prostopadłościan może się całkowicie
zawrzeć. Ta kula nazywana jest "kulą opisaną na prostopadłościanie". Dla prostopadłościanu o wymiarach długości *l*,
szerokości *w* i wysokości *h*, średnica *D* kuli opisanej na prostopadłościanie jest równa przekątnej
prostopadłościanu. Można ją obliczyć za pomocą twierdzenia Pitagorasa:

D = sqrt(l*l + w*w + h*h)

Aby uzyskać promień, trzeba to jeszcze podzielić przez 2, co widać w metodzie **getRadius** klasy adaptera:

```java

@AllArgsConstructor
public class CuboidAdapter implements Sphere {

    private final Cuboid cuboid;

    @Override
    public String getName() {
        return cuboid.getName();
    }

    @Override
    public double getRadius() {
        double l = cuboid.getLength();
        double w = cuboid.getWidth();
        double h = cuboid.getHeight();

        // Calculating the radius as half the diagonal of the cuboid
        return Math.sqrt(l ^ 2 + w ^ 2 + h ^ 2) / 2;
    }
}
```

Widzimy, że `CuboidAdaper.java` zaimplementował interfejs `Sphere.java` oraz otoczył obiekt prostopadłościanu. Dzięki
temu, mamy zgodność ze starym inferfejsem dla zupełnie nowego, nieprzystającego na pierwszy rzut oka obiektu klasy
prostopadłościanu. Od tej pory obiekt klasy pudełka będzie mógł wrzucać do środka obiekty klasy `CuboidAdapter.java`
zawierające w środku prostopadłościany, o ile ich promień kuli opisanej na tych prostopadłościanach jest mniejszy bądź
równy promieniowi otworu pudełka. Możemy teraz w klasie głównej opakować adapterem prostopadłościany, i sprawdzić, czy
udało się je wrzucić do pudełka:

```java
public static void main(String[] args) {
    Cuboid cuboid1 = new Cuboid("cuboid1", 4.9, 6.0, 21.0);
    Cuboid cuboid2 = new Cuboid("cuboid2", 3.0, 4.0, 8.5);
    Sphere cuboidAdapter1 = new CuboidAdapter(cuboid1);
    Sphere cuboidAdapter2 = new CuboidAdapter(cuboid2);

    box.put(cuboidAdapter1);
    box.put(cuboidAdapter2);
}
```

Rezultat jest następujący:

```console
cuboid1 is too large to fit through the box!
cuboid2 added added to the box!

Process finished with exit code 0
```

Jak widać, dzięki adapterowi możemy pogodzić dwa sprzeczne ze sobą interfejsy. Dzięki niemu możemy wrzucać
prostopadłościany do kulistego otworu pudełka. Odseparowaliśmy nowy kod od starego, nie tykając starego i nie zmieniając
w nim nic. Niestety, kod się trochę skomplikował.