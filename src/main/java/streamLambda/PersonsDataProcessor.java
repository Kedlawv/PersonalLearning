package streamLambda;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class PersonsDataProcessor {

    public static void processPersonsWithConsumer(
            List<Person> roster,
            Predicate<Person> tester,
            Consumer<Person> block) {
        System.out.println("processPersonsWithConsumer()");
        int val;
        for(Person p : roster){
            if(tester.test(p)){
                block.accept(p);
            }
        }
    }

    public static void processPersonsWithFunction(
            List<Person> roster,
            Predicate<Person> tester,
            Consumer<String> block,
            Function<Person, String> mapper){
        System.out.println("processPersonsWithFunction()");
        for(Person p:roster){
            if(tester.test(p)){
                String s = mapper.apply(p);
                block.accept(s);
            }
        }

    }



    public static void printPersons(List<Person> roster, CheckPerson tester){
        System.out.println("printPerson()");
        for(Person p : roster){
            if(tester.test(p)){
                p.printPerson();
            }
        }

    }

    public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester){
        System.out.println("printPersonsWithPredicate()");
        for(Person p : roster){
            if(tester.test(p)){
                p.printPerson();
            }
        }

    }

    class CheckPersonEligibleForSelectiveService implements CheckPerson{
        @Override
        public boolean test(Person p) {
            return p.gender == Person.Sex.MALE && p.getAge()>=18 && p.getAge()<=25;
        }
    }
}
