package ooga.ViewTests.displayTests.designSettingsTests;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.view.Config;
import ooga.view.resources.Resources;
import ooga.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

/**
 * These tests assess the functionality of the DesignSettings.java class.
 *
 * @author Cate Schick cms168
 */
public class DesignSettingsTests extends DukeApplicationTest {

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
        Config.DESIGN_SETTINGS);
    myPane = myView.getMyDisplay().createDisplay();
    myView.getRoot().getChildren().add(myPane);

    stage.show();
  }

  /**
   * Tests createDisplay.
   */
  @Test
  public void createDisplayTest() {
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.DESIGN_SETTINGS));
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
    Assertions.assertTrue(ViewHelpers.checkTitleName(myPane, Config.DESIGN_SETTINGS_TITLE));
  }

  /**
   * Ensures HBox with buttons are in display.
   */
  @Test
  public void checkButtonsHBox() {
    Assertions.assertTrue(ViewHelpers.checkContentDisplay(myPane, ViewHelpers.HBOX_KEY));
  }

  /**
   * Ensures all 3 buttons are in display.
   */
  @Test
  public void checkNumButtons() {
    HBox h = (HBox) myPane.getCenter();
    Assertions.assertTrue(ViewHelpers.checkNumButtons(h.getChildren(), 3));
  }

  /**
   * Checks for changeFont button.
   */
  @Test
  public void checkButtonsChangeFont() {
    HBox h = (HBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String changeFont = Resources.getKey(Config.FONT_OPTIONS);
    assert changeFont != null;
    Assertions.assertTrue(children.toString().contains(changeFont));
  }

  /**
   * Checks for changeTheme button.
   */
  @Test
  public void checkButtonsChangeTheme() {
    HBox h = (HBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String changeTheme = Resources.getKey(Config.THEME_OPTIONS);
    assert changeTheme != null;
    Assertions.assertTrue(children.toString().contains(changeTheme));
  }

  /**
   * Checks for changeSize button.
   */
  @Test
  public void checkButtonsChangeSize() {
    HBox h = (HBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String changeSize = Resources.getKey(Config.SIZE_OPTIONS);
    assert changeSize != null;
    Assertions.assertTrue(children.toString().contains(changeSize));
  }

  /**
   * Tests changeFont button.
   */
  @Test
  public void testButtonsChangeFont() {
    HBox h = (HBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String key = Resources.getKey(Config.FONT_OPTIONS);

    Button b = ViewHelpers.findButton(children, key);
    assert b != null;
    clickOn(b);

    // Check that program navigated to FontDisplay
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.FONT_DISPLAY));
  }

  /**
   * Tests changeTheme button.
   */
  @Test
  public void testButtonsChangeTheme() {
    HBox h = (HBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String key = Resources.getKey(Config.THEME_OPTIONS);

    Button b = ViewHelpers.findButton(children, key);
    assert b != null;
    clickOn(b);

    // Check that program navigated to ThemeDisplay
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.THEME_DISPLAY));
  }


  /**
   * Tests changeSize button.
   */
  @Test
  public void testButtonsChangeSize() {
    HBox h = (HBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String key = Resources.getKey(Config.SIZE_OPTIONS);

    Button b = ViewHelpers.findButton(children, key);
    assert b != null;
    clickOn(b);

    // Check that program navigated to SizeDisplay
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.SIZE_DISPLAY));
  }
}
