package edu.ntnu.idi.idatt2003.oving5;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
    if (handOfCards == null) {
        throw new IllegalArgumentException("Hand of cards cannot be null");
    }
    for (PlayingCard card : handOfCards) {
        if (card == null) throw new IllegalArgumentException("Card cannot be null");
    }
    // defensive copy
    this.handOfCards = new ArrayList<>(handOfCards);
  }

  /**
   * Returns the list of playing cards in the hand.
   * @return the list of playing cards in the hand
   */
  public List<PlayingCard> getHandOfCards() {
    return new ArrayList<>(handOfCards);
  }

  /**
   * Evaluates the hand of cards and returns a string representation of
   * the best possible poker hand that can be made with the cards in the hand.
   * The method checks for each possible poker hand in order of rank, 
   * starting with the highest rank (royal flush) and ending with the lowest rank (high card).

   * @return a {@code String} representation of the best possible poker hand 
   * that can be made with the cards in the hand
   */
  public String evaluateHand() {
    if (hasRoyalFlush()) return "Royal Flush";
    if (hasStraightFlush()) return "Straight Flush";
    if (hasFourOfAKind()) return "Four of a Kind";
    if (hasFullHouse()) return "Full House";
    if (hasFlush()) return "Flush";
    if (hasStraight()) return "Straight";
    if (hasThreeOfAKind()) return "Three of a Kind";
    if (hasTwoPair()) return "Two Pair";
    if (hasPair()) return "Pair";
    return "High Card";
}

  /**
  * Returns the sum of the face values of the cards in the hand. 
  * The face value of a card is an integer that represents the rank of the card,
  * with Ace being 1, Jack being 11, Queen being 12, and King being 13.
  * @return the sum of the face values of the cards in the hand
  */
  public int getSumOfFaces() {
    return handOfCards.stream().mapToInt(PlayingCard::getFace).sum();
  }

  /**
   * Returns a map of face values to their counts in the hand. The keys of the map are the face values of the cards (1 to 13), and the values are the number of
   * cards in the hand with that face value.
   * @return a map of face values to their counts in the hand
   */
  private Map<Integer, Long> getFaceCounts() {
    return handOfCards.stream()
            .collect(Collectors.groupingBy(
                    PlayingCard::getFace,
                    Collectors.counting()
            ));
}

/**
 * Returns a map of suits to their counts in the hand. 
 * The keys of the map are the suits of the cards ('S', 'H', 'D', 'C'), and the values are the number of
 * cards in the hand with that suit.
 * @return a map of suits to their counts in the hand
 */
  private Map<Character, Long> getSuitCounts() {
    return handOfCards.stream()
            .collect(Collectors.groupingBy(
                    PlayingCard::getSuit,
                    Collectors.counting()));
  }

  /**
   * Returns a sorted list of the face values of the cards in the hand, 
   * treating Ace as either low (1) or high (14) depending on the value of aceHigh.
   * If aceHigh is true, Ace is treated as high and its face value is considered to be 14. 
   * If aceHigh is false, Ace is treated as low and its face value is considered to be 1.
   * The returned list is sorted in ascending order.
   * @param aceHigh a boolean value that determines whether Ace is treated as high or low
   * @return a sorted list of the face values of the cards in the hand, with Ace treated as either high or low depending on the value of aceHigh
   */
  private List<Integer> getSortedFaces(boolean aceHigh) {
    List<Integer> sorted = new ArrayList<>();
    for (PlayingCard card : handOfCards) {
        int face = card.getFace();
        if (aceHigh && face == 1) face = 14; // treat Ace as high
        sorted.add(face);
    }
    Collections.sort(sorted);
    return sorted;
}

  /**
   * Returns true if the hand contains a pair, which is a hand that contains two cards with the same face value.
   * A pair is a common hand in poker, and is ranked higher than a high card but lower than two pair.
   * @return true if the hand contains a pair, false otherwise
   */
  public boolean hasPair() {
    return getFaceCounts().values().contains(2L);
  }

  /**
   * Returns true if the hand contains two pair, which is a hand that contains two different pairs of cards with the same face value.
   * Two pair is a hand in poker that is ranked higher than a pair but lower than three of a kind.
   * @return true if the hand contains two pair, false otherwise
   */
  public boolean hasTwoPair() {
    return Collections.frequency(getFaceCounts().values(), 2L) == 2;
  }

  /**
   * Returns true if the hand contains three of a kind, which is a hand that contains three cards with the same face value.
   * Three of a kind is a hand in poker that is ranked higher than two pair but
   * lower than a straight.
   * @return true if the hand contains three of a kind, false otherwise
   */
  public boolean hasThreeOfAKind() {
    return Collections.frequency(getFaceCounts().values(), 3L) == 1;
  }

  /**
   * Returns true if the hand contains a straight, which is a hand that contains five cards with consecutive face values, regardless of their suits.
   * A straight is a hand in poker that is ranked higher than three of a kind but lower than a flush.
   * @return true if the hand contains a straight, false otherwise
   */
  public boolean hasStraight() {
    List<Integer> straight = getSortedFaces(true);
    int handSize = straight.size();
    for (int i = 0; i < handSize - 1 ; i++) {
      if (straight.get(i+1) != straight.get(i) + 1) {
        return false;
      }
    } return true;
  }

  /**
   * Returns true if the hand contains a flush, which is a hand that contains five cards of the same suit, regardless of their face values.
   * A flush is a hand in poker that is ranked higher than a straight but lower than a full house. 
   * @return true if the hand contains a flush, false otherwise
   */
  public boolean hasFlush() {
    return Collections.frequency(getSuitCounts().values(), 5L) == 1;
  }

  /**
   * Returns true if the hand contains a full house, which is a hand that contains three cards with the 
   * same face value and two cards with another face value.
   * A full house is a hand in poker that is ranked higher than a flush but lower than four of a kind. 
   * @return true if the hand contains a full house, false otherwise
   */
  public boolean hasFullHouse() {
    return getFaceCounts().values().contains(3L) && 
          getFaceCounts().values().contains(2L);
  }

  /**
   * Returns true if the hand contains four of a kind, which is a hand that contains four cards with the same face value.
   * Four of a kind is a hand in poker that is ranked higher than a full house but lower than a straight flush.
   * @return true if the hand contains four of a kind, false otherwise
   */
  public boolean hasFourOfAKind() {
    return Collections.frequency(getFaceCounts().values(), 4L) == 1;
  }

  /**
   * Returns true if the hand contains a straight flush, which is a hand that contains five cards with consecutive face values, all of the same suit.
   * A straight flush is a hand in poker that is ranked higher than four of a kind but lower than a royal flush.
   * @return true if the hand contains a straight flush, false otherwise
   */
  public boolean hasStraightFlush() {
    return hasStraight() && hasFlush();
  }

  /**
   * Returns true if the hand contains a royal flush, which is a straight flush consisting of the cards 10, Jack (11), Queen (12), King (13) and Ace (14).
   * A royal flush is the highest possible hand in poker, and is a special case of a straight flush. 
   * @return true if the hand contains a royal flush, false otherwise
   */
  public boolean hasRoyalFlush() {
    if (!hasFlush()) return false;
    List<Integer> sorted = getSortedFaces(true);
    return sorted.equals(List.of(10, 11, 12, 13, 14));
  }
}
