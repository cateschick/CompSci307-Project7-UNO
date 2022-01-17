package ooga.ViewTests.displayTests.designSettingsTests;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.view.Config;
import ooga.view.resources.Resources;
import ooga.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class ThemeDisplayTests extends DukeApplicationTest {

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
        Config.THEME_DISPLAY);
    myPane = myView.getMyDisplay().createDisplay();
    myView.getRoot().getChildren().add(myPane);

    stage.show();
  }


  /**
   * Tests createDisplay.
   */
  @Test
  public void createDisplayTest() {
    Assertions.assertTrue(ViewHelpers.checkTitleName(myPane, Config.THEME_OPTIONS));
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
    Assertions.assertTrue(ViewHelpers.checkTitleName(myPane, Config.THEME_OPTIONS));
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
    Assertions.assertTrue(myView.getMyDisplay().toString().contains(Config.DESIGN_SETTINGS_PATH
    + Config.DESIGN_SETTINGS));
  }

  /**
   * Tests that a GridPane containing theme options is in display.
   */
  @Test
  public void checkThemeContainer() {
    Assertions.assertTrue(ViewHelpers.checkContentDisplay(myPane, ViewHelpers.GRIDPANE_KEY));
  }

  /**
   * Checks that all theme options are in display.
   */
  @Test
  public void checkThemeOptions() {
    GridPane children = (GridPane) myPane.getCenter();
    ViewHelpers.checkNumButtons(children.getChildren(), 14);
  }

  /**
   * Tests Cotton Candy theme.
   */
  @Test
  public void checkThemesCottonCandy() {
    GridPane pane = (GridPane) myPane.getCenter();
    String key = Resources.getKey("CandyTitle");

    Button b = ViewHelpers.findButton(pane.getChildren(), key);
    assert b != null;
    clickOn(b);
  }

  /**
   * Tests Default theme.
   */
  @Test
  public void checkThemesDefault() {
    GridPane pane = (GridPane) myPane.getCenter();
    String key = Resources.getKey("DefaultTitle");

    Button b = ViewHelpers.findButton(pane.getChildren(), key);
    assert b != null;
    clickOn(b);
  }

  /**
   * Tests Duke theme.
   */
  @Test
  public void checkThemesDuke() {
    GridPane pane = (GridPane) myPane.getCenter();
    String key = Resources.getKey("DukeTitle");

    Button b = ViewHelpers.findButton(pane.getChildren(), key);
    assert b != null;
    clickOn(b);
  }

  /**
   * Tests Gothic theme.
   */
  @Test
  public void checkThemesGothic() {
    GridPane pane = (GridPane) myPane.getCenter();
    String key = Resources.getKey("GothicTitle");

    Button b = ViewHelpers.findButton(pane.getChildren(), key);
    assert b != null;
    clickOn(b);
  }

  /**
   * Tests Primary Colors theme.
   */
  @Test
  public void checkThemesPrimary() {
    GridPane pane = (GridPane) myPane.getCenter();
    String key = Resources.getKey("PrimaryTitle");

    Button b = ViewHelpers.findButton(pane.getChildren(), key);
    assert b != null;
    clickOn(b);
  }

  /**
   * Tests Sunset theme.
   */
  @Test
  public void checkThemesSunset() {
    GridPane pane = (GridPane) myPane.getCenter();
    String key = Resources.getKey("SunsetTitle");

    Button b = ViewHelpers.findButton(pane.getChildren(), key);
    assert b != null;
    clickOn(b);
  }

  /**
   * Tests UNC theme.
   */
  @Test
  public void checkThemesUNC() {
    GridPane pane = (GridPane) myPane.getCenter();
    String key = Resources.getKey("TarheelsTitle");

    Button b = ViewHelpers.findButton(pane.getChildren(), key);
    assert b != null;
    clickOn(b);
  }
}
