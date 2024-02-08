# Arrow Pattern / Anti Arrow Pattern

## Czym jest anti arrow pattern?

"**Arrow Pattern**" (pol. *wzorzec strzałki*) to antywzorzec, który opisuje sytuację, w której struktura kodu przyjmuje
formę strzałki lub schodów, zazwyczaj jako wynik zagnieżdżonych instrukcji warunkowych lub pętli. Taka struktura kodu
jest
trudna do czytania, utrzymywania i może prowadzić do błędów.

Antywzorzec "Arrow Pattern" jest często obserwowany w kodzie, gdzie zbyt wiele warunków jest sprawdzanych jeden po
drugim, prowadząc do głębokiego zagnieżdżenia. Aby uniknąć tego antywzorca, zaleca się refaktoryzację kodu poprzez
wyodrębnienie części kodu do osobnych metod lub zastosowanie wzorców projektowych takich jak Strategia, Stan czy Łańcuch
odpowiedzialności.

## Przykład antywzorca "Arrow Pattern" w Javie

Przyjrzyjmy się przykładowi, który demonstruje antywzorzec "Arrow Pattern" z pliku `ArrowAntiPatternExample.java`:

```java
public class ArrowAntiPatternExample {

    public static List<String> filterStrings(List<String> strings) {
        List<String> filteredStrings = new ArrayList<>();
        for (String s : strings) {
            if (s.startsWith("a")) {
                if (s.endsWith("itd.")) {
                    if (s.length() > 10) {
                        if (s.contains("example")) {
                            if (s.matches(".*\\d.*")) {
                                if (s.contains("special")) {
                                    filteredStrings.add(s);
                                } else {
                                    System.out.println("String does not contain 'special'");
                                }
                            } else {
                                System.out.println("String does not contain a number");
                            }
                        } else {
                            System.out.println("String does not contain 'example'");
                        }
                    } else {
                        System.out.println("String is too short");
                    }
                } else {
                    System.out.println("String does not end with 'itd.'");
                }
            } else {
                System.out.println("String does not start with 'a'");
            }
        }
        return filteredStrings;
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("a very long example string itd.");
        strings.add("a very long special example string with 1 number itd.");
        strings.add("not matching string");
        strings.add("another example itd.");
        strings.add("aitd.");

        List<String> filteredStrings = filterStrings(strings);
        System.out.println("Filtered strings: " + filteredStrings);
    }

}
```

W tym przykładzie funkcja `filterStrings` przeszukuje listę stringów, aplikując serię zagnieżdżonych warunków. Jest to
demonstracja antywzorca "Arrow Pattern", który czyni kod trudnym do czytania i utrzymania. Każdy kolejny warunek
powoduje głębsze zagnieżdżenie, prowadząc do struktury kodu przypominającej strzałkę.

Takie podejście nie jest zalecane, ponieważ znacząco obniża czytelność kodu. Lepszym rozwiązaniem jest refaktoryzacja
kodu w celu zredukowania zagnieżdżenia, na przykład poprzez wczesne zwracanie z funkcji, korzystanie ze strumieni (w
przypadku Javy) lub wydzielanie logiki do mniejszych funkcji/metod.

## Zrefaktoryzowany przykład z użyciem strumieni

Aby zrefaktoryzować powyższy przykład kodu i uniknąć antywzorca "Arrow Pattern", możemy skorzystać ze strumieni i
wyrażeń lambda dostępnych w Javie 8 i nowszych wersjach. Podejście to pozwoli na zastąpienie zagnieżdżonych instrukcji
if bardziej zwięzłymi i czytelnymi operacjami na strumieniach, co znacznie uprości kod. Oto nowa
klasa `StreamRefactoredExample.java`:

```java
public class StreamRefactoredExample {

    public static List<String> filterStrings(List<String> strings) {
        return strings.stream()
                .filter(s -> s.startsWith("a"))
                .filter(s -> s.endsWith("itd."))
                .filter(s -> s.length() > 10)
                .filter(s -> s.contains("example"))
                .filter(s -> s.matches(".*\\d.*")) // Checks whether a string contains a digit
                .filter(s -> s.contains("special"))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("a very long example string itd.");
        strings.add("a very long special example string with 1 number itd.");
        strings.add("not matching string");
        strings.add("another example itd.");
        strings.add("aitd.");

        List<String> filteredStrings = filterStrings(strings);
        System.out.println("Filtered strings: " + filteredStrings);
    }
}
```

W tym zrefaktoryzowanym przykładzie, zagnieżdżone instrukcje `if` zostały zastąpione przez łańcuch metod `filter`
aplikowanych na strumieniu utworzonym z listy stringów. Każdy `filter` odpowiada jednemu z warunków, które wcześniej
były sprawdzane za pomocą zagnieżdżonych `if`.

Dzięki temu kod jest znacznie bardziej zwięzły i łatwiejszy do zrozumienia. Operacje na strumieniach umożliwiają również
łatwe dodawanie lub usuwanie kryteriów filtrowania bez wprowadzania dodatkowego zagnieżdżenia. To sprawia, że taki kod
jest lepiej skalowalny i łatwiejszy w utrzymaniu.