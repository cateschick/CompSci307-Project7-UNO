package ooga.ViewTests.displayTests;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.view.Config;
import ooga.view.View;
import ooga.view.display.EndDisplay;
import ooga.view.resources.Resources;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class EndDisplayTests extends DukeApplicationTest {

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

    myView.getMyDisplay().switchDisplay(Config.END_DISPLAY);
    myPane = myView.getMyDisplay().createDisplay();
    myView.getRoot().getChildren().add(myPane);

    stage.show();
  }

  /**
   * Tests createDisplay.
   */
  @Test
  public void createDisplayTest() {
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.END_DISPLAY));
  }

  /**
   * Check for HBox containing Title.
   */
  @Test
  public void checkTitleHBox() {
    Assertions.assertTrue(ViewHelpers.checkTitleHBox(myPane));
  }

  /**
   * Makes sure the proper title is displayed when user wins.
   */
  @Test
  public void checkTitleNameWin() {
    // Set winner and see if win title is on display.
    EndDisplay.setWinner(0);
    Assertions.assertTrue(ViewHelpers.checkTitleName(myPane, Resources.getKey(Config.WIN_TITLE)));
  }

  /**
   * Makes sure the proper title is displayed when user loses.
   */
  @Test
  public void checkTitleNameLoss() {
    Assertions.assertTrue(ViewHelpers.checkTitleName(myPane, Resources.getKey(Config.LOSS_TITLE)));
  }

  /**
   * Checks HBox for buttons.
   */
  @Test
  public void checkButtonsHBox() {
    Assertions.assertTrue(ViewHelpers.checkContentDisplay(myPane, ViewHelpers.HBOX_KEY));
  }

  /**
   * Checks that all 3 buttons are present in display.
   */
  @Test
  public void checkNumButtons() {
    HBox h = (HBox) myPane.getCenter();
    Assertions.assertTrue(ViewHelpers.checkNumButtons(h.getChildren(), 3));
  }

  /**
   * Test backHome button.
   */
  @Test
  public void testButtonsBackHome() {
    HBox h = (HBox) myPane.getCenter();

    Button b = ViewHelpers.findButton(h.getChildren(), Resources.getKey(Config.RETURN_HOME));
    assert b != null;
    clickOn(b);

    // Check that program navigated to Intro display
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView,
        Config.INTRO_DISPLAY_PATH + Config.INTRO_DISPLAY));
  }

  /**
   * Test playAgain button.
   */
  @Test
  public void testButtonsPlayAgain() {
    HBox h = (HBox) myPane.getCenter();

    Button b = ViewHelpers.findButton(h.getChildren(), Resources.getKey(Config.PLAY_AGAIN));
    assert b != null;

    // TODO: Integrate and test result
  }

  /**
   * Tests Settings button.
   */
  @Test
  public void testButtonsSettings() {
    HBox h = (HBox) myPane.getCenter();

    Button b = ViewHelpers.findButton(h.getChildren(), Resources.getKey(Config.GAME_SETTINGS));
    assert b != null;
    clickOn(b);

    // Check that program navigated to Intro display
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView,
        Config.GAME_SETTINGS_PATH + Config.GAME_SETTINGS));
  }
}
