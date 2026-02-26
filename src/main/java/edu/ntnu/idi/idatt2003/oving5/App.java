package edu.ntnu.idi.idatt2003.oving5;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
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
    primaryStage.setTitle("Poker");
    BorderPane rootNode = new BorderPane();

    Button dealHand = new Button("Deal Hand");
    rootNode.setRight(dealHand);

    dealHand.setOnMouseEntered(e -> dealHand.setCursor(Cursor.HAND));
    dealHand.setOnMouseExited(e -> dealHand.setCursor(Cursor.DEFAULT));
    
    primaryStage.setScene(new Scene(rootNode, 750, 750));
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
