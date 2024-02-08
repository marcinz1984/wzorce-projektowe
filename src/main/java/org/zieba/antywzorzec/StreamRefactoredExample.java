package org.zieba.antywzorzec;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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