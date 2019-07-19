package designPatterns.observer;

public class Observer implements ActionPerformer {
    @Override
    public void performAction() {
        System.out.println("Action performed! On conrete class");
    }
}
