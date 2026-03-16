package edu.ntnu.idi.idatt2003.oving5;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ArgumentValidatorTest {
  
  @BeforeAll
  static void initJavaFX() {
      new JFXPanel();
  }

  @Test
  void testValidateSuitThrows() {
    IllegalArgumentException exception = 
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidator.validateSuit('X'));
    assertEquals("Parameter suit must be one of H, D, C or S", exception.getMessage());
  }

  @Test
  void testValidateSuitDoesNotThrow() {
    assertDoesNotThrow(() -> ArgumentValidator.validateSuit('H'));
    assertDoesNotThrow(() -> ArgumentValidator.validateSuit('D'));
    assertDoesNotThrow(() -> ArgumentValidator.validateSuit('C'));
    assertDoesNotThrow(() -> ArgumentValidator.validateSuit('S'));
  }


  @Test
  void testValidateFaceThrows() {
    IllegalArgumentException exception1 =
    assertThrows(IllegalArgumentException.class, () ->
    ArgumentValidator.validateFace(0));
    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () ->
    ArgumentValidator.validateFace(14));
    assertEquals("Parameter face must be a number between 1 to 13", exception1.getMessage());
    assertEquals("Parameter face must be a number between 1 to 13", exception2.getMessage());
  }

  @Test
  void testValidateFaceDoesNotThrow() {
    assertDoesNotThrow(() -> ArgumentValidator.validateFace(1));
    assertDoesNotThrow(() -> ArgumentValidator.validateFace(13));
  }

  @Test
  void testValidateHandOfCardsThrows() {
    IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () ->
    ArgumentValidator.validateHandOfCards(null));
    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () ->
    ArgumentValidator.validateHandOfCards(Arrays.asList(new PlayingCard('H', 10), null)));

    assertEquals("Hand of cards cannot be null", exception1.getMessage());
    assertEquals("Hand of cards contains an illegal null card", exception2.getMessage());
  }

  @Test
  void testValidateHAndOfCardsDoesNotThrow() {
    assertDoesNotThrow(() -> ArgumentValidator.validateHandOfCards(Arrays.asList(
      new PlayingCard('H', 10),
      new PlayingCard('D', 5),
      new PlayingCard('C', 12)
    )));
  }

  @Test
  void testValidateCardDisplayArgumentsThrows() {
    IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () ->
    ArgumentValidator.validateCardDisplayArguments(null, new Label()));
    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () ->
    ArgumentValidator.validateCardDisplayArguments(new HBox(), null));

    assertEquals("Deck display cannot be null", exception1.getMessage());
    assertEquals("Hand label cannot be null", exception2.getMessage());
  }

  @Test
  void testValidateCardDisplayArgumentsDoesNotThrow() {
    assertDoesNotThrow(() -> ArgumentValidator.validateCardDisplayArguments(new HBox(), new Label()));
  }

  @Test
  void testValidateCardImageCreationThrows() {
    IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () ->
    ArgumentValidator.validateCardImageCreation(null, 100));
    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () ->
    ArgumentValidator.validateCardImageCreation(new PlayingCard('H', 10), -50));

    assertEquals("Card cannot be null", exception1.getMessage());
    assertEquals("Height must be a positive number", exception2.getMessage());
  }

  @Test
  void testValidateCardImageCreationDoesNotThrow() {
    assertDoesNotThrow(() -> ArgumentValidator.validateCardImageCreation(new PlayingCard('H', 10), 100));
  }

  @Test
  void testValidateShowCardThrows() {
    IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () ->
    ArgumentValidator.validateShowCard(null, 100));
    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () ->
    ArgumentValidator.validateShowCard(new HBox(), -50));

    assertEquals("Container cannot be null", exception1.getMessage());
    assertEquals("Height must be a positive number", exception2.getMessage());
  }

  @Test
  void testValidateShowCardDoesNotThrow() {
    assertDoesNotThrow(() -> ArgumentValidator.validateShowCard(new HBox(), 100));
  }
}
