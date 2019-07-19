package designPatterns.observer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        ActionPerformer observer = new Observer();
        subject.assignObserver(observer);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter single digit\nInput :");
        String input = scanner.next();

        if (input.matches("[0-9]")) {
            if (Integer.parseInt(input) == 1) {
                subject.observer.performAction();
            }else if (Integer.parseInt(input) == 2){
                subject.assignObserver(new ActionPerformer() {
                    @Override
                    public void performAction() {
                        System.out.println("Action Performed! Anonymous Class");
                    }
                });
                subject.observer.performAction();
            }else if(Integer.parseInt(input) == 3){
                subject.assignObserver(() -> System.out.println("Action Performed with lambda"));
                subject.observer.performAction();
            }


        }else{
            System.out.println("Wrong input");
        }


    }
}
