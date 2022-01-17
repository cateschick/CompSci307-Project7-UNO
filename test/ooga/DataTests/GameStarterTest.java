package ooga.DataTests;

import java.util.List;
import ooga.ModelTests.ModelTests;
import ooga.data.GameStarter;
import ooga.data.dataExceptions.MissingGameKeysException;
import ooga.data.dataExceptions.UnrecognizedValueException;
import ooga.data.dataExceptions.WrongDataTypeException;
import ooga.data.dataExceptions.MissingCardKeysException;
import ooga.data.dataExceptions.IllogicalGameException;

import ooga.data.dataResources.DataConfig;
import ooga.model.cardcollection.deck.DrawDeck;
import ooga.model.cards.cardcomponents.Card;
import ooga.model.games.BasicGame;
import ooga.model.games.PirateGame;
import ooga.model.games.CardGame;
import ooga.model.players.Player;

import org.json.simple.JSONArray;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * test that the methods of the GameStarter class work correctly
 *
 * @author Keith Cressman
 */
public class GameStarterTest {

  private GameStarter myGameStarter;
  private CardGame myGame;
  public static final String TESTS_DIR = "data/UnoGames/";
  public static final String SAVED_TESTS_DIR = TESTS_DIR + "saved/";
  public static final String BASIC_UNO_DEFAULT = "BasicUnoDefault.json";
  public static final String UNO_SMALL_DECK = "BasicUnoSmallTestDeck.json";
  public static final String MISSING_STARTED_KEY = "badInputFiles/MissingStartedKey.json";
  public static final String BLANK_TEST = "badInputFiles/TestGameEmpty.json";
  public static final String MISSING_DRAW_DECK_TEST = "badInputFiles/TestGameNoDrawDeck.json";
  public static final String WRONG_DATA_TYPE_TEST = "badInputFiles/TestGameWrongDataType.json";
  public static final String UNRECOGNIZED_VALUE_TEST = "badInputFiles/TestGameUnrecognizedValue.json";
  public static final String MISSING_CARD_KEY_TEST = "badInputFiles/TestGameMissingCardKey.json";
  public static final String NOT_ENOUGH_CARDS_TEST = "badInputFiles/TestGameNotEnoughCards.json";
  private static final int DEFAULT_NUM_PLAYERS = 4;
  private static final String BASIC_UNO_PATH = "data/UnoGames/BasicUnoDefault.json";
  private static final String SMALL_TEST_DECK_PATH = "data/UnoGames/BasicUnoSmallTestDeck.json";
  public static final String BASIC_UNO_CLASS_PATH = "ooga.model.games.BasicGame";
  public static final String GOOD_SAVED_GAME_PATH = "data/UnoGames/testFiles/testSaveGood.json";


  @BeforeEach
  void setup() {
    myGameStarter = new GameStarter();
    myGame = new BasicGame(DEFAULT_NUM_PLAYERS, 0, new ModelTests().makeVerySimpleCardsInfo());
  }

  /**
   * test that the createGame method throws an exception when passed hte path to a blank file
   */
  @Test
  void testBlankGameFile() {
    String filePath = TESTS_DIR + BLANK_TEST;
    assertThrows(Exception.class, () -> myGameStarter.createGame(filePath));
  }

  /**
   * test that an exception is thrown when the file is missing required keys
   */
  @Test
  void testGameMissingDrawDeck() {
    String filePath = TESTS_DIR + MISSING_DRAW_DECK_TEST;
    assertThrows(MissingGameKeysException.class, () -> myGameStarter.createGame(filePath));
  }

  /**
   * test that the createDrawDeck method throws an exception when the file is missing keys
   */
  @Test
  void testGetDrawDeckGameMissingDrawDeck() {
    String filePath = TESTS_DIR + MISSING_DRAW_DECK_TEST;
    assertThrows(MissingGameKeysException.class, () -> myGameStarter.createDrawDeck(filePath));
  }

  /**
   * test that the createDrawDeck method throws an exception when the file has the wrong data type for a value
   */
  @Test
  void testWrongDataTypeException() {
    String filePath = TESTS_DIR + WRONG_DATA_TYPE_TEST;
    assertThrows(WrongDataTypeException.class, () -> myGameStarter.createDrawDeck(filePath));
  }

  /**
   * test that the createDrawDeck method throws an exception if the value for a key is unrecognized
   */
  @Test
  void testUnrecognizedValueException() {
    String filePath = TESTS_DIR + UNRECOGNIZED_VALUE_TEST;
    assertThrows(UnrecognizedValueException.class, () -> myGameStarter.createDrawDeck(filePath));
  }

