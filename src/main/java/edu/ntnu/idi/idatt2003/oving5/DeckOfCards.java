package edu.ntnu.idi.idatt2003.oving5;
import java.util.Random;
/**
 * Represents a deck of playing cards. A deck of cards consists of 52 unique
 * playing cards, one for each combination of the 4 suits and 13 faces.
 */
public class DeckOfCards {
  private final PlayingCard[] deckOfCards;
  private final char[] suit = { 'S', 'H', 'D', 'C' }; 

  /**
   * Creates a new deck of cards, consisting of 52 unique playing cards, one for
   * each combination of the 4 suits and 13 faces.
   */
  public DeckOfCards() {
    deckOfCards = new PlayingCard[52];
    int index = 0;
    for (char s : suit) {
      for (int i = 1; i <= 13; i++) {
        deckOfCards[index] = new PlayingCard(s, i);
        index++;
      }
    }
  }

  /**
   * Deals a hand of n random cards from the deck. The same card may be dealt
   * multiple times, since the method does not remove cards from the deck.
   * @param n the number of cards to deal
   * @return an array of n random playing cards from the deck
   * @throws IllegalArgumentException if n is negative or greater than the number of cards in the deck
   */
  public PlayingCard[] dealHand(int n) {
    if (n < 0 || n > deckOfCards.length) {
      throw new IllegalArgumentException("Number of cards to deal must be between 0 and " + deckOfCards.length);
    }
    Random random = new Random(deckOfCards.length);
    PlayingCard[] hand = new PlayingCard[n];
    for (int i = 0; i < n; i++) {
      int randomCardIndex = random.nextInt(deckOfCards.length);
      hand[i] = deckOfCards[randomCardIndex];
    }
    return hand;
  }
}
