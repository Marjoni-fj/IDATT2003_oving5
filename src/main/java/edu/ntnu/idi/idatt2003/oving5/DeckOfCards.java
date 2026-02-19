package edu.ntnu.idi.idatt2003.oving5;
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
}
