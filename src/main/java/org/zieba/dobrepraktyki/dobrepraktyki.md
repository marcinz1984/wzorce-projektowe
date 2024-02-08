## 1) KISS

Zasada **KISS** (Keep It Simple, Stupid) to jedna z fundamentalnych koncepcji w inżynierii oprogramowania, która zachęca
do
utrzymywania projektów w jak najprostszej formie. Główna idea stojąca za KISS polega na unikaniu niepotrzebnej
złożoności, co ułatwia zrozumienie, rozwój i utrzymanie kodu. Prostota w projektowaniu i kodowaniu przekłada się na
mniejszą liczbę błędów, łatwiejsze testowanie oraz efektywniejsze wdrażanie nowych funkcji.

Stosowanie zasady KISS wymaga skupienia się na najbardziej bezpośrednich i oczywistych rozwiązaniach problemów. Zamiast
komplikować projekt wieloma warstwami abstrakcji czy zaawansowanymi technikami programistycznymi, które mogą wydawać się
sprytnymi w krótkim terminie, KISS promuje podejście, w którym priorytetem jest prostota i bezpośredniość. To nie
oznacza, że rozwiązania powinny być prymitywne lub ograniczone; raczej chodzi o to, aby unikać nadmiaru i skupić się na
tym, co naprawdę jest potrzebne do realizacji danej funkcjonalności.

## 2) YAGNI

Zasada YAGNI, która jest skrótem od "You Aren't Gonna Need It" (pol. "Nie będziesz tego potrzebować"), jest kluczowym
przekazem w programowaniu i projektowaniu oprogramowania, akcentującym potrzebę unikania prac nad funkcjonalnościami,
które na obecnym etapie nie są wymagane. Idea ta wychodzi z założenia, że programiści nie powinni implementować funkcji
tylko dlatego, że przypuszczają iż mogą one być potrzebne w przyszłości. Jest to ostrzeżenie przed dodawaniem
nadmiarowego kodu, który w założeniu ma służyć przyszłym rozszerzeniom lub przypadkom użycia, które mogą nigdy nie
nadejść. Implementacja YAGNI ma na celu skupienie zespołu projektowego na bieżących wymaganiach, zamiast tracić czas i
zasoby na rozwijanie aspektów, które nie są niezbędne dla działania projektu w jego obecnej formie.

## 3) DRY

Zasada DRY, która oznacza "Don't Repeat Yourself" (Nie Powtarzaj Się), jest kluczowym podejściem w tworzeniu
oprogramowania, mającym na celu redukcję powtarzania kodu oraz informacji w projekcie. Jest to jedna z podstawowych
zasad, która zachęca programistów do poszukiwania i eliminowania duplikacji w kodzie poprzez abstrakcję i ponowne
wykorzystanie kodu. Implementacja tej zasady ma na celu poprawę czytelności, łatwości utrzymania i aktualizacji
oprogramowania, a także zmniejszenie ryzyka błędów.

## 4) SOLID

SOLID to akronim reprezentujący pięć zasad projektowania oprogramowania, które mają na celu poprawę jakości kodu poprzez
jego większą czytelność, elastyczność oraz łatwość utrzymania. Te zasady, rozpromowane przez Roberta C. Martina, są
szczególnie cenione w programowaniu obiektowym.

### S - Single Responsibility Principle (Zasada Jednej Odpowiedzialności)

- Każda klasa powinna mieć tylko jeden powód do zmiany. Oznacza to, że klasa powinna być odpowiedzialna za jedną
  funkcjonalność lub aspekt programu, co ułatwia zarządzanie kodem i jego testowanie.

### O - Open/Closed Principle (Zasada Otwarte/Zamknięte)

- Oprogramowanie powinno być otwarte na rozszerzenia, ale zamknięte na modyfikacje. Powinno być możliwe dodawanie nowej
  funkcjonalności bez zmiany istniejącego kodu, co zwykle osiąga się przez użycie abstrakcji i polimorfizmu.

### L - Liskov Substitution Principle (Zasada Podstawienia Liskov)

- Obiekty w programie powinny być zastępowalne przez instancje ich podtypów bez wpływu na poprawność programu. Klasy
  pochodne powinny zachowywać się w sposób zgodny z oczekiwaniami dotyczącymi klas bazowych.

### I - Interface Segregation Principle (Zasada Segregacji Interfejsu)

- Klienci nie powinni być zmuszani do polegania na interfejsach, których nie używają. Lepiej jest mieć wiele
  specjalizowanych interfejsów niż jeden ogólny, co pomaga w utrzymaniu kodu łatwiejszego do implementacji i utrzymania.

### D - Dependency Inversion Principle (Zasada Odwrócenia Zależności)

- Moduły wysokiego poziomu nie powinny zależeć od modułów niskiego poziomu. Oba typy powinny zależeć od abstrakcji, a
  abstrakcje nie powinny zależeć od szczegółów, lecz szczegóły od abstrakcji. Zasada ta zachęca do projektowania
  zależności w kodzie od konkretów do abstrakcji, co ułatwia zarządzanie zależnościami i testowanie.

## 5) GRASP

GRASP to zbiór wzorców projektowych i zasad przydzielania odpowiedzialności w programowaniu obiektowym, które pomagają w
decydowaniu, gdzie umieścić odpowiedzialność za wykonanie określonych funkcji i zachowań w kodzie.

### Information Expert (Ekspert Informacji)

Przydzielaj odpowiedzialność za wykonanie zadania klasie, która posiada niezbędne informacje do jego wykonania.

### Creator (Twórca)

Klasa A powinna być odpowiedzialna za tworzenie instancji klasy B, jeśli:

- Obiekty A zawierają lub agregują obiekty B,
- Obiekty A używają obiektów B,
- Obiekty A mają inicjatywę do tworzenia obiektów B.

### Controller (Kontroler)

Przydzielaj odpowiedzialność za obsługę zdarzeń systemowych do klasy "kontroler", która reprezentuje cały system lub
scenariusz przypadku użycia (a nie tylko pojedynczy obiekt).

### Low Coupling (Słabe Sprzężenie)

Projektuj system w taki sposób, aby zależności między modułami były jak najmniejsze, co prowadzi do większej
modularności i łatwości w utrzymaniu.

### High Cohesion (Wysoka Spójność)

Utrzymuj klasy skoncentrowane i skupione na wykonaniu jednego zadania lub zestawu ściśle powiązanych zadań, co ułatwia
zrozumienie, rozwój i utrzymanie kodu.

### Polymorphism (Polimorfizm)

Wykorzystaj polimorfizm do obsługi decyzji opartych na typach, co pozwala na eliminację instrukcji warunkowych i
sprawia, że kod jest łatwiejszy do rozszerzenia.

### Pure Fabrication (Czysta fabrykacja)

Kiedy trudno jest przypisać odpowiedzialność zgodnie z zasadami wysokiej spójności i słabego sprzężenia, utwórz nową
klasę, która nie reprezentuje konceptów z dziedziny problemu, aby zachować spójność i słabe sprzężenie.

### Indirection (Pośrednictwo)

Użyj pośrednika (np. mediatora) do zredukowania bezpośredniego sprzężenia między dwoma klasami.

### Protected Variations (Ochrona Przed Zmianami)

Izoluj elementy systemu, które mogą ulec zmianie, od tych, które pozostają stabilne.