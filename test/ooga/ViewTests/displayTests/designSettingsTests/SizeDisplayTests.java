package ooga.ViewTests.displayTests.designSettingsTests;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.view.Config;
import ooga.view.resources.Resources;
import ooga.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class SizeDisplayTests extends DukeApplicationTest {

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
        Config.SIZE_DISPLAY);
    myPane = myView.getMyDisplay().createDisplay();
    myView.getRoot().getChildren().add(myPane);

    stage.show();
  }

  /**
   * Tests createDisplay.
   */
  @Test
  public void createDisplayTest() {
    Assertions.assertTrue(myView.getMyDisplay().toString().contains(Config.SIZE_DISPLAY));
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
    Assertions.assertTrue(ViewHelpers.checkTitleName(myPane, Config.SIZE_OPTIONS));
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
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView,
        Config.DESIGN_SETTINGS_PATH + Config.DESIGN_SETTINGS));
  }

  /**
   * Tests that the BorderPane with font buttons is in display.
   */
  @Test
  public void checkDisplayVBox() {
    Assertions.assertTrue(ViewHelpers.checkContentDisplay(myPane, ViewHelpers.VBOX_KEY));
  }

  /**
   * Tests that the proper number of fonts are displayed.
   */
  @Test
  public void checkDisplayNumButtons() {
    VBox pane = (VBox) myPane.getCenter();

    // Check that all 4 size options are present
    Assertions.assertTrue(ViewHelpers.checkNumButtons(pane.getChildren(), 4));
  }

  /**
   * Tests Small button.
   */
  @Test
  public void testButtonsSmall() {
    VBox p = (VBox) myPane.getCenter();
    String key = Resources.getKey(Config.SMALL);

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(Config.SMALL, myView.getSize());
  }

  /**
   * Tests Medium button.
   */
  @Test
  public void testButtonsMedium() {
    VBox p = (VBox) myPane.getCenter();
    String key = Resources.getKey(Config.MEDIUM);

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(Config.MEDIUM, myView.getSize());
  }

  /**
   * Tests Large button.
   */
  @Test
  public void testButtonsLarge() {
    VBox p = (VBox) myPane.getCenter();
    String key = Resources.getKey(Config.LARGE);

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(Config.LARGE, myView.getSize());
  }

  /**
   * Tests XLarge button.
   */
  @Test
  public void testButtonsXLarge() {
    VBox p = (VBox) myPane.getCenter();
    String key = Resources.getKey(Config.XLARGE);

    Button b = ViewHelpers.findButton(p.getChildren(), key);
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(Config.XLARGE, myView.getSize());
  }
}
