package edu.ntnu.idi.idatt2003.oving5;

import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.embed.swing.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.net.URI;
import java.net.URL;
import java.util.List;
import com.kitfox.svg.SVGUniverse;
import com.kitfox.svg.SVGDiagram;

/**
 * The {@code CardDisplay} class is responsible for displaying a hand of playing cards in a JavaFX application.
 * It uses the SVGUniverse library to load and render SVG images of playing cards, and displays
 * the best possible poker hand that can be made with the cards in the hand.
 */
public class CardDisplay {
  private final SVGUniverse svgUniverse = new SVGUniverse();
  private final HBox deckDisplay;
  private final Label handLabel;
  private final HBox checkDisplay;
  private List<PlayingCard> currentCards;

  /**
   * Creates a new {@code CardDisplay} with the specified {@code HBox} for displaying the cards and
   * {@code Label} for displaying the best possible poker hand.
   */
  public CardDisplay(HBox deckDisplay, Label handLabel, HBox checkDisplay) {
    if (deckDisplay == null) {
      throw new IllegalArgumentException("Deck display cannot be null");
    }
    if (handLabel == null) {
      throw new IllegalArgumentException("Hand label cannot be null");
    }
    if (checkDisplay == null) {
      throw new IllegalArgumentException("Check display cannot be null");
    }
    this.deckDisplay = deckDisplay;
    this.handLabel = handLabel;
    this.checkDisplay = checkDisplay;
  }

  /**
   * Returns the currently displayed cards.
   * @return the list of currently displayed playing cards
   */
  public List<PlayingCard> getCurrentCards() {
    return currentCards;
  }

  private void showCards(List<PlayingCard> cards, HBox container, int height) {
  container.getChildren().clear();
  for (PlayingCard card : cards) {
    try {
      String filePath =
        "/cards/" +
        faceToName(card.getFace()) +
        "_of_" +
        suitToName(card.getSuit()) +
        ".svg";
      URL url = getClass().getResource(filePath);
      if (url == null) {
        System.out.println("File not found: " + filePath);
        continue;
      }
      URI uri = svgUniverse.loadSVG(url);
      SVGDiagram diagram = svgUniverse.getDiagram(uri);

      int width = Math.max(1, (int) diagram.getWidth());
      int imgHeight = Math.max(1, (int) diagram.getHeight());

      BufferedImage bufferedImage =
        new BufferedImage(width, imgHeight, BufferedImage.TYPE_INT_ARGB);

      Graphics2D g2d = bufferedImage.createGraphics();
      diagram.render(g2d);
      g2d.dispose();

      ImageView imageView =
        new ImageView(SwingFXUtils.toFXImage(bufferedImage, null));
      imageView.setFitHeight(height);
      imageView.setPreserveRatio(true);

      container.getChildren().add(imageView);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

  /**
   * Displays the specified list of playing cards and evaluates the hand.
   */
  public void showHand(List<PlayingCard> cards) {
    if (cards == null || cards.contains(null)) {
    throw new IllegalArgumentException("Cards cannot be null");
    }
    currentCards = cards;
    HandOfCards hand = new HandOfCards(cards);
    handLabel.setText(hand.evaluateHand());
    showCards(cards, deckDisplay, 150);
  }

  public void clearDisplay() {
    currentCards = null;
    deckDisplay.getChildren().clear();
    handLabel.setText("");
    checkDisplay.getChildren().clear();
  }

  public void showHearts(List<PlayingCard> hearts) {
    showCards(hearts, checkDisplay, 60);
  }


  /**
   * Converts face value to card name.
   * @param face the face value of the card (1 to 13)
   * @return the name of the card corresponding to the face value
   * @throws IllegalArgumentException if the face value is not between 1 and 13
   */
  private String faceToName(int face) {
    if (face < 1 || face > 13) {
      throw new IllegalArgumentException("Invalid face value: " + face);
    }
    return switch (face) {
      case 1 -> "ace";
      case 11 -> "jack";
      case 12 -> "queen";
      case 13 -> "king";
      default -> String.valueOf(face);
    };
  }

  /**
   * Converts suit character to name.
   * @param suit the suit character of the card ('S', 'H', 'D', or 'C')
   * @return the name of the card corresponding to the suit character
   * @throws IllegalArgumentException if the suit character is not valid
   */
  private String suitToName(char suit) {
    if (suit != 'S' && suit != 'H' && suit != 'D' && suit != 'C') {
      throw new IllegalArgumentException("Invalid suit: " + suit);
    }
    return switch (suit) {
      case 'S' -> "spades";
      case 'H' -> "hearts";
      case 'D' -> "diamonds";
      case 'C' -> "clubs";
      default -> throw new IllegalArgumentException("Invalid suit: " + suit);
    };
  }

}