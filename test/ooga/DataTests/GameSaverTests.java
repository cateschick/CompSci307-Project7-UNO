package ooga.DataTests;

import ooga.data.GameStarter;
import ooga.data.GameSaver;
import ooga.data.dataExceptions.InvalidFileNameException;
import ooga.model.games.CardGame;

import ooga.model.cardcollection.deck.DrawDeck;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * test the methods of the GameSaver class
 *
 * @author Keith Cressman
 */
public class GameSaverTests {

  /**
   * test that a game is saved correctly
   */
  @Test
  void testSavingGame() {
    try {
      CardGame g = GameStarter.createGame(
          GameStarterTest.TESTS_DIR + GameStarterTest.BASIC_UNO_DEFAULT);

      String testName = "testSave";
      int randomNum = new Random().nextInt(999999);
      String testNameFull = testName + randomNum;
      GameSaver.saveGame(g, testNameFull);
      DrawDeck testDeck = GameStarter.createDrawDeck(
          GameStarterTest.TESTS_DIR + "saved/" + testNameFull + ".json");
      assertEquals(87, testDeck.getAllCards().size());
    } catch (Exception e) {

      assertTrue(false);
    }
  }

  /**
   * test that an exception is thrown if you request to save a file under a blank name
   */
  @Test
  void testEmptyFileNameThrowsException() {
    CardGame g1 = null;
    try {
      CardGame g = GameStarter.createGame(
          GameStarterTest.TESTS_DIR + GameStarterTest.BASIC_UNO_DEFAULT);
      assertThrows(InvalidFileNameException.class, () -> GameSaver.saveGame(g, ""));
    } catch (Exception e) {
      assertTrue(false);
    }
  }

  /**
   * test that the saveGame method throws an exception if you try to save it under a name with
   * disallowed characters, such as . , % # in it
   */
  @Test
  void testBadFileNameThrowsException() {
    CardGame g1 = null;
    try {
      CardGame g = GameStarter.createGame(
          GameStarterTest.TESTS_DIR + GameStarterTest.BASIC_UNO_DEFAULT);
      assertThrows(InvalidFileNameException.class, () -> GameSaver.saveGame(g, "keith.json"));
      assertThrows(InvalidFileNameException.class, () -> GameSaver.saveGame(g, "data/keith"));
      assertThrows(InvalidFileNameException.class, () -> GameSaver.saveGame(g, "keith\\json"));
    } catch (Exception e) {
      assertTrue(false);
    }
  }


}
