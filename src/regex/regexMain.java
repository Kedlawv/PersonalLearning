package regex;

import java.util.Arrays;
import java.util.regex.Pattern;

public class regexMain {

    public static void main(String[] args) {

        String[] splitInput;
        String regex = "\\d*";
        String inputString = "12345";
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


    }
}
