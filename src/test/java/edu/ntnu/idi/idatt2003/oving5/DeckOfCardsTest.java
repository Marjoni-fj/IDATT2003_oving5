package edu.ntnu.idi.idatt2003.oving5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeckOfCardsTest {
  private DeckOfCards deck;

  @BeforeEach
  void setUp() {
    deck = new DeckOfCards();
  }

  @Test
  void testDeckSize() {
    assertEquals(52, deck.dealHand(52).size());
  }

  @Test
  void testDealHandSize() {
    assertEquals(5, deck.dealHand(5).size());
  }

  @Test
  void testDealHandUniqueness() {
    var hand = deck.dealHand(5);
    assertEquals(5, hand.size());
    assertEquals(5, hand.stream().distinct().count());
  }

  @Test
  void testDealHandInvalidSize() {
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(-1));
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(53));
  }
}
