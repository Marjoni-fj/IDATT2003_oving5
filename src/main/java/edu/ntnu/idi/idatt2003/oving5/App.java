package edu.ntnu.idi.idatt2003.oving5;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    public static void main( String[] args ) {
      launch(args);
      System.out.println( "Hello World!" );
    }

    @Override
    public void start(Stage primaryStage) {
    primaryStage.setTitle("My first stage with scene");
    primaryStage.show();
    }
    @Override
    public void init() throws Exception {
    super.init();
    }
    @Override
    public void stop() throws Exception {
    super.stop();
    }
}
