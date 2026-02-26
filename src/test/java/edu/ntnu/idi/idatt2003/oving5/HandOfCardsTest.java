package edu.ntnu.idi.idatt2003.oving5;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.List;

public class HandOfCardsTest {
  private static HandOfCards royalFlush() {
    return new HandOfCards(List.of(
            new PlayingCard('H', 10),
            new PlayingCard('H', 11),
            new PlayingCard('H', 12),
            new PlayingCard('H', 13),
            new PlayingCard('H', 1)
    ));
  }

  private static HandOfCards straightFlush() {
      return new HandOfCards(List.of(
              new PlayingCard('S', 5),
              new PlayingCard('S', 6),
              new PlayingCard('S', 7),
              new PlayingCard('S', 8),
              new PlayingCard('S', 9)
      ));
  }

  private static HandOfCards fourOfAKind() {
      return new HandOfCards(List.of(
              new PlayingCard('H', 8),
              new PlayingCard('D', 8),
              new PlayingCard('S', 8),
              new PlayingCard('C', 8),
              new PlayingCard('H', 2)
      ));
  }

  private static HandOfCards fullHouse() {
      return new HandOfCards(List.of(
              new PlayingCard('H', 4),
              new PlayingCard('D', 4),
              new PlayingCard('S', 4),
              new PlayingCard('C', 9),
              new PlayingCard('H', 9)
      ));
  }

  private static HandOfCards flush() {
      return new HandOfCards(List.of(
              new PlayingCard('C', 2),
              new PlayingCard('C', 5),
              new PlayingCard('C', 8),
              new PlayingCard('C', 11),
              new PlayingCard('C', 13)
      ));
  }

  private static HandOfCards straight() {
      return new HandOfCards(List.of(
              new PlayingCard('H', 3),
              new PlayingCard('D', 4),
              new PlayingCard('S', 5),
              new PlayingCard('C', 6),
              new PlayingCard('H', 7)
      ));
  }

  private static HandOfCards threeOfAKind() {
      return new HandOfCards(List.of(
              new PlayingCard('H', 6),
              new PlayingCard('D', 6),
              new PlayingCard('S', 6),
              new PlayingCard('C', 9),
              new PlayingCard('H', 12)
      ));
  }

  private static HandOfCards twoPair() {
      return new HandOfCards(List.of(
              new PlayingCard('H', 5),
              new PlayingCard('D', 5),
              new PlayingCard('S', 9),
              new PlayingCard('C', 9),
              new PlayingCard('H', 13)
      ));
  }

  private static HandOfCards pair() {
      return new HandOfCards(List.of(
              new PlayingCard('H', 7),
              new PlayingCard('D', 7),
              new PlayingCard('S', 3),
              new PlayingCard('C', 10),
              new PlayingCard('H', 12)
      ));
  }

  private static HandOfCards highCard() {
      return new HandOfCards(List.of(
              new PlayingCard('H', 2),
              new PlayingCard('D', 5),
              new PlayingCard('S', 8),
              new PlayingCard('C', 11),
              new PlayingCard('H', 13)
      ));
  }

  @Test
  void testRoyalFlush() {
      assertEquals("Royal Flush", royalFlush().evaluateHand());
  }
  @Test
  void testnotRoyalFlush() {
      assertNotEquals("Royal Flush", straightFlush().evaluateHand());
  }

  @Test
  void testStraightFlush() {
      assertEquals("Straight Flush", straightFlush().evaluateHand());
  }
  @Test
  void testnotStraightFlush() {
      assertNotEquals("Straight Flush", fourOfAKind().evaluateHand());
  }

  @Test
  void testFourOfAKind() {
      assertEquals("Four of a Kind", fourOfAKind().evaluateHand());
  }
  @Test
  void testnotFourOfAKind() {
      assertNotEquals("Four of a Kind", fullHouse().evaluateHand());
  }

  @Test
  void testFullHouse() {
      assertEquals("Full House", fullHouse().evaluateHand());
  }
  @Test
  void testnotFullHouse() {
      assertNotEquals("Full House", flush().evaluateHand());
  }

  @Test
  void testFlush() {
      assertEquals("Flush", flush().evaluateHand());
  }
  @Test
  void testnotFlush() {
      assertNotEquals("Flush", straight().evaluateHand());
  }

  @Test
  void testStraight() {
      assertEquals("Straight", straight().evaluateHand());
  }
  @Test
  void testnotStraight() {
      assertNotEquals("Straight", threeOfAKind().evaluateHand());
  }

  @Test
  void testThreeOfAKind() {
      assertEquals("Three of a Kind", threeOfAKind().evaluateHand());
  }
  @Test
  void testnotThreeOfAKind() {
      assertNotEquals("Three of a Kind", twoPair().evaluateHand());
  }

  @Test
  void testTwoPair() {
      assertEquals("Two Pair", twoPair().evaluateHand());
  }
  @Test
  void testnotTwoPair() {
      assertNotEquals("Two Pair", pair().evaluateHand());
  }

  @Test
  void testPair() {
      assertEquals("Pair", pair().evaluateHand());
  }
  @Test
  void testnotPair() {
      assertNotEquals("Pair", highCard().evaluateHand());
  }

  @Test
  void testHighCard() {
      assertEquals("High Card", highCard().evaluateHand());
  }
  @Test
  void testnotHighCard() {
      assertNotEquals("High Card", royalFlush().evaluateHand());
  }
}
