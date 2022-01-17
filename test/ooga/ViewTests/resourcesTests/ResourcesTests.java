package ooga.ViewTests.resourcesTests;

import java.util.ResourceBundle;
import javafx.stage.Stage;
import ooga.view.Config;
import ooga.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ooga.view.resources.Resources;
import util.DukeApplicationTest;

/**
 * This class tests the functionality of the Resources.java class.
 */
public class ResourcesTests extends DukeApplicationTest {

  /**
   * String for default resource file.
   */
  private static final String FILEPATH = "resources/propertyFiles/%s";

  /**
   * Override start method.
   *
   * @param stage Stage scene is built on.
   */
  @Override
  public void start(Stage stage) {
    View myView = new View();
    myView.makeScene(ooga.Config.MY_WIDTH, ooga.Config.MY_HEIGHT);
  }

  /**
   * This test ensures proper functionality of the getPath method with English.
   */
  @Test
  void getPathTestEnglish() {
    Resources.setPath("english");
    Assertions.assertEquals(String.format(FILEPATH, "english"), Resources.getPath());
  }

  /**
   * Assesses functionality of getPath() method, when the path is changed with setPath().
   */
  @Test
  void getPathTestSpanish() {
    Resources.setPath("spanish");
    Assertions.assertEquals(String.format(FILEPATH, "spanish"), Resources.getPath());
  }

  /**
   * Assesses functionality of getPath() method, when the path is changed with setPath().
   */
  @Test
  void getPathTestDanish() {
    Resources.setPath("danish");
    Assertions.assertEquals(String.format(FILEPATH, "danish"), Resources.getPath());
  }

  /**
   * Assesses functionality of getPath() method, when the path is changed with setPath().
   */
  @Test
  void getPathTestGerman() {
    Resources.setPath("german");
    Assertions.assertEquals(String.format(FILEPATH, "german"), Resources.getPath());
  }

  /**
   * Assesses functionality of getPath() method, when the path is changed with setPath().
   */
  @Test
  void getPathTestDutch() {
    Resources.setPath("dutch");
    Assertions.assertEquals(String.format(FILEPATH, "dutch"), Resources.getPath());
  }

  /**
   * Assesses functionality of getPath() method, when the path is changed with setPath().
   */
  @Test
  void getPathTestItalian() {
    Resources.setPath("italian");
    Assertions.assertEquals(String.format(FILEPATH, "italian"), Resources.getPath());
  }

  /**
   * Ensures resource file filePath changes when setPath is called for English.
   */
  @Test
  void setPathTestEnglish() {
    Resources.setPath("english");
    Assertions.assertEquals("resources/propertyFiles/english", Resources.getPath());
  }

  /**
   * Ensures resource file filePath changes when setPath is called for Spanish.
   */
  @Test
  void setPathTestSpanish() {
    Resources.setPath("spanish");
    Assertions.assertEquals("resources/propertyFiles/spanish", Resources.getPath());
  }

  /**
   * Ensures resource file filePath changes when setPath is called for Danish.
   */
  @Test
  void setPathTestDanish() {
    Resources.setPath("danish");
    Assertions.assertEquals("resources/propertyFiles/danish", Resources.getPath());
  }

  /**
   * Ensures resource file filePath changes when setPath is called for German.
   */
  @Test
  public void setPathTestGerman() {
    Resources.setPath("german");
    Assertions.assertEquals("resources/propertyFiles/german", Resources.getPath());
  }

  /**
   * Ensures resource file filePath changes when setPath is called for Dutch.
   */
  @Test
  public void setPathTestDutch() {
    Resources.setPath("dutch");
    Assertions.assertEquals("resources/propertyFiles/dutch", Resources.getPath());
  }

  /**
   * Ensures resource file filePath changes when setPath is called for Italian.
   */
  @Test
  public void setPathTestItalian() {
    Resources.setPath("italian");
    Assertions.assertEquals("resources/propertyFiles/italian", Resources.getPath());
  }

