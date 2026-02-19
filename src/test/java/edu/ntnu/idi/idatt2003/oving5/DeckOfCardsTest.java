package edu.ntnu.idi.idatt2003.oving5;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckOfCardsTest {
  private DeckOfCards deck;

  @BeforeEach
  void setUp() {
    deck = new DeckOfCards();
  }
}
