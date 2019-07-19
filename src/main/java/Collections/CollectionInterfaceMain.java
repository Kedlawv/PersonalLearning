package Collections;

import java.util.*;
import java.util.stream.Collectors;

//https://docs.oracle.com/javase/tutorial/collections/interfaces/collection.html

public class CollectionInterfaceMain {
    public static void main(String[] args) {
        Collection<String> stringCollection = new ArrayList<>();

        stringCollection.add("One"); // Collection interface contains many basic methods
        stringCollection.isEmpty();
        stringCollection.size();

        stringCollection.addAll(Arrays.asList("Two","Three","Four","Five"));
        System.out.println(stringCollection);

        //Traversing Collection __________
        // 1. Stream
        String csvString = stringCollection.stream()
                .filter(s -> s.length() <= 3)
                .collect(Collectors.joining(", "));
        System.out.println("Stream traversing _______  " + csvString);

        //2. For-each
        System.out.print("For-each traversing_______  ");
        for(String s : stringCollection){
            System.out.print(s + ", ");
        }
        System.out.println();

        List<String> copyList = stringCollection.stream().collect(Collectors.toList());

        //3. Iterator
        for(Iterator<String> it = copyList.iterator(); it.hasNext();){
            if(it.next().length() > 3){
                it.remove();
            }
        }
        // intelliJ proposed alternative
        copyList.removeIf(s -> s.length() > 3);

        //Collection Interface bulk operations
        ArrayList<String> checkString = new ArrayList<>(Arrays.asList("One","Two","Three","Four","Five"));

        //containsAll()
        System.out.println("stringCollection contains " + checkString
                + " == " + stringCollection.containsAll(checkString));

        //clear()
        checkString.clear();
        System.out.println("checkString after clear() :" + checkString );

        //addAll()
        checkString.addAll(stringCollection);
        System.out.println("checkString after addAll(stringCollection): " + checkString);

        //removeAll | removes elements common to both lists
        System.out.println("stringCollection before: " + stringCollection);
        stringCollection.removeAll(Arrays.asList("One","Two"));
        System.out.println("stringCollection after removing \"One\" and \"Two\" :" + stringCollection);

        //retainAll | keeps elements common to both lists
        System.out.println("stringCollection before: " + stringCollection);
        stringCollection.retainAll(Arrays.asList("Three"));
        System.out.println("stringCollection after keeping \"Three\" :" + stringCollection);

        // Collection to Array
        System.out.println();

        Object[] col2arrObj = checkString.toArray();
        System.out.println("getClass on col2arrObj == " + col2arrObj.getClass());
        System.out.println("getClass on element 0 of col2arrObj == " + col2arrObj[0].getClass());

        String[] col2arrType = checkString.toArray(new String[0]);
        System.out.println("getClass on col2arrType == " + col2arrType.getClass());
        System.out.println("getClass on element 0 of col2arrType == " + col2arrType[0].getClass());







    }
}
