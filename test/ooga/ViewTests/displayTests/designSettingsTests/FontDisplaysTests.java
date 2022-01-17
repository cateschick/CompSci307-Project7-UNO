package ooga.ViewTests.displayTests.designSettingsTests;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.view.Config;
import ooga.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class FontDisplaysTests extends DukeApplicationTest {

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

    myView.getMyDisplay().switchDisplay(Config.DESIGN_SETTINGS_PATH +
        Config.FONT_DISPLAY);
    myPane = myView.getMyDisplay().createDisplay();
    myView.getRoot().getChildren().add(myPane);

    stage.show();
  }

  /**
   * Tests createDisplay.
   */
  @Test
  public void createDisplayTest() {
    Assertions.assertTrue(ViewHelpers.checkTitleName(myPane, Config.FONT_OPTIONS));
  }

  /**
   * Makes sure an HBox containing title is at the top of display.
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
    Assertions.assertTrue(ViewHelpers.checkTitleName(myPane, Config.FONT_OPTIONS));
  }

  /**
   * Checks that a Back button is present in display.
   */
  @Test
  public void checkBackButton() {
    Assertions.assertTrue(ViewHelpers.checkBackButton(myPane));
  }

  /**
   * Checks that  back button navigates back to DesignSettings.java.
   */
  @Test
  public void testBackButton() {
    Node button = myPane.getLeft();

    clickOn((Button) button);

    // Check that display has properly changed
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.DESIGN_SETTINGS_PATH +
        Config.DESIGN_SETTINGS));
  }

  /**
   * Tests that the GridPane with font buttons is in display.
   */
  @Test
  public void checkDisplayGridPane() {
    Assertions.assertTrue(ViewHelpers.checkContentDisplay(myPane, ViewHelpers.GRIDPANE_KEY));
  }

  /**
   * Tests that the proper number of fonts are displayed.
   */
  @Test
  public void checkDisplayNumButtons() {
    GridPane p = (GridPane) myPane.getCenter();

    // Check that all 12 font options are present
    Assertions.assertEquals(12, p.getChildren().size());
  }

  /**
   * Checks for Arial Button.
   */
  @Test
  public void testButtonsArial() {
    GridPane p = (GridPane) myPane.getCenter();
    String key = Config.ARIAL;

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(key, myView.getFont());
  }

  /**
   * Checks for Chalkduster Button.
   */
  @Test
  public void testButtonsChalkduster() {
    GridPane p = (GridPane) myPane.getCenter();
    String key = Config.CHALKDUSTER;

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(key, myView.getFont());
  }

  /**
   * Checks for Copperplate Button.
   */
  @Test
  public void testButtonsCopperplate() {
    GridPane p = (GridPane) myPane.getCenter();
    String key = Config.COPPERPLATE;

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(key, myView.getFont());
  }

  /**
   * Checks for Futura Button.
   */
  @Test
  public void testButtonsFutura() {
    GridPane p = (GridPane) myPane.getCenter();
    String key = Config.FUTURA;

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(key, myView.getFont());
  }

  /**
   * Checks for Georgia Button.
   */
  @Test
  public void testButtonsGeorgia() {
    GridPane p = (GridPane) myPane.getCenter();
    String key = Config.GEORGIA;

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(key, myView.getFont());
  }

  /**
   * Checks for Impact Button.
   */
  @Test
  public void testButtonsImpact() {
    GridPane p = (GridPane) myPane.getCenter();
    String key = Config.IMPACT;

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(key, myView.getFont());
  }

  /**
   * Checks for Luminari Button.
   */
  @Test
  public void testButtonsLuminari() {
    GridPane p = (GridPane) myPane.getCenter();
    String key = Config.LUMINARI;

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(key, myView.getFont());
  }

  /**
   * Checks for Monaco Button.
   */
  @Test
  public void testButtonsMonaco() {
    GridPane p = (GridPane) myPane.getCenter();
    String key = Config.MONACO;

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(key, myView.getFont());
  }

  /**
   * Checks for Noteworthy Button.
   */
  @Test
  public void testButtonsNoteworthy() {
    GridPane p = (GridPane) myPane.getCenter();
    String key = Config.NOTEWORTHY;

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(key, myView.getFont());
  }

  /**
   * Checks for Papyrus Button.
   */
  @Test
  public void testButtonsPapyrus() {
    GridPane p = (GridPane) myPane.getCenter();
    String key = Config.PAPYRUS;

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(key, myView.getFont());
  }

  /**
   * Checks for Phosphate Button.
   */
  @Test
  public void testButtonsPhosphate() {
    GridPane p = (GridPane) myPane.getCenter();
    String key = Config.PHOSPHATE;

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(key, myView.getFont());
  }

  /**
   * Checks for SignPainter Button.
   */
  @Test
  public void testButtonsSignPainter() {
    GridPane p = (GridPane) myPane.getCenter();
    String key = Config.SIGNPAINTER;

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(key, myView.getFont());
  }
}
