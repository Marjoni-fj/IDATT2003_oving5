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
  private List<PlayingCard> currentCards;

  /**
   * Creates a new {@code CardDisplay} with the specified {@code HBox} for displaying the cards and
   * {@code Label} for displaying the best possible poker hand.
   */
  public CardDisplay(HBox deckDisplay, Label handLabel) {
    ArgumentValidator.validateCardDisplayArguments(deckDisplay, handLabel);
    this.deckDisplay = deckDisplay;
    this.handLabel = handLabel;
  }

  /**
   * Returns the currently displayed cards.
   * @return the list of currently displayed playing cards
   */
  public List<PlayingCard> getCurrentCards() {
    return currentCards;
  }

  /**
   * Displays the specified list of playing cards and evaluates the hand.
   * @param cards the list of playing cards to display
   * @throws IllegalArgumentException if the list of cards is null or contains null cards
   */
  public void showHand(List<PlayingCard> cards, String handText) {
  ArgumentValidator.validateHandOfCards(cards);
  currentCards = cards;
  handLabel.setText(handText);
  showCards(cards, deckDisplay, 150);
}

  /**
   * Clears the card display and resets the current cards.
   */
  public void clearDisplay() {
    currentCards = null;
    deckDisplay.getChildren().clear();
    handLabel.setText("");
  }

  /**
   * Displays the specified list of playing cards in the specified container with the specified height.
   * 
   * @param cards the list of playing cards to display
   * @param container the container to display the cards in
   * @param height the height of each card image
   * @throws IllegalArgumentException if the list of cards is null or contains null cards, 
   * if the container is null, or if the height is not positive
   */
  private void showCards(List<PlayingCard> cards, HBox container, int height) {
    ArgumentValidator.validateHandOfCards(cards);
    ArgumentValidator.validateShowCard(container, height);

    container.getChildren().clear();
    for (PlayingCard card : cards) {
      try {
        ImageView image = createCardImage(card, height);
        container.getChildren().add(image);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }


  /**
   * Creates an ImageView for the specified playing card with the specified height.
   * @param card the playing card to create an image for 
   * @param height the height of the card image
   * @return an ImageView containing the image of the specified playing card
   * @throws Exception if there is an error loading or rendering the SVG image for the card
   */
  private ImageView createCardImage(PlayingCard card, int height) throws Exception {
    ArgumentValidator.validateCardImageCreation(card, height);

    String filePath = "/cards/" + faceToName(card.getFace()) +
        "_of_" + suitToName(card.getSuit()) + ".svg";
    URL url = getClass().getResource(filePath);
    if (url == null) {
      throw new RuntimeException("File not found: " + filePath);
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

    return imageView;
  }

  /**
   * Converts face value to card name.
   * @param face the face value of the card (1 to 13)
   * @return the name of the card corresponding to the face value
   * @throws IllegalArgumentException if the face value is not between 1 and 13
   */
  private String faceToName(int face) {
    ArgumentValidator.validateFace(face);
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
    ArgumentValidator.validateSuit(suit);
    return switch (suit) {
      case 'S' -> "spades";
      case 'H' -> "hearts";
      case 'D' -> "diamonds";
      case 'C' -> "clubs";
      default -> throw new IllegalArgumentException("Invalid suit: " + suit);
    };
  }

}