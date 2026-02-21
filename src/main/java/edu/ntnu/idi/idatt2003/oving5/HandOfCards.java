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
   * Returns a sorted list of the face values of the cards in the hand. 
   * The list is sorted in ascending order, and may contain duplicate values 
   * if there are multiple cards with the same face value in the hand.
   * @return a sorted list of the face values of the cards in the hand
   */
  private List<Integer> getSortedFaces() {
    return handOfCards.stream()
            .map(PlayingCard::getFace).sorted()
            .collect(Collectors.toList());
  }

  /**
   * Returns a sorted list of the face values of the cards in the hand, with Aces (face value 1) treated as 14.
   * The list is sorted in ascending order, and may contain duplicate values
   * if there are multiple cards with the same face value in the hand. If the hand contains an Ace (face value 1), 
   * it is treated as having a face value of 14 for the purposes of sorting and evaluating straights.
   * @return a sorted list of the face values of the cards in the hand, with Aces treated as 14
   */
  public List<Integer> hasAce() {
    List<Integer> sorted = new ArrayList<>(getSortedFaces());
    if (sorted.contains(1)) {
      sorted.remove(Integer.valueOf(1));
      sorted.add(14);
      Collections.sort(sorted);
    }
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
    List<Integer> straight = hasAce();
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
    List<Integer> sorted = hasAce();
    return sorted.equals(List.of(10, 11, 12, 13, 14));
  }
}
