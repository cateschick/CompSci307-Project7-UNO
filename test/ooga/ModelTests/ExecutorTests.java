package ooga.ModelTests;

import java.util.List;
import javafx.stage.Stage;
import ooga.ModelTests.ModelTests;
import ooga.controller.Controller;
import ooga.controller.DisplayController;
import ooga.controller.ExecutorObserver;
import ooga.data.GameStarter;
import ooga.model.cards.CardColor;
import ooga.model.cards.cardcomponents.Card;
import ooga.model.cards.cardcomponents.CardInfo;
import ooga.model.cards.cardcomponents.UnoCard;
import ooga.model.cards.cardcomponents.UnoCardInfo;
import ooga.model.games.BasicGame;
import ooga.model.games.CardGame;
import ooga.model.games.executors.DrawExecutor;
import ooga.model.games.executors.Executor;
import ooga.model.games.executors.ReverseExecutor;
import ooga.model.games.executors.SkipExecutor;
import ooga.view.Config;
import ooga.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

/**
 * JUnit tests that test the executor module
 *
 * @author Alicia Wu
 */
public class ExecutorTests extends DukeApplicationTest {

  private Controller controller;
  private ModelTests modelTestFunctions;

  /**
   * Override start method.
   *
   * @param stage Stage scene is built on.
   */
  @Override
  public void start(Stage stage) {
    controller = new Controller(stage, new int[]{ooga.Config.MY_WIDTH, ooga.Config.MY_HEIGHT});
    controller.generateView();
    modelTestFunctions = new ModelTests();
  }

//  /**
//   * Test if the draw card executor correctly executes
//   */
//  @Test
//  void testDrawCardsExecutor() {
//    CardGame game = makeNewGame(BasicRedTestDeck);
//    addExecutorObservers("draw", new DrawExecutor(), game);
//    Card draw2 = new UnoCard(
//        CardColor.RED, new CardInfo[]{new UnoCardInfo("draw", 2)});
//    checkPlayerHandSizes(game, new int[]{0, 1}, new int[]{1, 1});
//    game.addCardsToPlayerHand(game.getCurrPlayerId(), List.of(draw2));
//    controller.playCard(draw2);
//    checkPlayerHandSizes(game, new int[]{0, 1}, new int[]{3, 1});
//  }

//  /**
//   * Test if the skip turns executor correctly executes
//   */
//  @Test
//  void testSkipTurnsExecutor() {
//    CardGame game = makeNewGame(BasicRedEmptyHands);
//    checkPlayerHandSizes(game, new int[]{0, 1}, new int[]{1, 1});
//    Assertions.assertNotEquals(game.getCurrPlayerId(), game.getNextPlayerId());
//    Card skip1 = new UnoCard(
//        CardColor.RED, new CardInfo[]{new UnoCardInfo("skip", 1)});
//    game.addCardsToPlayerHand(game.getCurrPlayerId(), List.of(skip1));
//    controller.playCard(skip1);
//    Assertions.assertEquals(game.getCurrPlayerId(), game.getNextPlayerId());
//  }

