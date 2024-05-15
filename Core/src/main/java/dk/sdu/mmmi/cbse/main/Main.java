package dk.sdu.mmmi.cbse.main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnnotationConfigApplicationContext start = new AnnotationConfigApplicationContext(ModuleConfig.class);
        for (String bean : start.getBeanDefinitionNames()) {
            System.out.println(bean);
        }
        Game game = start.getBean(Game.class);
        game.start(primaryStage);
        game.render();
    }
}
