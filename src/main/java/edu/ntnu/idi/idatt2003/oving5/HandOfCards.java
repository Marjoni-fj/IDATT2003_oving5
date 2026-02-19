package edu.ntnu.idi.idatt2003.oving5;

public class HandOfCards {
  private final PlayingCard[] handOfCards;

    public HandOfCards(PlayingCard[] cards) {
        this.handOfCards = cards;
    }

    public PlayingCard[] getCards() {
        return handOfCards;
    }

    public int sumOfCardValues() {
        int sum = 0;
        for (PlayingCard card : handOfCards) {
            sum += card.getFace();
        }
        return sum;
    }

    public boolean hasPair() {
      for (int i = 0; i < handOfCards.length; i++) {
          for (int j = i + 1; j < handOfCards.length; j++) {
              if (handOfCards[i].getFace() == handOfCards[j].getFace()) {
                  return true;
              }
          }
      }
      return false;
    }

    public boolean hasThreeOfAKind() {
      for (PlayingCard card : handOfCards) {
          int count = 0;
          for (PlayingCard otherCard : handOfCards) {
              if (card.getFace() == otherCard.getFace()) {
                  count++;
              }
          }
          if (count == 3) {
              return true;
          }
      }
      return false;
    }

}
