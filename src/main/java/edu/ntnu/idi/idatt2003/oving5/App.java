package edu.ntnu.idi.idatt2003.oving5;

import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
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
    

    /* Buttons */
    Button dealHand = new Button("Deal Hand");
    dealHand.setOnMouseEntered(e -> dealHand.setCursor(Cursor.HAND));
    dealHand.setOnMouseExited(e -> dealHand.setCursor(Cursor.DEFAULT));

    Button checkHand = new Button("Check Hand");
    checkHand.setDisable(true);
    checkHand.setOnMouseEntered(e -> checkHand.setCursor(Cursor.HAND));
    checkHand.setOnMouseExited(e -> checkHand.setCursor(Cursor.DEFAULT));

    /*ButtonWrapper */
    VBox buttonWrapper = createButtonPanel(dealHand, checkHand);
    rootNode.setLeft(buttonWrapper);

    /* Card display */


    HBox deckDisplay = new HBox(10);

    Label handLabel = new Label();
    handLabel.setFont(new Font("Arial", 36));
    handLabel.setStyle("-fx-text-fill: darkblue;");

    /* Info labels */
    Label sumLabel = new Label();
    sumLabel.setFont(new Font("Arial", 18));
    Label heartsLabel = new Label();
    heartsLabel.setFont(new Font("Arial", 18));
    Label queenLabel = new Label();
    queenLabel.setFont(new Font("Arial", 18));
    Label flushLabel = new Label();
    flushLabel.setFont(new Font("Arial", 18));

    VBox cardDisplayWrapper = createCardDisplayWrapper(
        deckDisplay, handLabel,
        sumLabel, heartsLabel, queenLabel, flushLabel);

    rootNode.setCenter(cardDisplayWrapper);

    CardDisplay cardDisplay = new CardDisplay(deckDisplay, handLabel);

    /* Deal hand */
    dealHand.setOnAction(e -> {
      DeckOfCards deck = new DeckOfCards();
      List<PlayingCard> dealtCards = deck.dealHand(5);
      HandOfCards hand = new HandOfCards(dealtCards);
      clearInfoLabels(sumLabel, heartsLabel, queenLabel, flushLabel);
      cardDisplay.clearDisplay();
      cardDisplay.showHand(dealtCards, hand.evaluateHand());
      checkHand.setDisable(false);
    });

    /* Check hand */
    checkHand.setOnAction(e -> {
      List<PlayingCard> currentCards = cardDisplay.getCurrentCards();
      HandOfCards hand = new HandOfCards(currentCards);

      int sum = hand.getSumOfFaces();
      List<PlayingCard> hearts = hand.getCardsContaining('H');
      String heartsText = hearts.isEmpty() ? "No Hearts"
      : hearts.stream().map(c -> "H" + c.getFace()).collect(Collectors.joining(" "));

      boolean queenSpades = hand.hasQueenOfSpades();
      boolean flush = hand.hasFlush();
      
      sumLabel.setText("Sum of faces: " + sum);
      heartsLabel.setText("Hearts: " + heartsText);
      queenLabel.setText("Has Queen of Spades: " + (queenSpades ? "Yes" : "No"));
      flushLabel.setText("Has Flush: " + (flush ? "Yes" : "No"));
    });

    primaryStage.setScene(new Scene(rootNode, 750, 750));
    primaryStage.show();
  }

  private VBox createButtonPanel(Button dealHand, Button checkHand) {
    VBox buttonWrapper = new VBox(10);
    buttonWrapper.setPadding(new Insets(20));
    buttonWrapper.setStyle("-fx-background-color: lightgray; -fx-alignment: center;");
    buttonWrapper.setPrefWidth(150);

    buttonWrapper.getChildren().addAll(dealHand, checkHand);

    return buttonWrapper;
  }

  private VBox createCardDisplayWrapper(
        HBox deckDisplay,
        Label handLabel,
        Label sumLabel,
        Label heartsLabel,
        Label queenLabel,
        Label flushLabel) {

    VBox cardDisplayWrapper = new VBox(20);
    cardDisplayWrapper.setPadding(new Insets(20));

    cardDisplayWrapper.getChildren().addAll(
        deckDisplay,
        handLabel,
        sumLabel,
        heartsLabel,
        queenLabel,
        flushLabel
    );
    return cardDisplayWrapper;
  }

  private void clearInfoLabels(Label sumLabel, Label heartsLabel, Label queenLabel, Label flushLabel) {
    sumLabel.setText("");
    heartsLabel.setText("");
    queenLabel.setText("");
    flushLabel.setText("");
  }
}