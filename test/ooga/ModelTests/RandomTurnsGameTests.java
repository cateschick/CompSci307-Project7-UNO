package ooga.ModelTests;

import java.util.List;
import ooga.model.cardcollection.DeckCardCollection;
import ooga.model.cardcollection.deck.DrawDeck;
import ooga.model.cardcollection.deck.PlayDeck;
import ooga.model.cards.CardColor;
import ooga.model.cards.UnoCardMaker;
import ooga.model.cards.cardcomponents.Card;
import ooga.model.cards.cardcomponents.CardInfo;
import ooga.model.cards.cardcomponents.UnoCardInfo;
import ooga.model.games.CardGame;
import ooga.model.games.RandomTurnsGame;
import ooga.model.players.GamePlayer;
import ooga.model.players.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests that test the random turns game variation
 *
 * @author Alicia Wu
 */
public class RandomTurnsGameTests extends ModelTests {

  private CardGame game;
  private static final int DEFAULT_NUM_PLAYERS = 100000;

  @BeforeEach
  void setup() {
    game = new RandomTurnsGame(DEFAULT_NUM_PLAYERS, 0, makeVerySimpleCardsInfo());
  }

  /**
   * Test making a random turns game by testing that curr and next players are not next to each
   * other
   */
  @Test
  void testMakingRandomTurnsGame() {
    int currPlayerId = game.getCurrPlayerId();
    int nextPlayerId = game.getNextPlayerId();
    Assertions.assertTrue(Math.abs(currPlayerId - nextPlayerId) != 1);
  }

  /**
   * Test reversing direction in a random game, should do nothing
   */
  @Test
  void testReversingDirection() {
    int nextPlayerId = game.getNextPlayerId();
    game.reverseDirection();
    Assertions.assertTrue(nextPlayerId == game.getNextPlayerId());
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
    Assertions.assertDoesNotThrow(() -> game = new RandomTurnsGame(drawDeck, playDeck, players));
  }

}
