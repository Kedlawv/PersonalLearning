package streamLambda;


import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static streamLambda.PersonsDataProcessor.*;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Jan Kowlaski", LocalDate.of(1980, 3, 12)
                , Person.Sex.MALE, "j.kowalski@gmail.com");
        Person person2 = new Person("Grzegorz Brzoza", LocalDate.of(1975, 8, 31)
                , Person.Sex.MALE, "g.brzoza@gmail.com");
        Person person3 = new Person("Anna Rzepa", LocalDate.of(1989, 4, 11)
                , Person.Sex.FEMALE, "a.rzepa@gmail.com");
        Person person4 = new Person("John Smith", LocalDate.of(1999, 4, 11)
                , Person.Sex.MALE, "j.smith@gmail.com");


        List<Person> personList = new ArrayList<>();
        personList.addAll(Arrays.asList(new Person[]{person, person2, person3, person4}));

        Collections.sort(personList, new PersonAgeComparator()); // comparator object


        Collections.sort(personList, (Person a, Person b) -> {
                    return a.getBirthday().compareTo(b.getBirthday()); // lambda implementation
                }
        );

        Collections.sort(personList, (a, b) -> Person.compareByAge(a, b));// lambda method call

        Collections.sort(personList, Person::compareByAge); // static method referance
        // formal parameters of the second formal parameter(the method name after ::) is copied from the signature
        // of the method expected,
        // in this case Comparator<I>.compare(Person,Person)


        Collections.sort(personList, person2::compareByName); // instance method call
        // call on the instance/object of class Person

        // https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html

        printPersons(personList, new PersonsDataProcessor()
                .new CheckPersonEligibleForSelectiveService());
        //Inner class used instead of Local class like in the Java Tut example
        //To use a Local class we would define the class CheckPersonEligibleForSelectiveService
        //directly inside of main method and create a new instance in the printPersons()

        printPersons(personList, new CheckPerson() {
            @Override
            public boolean test(Person p) {
                return p.gender == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
            }
        });// Same as Local but this is Anonymous directly in the method call

        printPersonsWithPredicate(personList, p ->
                p.gender == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25
        );
        // Lambda using a standard functional generic interface java.util.function.Predicate<T>
        //Predicate<T> has one abstract method boolean test(T t) --- conventional param name -----> tester

        processPersonsWithConsumer(personList,
                p -> p.gender == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25,
                p -> p.printPerson());
        //Consumer<T> void accept(T t) --- conventional param name -----> block

        processPersonsWithFunction(personList,
                p -> p.gender == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25,
                p -> System.out.println(p),
                p -> p.getEmailAddress());
        //Function<T,R> R apply(T t); --- conventional param name -----> mapper
        // Predicate cheks the condition | Function retrives the email | Consumer prints email

        System.out.println("processPersonsWithFunction() method reference");
        processPersonsWithFunction(personList,
                p -> p.gender == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25,
                System.out::println,
                Person::getEmailAddress); // same as above but using method refrences instead of lambda
        // even thou getEmailAddress is not a static method we use the class type

        System.out.println("processPersonsWithFunction() not eligible");
        processPersonsWithFunction(personList,
                p -> (p.getAge() <= 18 || p.getAge() >= 25),
                System.out::println,
                Person::getEmailAddress);

        Predicate<Person> checkAge = p -> p.gender == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
        Consumer<String> print = System.out::println;
        Function<Person, String> getEmail = Person::getEmailAddress;

        System.out.println("processPersonsWithFunction() with lambdas as variables");
        processPersonsWithFunction(personList, checkAge, print, getEmail);

        // https://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html

        System.out.println("\nStreams get Male sorted by email_______________");
        personList.stream().filter(p -> p.gender == Person.Sex.MALE)
                .sorted(Comparator.comparing(Person::getEmailAddress))
                .map(Person::getEmailAddress)
                .forEach(System.out::println);

        System.out.println("\nGet Male by age");
        personList.stream().filter(p -> p.gender == Person.Sex.MALE)
                .sorted(Comparator.comparing(Person::getAge))
                .map(p -> p.getName() + " " + p.getAge())
                .forEach(System.out::println);

        System.out.println("\nallmatch agreggate function");
        System.out.println(
                personList.stream().allMatch(p -> p.getEmailAddress().contains("@")));

        System.out.println("\nfindAny, returns an object of type Optional ");
        personList.stream().filter(p -> p.getName().contains("Jan"))
                .findAny().ifPresent(System.out::println);
        personList.stream().filter(p -> p.getName().contains("Fail"))
                .findAny().ifPresent(System.out::println);

        System.out.println("\nmap one element from the stream to another");
        List<String> names = personList.stream().map(Person::getName).collect(Collectors.toList());
        System.out.println(names);

        System.out.println("\nreduce() repeatedly apply an operation on every element of the stream " +
                "until a result is produced");
        int[] nums = new int[]{5, 7, 8, 7, 5, 34, 3, 4, 5, 6, 7, 777};
        int sum = Arrays.stream(nums).reduce(0, (a, b) -> a + b);
        System.out.println("sum =" + sum);
        int largest = Arrays.stream(nums).reduce(0, (a, b) -> b > a ? b : a);
        //'a' is identity(a variable holding result, b is the element of the stream
        int smallest = Arrays.stream(nums).reduce(Integer.MAX_VALUE, (a, b) -> b < a ? b : a);
        System.out.println("find largest = " + largest);
        System.out.println("find smallest = " + smallest);

        System.out.println("\nStream boxing and int stream creation with sum");
        List<Integer> numsInteger = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int sumInteger = numsInteger.stream().mapToInt(  Integer::intValue).sum();
        System.out.println(sumInteger);



    }
}
