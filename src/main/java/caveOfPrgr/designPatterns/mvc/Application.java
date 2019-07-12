package caveOfPrgr.designPatterns.mvc;

import caveOfPrgr.designPatterns.mvc.controller.Controller;
import caveOfPrgr.designPatterns.mvc.model.Model;
import caveOfPrgr.designPatterns.mvc.view.View;

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
