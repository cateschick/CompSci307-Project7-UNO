package ooga.ModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import ooga.model.cardcollection.DeckCardCollection;
import ooga.model.cardcollection.deck.DrawDeck;
import ooga.model.cardcollection.deck.PlayDeck;
import ooga.model.cards.CardColor;
import ooga.model.cards.UnoCardMaker;
import ooga.model.cards.cardcomponents.Card;
import ooga.model.cards.cardcomponents.CardInfo;
import ooga.model.cards.cardcomponents.UnoCardInfo;
import ooga.model.exceptions.InvalidNumberOfCardsToStartException;
import ooga.model.exceptions.InvalidNumberOfPlayersException;
import ooga.model.exceptions.NoCardDeckException;
import ooga.model.exceptions.NoPossibleCardsToDrawException;
import ooga.model.exceptions.WildCardNotOfColorNoneException;
import ooga.model.games.BasicGame;
import ooga.model.games.CardGame;

import ooga.model.games.RandomTurnsGame;
import ooga.model.players.GamePlayer;
import ooga.model.players.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests that test the basic game
 *
 * @author Alicia Wu
 */
public class BasicGameTests extends ModelTests {

  /**
   * Test making a simple basic game
   */
  @Test
  void testMakingBasicGame() {
    CardGame myGame = new BasicGame(4, 1, makeComplexCardsInfo());
    Assertions.assertNotNull(myGame.getGameBoard());
    Assertions.assertDoesNotThrow(myGame::currPlayerDrawCard);
  }

  /**
   * Test default clockwise direction
   */
  @Test
  void testNextPlayerID() {
    CardGame myGame = new BasicGame(3, 1, makeComplexCardsInfo());
    int current = myGame.getCurrPlayerId();
    int next = myGame.getNextPlayerId();
    assertTrue(next > current || next == 0);
  }

  /**
   * Test reversing gameplay direction
   */
  @Test
  void testReverseDirection() {
    CardGame myGame = new BasicGame(4, 1, makeComplexCardsInfo());
    myGame.reverseDirection();
    int current = myGame.getCurrPlayerId();
    int next = myGame.getNextPlayerId();
    assertTrue(next < current || next == 3);
  }

  /**
   * Test setting the next player
   */
  @Test
  void testSetNextID() {
    CardGame myGame = new BasicGame(4, 1, makeComplexCardsInfo());
    myGame.setNextPlayer(2);
    assertEquals(2, myGame.getNextPlayerId());
  }

  /**
   * Test finishing a turn and updating to the next player
   */
  @Test
  void testFinishTurn() {
    CardGame myGame = new BasicGame(4, 1, makeComplexCardsInfo());
    int next = myGame.getNextPlayerId();
    myGame.finishTurn();
    assertEquals(next, myGame.getCurrPlayerId());
  }

  /**
   * Test instantiating a game with a negative number of players
   */
  @Test
  void testNegativePlayers() {
    Assertions.assertThrows(InvalidNumberOfPlayersException.class, () -> {
      new BasicGame(-3, 1, makeComplexCardsInfo());
    });
  }

  /**
   * Test instantiating a game with only a single player
   */
  @Test
  void testTooFewPlayers() {
    Assertions.assertThrows(InvalidNumberOfPlayersException.class, () -> {
      new BasicGame(1, 3, makeComplexCardsInfo());
    });
  }

  /**
   * Test instantiating a game with a negative number of cards to draw
   */
  @Test
  void testNegativeNumCardsToStart() {
    Assertions.assertThrows(InvalidNumberOfCardsToStartException.class, () -> {
      new BasicGame(2, -5, makeComplexCardsInfo());
    });
  }

  /**
   * Test instantiating a game with no draw deck
   */
  @Test
  void testNullDeck() {
    Assertions.assertThrows(NoCardDeckException.class, () -> {
      new BasicGame(3, 1, null);
    });
  }

  /**
   * Test instantiating a game with an empty draw deck
   */
  @Test
  void testEmptyDeck() {
    Assertions.assertThrows(NoCardDeckException.class, () -> {
      new BasicGame(3, 1, new HashMap<>());
    });
  }

  /**
   * Test deck too small, no cards to draw
   */
  @Test
  void testDeckTooSmall() {
    Assertions.assertThrows(NoPossibleCardsToDrawException.class, () -> {
      new BasicGame(2, 1, makeSimpleWithWildCardsInfo());
    });
  }