  /**
   * Ensures proper resource file is returned, even if language is capitalized.
   */
  @Test
  public void setPathCheckFormat1() {
    // Resource path uses capital instead of lower case.
    Resources.setPath("English");
    Assertions.assertEquals("resources/propertyFiles/english", Resources.getPath());
  }

  /**
   * Ensures proper resource file is returned, even if language is capitalized.
   */
  @Test
  public void setPathCheckFormat2() {
    // Resource path uses capital instead of lower case.
    Resources.setPath("Danish");
    Assertions.assertEquals("resources/propertyFiles/danish", Resources.getPath());
  }

  /**
   * Assesses functionality of getResources method for INVALID resource file. Makes sure code
   * doesn't break and missing resource exception is handled properly.
   */
  @Test
  public void setPathTestInvalidInput() {
    runAsJFXAction(() -> Resources.setPath("BadPath"));
    Assertions.assertEquals(String.format(FILEPATH, "english"), Resources.getPath());
  }

  /**
   * Checks that setting to an invalid path does not crash program.
   */
  @Test
  public void setPathInvalidInputHandling() {
    runAsJFXAction(() ->
        Resources.setPath("BadPath"));
  }

  /**
   * Assesses functionality of getResources method.
   */
  @Test
  public void getResourcesTestEnglish() {
    Resources.setPath("english");
    ResourceBundle myResources = Resources.getResources(Resources.getPath());
    assert myResources != null;
    Assertions.assertEquals("Home", myResources.getString(Config.HOME));
  }

  /**
   * Assesses functionality of getResources method for Spanish resource file.
   */
  @Test
  public void getResourcesTestSpanish() {
    Resources.setPath("spanish");
    ResourceBundle myResources = Resources.getResources(Resources.getPath());
    assert myResources != null;
    Assertions.assertEquals("Hogar", myResources.getString(Config.HOME));
  }

  /**
   * Assesses functionality of getResources method for Danish resource file.
   */
  @Test
  public void getResourcesTestDanish() {
    Resources.setPath("danish");
    ResourceBundle myResources = Resources.getResources(Resources.getPath());
    assert myResources != null;
    Assertions.assertEquals("Hjem", myResources.getString(Config.HOME));
  }

  /**
   * Assesses functionality of getResources method for German resource file.
   */
  @Test
  public void getResourcesTestGerman() {
    Resources.setPath("german");
    ResourceBundle myResources = Resources.getResources(Resources.getPath());
    assert myResources != null;
    Assertions.assertEquals("Heim", myResources.getString(Config.HOME));
  }

  /**
   * Assesses functionality of getResources method for Dutch resource file.
   */
  @Test
  public void getResourcesTestDutch() {
    Resources.setPath("dutch");
    ResourceBundle myResources = Resources.getResources(Resources.getPath());
    assert myResources != null;
    Assertions.assertEquals("Huis", myResources.getString(Config.HOME));
  }

  /**
   * Assesses functionality of getResources method for Italian resource file.
   */
  @Test
  public void getResourcesTestItalian() {
    Resources.setPath("italian");
    ResourceBundle myResources = Resources.getResources(Resources.getPath());
    assert myResources != null;
    Assertions.assertEquals("Casa", myResources.getString(Config.HOME));
  }

  /**
   * Checks to see program doesn't crash when there is a missing resource exception. Instead,
   * resource file path should be set to resources/propertyFiles/english.
   */
  @Test
  public void getResourcesTestInvalidFile() {
    // Program in Spanish
    runAsJFXAction(() -> {
      Resources.setPath("spanish");
      Resources.getResources("BadPath");
    });
    // Check to see program did not crash / is now in English
    Assertions.assertEquals("resources/propertyFiles/english", Resources.getPath());
  }

  /**
   * Checks to see that program handles missing resource exception.
   */
  @Test
  void getKeyTestInvalid() {
    // Tests invalid key returns a blank string
    String key = Resources.getKey("INVALIDKEY");
    Assertions.assertEquals(Config.BLANK, key);
  }

}
