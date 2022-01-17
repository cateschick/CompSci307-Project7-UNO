package ooga.ViewTests.displayTests.introDisplayTests;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.view.Config;
import ooga.view.View;
import ooga.view.resources.Resources;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class GameChooserDisplayTests extends DukeApplicationTest {

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
    Scene myScene = myView.makeScene(900, 600);
    stage.setScene(myScene);

    myView.getMyDisplay().switchDisplay(Config.INTRO_DISPLAY_PATH +
        Config.SET_GAME_DISPLAY);

    myPane = myView.getMyDisplay().createDisplay();
    myView.getRoot().getChildren().add(myPane);

    stage.show();
  }

  /**
   * Tests createDisplay.
   */
  @Test
  public void createDisplayTest() {
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.SET_GAME_DISPLAY));
  }

  /**
   * Checks that HBox and back button are in display.
   */
  @Test
  public void checkChildrenNumber() {
    Assertions.assertTrue(ViewHelpers.checkNumButtons(myPane.getChildren(), 2));
  }

  /**
   * Checks that back button is in display.
   */
  @Test
  public void checkBackButton() {
    Assertions.assertTrue(ViewHelpers.checkBackButton(myPane));
  }

  /**
   * Checks that there are 3 buttons in GameChooserDisplay's HBox.
   */
  @Test
  public void checkHBoxButtons() {
    HBox h = (HBox) myPane.getCenter();
    Assertions.assertTrue(ViewHelpers.checkNumButtons(h.getChildren(), 3));
  }

  /**
   * Checks that HBox contains loadGame Button.
   */
  @Test
  public void checkHBoxLoadGame() {
    HBox h = (HBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String loadGame = Resources.getKey(Config.LOAD_GAME);

    assert loadGame != null;
    Assertions.assertTrue(children.toString().contains(loadGame));
  }

  /**
   * Checks that HBox contains customGame Button.
   */
  @Test
  public void checkHBoxCustomGame() {
    HBox h = (HBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String customGame = Resources.getKey(Config.CREATE_GAME);

    assert customGame != null;
    Assertions.assertTrue(children.toString().contains(customGame));
  }

  /**
   * Checks that HBox contains basicGame Button.
   */
  @Test
  public void checkHBoxBasicGame() {
    HBox h = (HBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String basicGame = Resources.getKey(Config.BASIC_START);

    assert basicGame != null;
    Assertions.assertTrue(children.toString().contains(basicGame));
  }

  /**
   * Tests functionality of loadGame Button.
   */
  @Test
  public void testLoadGameButton() {
    HBox h = (HBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String key = Resources.getKey(Config.LOAD_GAME);

    Button b = ViewHelpers.findButton(children, key);
    assert b != null;
    clickOn(b);
  }

  /**
   * Tests functionality of createGame Button.
   */
  @Test
  public void testCustomGameButton() {
    HBox h = (HBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String key = Resources.getKey(Config.CREATE_GAME);

    Button b = ViewHelpers.findButton(children, key);
    assert b != null;
    clickOn(b);

    // Display should now be GameSettings.java
    Assertions.assertTrue(myView.getMyDisplay().toString().contains(Config.GAME_SETTINGS));
  }

  /**
   * Tests functionality of basicGame Button.
   */
  @Test
  public void testBasicGameButton() {
    HBox h = (HBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String key = Resources.getKey(Config.BASIC_START);

    Button b = ViewHelpers.findButton(children, key);
    assert b != null;
    clickOn(b);

    // TODO: Determine button's action and implement
  }

  /**
   * Tests functionality of loadGame's fileChooser.
   */
  @Test
  public void testLoadGameFileChooser() {
    HBox h = (HBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String key = Resources.getKey(Config.LOAD_GAME);

    Button b = ViewHelpers.findButton(children, key);
    assert b != null;
    clickOn(b);
  }
}
