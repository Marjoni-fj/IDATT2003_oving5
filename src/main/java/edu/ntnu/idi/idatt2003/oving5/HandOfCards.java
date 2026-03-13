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
  private final List<PlayingCard> handOfCards;

  /**
   * Creates a new hand of cards with the specified list of playing cards.
   * @param handOfCards the list of playing cards in the hand
  */
  public HandOfCards(List<PlayingCard> handOfCards) {
    ArgumentValidator.validateHandOfCards(handOfCards);
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
  * @return the sum of the face values of the cards in the hand
  */
  public int getSumOfFaces() {
    return handOfCards.stream().mapToInt(PlayingCard::getFace).sum();
  }

  /**
   * Returns true if the hand contains the Queen of Spades, which is a card with the suit 'S' and the face value 12.
   * @return true if the hand contains the Queen of Spades, false otherwise
   */
  public boolean hasQueenOfSpades() {
    return handOfCards.stream().anyMatch(card -> card.getFace() == 12 && card.getSuit() == 'S');
  }

  /**
   * Returns a list of the cards in the hand that have the specified suit. 
   * The suit is represented by a single character: 'S' for Spades, 'H' for Hearts, 'D' for Diamonds, and 'C' for Clubs.
   * @param suit the suit to search for
   * @return a list of cards with the specified suit
   */
  public List<PlayingCard> getCardsContaining(char suit) {
    ArgumentValidator.validateSuit(suit);
    return handOfCards.stream().filter(card -> card.getSuit() == suit).collect(Collectors.toList());
  }

  /**
   * Returns a map of face values to their counts in the hand.
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
 * @return a map of suits to their counts in the hand
 */
  private Map<Character, Long> getSuitCounts() {
    return handOfCards.stream()
            .collect(Collectors.groupingBy(
                    PlayingCard::getSuit,
                    Collectors.counting()));
  }

  /**
   * Returns a sorted list of the face values of the cards in the hand in ascending order, 
   * treating Ace as either low (1) or high (14) depending on the value of aceHigh.
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
   * Returns true if the hand contains a pair.
   * @return true if the hand contains a pair, false otherwise
   */
  public boolean hasPair() {
    return getFaceCounts().values().contains(2L);
  }

  /**
   * Returns true if the hand contains two pair.
   * @return true if the hand contains two pair, false otherwise
   */
  public boolean hasTwoPair() {
    return Collections.frequency(getFaceCounts().values(), 2L) == 2;
  }

  /**
   * Returns true if the hand contains three of a kind.
   * @return true if the hand contains three of a kind, false otherwise
   */
  public boolean hasThreeOfAKind() {
    return Collections.frequency(getFaceCounts().values(), 3L) == 1;
  }

  /**
   * Returns true if the hand contains a straight.
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
   * Returns true if the hand contains a flush. 
   * @return true if the hand contains a flush, false otherwise
   */
  public boolean hasFlush() {
    return Collections.frequency(getSuitCounts().values(), 5L) == 1;
  }

  /**
   * Returns true if the hand contains a full house. 
   * @return true if the hand contains a full house, false otherwise
   */
  public boolean hasFullHouse() {
    return getFaceCounts().values().contains(3L) && 
          getFaceCounts().values().contains(2L);
  }

  /**
   * Returns true if the hand contains four of a kind.
   * @return true if the hand contains four of a kind, false otherwise
   */
  public boolean hasFourOfAKind() {
    return Collections.frequency(getFaceCounts().values(), 4L) == 1;
  }

  /**
   * Returns true if the hand contains a straight flush.
   * @return true if the hand contains a straight flush, false otherwise
   */
  public boolean hasStraightFlush() {
    return hasStraight() && hasFlush();
  }

  /**
   * Returns true if the hand contains a royal flush. 
   * @return true if the hand contains a royal flush, false otherwise
   */
  public boolean hasRoyalFlush() {
    if (!hasFlush()) return false;
    List<Integer> sorted = getSortedFaces(true);
    return sorted.equals(List.of(10, 11, 12, 13, 14));
  }
}