  /**
   * Test add cards to player hand
   */
  @Test
  void testAddingCardsToPlayersHands() {
    CardGame game = new BasicGame(2, 1, makeComplexCardsInfo());
    Card newCard = makeCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 3)});
    game.addCardsToPlayerHand(0, List.of(newCard));
    Assertions.assertEquals(2, game.getPlayerById(0).getAllCards().size());
  }

  /**
   * Test add faulty card to player hand
   */
  @Test
  void testAddFaultyCardToPlayersHand() {
    CardGame game = new BasicGame(2, 1, makeComplexCardsInfo());
    Card newCard = makeCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("randommm", 3)});
    game.addCardsToPlayerHand(0, List.of(newCard));
    Assertions.assertEquals(2, game.getPlayerById(0).getAllCards().size());
  }

  /**
   * Test add null card to player hand
   */
  @Test
  void testAddNullCardToPlayersHand() {
    CardGame game = new BasicGame(2, 1, makeComplexCardsInfo());
    Assertions.assertThrows(NullPointerException.class, () -> game.addCardsToPlayerHand(0, List.of(null)));
  }

  /**
   * Test finding valid cards in a player's hand
   */
  @Test
  void testFindValidCardsInPlayerHand() {
    CardGame game = new BasicGame(2, 0, makeRedNumberedCardsInfo());
    Card newCard = makeCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 3)});
    Card newCard2 = makeCard(CardColor.BLUE, new CardInfo[]{new UnoCardInfo("reverse", 2)});
    game.addCardsToPlayerHand(game.getCurrPlayerId(), List.of(newCard, newCard2));
    List<Card> validCards = game.findCurrPlayerValidCardsToPlay().stream().toList();
    Assertions.assertEquals(1, validCards.size());
    checkCardIsExpected(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 3)},
        validCards.get(0));
  }

  /**
   * Test no valid cards in a player's hand
   */
  @Test
  void testNoValidCardsInPlayerHand() {
    CardGame game = new BasicGame(2, 0, makeRedNumberedCardsInfo());
    Card newCard2 = makeCard(CardColor.BLUE, new CardInfo[]{new UnoCardInfo("reverse", 2)});
    game.addCardsToPlayerHand(game.getCurrPlayerId(), List.of(newCard2));
    List<Card> validCards = game.findCurrPlayerValidCardsToPlay().stream().toList();
    Assertions.assertEquals(0, validCards.size());
  }


  /**
   * Test playing valid cards in a player's hand
   */
  @Test
  void testPlayValidCardsInPlayerHand() {
    CardGame game = new BasicGame(2, 0, makeRedNumberedCardsInfo());
    Card newCard = makeCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 3)});
    Card newCard2 = makeCard(CardColor.BLUE, new CardInfo[]{new UnoCardInfo("reverse", 2)});
    game.addCardsToPlayerHand(game.getCurrPlayerId(), List.of(newCard, newCard2));
    List<Card> validCards = game.findCurrPlayerValidCardsToPlay().stream().toList();
    Assertions.assertDoesNotThrow(() -> game.currPlayerPlayCard(validCards.get(0)));
  }

  /**
   * Test checking if game is over
   */
  @Test
  void testIsGameOver() {
    CardGame myGame = new BasicGame(2, 1, makeRedNumberedCardsInfo());
    myGame.currPlayerPlayCard((Card) myGame.findCurrPlayerValidCardsToPlay().toArray()[0]);
    Assertions.assertTrue(myGame.isGameOver());
  }

  /**
   * Test checking if game is not over
   */
  @Test
  void testGameNotOver() {
    CardGame myGame = new BasicGame(2, 1, makeRedNumberedCardsInfo());
    Assertions.assertFalse(myGame.isGameOver());
  }

  /**
   * Test starting a game with existing players
   */
  @Test
  void testNewGameExistingPlayers() {
    Player player0 = new GamePlayer(0);
    Player player1 = new GamePlayer(1);
    Card numberCard = new UnoCardMaker().makeCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 0)});
    player0.addToHand(numberCard);
    List<Player> players = List.of(player0, player1);
    DeckCardCollection drawDeck = new DrawDeck(makeComplexCardsInfo());
    DeckCardCollection playDeck = new PlayDeck(makeVerySimpleCardsInfo());
    Assertions.assertDoesNotThrow(() -> new BasicGame(drawDeck, playDeck, players));
  }

}
