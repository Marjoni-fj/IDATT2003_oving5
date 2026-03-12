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

  /**
   * Creates a new {@code CardDisplay} with the specified {@code HBox} for displaying the cards and
   * {@code Label} for displaying the best possible poker hand. The {@code HBox} and {@code Label} are
   * provided by the caller and are used to display the cards and hand evaluation results.
   * @param deckDisplay the {@code HBox} for displaying the cards
   * @param handLabel the {@code Label} for displaying the best possible poker hand
   * @throws IllegalArgumentException if either {@code deckDisplay} or {@code handLabel} is null
   */
  public CardDisplay(HBox deckDisplay, Label handLabel) {
    if (deckDisplay == null) {
        throw new IllegalArgumentException("Deck display cannot be null");
    }
    if (handLabel == null) {
        throw new IllegalArgumentException("Hand label cannot be null");
    }
    this.deckDisplay = deckDisplay;
    this.handLabel = handLabel;
  }

  /**
   * Displays the specified list of playing cards in the {@code HBox} and updates the {@code Label} to show
   * the best possible poker hand that can be made with the cards in the hand. The method clears any existing
   * cards from the {@code HBox}, evaluates the hand of cards, and then loads and renders the SVG images for each card
   * to display them in the {@code HBox}. 
   * The method also updates the {@code Label} to show the best possible poker hand that can be made with the cards in the hand.
   * @param cards the list of playing cards to display
   * @throws IllegalArgumentException if {@code cards} is null or contains any null elements
   */
  public void showHand(List<PlayingCard> cards) {
    deckDisplay.getChildren().clear();
    HandOfCards hand = new HandOfCards(cards);
    handLabel.setText(hand.evaluateHand());

    for (PlayingCard card : cards) {
      try {
        String filePath = "/cards/" + faceToName(card.getFace()) + "_of_" + suitToName(card.getSuit()) + ".svg";
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

        ImageView imageView = new ImageView(SwingFXUtils.toFXImage(bufferedImage, null));
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);
        deckDisplay.getChildren().add(imageView);

      } catch (Exception ex) {
          ex.printStackTrace();
      }
    }
  }

  /**
   * Converts a face value to its corresponding name. 
   * For example, 1 is converted to "ace", 11 to "jack", 12 to "queen", and 13 to "king".
   * @param face the face value to convert
   * @return the name corresponding to the face value
   * @throws IllegalArgumentException if the face value is not between 1 and 13 (inclusive)
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
   * Converts a suit character to its corresponding name.
   * For example, 'S' is converted to "spades", 'H' to "hearts", 'D' to "diamonds", and 'C' to "clubs".
   * @param suit the suit character to convert
   * @return the name corresponding to the suit character
   * @throws IllegalArgumentException if the suit character is not one of 'S', 'H', 'D', or 'C'
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