  /**
   * test that the createDrawDeck method throws an exception if card(s) are missing keys
   */
  @Test
  void testMissingCardKeyException() {
    String filePath = TESTS_DIR + MISSING_CARD_KEY_TEST;
    assertThrows(MissingCardKeysException.class, () -> myGameStarter.createDrawDeck(filePath));
  }

  /**
   * test that the createGame method throws an exception if the started key is missing
   */
  @Test
  void testMissingStartedKeyException() {
    String filePath = TESTS_DIR + MISSING_STARTED_KEY;
    assertThrows(MissingGameKeysException.class, () -> myGameStarter.createGame(filePath));
  }


  @Test
  void testNotEnoughCardsGame() {
    String filePath = TESTS_DIR + NOT_ENOUGH_CARDS_TEST;
    assertThrows(IllogicalGameException.class, () -> myGameStarter.createGame(filePath));
  }

  @Test
  void testSmallDeck() {
    String filePath = TESTS_DIR + UNO_SMALL_DECK;
    try {
      DrawDeck deck = myGameStarter.createDrawDeck(filePath);
      assertEquals(4, deck.getAllCards().size());

    } catch (Exception e) {
      assertTrue(false);
    }
  }

  @Test
  void testGetBasicDrawDeck() {
    String filePath = TESTS_DIR + BASIC_UNO_DEFAULT;
    try {
      DrawDeck d = myGameStarter.createDrawDeck(filePath);
      assertEquals(108, d.getSize());

    } catch (Exception e) {
      assertTrue(false);
    }
  }

  /**
   * find a better test condition here than just checking game's current player ID = 0
   */
  @Test
  void testMakeBasicGame() {
    String filePath = TESTS_DIR + BASIC_UNO_DEFAULT;
    try {
      CardGame g = myGameStarter.createGame(filePath);
      assertTrue(true);
      testGetBasicDrawDeck();
    } catch (Exception e) {
      assertTrue(false);
    }

  }

  @Test
  void testPirateInstantiation() {
    try {
      CardGame g = myGameStarter.createGame(DataConfig.PIRATE_UNO_PATH);
      assertInstanceOf(PirateGame.class, g);
    } catch (Exception e) {
      assertTrue(false);
    }
  }

  @Test
  void testSetPlayerHandAndValidCards() {
    try {
      myGame = GameStarter.createGame(SMALL_TEST_DECK_PATH);
      DrawDeck deck = GameStarter.createDrawDeck(SMALL_TEST_DECK_PATH);
      List<Card> cards = deck.getAllCards();
      assertEquals(1, myGame.findCurrPlayerValidCardsToPlay().size());

    } catch (Exception e) {

      assertTrue(false);
    }
  }

  @Test
  void testPlayerDrawCards() {
    GameStarter starter = new GameStarter();
    try {
      myGame = starter.createGame(BASIC_UNO_PATH);
      Player p0 = myGame.getPlayerById(0);
      int numCardsBefore = p0.getAllCards().size();
      myGame.playerDrawCards(0, 5);
      assertEquals(5, p0.getAllCards().size() - numCardsBefore);
    } catch (Exception e) {
      assertTrue(false); //shouldn't ever get here
    }

  }

  @Test
  void testSetUpGameBoardAndCurrPlayerDraws() {
    GameStarter starter = new GameStarter();
    try {
      myGame = starter.createGame(BASIC_UNO_PATH);
      Player p = myGame.getPlayerById(myGame.getCurrPlayerId());
      int numCardsBefore = p.getAllCards().size();
      myGame.currPlayerDrawCard();
      assertEquals(1, p.getAllCards().size() - numCardsBefore);
    } catch (Exception e) {
      assertTrue(false); //shouldn't ever get here
    }
  }

  @Test
  void testLoadSavedGame() {
    try {

      String testName = GOOD_SAVED_GAME_PATH;
      CardGame g2 = GameStarter.createGame(testName);

      for (int i = 0; i < g2.getNumPlayers(); i++) {
        assertEquals(5, g2.getPlayerById(i).getPlayerHand().getAllCards().size());
      }
      assertEquals(2, g2.getGameBoard().getAllPlayDeckCards().size());
    } catch (Exception e) {
      assertTrue(false);
    }
  }

  @Test
  void testGetDrawDeckArr() {
    try {
      JSONArray arr = GameStarter.getDrawDeckJSONArr(BASIC_UNO_PATH);
      assertEquals(108, arr.size());
    } catch (Exception e) {
      assertTrue(false);
    }
  }


}
