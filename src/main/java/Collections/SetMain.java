package Collections;

import java.util.*;
import java.util.stream.Collectors;

//https://docs.oracle.com/javase/tutorial/collections/interfaces/set.html

public class SetMain {
    public static void main(String[] args) {
        System.out.println("_____________Set interface______________");

        Collection<String> stringArrayList = new ArrayList<>
                (Arrays.asList("One","One","Two","Three","Four","Five"));
        System.out.println("ArrayList with duplicates: " + stringArrayList);

        Set<String> stringHashSet = new HashSet<>(stringArrayList);
        System.out.println("HashSet of string, removed duplicate but random order: " + stringHashSet);

        Set<String> stringLinkedHashSet = new LinkedHashSet<>(stringArrayList);
        System.out.println("LinkedHashSet, duplicates removed and order retained: "
                + stringLinkedHashSet);

        Set<String> stringAggrSet = stringArrayList.stream().collect(Collectors.toSet());
        System.out.println("List to Set using Aggregate Operations: " + stringAggrSet);

        Set<String> stringTreeSet = stringArrayList
                .stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println("List to specifically a TreeSet");

        System.out.println("___________Bulk operations____________");

        Set<String> otherSet = new LinkedHashSet<>(Arrays.asList("Two","Five","Seven","Ten"));

        Set<String> union = new LinkedHashSet<>(otherSet);
        union.addAll(stringLinkedHashSet);
        System.out.println("__Union__ of two sets, all elements contained in either set:");
        System.out.printf("Set 1:\t\t\t %s \nSet 2:\t\t\t %s \nUnion of sets:\t%s\n"
                ,stringLinkedHashSet, otherSet,union);

        Set<String> intersection = new LinkedHashSet<>(otherSet);
        intersection.retainAll(stringLinkedHashSet);
        System.out.println("__Intersection__ of two sets, only elements contained by both sets:");
        System.out.printf("Set 1:\t\t\t %s \nSet 2:\t\t\t %s \nIntersection of sets:\t%s\n"
                ,stringLinkedHashSet, otherSet,intersection);

        Set<String> difference = new LinkedHashSet<>(otherSet);
        difference.removeAll(stringLinkedHashSet);
        System.out.println("__Difference(asymmetric)__ of two sets, removes elements from " +
                "one set that also exist in other set:");
        System.out.printf("Set 1:\t\t\t %s \nSet 2:\t\t\t %s \nDifference of sets(asymetrical):\t%s\n"
                ,stringLinkedHashSet, otherSet,difference);

        System.out.println("__Difference(symmetric)__ Inverse of intersection " +
                "we get only what is not common or removes common elements :");
        System.out.printf("Set 1:\t\t\t %s \nSet 2:\t\t\t %s \nDifference of sets(symetrical):\t%s\n"
                ,stringLinkedHashSet, otherSet,symetricDiff(stringLinkedHashSet,otherSet));

    }

    public static <E> Set<E> removeDups(Collection<E> c) { // todo Dont't understand where
        return new LinkedHashSet<E>(c);                    // the first generic after static is doing
    }

    public static <E> Set<E> symetricDiff(Set<E> set1, Set<E> set2){
        Set<E> symmetricDiffSet = new HashSet<>(set1);
        symmetricDiffSet.addAll(set2);                    //Union
        Set<E> tmpSet = new HashSet<>(set1);
        tmpSet.retainAll(set2);                           //Intersection
        symmetricDiffSet.removeAll(tmpSet);               //Difference

        return symmetricDiffSet;
    }
}
