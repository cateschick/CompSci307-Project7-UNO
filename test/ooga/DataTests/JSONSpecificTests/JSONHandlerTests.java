package ooga.DataTests.JSONSpecificTests;

import static org.junit.jupiter.api.Assertions.*;

import ooga.data.dataExceptions.WrongDataTypeException;
import org.junit.jupiter.api.Test;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import ooga.data.JSONCreator;
import ooga.data.JSONHandler;


/**
 * test that the methods of the JSONHandler class work correctly
 *
 * @author Keith Cressman
 */
public class JSONHandlerTests {

  @Test
  void testGetLiteralsNoException() {
    Map<String, Object> objMap = Map.of("k1", "v1", "k2", 2, "k3", true);
    JSONObject jsonObj = JSONCreator.makeJSONObj(objMap);
    try {
      assertEquals("v1", JSONHandler.getStringValue("k1", jsonObj));
      assertEquals(2, JSONHandler.getIntValue("k2", jsonObj));
      assertEquals(true, JSONHandler.getBooleanVal("k3", jsonObj));
    } catch (Exception e) {
      assertTrue(false);
    }
  }

  @Test
  void testGetStringValWrongDataTypeException() {
    Map<String, Object> objMap = Map.of("k1", "v1", "k2", 2);
    JSONObject jsonObj = JSONCreator.makeJSONObj(objMap);
    assertThrows(WrongDataTypeException.class, () -> JSONHandler.getStringValue("k2", jsonObj));
  }

  @Test
  void testGetIntValWrongDataTypeException() {
    Map<String, Object> objMap = Map.of("k1", "v1", "k2", 2);
    JSONObject jsonObj = JSONCreator.makeJSONObj(objMap);
    assertThrows(WrongDataTypeException.class, () -> JSONHandler.getIntValue("k1", jsonObj));
  }

  @Test
  void testGetBooleanValWrongDataTypeException() {
    Map<String, Object> objMap = Map.of("k1", "v1", "k2", 2);
    JSONObject jsonObj = JSONCreator.makeJSONObj(objMap);
    assertThrows(WrongDataTypeException.class, () -> JSONHandler.getIntValue("k1", jsonObj));
  }

  @Test
  void testGetJSONArrNoException() {
    JSONArray myArr = new JSONArray();
    Map<String, Object> objMap = Map.of("k1", "v1", "k2", 2);
    JSONObject jsonObj = JSONCreator.makeJSONObj(objMap);
    Map<String, Object> objMap1 = Map.of("k1", "v1", "k2", 2);
    JSONObject jsonObj1 = JSONCreator.makeJSONObj(objMap);
    myArr.add(jsonObj);
    myArr.add(jsonObj1);
    JSONObject finalObj = new JSONObject();
    finalObj.put("a", myArr);
    try {
      assertEquals(2, JSONHandler.getJSONArray("a", finalObj).size());
    } catch (Exception e) {
      assertTrue(false);
    }
  }

  @Test
  void testGetJSONArrException() {
    JSONArray myArr = new JSONArray();
    Map<String, Object> objMap = Map.of("k1", "v1", "k2", 2);
    JSONObject jsonObj = JSONCreator.makeJSONObj(objMap);
    Map<String, Object> objMap1 = Map.of("k1", "v1", "k2", 2);
    JSONObject jsonObj1 = JSONCreator.makeJSONObj(objMap);
    myArr.add(jsonObj);
    myArr.add(jsonObj1);
    JSONObject finalObj = new JSONObject();
    finalObj.put("a", jsonObj1);
    assertThrows(WrongDataTypeException.class, () -> JSONHandler.getJSONArray("a", finalObj));
  }


}
