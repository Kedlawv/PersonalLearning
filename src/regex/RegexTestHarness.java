package regex;

import java.io.Console;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//https://docs.oracle.com/javase/tutorial/essential/regex/test_harness.html

public class RegexTestHarness {

    public static void main(String[] args){
//        Console console = System.console();
//        if (console == null) {
//            System.err.println("No console.");
//            System.exit(1);
//        }

        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nEnter your regex: ");
            Pattern pattern =
                    Pattern.compile(scanner.next());

            System.out.println("\nEnter input string to search: ");
            Matcher matcher =
                    pattern.matcher(scanner.next());

            boolean found = false;
            while (matcher.find()) {
                System.out.printf("I found the text" +
                                " \"%s\" starting at " +
                                "index %d and ending at index %d.%n",
                        matcher.group(),
                        matcher.start(),
                        matcher.end());
                found = true;
            }
            if(!found){
                System.out.printf("No match found.%n");
            }
        }
    }
}
