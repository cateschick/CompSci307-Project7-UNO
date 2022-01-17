package ooga.ViewTests.displayTests;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.view.Config;
import ooga.view.display.Display;
import ooga.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class DisplayTests extends DukeApplicationTest {

  /**
   * View object.
   */
  private View myView;
  /**
   * Display object.
   */
  private Display myDisplay;
  /**
   * Test style.
   */
  private static final String TEST_STYLE = "TestStyle";
  /**
   * Test title.
   */
  private static final String TEST_TITLE = "TestTitle";
  /**
   * Bad Class example.
   */
  private static final String BAD_CLASS = "BadClass";

  /**
   * Override start method.
   *
   * @param stage Stage scene is built on.
   */
  @Override
  public void start(Stage stage) {
    myView = new View();
    myDisplay = new Display(myView);
    myView.makeScene(ooga.Config.MY_WIDTH, ooga.Config.MY_HEIGHT);
  }

  /**
   * Tests createDisplay.
   */
  @Test
  public void createDisplayTest() {
    myDisplay.createDisplay();
  }

  /**
   * Tests functionality of switchDisplay method. Checks to make sure this test calls IntroDisplay's
   * createDisplay() method.
   */
  @Test
  public void switchDisplayIntroDisplay() {
    myDisplay.switchDisplay(Config.INTRO_DISPLAY_PATH + Config.INTRO_DISPLAY);
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.INTRO_DISPLAY));
  }

  /**
   * Tests functionality of switchDisplay method. Checks to make sure this test calls
   * SetGameDisplay's createDisplay() method.
   */
  @Test
  public void switchDisplaySetGameDisplay() {
    myDisplay.switchDisplay(Config.INTRO_DISPLAY_PATH + Config.SET_GAME_DISPLAY);
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.SET_GAME_DISPLAY));
  }

  /**
   * Tests functionality of switchDisplay method. Checks to make sure this test calls About's
   * createDisplay() method.
   */
  @Test
  public void switchDisplayAbout() {
    myDisplay.switchDisplay(Config.ABOUT);
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.ABOUT));
  }

  /**
   * Tests functionality of switchDisplay method. Checks to make sure this test calls GameSettings's
   * createDisplay() method.
   */
  @Test
  public void switchDisplayGameSettings() {
    runAsJFXAction(() -> {
      myDisplay.switchDisplay(Config.GAME_SETTINGS_PATH + Config.GAME_SETTINGS);
    });
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.GAME_SETTINGS));
  }

  /**
   * Tests functionality of switchDisplay method. Checks to make sure this test calls CustomDeck's
   * createDisplay() method.
   */
  @Test
  public void switchDisplayCustomDeckDisplay() {
    myDisplay.switchDisplay(Config.GAME_SETTINGS_PATH + Config.CUSTOM_DECK_DISPLAY);
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.CUSTOM_DECK_DISPLAY));
  }

  /**
   * Tests functionality of switchDisplay method. Checks to make sure this test calls
   * DesignSettings's createDisplay() method.
   */
  @Test
  public void switchDisplayDesignSettings() {
    myDisplay.switchDisplay(Config.DESIGN_SETTINGS_PATH + Config.DESIGN_SETTINGS);
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.DESIGN_SETTINGS));
  }

  /**
   * Tests functionality of switchDisplay method. Checks to make sure this test calls ThemeDisplay's
   * createDisplay() method.
   */
  @Test
  public void switchDisplayThemeDisplay() {
    myDisplay.switchDisplay(Config.DESIGN_SETTINGS_PATH + Config.THEME_DISPLAY);
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.THEME_DISPLAY));
  }

  /**
   * Tests functionality of switchDisplay method. Checks to make sure this test calls FontDisplay's
   * createDisplay() method.
   */
  @Test
  public void switchDisplayFontDisplay() {
    myDisplay.switchDisplay(Config.DESIGN_SETTINGS_PATH + Config.FONT_DISPLAY);
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.FONT_DISPLAY));
  }

  /**
   * Tests functionality of switchDisplay method. Checks to make sure this test calls SizeDisplay's
   * createDisplay() method.
   */
  @Test
  public void switchDisplaySizeDisplay() {
    myDisplay.switchDisplay(Config.DESIGN_SETTINGS_PATH + Config.SIZE_DISPLAY);
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.SIZE_DISPLAY));
  }

  /**
   * Tests functionality of switchDisplay method. Checks to make sure this test calls
   * ProfileSettings's createDisplay() method.
   */
  @Test
  public void switchDisplayProfileSettings() {
    myDisplay.switchDisplay(Config.PROFILE_DISPLAY_PATH + Config.PROFILE_SETTINGS);
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.PROFILE_SETTINGS));
  }

  /**
   * Tests functionality of switchDisplay method. Checks to make sure this test calls
   * MyProfileDisplay's createDisplay() method.
   */
  @Test
  public void switchDisplayMyProfileDisplay() {
    myDisplay.switchDisplay(Config.PROFILE_DISPLAY_PATH + Config.MY_PROFILE_DISPLAY);
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.MY_PROFILE_DISPLAY));
  }

  /**
   * Tests functionality of switchDisplay method. Checks to make sure this test calls
   * MyProfileDisplay's createDisplay() method.
   */
  @Test
  public void switchDisplaySwitchProfileDisplay() {
    myDisplay.switchDisplay(Config.PROFILE_DISPLAY_PATH + Config.SWITCH_PROFILE_DISPLAY);
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.SWITCH_PROFILE_DISPLAY));
  }

  /**
   * Tests functionality of switchDisplay method. Checks to make sure this test calls
   * LanguageSettings's createDisplay() method.
   */
  @Test
  public void switchDisplayLanguageSettings() {
    myDisplay.switchDisplay(Config.LANGUAGE_SETTINGS);
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.LANGUAGE_SETTINGS));
  }

  /**
   * Tests switchDisplay's handling of an INVALID display input.
   */
  @Test
  public void switchDisplayInvalidDisplayException() {
    Assertions.assertThrows(IllegalStateException.class, () ->
        myDisplay.switchDisplay(BAD_CLASS));
  }

  /**
   * Tests switchDisplay's handling of an INVALID display input and ensures application does not
   * crash.
   */
  @Test
  public void switchDisplayInvalidDisplayError() {
    runAsJFXAction(() ->
        myDisplay.switchDisplay(BAD_CLASS));
  }

  /**
   * Checks that makeBackButton() creates a button.
   */
  @Test
  public void testMakeBackButton() {
    myDisplay.switchDisplay(Config.INTRO_DISPLAY_PATH + Config.INTRO_DISPLAY);
    Button b = myView.getMyDisplay().makeBackButton(Config.LANGUAGE_SETTINGS);
    String key = Config.BACK_BUTTON;
    Assertions.assertTrue(b.toString().contains(key));
  }

  /**
   * Tests functionality of setTitle method. Checks to make sure this test creates an HBox
   * containing title.
   */
  @Test
  public void testSetTitleChildren() {
    BorderPane myPane = myDisplay.createDisplay();
    myPane.getChildren().clear();
    myView.getMyDisplay().setTitle(myPane, new Label(TEST_TITLE));
    ObservableList<Node> children = myPane.getChildren();
    Assertions.assertTrue(ViewHelpers.checkNumButtons(children, 1));
  }

  /**
   * Tests functionality of setTitle method. Checks to make sure this test creates an HBox
   * containing title.
   */
  @Test
  public void TestSetTitleHBox() {
    BorderPane myPane = myDisplay.createDisplay();
    myPane.getChildren().clear();
    myView.getMyDisplay().setTitle(myPane, new Label(TEST_TITLE));
    Assertions.assertTrue(ViewHelpers.checkTitleHBox(myPane));
  }

  /**
   * Tests functionality of setTitle method. Checks to make sure this test creates proper title.
   */
  @Test
  public void testSetTitleName() {
    // switch to a display
    myDisplay.switchDisplay(Config.DESIGN_SETTINGS_PATH + Config.DESIGN_SETTINGS);
    BorderPane display = myDisplay.createDisplay();

    // Check for design settings title
    Assertions.assertTrue(ViewHelpers.checkTitleName(display, Config.DESIGN_SETTINGS_TITLE));
  }

  /**
   * Tests functionality of setDisplayStyle method.
   */
  @Test
  public void testSetDisplayStyle() {
    BorderPane pane = new BorderPane();
    myView.getMyDisplay().setDisplayStyle(Config.INTRO_DISPLAY, pane);
    Assertions.assertEquals(Config.INTRO_DISPLAY, pane.getStyleClass().toString());
  }

  /**
   * Tests functionality of setDisplayStyle method.
   */
  @Test
  public void testSetDisplayStyle2() {
    BorderPane pane = new BorderPane();
    myView.getMyDisplay().setDisplayStyle(TEST_STYLE, pane);
    Assertions.assertEquals(TEST_STYLE, pane.getStyleClass().toString());
  }
}
