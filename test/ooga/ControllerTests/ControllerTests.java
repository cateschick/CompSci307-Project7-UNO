package ooga.ControllerTests;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.controller.Controller;
import ooga.data.GameStarter;
import ooga.view.Config;
import ooga.view.View;
import ooga.view.resources.Resources;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

/**
 * JavaFX tests that test user actions through the controller
 *
 * @author Alicia Wu
 */
public class ControllerTests extends DukeApplicationTest {

  private Controller controller;
  private int HEIGHT = 800;
  private int WIDTH = 1200;
  private int[] dimensions = {HEIGHT, WIDTH};
  private String INCORRECT_METHOD_KEY = "MethodThatDoesNotExist";
  private String CORRECT_PATH_KEY = "classPath";
  private Stage myStage;
  private static final String BasicRedTestDeck =
      System.getProperty("user.dir") + "/data/UnoGames/testFiles/BasicUnoRedNumbersTestDeck.json";
  private static final String BasicRedEmptyHands =
      System.getProperty("user.dir") + "/data/UnoGames/testFiles/BasicUnoRedNumbersEmptyHands.json";
  private static final String BasicRed4Players =
      System.getProperty("user.dir") + "/data/UnoGames/testFiles/BasicRed4Players.json";

  /**
   * Override start method.
   *
   * @param stage Stage scene is built on.
   */
  @Override
  public void start(Stage stage) {
    myStage = stage;
    controller = new Controller(myStage, new int[]{ooga.Config.MY_WIDTH, ooga.Config.MY_HEIGHT});
    controller.generateView();
  }

  /**
   * Test data controller game instantiation
   */
  @Test
  void testMakeNewGame() {
    Assertions.assertDoesNotThrow(
        () -> controller.getDataController().startNewGame(Config.BASIC_GAME_PATH));
  }

  /**
   * Test making a new game from the view
   */
  @Test
  void testPressingNewGameButton() {
    View myView = controller.getMyView();
    ToolBar toolbar = (ToolBar) myView.getRoot().getTop();
    Button b = ViewHelpers.findButton(toolbar.getItems(), Resources.getKey(Config.NEW_GAME));
    assert b != null;
    Assertions.assertDoesNotThrow(() -> clickOn(b));
  }

  /**
   * Test data controller game instantiation
   */
  @Test
  void testGameStarterMakeNewGame() {
    Assertions.assertDoesNotThrow(() -> new GameStarter().createGame(BasicRedTestDeck));
  }

  /**
   * Test data controller game instantiation
   */
  @Test
  void testDataControllerMakeNewGame() {
    Assertions.assertDoesNotThrow(
        () -> controller.getDataController().startNewGame(Config.BASIC_GAME_PATH));
  }


}
