package edu.ntnu.idi.idatt2003.oving5;

import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class App extends Application {

  public static void main(String[] args) {
    launch(args);
    System.out.println("Hello World!");
  }

  @Override
public void start(Stage primaryStage) {
    primaryStage.setTitle("Poker");
    BorderPane rootNode = new BorderPane();

    Button dealHand = new Button("Deal Hand");
    rootNode.setRight(dealHand);
    dealHand.setOnMouseEntered(e -> dealHand.setCursor(Cursor.HAND));
    dealHand.setOnMouseExited(e -> dealHand.setCursor(Cursor.DEFAULT));

    VBox cardDisplayWrapper = new VBox(20);
    cardDisplayWrapper.setPadding(new Insets(20));

    HBox deckDisplay = new HBox(10);
    Label handLabel = new Label();
    handLabel.setFont(new Font("Arial", 36));
    handLabel.setStyle("-fx-text-fill: darkblue;");

    cardDisplayWrapper.getChildren().addAll(deckDisplay, handLabel);
    rootNode.setTop(cardDisplayWrapper);

    CardDisplay cardDisplay = new CardDisplay(deckDisplay, handLabel);

    dealHand.setOnAction(e -> {
        DeckOfCards deck = new DeckOfCards();
        List<PlayingCard> dealtCards = deck.dealHand(5);
        cardDisplay.showHand(dealtCards);
    });

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