package ooga.DataTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ooga.data.GameValueRetriever;

/**
 * Test the methods of the GameValueRetriever class
 *
 * @author Keith Cressman
 */
public class GameValueRetrieverTests {

  public static final String MISSING_KEYS_TEST = "data/UnoGames/testFiles/GameValueRetrieverTestsMissingKeys.json";


  /**
   * test that the hasTimedTurns method returns false when given a non-existent file
   */
  @Test
  void testTimedTurnsFileNotfound() {
    assertEquals(false, GameValueRetriever.hasTimedTurns("asdfj.json"));
  }

  /**
   * test that the getBackCardImgPath method returns the right path when it is present int he json
   * file
   */
  @Test
  void testCardImgPathGoodFile() {
    try {
      assertEquals("src/resources/themes/cards/angryBirds.png",
          GameValueRetriever.getBackCardImgPath("data/UnoGames/ModdedUno.json"));
    } catch (Exception e) {
      assertTrue(false);
    }
  }

  /**
   * test that the getBackCardImgPath method returns null when given a non-existent file
   */
  @Test
  void testCardImgPathBadFileException() {
    assertEquals(null, GameValueRetriever.getBackCardImgPath("adsf.json"));
  }

  /**
   * test that the getBackCardImgPath returns null when the file lacks the key
   */
  @Test
  void testCardImgPathMissingKey() {
    assertEquals(null, GameValueRetriever.getBackCardImgPath(MISSING_KEYS_TEST));
  }

  /**
   * test that the getGameType method returns the correct game type
   */
  @Test
  void testGameTypeNoException() {
    try {
      String type = GameValueRetriever.getGameType(
          GameStarterTest.TESTS_DIR + GameStarterTest.MISSING_DRAW_DECK_TEST);
      assertEquals("basic", type);
    } catch (Exception e) {
      assertTrue(false);
    }
  }


}
