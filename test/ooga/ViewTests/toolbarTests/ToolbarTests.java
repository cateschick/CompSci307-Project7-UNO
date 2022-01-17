package ooga.ViewTests.toolbarTests;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.view.Config;
import ooga.view.resources.Resources;
import ooga.view.View;
import util.DukeApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToolbarTests extends DukeApplicationTest {

  /**
   * View object.
   */
  private View myView;
  /**
   * Toolbar object.
   */
  private ToolBar myToolbar;

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

    myToolbar = (ToolBar) myView.getRoot().getTop();
    stage.show();
  }


  /**
   * Ensures a ToolBar is created with buttons.
   */
  @Test
  public void createToolbarTest() {
    Assertions.assertNotNull(myToolbar.getItems());
  }

  /**
   * Ensures ToolBar contains Home button.
   */
  @Test
  public void checkButtonsHome() {
    ObservableList<Node> children = myToolbar.getItems();

    Button b = ViewHelpers.findButton(children, Resources.getKey(Config.HOME));
    assert b != null;
    clickOn(b);

    // Should now be on intro display
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.INTRO_DISPLAY_PATH +
        Config.INTRO_DISPLAY));
  }

  /**
   * Ensures ToolBar contains About button.
   */
  @Test
  public void checkButtonsAbout() {
    ObservableList<Node> children = myToolbar.getItems();

    Button b = ViewHelpers.findButton(children, Resources.getKey(Config.ABOUT));
    assert b != null;
    clickOn(b);

    // Should now be on game display
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.ABOUT));
  }

  /**
   * Ensures ToolBar contains Game Settings button.
   */
  @Test
  public void checkButtonsGameSettings() {
    ObservableList<Node> children = myToolbar.getItems();

    Button b = ViewHelpers.findButton(children, Resources.getKey(Config.GAME_SETTINGS));
    assert b != null;
    clickOn(b);

    // Should now be on game display
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.GAME_SETTINGS_PATH
        + Config.GAME_SETTINGS));
  }

  /**
   * Ensures ToolBar contains Design Settings button.
   */
  @Test
  public void checkButtonsDesignSettings() {
    ObservableList<Node> children = myToolbar.getItems();

    Button b = ViewHelpers.findButton(children, Resources.getKey(Config.DESIGN_SETTINGS));
    assert b != null;
    clickOn(b);

    // Should now be on game display
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.DESIGN_SETTINGS_PATH
        + Config.DESIGN_SETTINGS));
  }

  /**
   * Ensures ToolBar contains Language Settings button.
   */
  @Test
  public void checkButtonsLanguageSettings() {
    ObservableList<Node> children = myToolbar.getItems();

    Button b = ViewHelpers.findButton(children, Resources.getKey(Config.LANGUAGE_SETTINGS));
    assert b != null;
    clickOn(b);

    // Should now be on game display
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.LANGUAGE_SETTINGS));
  }

  /**
   * Ensures ToolBar contains Profile Settings button.
   */
  @Test
  public void checkButtonsProfileSettings() {
    ObservableList<Node> children = myToolbar.getItems();

    Button b = ViewHelpers.findButton(children, Resources.getKey(Config.PROFILE_SETTINGS));
    assert b != null;
    clickOn(b);

    // Should now be on game display
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.PROFILE_DISPLAY_PATH
        + Config.PROFILE_SETTINGS));
  }

  /**
   * Check Toolbar style class.
   */
  @Test
  public void checkToolBarStyleClass() {
    Assertions.assertTrue(myToolbar.getStyleClass().contains(ViewHelpers.TOOLBAR_KEY));
  }

  /**
   * Checks for greeting message in ToolBar.
   */
  @Test
  public void checkToolBarGreeting() {
    ObservableList<Node> children = myToolbar.getItems();

    Assertions.assertTrue(children.toString().contains(Config.GREETING));
  }

  /**
   * Ensures ToolBar contains profile picture.
   */
  @Test
  public void checkProfilePicture() {
    ObservableList<Node> children = myToolbar.getItems();

    Assertions.assertTrue(children.toString().contains(ViewHelpers.IMAGEVIEW_KEY));
  }
}
