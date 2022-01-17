package ooga.DataTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ooga.data.dataResources.DataConfig;
import ooga.data.DataUtilities;

import java.io.FileNotFoundException;
import ooga.data.dataExceptions.UnrecognizedValueException;

/**
 * test that the methods of the DataUtilities class work properly
 *
 * @author Keith Cressman
 */
public class DataUtilitiesTests {

  /**
   * test that the unoTypeToValue method can return the correct class path
   */
  @Test
  void testGetClassNameNoException() {
    try {
      String type = DataUtilities.unoTypeToValue("basic", DataConfig.UNO_TYPE_CLASS_NAME_PATH);
      assertEquals("ooga.model.games.BasicGame", type);
    } catch (Exception e) {
      assertTrue(false);
    }
  }

  /**
   * test that that the unoTypeToValue method throws the right exception when given a non-existent
   * file
   */
  @Test
  void testGetClassNameBadFile() {
    assertThrows(FileNotFoundException.class,
        () -> DataUtilities.unoTypeToValue("basic", "asdf.properties"));
  }

  /**
   * test that the unoTypeToValue method throws an exception when asked for a key not in the
   * properties file
   */
  @Test
  void testGetClassNameBadKey() {
    assertThrows(UnrecognizedValueException.class,
        () -> DataUtilities.unoTypeToValue("basi", DataConfig.UNO_TYPE_CLASS_NAME_PATH));
  }
}
