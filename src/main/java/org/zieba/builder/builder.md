# Builder (budowniczy)

## Przeznaczenie

Oddziela tworzenie złożonego obiektu od jego reprezentacji, dzięki czemu ten sam proces konstrukcji może prowadzić do
powstania różnych reprezentacji.

## Implementacja

Mamy klasę `Computer.java` złożoną z wielu pól. Dla ułatwienia użyłem Lomboka aby pozbyć się niechcianego nadmiarowego
kodu:

```java

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Computer {

    private String CPU;
    private String GPU;
    private Double RAM;
    private Double storage;
    private String motherboard;
    private String powerSupply;
    private String coolingSystem;
    private String operatingSystem;
    private Boolean isBluetoothEnabled;
    private Boolean isWifiEnabled;
}
```

Teraz w metodzie głównej (klasa `MainForBuilder`) tworzymy sobie 3 obiekty typu `Computer.java`:

```java
public static void main(String[] args) {
    Computer windowsComputer = new Computer("Intel Core i7", "NVIDIA RTX 3080", 16.0, 512.0,
            "ASUS ROG Maximus", "Corsair 750W", "Liquid Cooling",
            "Windows 11", true, true);

    Computer linuxComputer = new Computer("AMD Ryzen 5", "AMD Radeon RX 580", 8.0, null,
            null, null, null,
            "Linux", null, null);

    Computer macComputer = new Computer("Mac M2 pro", null, 32.0, 256.0,
            null, null, null,
            "Mac OS", true, false);

    System.out.println(windowsComputer);
    System.out.println(linuxComputer);
    System.out.println(macComputer);
}
```

Jak widać, dla różnych obiektów komputerów użyłem różnych wypełnień pól - raz wszystkich, raz części. Inicjalizacja pól
tych obiektów jest ukryta pośród dużego konstruktora z dużą liczbą parametrów. I teraz mamy problem - korzystamy z
jednego konsktruktora przyjmującego wszystkie parametry, a więc wielokrotnie podczas jego używania wiele pól będzie
miało wartość `null`, a tworzenie nowego obiektu często będzie zawierać dużo (czasami nawet większość) nulli w
parametrach konstruktora. Jest to uciążliwe i często zaciemnia kod. Rozwiązaniem mogłoby być dodanie wielu
konstruktorów (przeciążyć konstruktory), aby zaspokoić wszystkie możliwe opcje na utworzenie obiektu. Jednakże, dla klas
z wieloma polami liczba takich kombinacji może być ogromna. Rozwiązaniem tego problemu jest zaimplementowanie wzorca
projektowego Builder. Moglibyśmy oczywiście dodać adnotację `@Builder` z Lomboka (i tak się często robi w projektach,
aby skrócić powtarzalny kod), ale my postaramy się ręcznie zaimplementować takiego Buildera. Implementacja wzorca
projektowego Builder w Javie może być wykonana na różne sposoby. Można użyć wewnętrznej klasy lub osobnej klasy
Buildera. Ja wybieram zastosowanie wewnętrznej publicznej klasy statycznej `Builder` wewnątrz `Computer.java`. Kod klasy
komputera wygląda teraz następująco:

```java

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Computer {

    private String CPU;
    private String GPU;
    private Double RAM;
    private Double storage;
    private String motherboard;
    private String powerSupply;
    private String coolingSystem;
    private String operatingSystem;
    private Boolean isBluetoothEnabled;
    private Boolean isWifiEnabled;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String CPU;
        private String GPU;
        private Double RAM;
        private Double storage;
        private String motherboard;
        private String powerSupply;
        private String coolingSystem;
        private String operatingSystem;
        private Boolean isBluetoothEnabled;
        private Boolean isWifiEnabled;

        public Builder CPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder GPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        public Builder RAM(Double RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder storage(Double storage) {
            this.storage = storage;
            return this;
        }

        public Builder motherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }

        public Builder powerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }

        public Builder coolingSystem(String coolingSystem) {
            this.coolingSystem = coolingSystem;
            return this;
        }

        public Builder operatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public Builder isBluetoothEnabled(Boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        public Builder isWifiEnabled(Boolean isWifiEnabled) {
            this.isWifiEnabled = isWifiEnabled;
            return this;
        }

        public Computer build() {
            return new Computer(CPU, GPU, RAM, storage, motherboard, powerSupply, coolingSystem, operatingSystem, isBluetoothEnabled, isWifiEnabled);
        }
    }
}
```

Od tej pory możemy konstruować obiekty w następujący sposób:

```java
Computer mac2 = Computer.builder()
        .CPU("M1")
        .RAM(24.0)
        .operatingSystem("Mac OS")
        .build();

Computer pc2 = Computer.builder()
        .CPU("Amd Ryzen 5700x")
        .GPU("NVIDIA RTX 3070")
        .operatingSystem("Windows 10")
        .motherboard("Asus Rog")
        .isWifiEnabled(true)
        .build();

System.out.println(mac2);
System.out.println(pc2);
```

Rezultat:

```console
Computer(CPU=M1, GPU=null, RAM=24.0, storage=null, motherboard=null, powerSupply=null, coolingSystem=null, operatingSystem=Mac OS, isBluetoothEnabled=null, isWifiEnabled=null)
Computer(CPU=Amd Ryzen 5700x, GPU=NVIDIA RTX 3070, RAM=null, storage=null, motherboard=Asus Rog, powerSupply=null, coolingSystem=null, operatingSystem=Windows 10, isBluetoothEnabled=null, isWifiEnabled=true)
```

Widać od razu, że zamiast zastanawiać się nad kolejnością pól, wieloma nullami skupiamy się tylko na zbudowaniu
wszystkiego część po części. Kod jest o wiele bardziej czytelny i zorganizowany z punktu widzenia programisty. Łatwiej
zapobiec błędom wynikających z dużej liczby parametrów konsktruktora. Widać dokładnie, jakie pole aktualnie składamy i
prawdpopodobieństwo, że pomylimy pola mające podobne typy zmniejsza się.