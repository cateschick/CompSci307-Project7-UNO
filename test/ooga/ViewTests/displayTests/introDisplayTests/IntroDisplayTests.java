package ooga.ViewTests.displayTests.introDisplayTests;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.view.Config;
import ooga.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

/**
 * These tests assess the functionality of the IntroDisplay.java class.
 *
 * @author Cate Schick cms168
 */
public class IntroDisplayTests extends DukeApplicationTest {

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

    myView.getMyDisplay().switchDisplay(Config.INTRO_DISPLAY_PATH +
        Config.INTRO_DISPLAY);
    myPane = myView.getMyDisplay().createDisplay();
    myView.getRoot().getChildren().add(myPane);

    stage.show();
  }

  /**
   * Tests createDisplay.
   */
  @Test
  public void createDisplayTest() {
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView,
        Config.INTRO_DISPLAY_PATH +
            Config.INTRO_DISPLAY));
  }

  /**
   * Checks that both buttons are in display.
   */
  @Test
  public void checkButtonsNumber() {
    ObservableList<Node> children = myPane.getChildren();
    Assertions.assertTrue(ViewHelpers.checkNumButtons(children, 2));
  }

  /**
   * Checks that start button is in display.
   */
  @Test
  public void checkButtonsStart() {
    Node child = myPane.getLeft();
    String startButton = Config.MY_RESOURCES.getString(Config.START_BUTTON);
    Assertions.assertTrue(child.toString().contains(startButton));
  }

  /**
   * Checks that settings button is in display.
   */
  @Test
  public void checkButtonsSettings() {
    Node child = myPane.getRight();
    String settingsButton = Config.MY_RESOURCES.getString(Config.SETTINGS_BUTTON);
    Assertions.assertTrue(child.toString().contains(settingsButton));
  }

  /**
   * Checks that start button navigates to GameChooserDisplay.
   */
  @Test
  public void testStartButton() {
    Node button = myPane.getLeft();

    clickOn((Button) button);

    // Check that display has switched to GameChooserDisplay.java
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(
        myView, Config.SET_GAME_DISPLAY));
  }

  /**
   * Checks that settings button navigates to GameSettings.
   */
  @Test
  public void testSettingsButton() {
    Node button = myPane.getRight();

    clickOn((Button) button);

    // Check that display has switched to GameSettings.java
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(
        myView, Config.GAME_SETTINGS));
  }
}