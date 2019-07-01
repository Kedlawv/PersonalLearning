package main.java.caveOfPrgr.designPatterns.mvc;

import main.java.caveOfPrgr.designPatterns.mvc.controller.Controller;
import main.java.caveOfPrgr.designPatterns.mvc.model.Model;
import main.java.caveOfPrgr.designPatterns.mvc.view.View;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runApp();

            }
        });
    }

    public static void runApp(){
        Model model = new Model();

        View view = new View(model);

        Controller controller = new Controller(model,view);
        view.setLoginListener(controller);
    }
}
