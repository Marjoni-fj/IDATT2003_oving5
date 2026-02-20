package edu.ntnu.idi.idatt2003.oving5;
import java.util.List;
/**
 * Represents a hand of playing cards. A hand of cards consists of a list of
 * playing cards, which may be empty or contain any number of cards.
 */
public class HandOfCards {
  private List<PlayingCard> handOfCards;

  /**
   * Creates a new hand of cards with the specified list of playing cards.
   * @param handOfCards the list of playing cards in the hand
  */
  public HandOfCards(List<PlayingCard> handOfCards) {
    if (handOfCards == null || handOfCards.contains(null)) {
      throw new IllegalArgumentException("Hand of cards cannot be null or contain null cards");
    }
    this.handOfCards = handOfCards;
  }

  /**
   * Returns the list of playing cards in the hand.
   * @return the list of playing cards in the hand
   */
  public List<PlayingCard> getHandOfCards() {
    return handOfCards;

  
}
}