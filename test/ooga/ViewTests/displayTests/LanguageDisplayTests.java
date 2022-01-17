package ooga.ViewTests.displayTests;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.view.Config;
import ooga.view.View;
import ooga.view.resources.Resources;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class LanguageDisplayTests extends DukeApplicationTest {

  /**
   * View object.
   */
  private View myView;
  /**
   * BorderPane with display content.
   */
  private BorderPane myPane;

  /**
   * Override start method.
   *
   * @param stage Stage scene is built on.
   */
  @Override
  public void start(Stage stage) {
    myView = new View();
    Scene myScene = myView.makeScene(ooga.Config.MY_WIDTH, ooga.Config.MY_HEIGHT);
    stage.setScene(myScene);

    myView.getMyDisplay().switchDisplay(Config.LANGUAGE_SETTINGS);
    myPane = myView.getMyDisplay().createDisplay();
    myView.getRoot().getChildren().add(myPane);

    stage.show();
  }

  /**
   * Tests createDisplay.
   */
  @Test
  public void createDisplayTest() {
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.LANGUAGE_SETTINGS));
  }

  /**
   * Check for HBox containing Title.
   */
  @Test
  public void checkTitleHBox() {
    Assertions.assertTrue(ViewHelpers.checkTitleHBox(myPane));
  }

  /**
   * Makes sure the proper title is displayed.
   */
  @Test
  public void checkTitleName() {
    Assertions.assertTrue(ViewHelpers.checkTitleName(myPane, Config.LANGUAGE_SETTINGS_TITLE));
  }

  /**
   * Ensures GridPane with buttons are in display.
   */
  @Test
  public void checkButtonsGridPane() {
    Assertions.assertTrue(ViewHelpers.checkContentDisplay(myPane, "Grid"));
  }

  /**
   * Checks that the GridPane contains all current languages (20).
   */
  @Test
  public void checkNumLanguageButtons() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();
    Assertions.assertTrue(ViewHelpers.checkNumButtons(children, 20));
  }

  /**
   * Checks that clicking Afrikaans changes Resource path.
   */
  @Test
  public void testAfrikaansButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Afrikaans"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "afrikaans");
    Assertions.assertEquals(filePath, Resources.getPath());
  }


  /**
   * Checks that clicking Danish changes Resource path.
   */
  @Test
  public void testDanishButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Danish"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "danish");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Dutch changes Resource path.
   */
  @Test
  public void testDutchButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Dutch"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "dutch");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking English changes Resource path.
   */
  @Test
  public void testEnglishButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("English"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "english");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking French changes Resource path.
   */
  @Test
  public void testFrenchButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("French"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "french");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking German changes Resource path.
   */
  @Test
  public void testGermanButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("German"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "german");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Inuktitut changes Resource path.
   */
  @Test
  public void testInuktitutButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Inuktitut"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "inuktitut");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Italian changes Resource path.
   */
  @Test
  public void testItalianButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Italian"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "italian");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Pinyin changes Resource path.
   */
  @Test
  public void testPinyinButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Pinyin"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "pinyin");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Polish changes Resource path.
   */
  @Test
  public void testPolishButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Polish"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "polish");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Portuguese changes Resource path.
   */
  @Test
  public void testPortugueseButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Portuguese"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "portuguese");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Quechua changes Resource path.
   */
  @Test
  public void testQuechuaButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Quechua"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "quechua");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Romaji changes Resource path.
   */
  @Test
  public void testRomajiButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Romaji"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "romaji");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Spanish changes Resource path.
   */
  @Test
  public void testSpanishButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Spanish"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "spanish");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Swahili changes Resource path.
   */
  @Test
  public void testSwahiliButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Swahili"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "swahili");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Tagalong changes Resource path.
   */
  @Test
  public void testTagalongButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Tagalong"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "tagalong");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Turkish changes Resource path.
   */
  @Test
  public void testTurkishButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Turkish"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "turkish");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Uzbek changes Resource path.
   */
  @Test
  public void testUzbekButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Uzbek"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "uzbek");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Vietnamese changes Resource path.
   */
  @Test
  public void testVietnameseButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Vietnamese"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "vietnamese");
    Assertions.assertEquals(filePath, Resources.getPath());
  }

  /**
   * Checks that clicking Xhosa changes Resource path.
   */
  @Test
  public void testXhosaButton() {
    GridPane p = (GridPane) myPane.getCenter();
    ObservableList<Node> children = p.getChildren();

    Button b = ViewHelpers.findButton(children, Resources.getKey("Xhosa"));
    assert b != null;
    clickOn(b);

    String filePath = String.format(Config.RESOURCE_PATH, "xhosa");
    Assertions.assertEquals(filePath, Resources.getPath());
  }
}


