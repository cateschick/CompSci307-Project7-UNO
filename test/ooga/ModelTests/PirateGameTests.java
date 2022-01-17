package ooga.ModelTests;

import java.util.List;
import ooga.model.cards.CardColor;
import ooga.model.cards.cardcomponents.Card;
import ooga.model.cards.cardcomponents.CardInfo;
import ooga.model.cards.cardcomponents.UnoCardInfo;
import ooga.model.games.CardGame;
import ooga.model.games.PirateGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PirateGameTests extends ModelTests {

  private CardGame game;
  private static final int DEFAULT_NUM_PLAYERS = 2;

  @BeforeEach
  void setup() {
    game = new PirateGame(DEFAULT_NUM_PLAYERS, 1, makeRedNumberedCardsInfo());
  }

  /**
   * Test playing a pirate card
   */
  @Test
  void testMakingPlayingAPirateCard() {
    Card pirateCard = makeCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 7)});
    Card otherPaddingCard = makeCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 0)});
    game.addCardsToPlayerHand(game.getCurrPlayerId(), List.of(pirateCard, otherPaddingCard));
    game.currPlayerPlayCard(pirateCard);
    // should not have 2 cards after swap
    Assertions.assertNotEquals(2, game.getCurrPlayer().getAllCards().size());
  }

  /**
   * Test complex pirate card
   */
  @Test
  void testPirateCardOtherColor() {
    Card pirateCard = makeCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 7), new UnoCardInfo("reverse", 1)});
    Card otherPaddingCard = makeCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 0)});
    game.addCardsToPlayerHand(game.getCurrPlayerId(), List.of(pirateCard, otherPaddingCard));
    game.currPlayerPlayCard(pirateCard);
    // should not have 2 cards after swap
    Assertions.assertNotEquals(2, game.getCurrPlayer().getAllCards().size());
  }

}
