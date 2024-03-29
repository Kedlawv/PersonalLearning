package caveOfPrgr.designPatterns.mvc.controller;

import caveOfPrgr.designPatterns.mvc.model.Model;
import caveOfPrgr.designPatterns.mvc.view.LoginFormEvent;
import caveOfPrgr.designPatterns.mvc.view.LoginListener;
import caveOfPrgr.designPatterns.mvc.view.View;

public class Controller implements LoginListener {

    private Model model;
    private View  view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loginPerformed(LoginFormEvent event) {
        System.out.printf("Login event received Name:%s | Password:%s",
                event.getName(),event.getPassword());
    }
}
