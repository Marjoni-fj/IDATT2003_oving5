package edu.ntnu.idi.idatt2003.oving5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayingCardTest {

  private PlayingCard testCard;
  private PlayingCard testCard2;
  private PlayingCard identicalCard;

  @BeforeEach
  void setUp() {
    testCard = new PlayingCard('H', 4);
    testCard2 = new PlayingCard('S', 1);
    identicalCard = new PlayingCard('H', 4);

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

  @Test
  void testIdenticalCards() {
    assertTrue(testCard.equals(identicalCard));
    assertEquals(testCard.getAsString(), identicalCard.getAsString());
    assertEquals(testCard.getSuit(), identicalCard.getSuit());
    assertEquals(testCard.getFace(), identicalCard.getFace());
  }

  @Test
  void testNonIdenticalCards() {
    assertFalse(testCard.equals(testCard2));
    assertNotEquals(testCard.getAsString(), testCard2.getAsString());
    assertNotEquals(testCard.getSuit(), testCard2.getSuit());
    assertNotEquals(testCard.getFace(), testCard2.getFace());
  }
}
