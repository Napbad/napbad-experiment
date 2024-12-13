package org.napbad.score;

import org.napbad.score.controller.*;
import org.napbad.score.model.DataSource;
import org.napbad.score.utilities.Generator;
import org.napbad.score.view.LoginView;
import org.napbad.score.view.MainView;
import org.napbad.score.view.StudentScoreView;
import org.napbad.score.view.TeachingClassView;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        Generator generator = new Generator(dataSource);
        TeachingClassController teachingClassController = new TeachingClassController(dataSource);
        TeachingClassView teachingClassView = new TeachingClassView(teachingClassController);
        MainView mainView = new MainView(new MainController(dataSource, generator), teachingClassView);
        StudentScoreView studentScoreView = new StudentScoreView(new StudentScoreController(dataSource), dataSource);
        new LoginView(mainView, new AdminController(dataSource), new StudentController(dataSource), studentScoreView).run();
    }
}