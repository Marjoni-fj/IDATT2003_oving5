package edu.ntnu.idi.idatt2003.oving5;

import java.net.URI;
import java.net.URL;
import java.util.List;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.embed.swing.SwingFXUtils;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class App extends Application {
  private final SVGUniverse svgUniverse = new SVGUniverse();

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

    VBox cardDisplayWrapper = new VBox();
    cardDisplayWrapper.setSpacing(20);
    cardDisplayWrapper.setPadding(new Insets(20));

    HBox deckDisplay = new HBox();
    deckDisplay.setSpacing(10);

    Label handLabel = new Label();
    handLabel.setFont(new Font("Arial", 36));
    handLabel.setStyle("-fx-text-fill: darkblue;");

    cardDisplayWrapper.getChildren().addAll(deckDisplay, handLabel);
    rootNode.setTop(cardDisplayWrapper);

    dealHand.setOnAction(e -> {
      deckDisplay.getChildren().clear(); 

      DeckOfCards deck = new DeckOfCards();
      List<PlayingCard> dealtCards = deck.dealHand(5);
      HandOfCards hand = new HandOfCards(dealtCards);

      // Update hand type label
      handLabel.setText(hand.evaluateHand());

      // Render each card
      for (PlayingCard card : hand.getHandOfCards()) {
        try {
          String filePath = getCardFileName(card);
          URL url = getClass().getResource(filePath);
          if (url == null) {
            System.out.println("File not found: " + filePath);
            continue;
          }

          URI uri = svgUniverse.loadSVG(url);
          SVGDiagram diagram = svgUniverse.getDiagram(uri);

          int width = Math.max(1, (int) diagram.getWidth());
          int height = Math.max(1, (int) diagram.getHeight());
          BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

          Graphics2D g2d = bufferedImage.createGraphics();
          diagram.render(g2d);
          g2d.dispose();

          Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
          ImageView imageView = new ImageView(fxImage);
          imageView.setFitHeight(150);
          imageView.setPreserveRatio(true);

          deckDisplay.getChildren().add(imageView);

        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });

    primaryStage.setScene(new Scene(rootNode, 750, 750));
    primaryStage.show();
  }

  private String faceToName(int face) {
    switch (face) {
      case 1: return "ace";
      case 11: return "jack";
      case 12: return "queen";
      case 13: return "king";
      default: return String.valueOf(face);
    }
  }

  private String suitToName(char suit) {
    return switch (suit) {
      case 'S' -> "spades";
      case 'H' -> "hearts";
      case 'D' -> "diamonds";
      case 'C' -> "clubs";
      default -> throw new IllegalArgumentException("Invalid suit: " + suit);
    };
  }

  private String getCardFileName(PlayingCard card) {
    String faceName = faceToName(card.getFace());
    String suitName = suitToName(card.getSuit());
    return "/cards/" + faceName + "_of_" + suitName + ".svg";
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