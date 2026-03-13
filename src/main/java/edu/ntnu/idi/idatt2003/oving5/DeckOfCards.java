package edu.ntnu.idi.idatt2003.oving5;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Represents a deck of playing cards. A deck of cards consists of 52 unique
 * playing cards, one for each combination of the 4 suits and 13 faces.
 * The deck can be used to deal hands of cards, which are represented by the {@code HandOfCards} class.
 * The deck is initialized with all 52 cards, and the {@code dealHand} method can be used to shuffle the deck and deal a specified number of cards from the top of the deck.
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
   * Deals a hand of n cards from the deck.
   * @param n the number of cards to deal
   * @return a list of n unique playing cards from the deck
   * @throws IllegalArgumentException if n is negative or greater than the number of cards in the deck
   */
  public List<PlayingCard> dealHand(int n) {
    if (n < 0 || n > deckOfCards.size()) {
        throw new IllegalArgumentException("Number of cards to deal must be between 0 and " + deckOfCards.size());
    }
    Collections.shuffle(deckOfCards);
    return new ArrayList<>(deckOfCards.subList(0, n));
    }
}
