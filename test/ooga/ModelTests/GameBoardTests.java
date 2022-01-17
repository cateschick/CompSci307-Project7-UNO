package ooga.ModelTests;

import java.util.ArrayList;
import java.util.List;
import ooga.Config;
import ooga.model.board.GameBoard;
import ooga.model.board.UnoGameBoard;
import ooga.model.cardcollection.CardCollection;
import ooga.model.cardcollection.DeckCardCollection;
import ooga.model.cardcollection.deck.DrawDeck;
import ooga.model.cardcollection.hand.PlayerHand;
import ooga.model.cards.CardColor;
import ooga.model.cards.cardcomponents.Card;
import ooga.model.exceptions.EmptyCardCollectionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests that test the board module
 *
 * @author Alicia Wu
 */
public class GameBoardTests extends ModelTests {

  /**
   * Test if a game board is made successfully
   */
  @Test
  void makeGameBoardTest() {
    DeckCardCollection drawDeck = new DrawDeck(makeSimpleVariedCardsInfo());
    GameBoard board = new UnoGameBoard(drawDeck);
    Assertions.assertNotNull(board);
  }

  /**
   * Test if the game board correctly sets up the draw deck
   */
  @Test
  void getFromDrawDeckTest() {
    DeckCardCollection drawDeck = new DrawDeck(makeVerySimpleCardsInfo());
    List<CardColor> threeCardColors = new ArrayList<>();
    drawDeck.getAllCards().forEach(card -> threeCardColors.add(card.getCardColor()));
    GameBoard board = new UnoGameBoard(drawDeck);
    Card cardDrawn = board.getFromDrawDeck();
    Assertions.assertTrue(threeCardColors.contains(board.peekPlayDeckTopCard().getCardColor()));
    Assertions.assertTrue(threeCardColors.contains(cardDrawn.getCardColor()));
  }

  /**
   * Test if the game board is initialized with one card it the play deck and the rest in the draw
   * deck
   */
  @Test
  void drawDeckPlayDeckInitialSizeTest() {
    DeckCardCollection drawDeck = new DrawDeck(makeVerySimpleCardsInfo());
    GameBoard board = new UnoGameBoard(drawDeck);
    Assertions.assertEquals(2, board.getAllDrawDeckCards().size());
    Assertions.assertEquals(1, board.getAllPlayDeckCards().size());
  }

  /**
   * Test if the game board correctly moves cards back to draw deck when it runs out
   */
  @Test
  void refillDrawDeckTest() {
    DeckCardCollection drawDeck = new DrawDeck(makeSimpleWithWildCardsInfo());
    Assertions.assertEquals(2, drawDeck.getSize());
    GameBoard board = new UnoGameBoard(drawDeck); // play deck gets a card, draw deck is now size 1
    Card cardDrawn = board.getFromDrawDeck();
    board.addToPlayDeck(cardDrawn);
    // draw deck was 2 cards, got empty, but now should be 1 card
    Assertions.assertDoesNotThrow(board::getFromDrawDeck);
  }

  /**
   * Test if the game board directly transfer card to player's hand
   */
  @Test
  void transferCardDirectlyToHandTest() {
    DeckCardCollection drawDeck = new DrawDeck(makeSimpleWithWildCardsInfo());
    CardCollection playerHand = new PlayerHand(Config.EMPTY_CARD_COLLECTION);
    GameBoard board = new UnoGameBoard(drawDeck); // play deck gets a card, draw deck is now size 1
    Assertions.assertDoesNotThrow(() -> board.transferCardFromDrawDeckTo(playerHand));
    Assertions.assertEquals(1, playerHand.getSize());
  }

  /**
   * Test that an exception will be thrown when making an empty draw deck
   */
  @Test
  void testMakeEmptyDrawDeck() {
    Assertions.assertThrows( EmptyCardCollectionException.class, ()-> new DrawDeck(Config.EMPTY_CARD_COLLECTION));
  }

  /**
   * Test that an exception will be thrown when making an empty draw deck
   */
  @Test
  void testGetFromEmptyDrawDeck() {
    DeckCardCollection drawDeck = new DrawDeck(makeSimpleWithWildCardsInfo());
    drawDeck.getTopCard();
    drawDeck.getTopCard();
    Assertions.assertThrows(EmptyCardCollectionException.class, drawDeck::getTopCard);
  }


}
