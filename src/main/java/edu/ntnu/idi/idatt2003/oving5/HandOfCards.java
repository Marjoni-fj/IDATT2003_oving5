package edu.ntnu.idi.idatt2003.oving5;

public class HandOfCards {
  private DeckOfCards deckOfCards;
  private PlayingCard[] handOfCards;


  public HandOfCards() {
    this.deckOfCards = new DeckOfCards();
    this.handOfCards = deckOfCards.dealHand(5);
  }

}
