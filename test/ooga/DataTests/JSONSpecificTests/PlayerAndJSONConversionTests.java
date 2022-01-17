package ooga.DataTests.JSONSpecificTests;

import static org.junit.jupiter.api.Assertions.*;

import ooga.data.GameStarter;
import ooga.data.PlayersAndJSONConversion;
import ooga.data.dataResources.DataConfig;

import org.junit.jupiter.api.Test;
import java.util.List;
import org.json.simple.JSONArray;
import ooga.data.JSONHandler;
import ooga.model.players.Player;

/**
 * test that the methods of the PlayerAndJSONConversion class owrk correctly
 *
 * @author Keith cressman
 */
public class PlayerAndJSONConversionTests {

  @Test
  void testMakePlayers() {
    try {
      JSONArray playersArr = GameStarter.getJSONArray(DataConfig.PLAYERS_KEY,
          JSONHandler.getJSONObjectForFile("data/UnoGames/testFiles/testSaveGood.json"));
      List<Player> players = PlayersAndJSONConversion.makePlayers(playersArr);
      assertEquals(4, players.size());
      for (int i = 0; i < 4; i++) {
        assertEquals(5, players.get(i).getAllCards().size());
      }
    } catch (Exception e) {
      assertTrue(false);
    }
  }


}
