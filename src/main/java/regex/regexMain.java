package regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexMain {

    public static void main(String[] args) {

        String[] splitInput;
        String regex = "\\d*";
        String inputString = "12345";
        String replacement;
        boolean matched = Pattern.matches(regex, inputString);

        System.out.printf("%nInput string %s matches the regular expression %s%n",
                inputString, regex);

        regex = ":";
        inputString = "one:two:three:four:five";
        Pattern pattern = Pattern.compile(regex);
        splitInput = pattern.split(inputString);
        Arrays.stream(splitInput).forEach(System.out::println);

        regex = "\\d";
        inputString = "one5two6three9four2five";
        pattern = Pattern.compile(regex);

        splitInput = pattern.split(inputString);
        System.out.printf("%n%s input string splits by %s regex in to: %n", inputString, regex);
        Arrays.stream(splitInput).forEach(System.out::println);

        //same split functionality exists in the String class

        splitInput = inputString.split(regex);
        System.out.printf("%nInvoked on String object. " +
                "%n%s input string splits by %s regex in to: %n", inputString, regex);
        Arrays.stream(splitInput).forEach(System.out::println);

        inputString = "124no2352no23567no235";
        System.out.printf("%noriginal string: %s%n", inputString);
        inputString = inputString.replace("no", "yes");
        System.out.printf("%nno replaced with yes: %s", inputString);

        System.out.println("\n________________Matcher Class____________________");

        inputString = "dog dog dog doggie dogg";
        regex = "\\bdog\\b"; //word boundary regex looking for word "dog" not a substring "dog"
        matchAndPrint(regex, inputString);

        regex = "dog"; //no word boundary, finds all occurrences of dog
        matchAndPrint(regex, inputString);

        regex = "foo";
        inputString = "foooooooooo";
        lookAtVSmatches(regex, inputString);
        //matches() checks for a complete match and lookAt() just for substring match

        regex = "dog";
        inputString = "\nThe dog says meow. All dogs say meow.";
        replacement = "cat";

        replaceAllMatches(regex, inputString, replacement);

        regex = "a*b"; //many or none 'a' followed by 'b'
        inputString = "aabfooaabfooabfoob";
        replacement = "-";

        replaceAllMatches(regex, inputString, replacement);

        System.out.println("String equivalent of matcher.replaceAll");
        inputString = inputString.replaceAll(regex,replacement);
        System.out.println(inputString);


    }

    private static void replaceAllMatches(String regex, String inputString, String replacement) {
        Matcher matcher = getMatcher(regex, inputString);
        printMatcherLabel(regex, inputString);

        inputString = matcher.replaceAll(replacement);

        System.out.println("\n" + inputString);
    }

    private static void matchAndPrint(String regex, String inputString) {
        Matcher matcher = getMatcher(regex, inputString);
        printMatcherLabel(regex, inputString);

        while (matcher.find()) {
            System.out.printf("%n%s at starting index %d and ending index %d"
                    , matcher.group(), matcher.start(), matcher.end());
        }

    }

    private static void printMatcherLabel(String regex, String inputString) {
        System.out.printf("%n%nMatch for regex %s in an input string %s"
                , regex, inputString);
    }

    private static Matcher getMatcher(String regex, String inputString) {
        Pattern pattern = Pattern.compile(regex);
        pattern.compile(regex);
        return pattern.matcher(inputString);
    }

    private static void lookAtVSmatches(String regex, String inputString) {
        Matcher matcher = getMatcher(regex, inputString);
        printMatcherLabel(regex, inputString);

        System.out.printf("%nlookingAt(): %s", matcher.lookingAt());
        System.out.printf("%nmatches(): %s", matcher.matches());

    }

}
