package designPatterns.observer;

public class Subject {

    ActionPerformer observer;

    public void assignObserver(ActionPerformer observer){
        this.observer = observer;
    }


}
