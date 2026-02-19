package edu.ntnu.idi.idatt2003.oving5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayingCardTest {

  private PlayingCard testCard;
  private PlayingCard testCard2;

  @BeforeEach
  void setUp() {
    testCard = new PlayingCard('H', 4);
    testCard2 = new PlayingCard('S', 1);
  }

  @Test
  void testGetAsString() {
    assertEquals("H4", testCard.getAsString());
    assertEquals("S1", testCard2.getAsString());
  }

  @Test
  void testGetSuit() {
    assertEquals('H', testCard.getSuit());
    assertEquals('S', testCard2.getSuit());
  }

  @Test
  void testGetValue() {
    assertEquals(4, testCard.getFace());
    assertEquals(1, testCard2.getFace());
  }

  @Test
  void testIllegalCard() {
    assertThrows(IllegalArgumentException.class, () -> new PlayingCard('G', 12));
    assertThrows(IllegalArgumentException.class, () -> new PlayingCard('C', 0));
  }
}
