package edu.ntnu.idi.idatt2003.oving5;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
/**
 * The {@code ArgumentValidator} class provides static methods for validating the arguments passed to the constructors and methods of the other classes in the application.
 * It checks for null values, invalid suit characters, invalid face values, and other conditions that would indicate that the arguments are not valid. 
 * If an argument is found to be invalid, the corresponding method throws an {@code IllegalArgumentException} with a descriptive error message.
 * This class is used to ensure that the application behaves correctly and does not encounter unexpected errors due to invalid input.
 */
public class ArgumentValidator {

  /**
   * Validates that the given suit is one of 'H', 'D', 'C' or 'S'.
   *
   * @param suit the suit to validate
   * @throws IllegalArgumentException if the suit is not valid
   */
  public static void validateSuit(char suit) {
    if (suit != 'H' && suit != 'D' && suit != 'C' && suit != 'S') {
      throw new IllegalArgumentException("Parameter suit must be one of H, D, C or S");
    }
  }

  /**
   * Validates that the given face value is between 1 and 13.
   *
   * @param face the face value to validate
   * @throws IllegalArgumentException if the face value is not valid
   */
  public static void validateFace(int face) {
    if (face < 1 || face > 13) {
      throw new IllegalArgumentException("Parameter face must be a number between 1 to 13");
    }
  }

  /**
   * Validates that the given list of playing cards is not null and does not contain any null cards.
   * 
   * @param handOfCards the list of playing cards to validate
   * @throws IllegalArgumentException if the list of cards is null or contains null cards
   */
  public static void validateHandOfCards(List<PlayingCard> handOfCards) {
  if (handOfCards == null) {
        throw new IllegalArgumentException("Hand of cards cannot be null");
    }
    for (PlayingCard card : handOfCards) {
        if (card == null) throw new IllegalArgumentException("Hand of cards contains an illegal null card");
    }
  }

  /**
   * Validates that the given {@code HBox} and {@code Label} are not null.
   * 
   * @param deckDisplay the {@code HBox} for displaying the cards
   * @param handLabel the {@code Label} for displaying the best possible poker hand
   * @throws IllegalArgumentException if either the {@code HBox} or {@code Label} is null
   */
  public static void validateCardDisplayArguments(HBox deckDisplay, Label handLabel) {
    if (deckDisplay == null) {
      throw new IllegalArgumentException("Deck display cannot be null");
    }
    if (handLabel == null) {
      throw new IllegalArgumentException("Hand label cannot be null");
    }
  }

  /**
   * Validates that the given {@code PlayingCard} is not null and that the given height is positive.
   * 
   * @param card the {@code PlayingCard} to validate
   * @param height the height to validate
   * @throws IllegalArgumentException if the card is null or if the height is not positive
   */
  public static void validateCardImageCreation(PlayingCard card, int height) {
    if (card == null) {
      throw new IllegalArgumentException("Card cannot be null");
    }
    if (height <= 0) {
      throw new IllegalArgumentException("Height must be positive");
    }
  }

  /**
   * Validates that the given {@code HBox} is not null and that the given height is positive.
   * 
   * @param container the {@code HBox} to validate
   * @param height the height to validate
   * @throws IllegalArgumentException if the container is null or if the height is not positive
   */
  public static void validateShowCard(HBox container, int height) {
    if (container == null) {
      throw new IllegalArgumentException("Container cannot be null");
    }
    if (height <= 0) {
      throw new IllegalArgumentException("Height must be positive");
    }
  }

}
