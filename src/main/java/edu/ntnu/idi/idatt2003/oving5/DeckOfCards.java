package edu.ntnu.idi.idatt2003.oving5;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Represents a deck of playing cards. A deck of cards consists of 52 unique
 * playing cards, one for each combination of the 4 suits and 13 faces.
 */
public class DeckOfCards {
  private final List<PlayingCard> deckOfCards;
  private final char[] suit = { 'S', 'H', 'D', 'C' }; 

  /**
   * Creates a new deck of cards, consisting of 52 unique playing cards, one for
   * each combination of the 4 suits and 13 faces.
   */
  public DeckOfCards() {
    deckOfCards = new ArrayList<>();
    for (char suit : suit) {
      for (int face = 1; face <= 13; face++) {
        deckOfCards.add(new PlayingCard(suit, face));
      }
    }
  }

  /**
   * Deals a hand of n cards from the deck. The method shuffles the deck before
   * dealing the cards, and returns a list of n unique playing cards. If n is
   * less than 0 or greater than the number of cards in the deck, the method throws
   * an IllegalArgumentException.
   * @param n the number of cards to deal
   * @return a list of n unique playing cards from the deck
   */
  public List<PlayingCard> dealHand(int n) {
    if (n < 0 || n > deckOfCards.size()) {
        throw new IllegalArgumentException("Number of cards to deal must be between 0 and " + deckOfCards.size());
    }
    Collections.shuffle(deckOfCards);
    return new ArrayList<>(deckOfCards.subList(0, n));
    }
}