  /**
   * Test if the reverse direction executor correctly executes
   */
  @Test
  void testReverseDirectionExecutor() {
    int numPlayers = 3;
    CardGame game = new BasicGame(numPlayers, 0, modelTestFunctions.makeRedNumberedCardsInfo());
    // clkwise direction means next player id will be greater, or 0 if curr player has the greatest id #
    Assertions.assertTrue(
        game.getNextPlayerId() == game.getCurrPlayerId() + 1 || game.getNextPlayerId() == 0);
    Card reverse1 = new UnoCard(
        CardColor.RED, new CardInfo[]{new UnoCardInfo("reverse", 1)});
    game.addCardsToPlayerHand(game.getCurrPlayerId(), List.of(reverse1));
    Executor executor = new ReverseExecutor();
    playSingleValidCard(executor, 1, game, reverse1);
    // now direction should be clkwise
    Assertions.assertTrue(
        game.getNextPlayerId() == game.getCurrPlayerId() - 1
            || game.getNextPlayerId() == numPlayers - 1);
  }

//  /**
//   * Test if the skip turns executor correctly executes if 3 skip cards with different values are
//   * place on top of each other
//   */
//  @Test
//  void testSkipTurnsTwoTimesExecutor() {
//    CardGame game = makeNewGame(BasicRed4Players);
//    // was originally 5 players
//    Card redSkip = new UnoCard(
//        CardColor.RED, new CardInfo[]{new UnoCardInfo("skip", 1)});
//    Card blueSkip = new UnoCard(
//        CardColor.BLUE, new CardInfo[]{new UnoCardInfo("skip", 1)});
//    if (game.getCurrPlayerId() == 0) {
//      game.finishTurn();
//    }
//    int[] playerIds = getPlayerOrdering(game);
//    game.addCardsToPlayerHand(playerIds[0], List.of(redSkip));
//    game.addCardsToPlayerHand(playerIds[1], List.of(blueSkip));
//    int expectedNextPlayer = playerIds[3];
//    controller.playCard(redSkip);
//    Assertions.assertEquals(expectedNextPlayer, game.getNextPlayerId());
//    checkPlayerHandSizes(game, new int[]{playerIds[0], playerIds[1]},
//        new int[]{0, 0});
//  }
//
//  /**
//   * Test play wild draw 4 - valid executor actions
//   */
//  @Test
//  void testWildDraw4() {
//    CardGame game = makeNewGame(BasicRedEmptyHands);
//    Card wildDraw4 = new UnoCard(
//        CardColor.NONE, new CardInfo[]{new UnoCardInfo("draw", 4), new UnoCardInfo("wild", 0)});
//    Card yellow8 = new UnoCard(
//        CardColor.YELLOW, new CardInfo[]{new UnoCardInfo("yellow", 8)});
//    Card yellow5 = new UnoCard(
//        CardColor.YELLOW, new CardInfo[]{new UnoCardInfo("yellow", 5)});
//    Card yellow3 = new UnoCard(
//        CardColor.YELLOW, new CardInfo[]{new UnoCardInfo("yellow", 3)});
//    if (game.getCurrPlayerId() == 0) {
//      game.finishTurn();
//    }
//    int[] playerIds = getPlayerOrdering(game);
//    game.addCardsToPlayerHand(playerIds[0], List.of(wildDraw4, yellow3, yellow5));
//    //addExecutorObservers("draw", new DrawExecutor(), game);
//    controller.playCard(wildDraw4);
//    checkPlayerHandSizes(game, new int[]{playerIds[0], playerIds[1]}, new int[]{1, 4});
//    Assertions.assertDoesNotThrow(() -> controller.playCard(yellow8));
//    // game.currPlayerPlayCard(yellow8));
//    Assertions.assertTrue(game.isGameOver());
//  }

//  /**
//   * Test if the draw executor correctly executes if 2 draw cards are place on top of each other
//   */
//  @Test
//  void testDraw2TwoTimesExecutor() {
//    CardGame game = new BasicGame(5, 0, makeRedNumberedCardsInfo());
//    Card redDraw2 = new UnoCard(
//        CardColor.RED, new CardInfo[]{new UnoCardInfo("draw", 2)});
//    Card blueDraw2 = new UnoCard(
//        CardColor.BLUE, new CardInfo[]{new UnoCardInfo("draw", 2)});
//    int[] playerIds = getPlayerOrdering(game);
//    game.addCardsToPlayerHand(playerIds[0], List.of(redDraw2));
//    game.addCardsToPlayerHand(playerIds[1], List.of(blueDraw2));
//    int expectedNextPlayer = playerIds[3];
//    Assertions.assertDoesNotThrow(() -> game.currPlayerPlayCard(redDraw2));
//    Assertions.assertEquals(expectedNextPlayer, game.getNextPlayerId());
//    checkPlayerHandSizes(game, new int[]{playerIds[0], playerIds[1], playerIds[2]},
//        new int[]{0, 0, 2});
//
//  }
//

//
//  /**
//   * Test if the skip turns executor correctly executes if 3 skip cards are place on top of each
//   * other
//   */
//  @Test
//  void testSkipTurnsThreeTimesExecutor() {
//    CardGame game = new BasicGame(5, 0, makeRedNumberedCardsInfo());
//    Card redSkip = new UnoCard(
//        CardColor.RED, new CardInfo[]{new UnoCardInfo("skip", 1)});
//    Card blueSkip = new UnoCard(
//        CardColor.BLUE, new CardInfo[]{new UnoCardInfo("skip", 1)});
//    Card greenSkip = new UnoCard(
//        CardColor.GREEN, new CardInfo[]{new UnoCardInfo("skip", 1)});
//    int[] playerIds = getPlayerOrdering(game);
//    game.addCardsToPlayerHand(playerIds[0], List.of(redSkip));
//    game.addCardsToPlayerHand(playerIds[1], List.of(blueSkip));
//    game.addCardsToPlayerHand(playerIds[2], List.of(greenSkip));
//    int expectedNextPlayer = playerIds[4];
//    playSingleValidCard(game);
//    Assertions.assertEquals(expectedNextPlayer, game.getNextPlayerId());
//    checkPlayerHandSizes(game, new int[]{playerIds[0], playerIds[1], playerIds[2]},
//        new int[]{0, 0, 0});
//  }
//

//
//
//
//  /**
//   * Test play wild draw 4 one on top of the other
//   */
//  @Test
//  void testTwoWildDraw4() {
//    CardGame game = new BasicGame(5, 0, makeRedNumberedCardsInfo());
//    Card wildDraw4 = new UnoCard(
//        CardColor.NONE, new CardInfo[]{new UnoCardInfo("draw", 4), new UnoCardInfo("wild", 0)});
//    Card secondWildDraw4 = new UnoCard(
//        CardColor.NONE, new CardInfo[]{new UnoCardInfo("wild", 0), new UnoCardInfo("draw", 4)});
//    int[] playerIds = getPlayerOrdering(game);
//    game.addCardsToPlayerHand(playerIds[0], List.of(wildDraw4));
//    game.addCardsToPlayerHand(playerIds[1], List.of(secondWildDraw4));
//    Assertions.assertDoesNotThrow(() -> game.currPlayerPlayCard(wildDraw4));
//    Assertions.assertEquals(playerIds[3], game.getNextPlayerId());
//    checkPlayerHandSizes(game, new int[]{playerIds[0], playerIds[1], playerIds[2]},
//        new int[]{0, 0, 8});
//  }
//

//
  private int[] getPlayerOrdering(CardGame game) {
    int[] ids = new int[game.getNumPlayers()];
    ids[0] = game.getCurrPlayerId();
    ids[1] = game.getNextPlayerId();
    if (game.getNumPlayers() > 2) {
      int lastId = game.getNextPlayerId();
      for (int i = 2; i < game.getNumPlayers(); i++) {
        ids[i] = game.getNextPlayerId(lastId);
        lastId = ids[i];
      }
    }
    return ids;
  }

