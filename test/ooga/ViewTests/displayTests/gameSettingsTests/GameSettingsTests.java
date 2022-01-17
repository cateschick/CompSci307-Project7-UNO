package ooga.ViewTests.displayTests.gameSettingsTests;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.view.Config;
import ooga.view.display.gameSettings.GameSettings;
import ooga.view.resources.Resources;
import ooga.view.View;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class GameSettingsTests extends DukeApplicationTest {

  /**
   * View object.
   */
  private View myView;
  /**
   * BorderPane with display content.
   */
  private BorderPane myPane;
  /**
   * File path for saved custom game.
   */
  private static final String SAVED_GAME_PATH = "data/UnoGames/saved/CustomGame.json";

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

    myView.getMyDisplay().switchDisplay(Config.GAME_SETTINGS_PATH + Config.GAME_SETTINGS);
    myPane = myView.getMyDisplay().createDisplay();
    myView.getRoot().getChildren().add(myPane);

    stage.show();
  }

  /**
   * Tests createDisplay.
   */
  @Test
  public void createDisplayTest() {
    Assertions.assertTrue(myView.getMyDisplay().toString().contains(Config.GAME_SETTINGS));
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
    Assertions.assertTrue(ViewHelpers.checkTitleName(myPane, Resources.getKey(
        Config.GAME_SETTINGS_TITLE)));
  }

  /**
   * This method tests generateGame button.
   */
  @Test
  public void testButtonsGenerateGame(){
    VBox content = (VBox) myPane.getCenter();

    Button b = ViewHelpers.findButton(content.getChildren(),
        Resources.getKey(Config.GENERATE_GAME));
    assert b != null;
    clickOn(b);

    // Check that file is in data/UnoGames/saved/CustomGame.json
    File savedGame = new File(SAVED_GAME_PATH);
    Assertions.assertTrue(savedGame.exists());
  }

  /**
   * This method tests setDeck() method.
   */
  @Test
  public void testSetDeck(){
    JSONArray customDeck = new JSONArray();
    GameSettings.setDeck(customDeck);
  }

  /**
   * This method makes sure all 4 settings buttons are in the GridPane.
   */
  @Test
  public void checkNumButtons(){
    VBox container = (VBox) myPane.getCenter();
    GridPane grid = (GridPane) container.getChildren().get(0);

    Assertions.assertTrue(ViewHelpers.checkNumButtons(grid.getChildren(), 4));
  }

  /**
   * Test changeNumCards button.
   */
  @Test
  public void testButtonsChangeCards() {
    VBox container = (VBox) myPane.getCenter();
    GridPane grid = (GridPane) container.getChildren().get(0);

    Button b = ViewHelpers.findButton(grid.getChildren(), Resources.getKey(Config.CHANGE_CARDS));
    assert b != null;
    clickOn(b);
  }

  /**
   * Test changePlayers button.
   */
  @Test
  public void testButtonsChangePlayers() {
    VBox container = (VBox) myPane.getCenter();
    GridPane grid = (GridPane) container.getChildren().get(0);

    Button b = ViewHelpers.findButton(grid.getChildren(), Resources.getKey(Config.CHANGE_PLAYERS));
    assert b != null;
    clickOn(b);
  }

  /**
   * Test changeVersion button.
   */
  @Test
  public void testButtonsChangeVersion() {
    VBox container = (VBox) myPane.getCenter();
    GridPane grid = (GridPane) container.getChildren().get(0);

    Button b = ViewHelpers.findButton(grid.getChildren(), Resources.getKey(Config.CHANGE_VERSION));
    assert b != null;
    clickOn(b);
  }

  /**
   * Test customDeck button.
   */
  @Test
  public void testButtonsCustomDeck() {
    VBox container = (VBox) myPane.getCenter();
    GridPane grid = (GridPane) container.getChildren().get(0);

    Button b = ViewHelpers.findButton(grid.getChildren(), Resources.getKey(Config.CUSTOM_DECK));
    assert b != null;
    clickOn(b);

    // Check that program navigated to CustomDeckDisplay
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.GAME_SETTINGS_PATH +
        Config.CUSTOM_DECK_DISPLAY));
  }
}
