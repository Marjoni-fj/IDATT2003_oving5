package edu.ntnu.idi.idatt2003.oving5;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

    HBox cardDisplay = new HBox();
    cardDisplay.setSpacing(10);
    cardDisplay.setPadding(new Insets(20));
    rootNode.setCenter(cardDisplay);

    Button dealHand = new Button("Deal Hand");
    rootNode.setRight(dealHand);

    dealHand.setOnMouseEntered(e -> dealHand.setCursor(Cursor.HAND));
    dealHand.setOnMouseExited(e -> dealHand.setCursor(Cursor.DEFAULT));
    
    // When button is pressed, show the cards
    dealHand.setOnAction(e -> {
    cardDisplay.getChildren().clear();

    DeckOfCards deck = new DeckOfCards();
    List<PlayingCard> dealtCards = deck.dealHand(5);
    HandOfCards hand = new HandOfCards(dealtCards);
    List<PlayingCard> cards = hand.getHandOfCards();

    for (PlayingCard card : cards) {
        Text cardText = new Text(card.getAsString());
        cardText.setFont(Font.font(30));
        cardDisplay.getChildren().add(cardText);
    }
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