  /**
   * Test if the reverse direction executor correctly executes for reverse 2
   */
  @Test
  void testReverse2() {
    int numPlayers = 3;
    CardGame game = new BasicGame(numPlayers, 0, modelTestFunctions.makeRedNumberedCardsInfo());
    // clkwise direction means next player id will be greater, or 0 if curr player has the greatest id #
    Assertions.assertTrue(
        game.getNextPlayerId() == game.getCurrPlayerId() + 1 || game.getNextPlayerId() == 0);
    Card reverse2 = new UnoCard(
        CardColor.RED, new CardInfo[]{new UnoCardInfo("reverse", 2)});
    game.addCardsToPlayerHand(game.getCurrPlayerId(), List.of(reverse2));
    Executor executor = new ReverseExecutor();
    playSingleValidCard(executor, 2, game, reverse2);
    // now direction should be clkwise
    Assertions.assertTrue(
        game.getNextPlayerId() == game.getCurrPlayerId() + 1 || game.getNextPlayerId() == 0);
  }

  private void playSingleValidCard(Executor executor, int param, CardGame game, Card cardToPlay) {
    Assertions.assertDoesNotThrow(() -> {
      game.currPlayerPlayCard(cardToPlay);
      executor.execute(game, param);
    });
  }

  private void checkPlayerHandSizes(CardGame game, int[] playerIds, int[] expectedHandSizes) {
    Assertions.assertEquals(expectedHandSizes.length, playerIds.length);
    for (int i = 0; i < playerIds.length; i++) {
      Assertions.assertEquals(expectedHandSizes[i],
          game.getPlayerById(playerIds[i]).getAllCards().size());
    }
  }

  private void addExecutorObservers(String executorName, Executor executor, CardGame game) {
    executor.addObserver(executorName, controller);
    executor.addObserver(executorName,
        new ExecutorObserver(game, controller, new DisplayController(controller)));
    executor.addObserver(ooga.Config.INCREMENT_NUM, controller);
    executor.addObserver(ooga.Config.EXECUTE_CASCADED, controller);
    if (game.getCurrPlayerId() == 0) {
      game.finishTurn();
    }
  }

  private CardGame makeNewGame(String deck) {
    final CardGame[] gameArray = new CardGame[1];
    Assertions.assertDoesNotThrow(() -> {
      gameArray[0] = new GameStarter().createGame(deck);
    });
    CardGame game = gameArray[0];
    controller.initializeNewGame(game);
    return game;
  }

}
