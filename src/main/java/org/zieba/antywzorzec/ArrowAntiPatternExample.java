package org.zieba.antywzorzec;

import java.util.ArrayList;
import java.util.List;

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
