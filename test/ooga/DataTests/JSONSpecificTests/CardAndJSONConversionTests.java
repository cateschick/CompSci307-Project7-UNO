package ooga.DataTests.JSONSpecificTests;

import static org.junit.jupiter.api.Assertions.*;

import ooga.data.CardAndJSONConversion;
import ooga.data.GameStarter;
import ooga.data.JSONHandler;
import ooga.data.dataResources.DataConfig;

import ooga.model.cards.cardcomponents.UnoCardInfo;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import ooga.data.JSONCreator;
import ooga.model.cardcollection.deck.DrawDeck;
import ooga.model.cards.CardColor;
import ooga.model.cards.cardcomponents.UnoCard;

import ooga.model.cards.cardcomponents.CardInfo;

/**
 * test that the methods of the CardAndJSONConversion class work correctly
 *
 * @author Keith Cressman
 */
public class CardAndJSONConversionTests {

  @Test
  void testMakeCardsInfoSize() {
    try {
      DrawDeck d = new DrawDeck(CardAndJSONConversion.makeCardsInfo(GameStarter.getDrawDeckJSONArr(
          DataConfig.BASIC_UNO_PATH)));
      assertEquals(108, d.getAllCards().size());
    } catch (Exception e) {
      assertTrue(false);
    }
  }

  @Test
  void testMakeOneCardInfo() {
    JSONArray arr = new JSONArray();
    JSONObject cardObj = new JSONObject();
    cardObj.put(DataConfig.COLOR_KEY, "YELLOW");
    JSONArray cardInfos = new JSONArray();
    JSONObject cInfo = new JSONObject();
    cInfo.put(DataConfig.TYPE_KEY, "number");
    cInfo.put(DataConfig.PARAM_KEY, 1);
    cardInfos.add(cInfo);
    cardObj.put(DataConfig.CARD_INFOS_KEY, cardInfos);
    arr.add(cardObj);
    try {
      Map<CardColor, List<CardInfo[]>> map = CardAndJSONConversion.makeCardsInfo(arr);
      assertEquals(map.keySet().size(), 1);
      assertEquals(true, map.keySet().contains(CardColor.YELLOW));
      List<CardInfo[]> list = map.get(CardColor.YELLOW);
      CardInfo info = list.get(0)[0];
      assertEquals("number", info.getType());
      assertEquals(1, info.getParam());
    } catch (Exception e) {
      assertTrue(false);
    }

  }

  @Test
  void testMakeJSONObjForOneCard() {
    CardInfo cI = new UnoCardInfo("number", 1);
    UnoCard c = new UnoCard(CardColor.YELLOW, new CardInfo[]{cI});
    JSONObject o = CardAndJSONConversion.makeJSONObjectForCard(c);
    try {
      assertEquals(JSONHandler.getStringValue("color", o), "YELLOW");
      JSONArray a = JSONHandler.getJSONArray("cardInfos", o);
      assertEquals(1, a.size());
    } catch (Exception e) {
      assertTrue(false);
    }


  }
}
