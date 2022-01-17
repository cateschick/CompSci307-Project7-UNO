package ooga.DataTests.JSONSpecificTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Map;
import org.json.simple.JSONObject;
import ooga.data.JSONCreator;
import ooga.data.JSONHandler;

/**
 * test that the methods of the JSONCreator class work correctly
 *
 * @author Keith Cressman
 */
public class JSONCreatorTest {

  @Test
  void testMakeJSONObj() {
    Map<String, Object> objMap = Map.of("k1", "v1", "k2", 2);
    JSONObject jsonObj = JSONCreator.makeJSONObj(objMap);
    assertEquals("v1", jsonObj.get("k1"));
    try {
      assertEquals("v1", JSONHandler.getStringValue("k1", jsonObj));
      assertEquals(2, JSONHandler.getIntValue("k2", jsonObj));
    } catch (Exception e) {
      assertTrue(false);
    }

  }
}
