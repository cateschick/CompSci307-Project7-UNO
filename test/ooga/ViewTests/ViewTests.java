package ooga.ViewTests;

import javafx.stage.Stage;
import ooga.view.Config;
import ooga.view.View;
import ooga.view.display.Display;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class ViewTests extends DukeApplicationTest {

  /**
   * View object.
   */
  private View myView;
  /**
   * Display object.
   */
  private Display myDisplay;

  @Override
  public void start(Stage stage) {
    myView = new View();
    myDisplay = new Display(myView);
    myView.makeScene(ooga.Config.MY_WIDTH, ooga.Config.MY_HEIGHT);
  }

  /**
   * Assesses the functionality of getDisplay() method by creating a new Scene and then making sure
   * MY_DISPLAY is updated with an intro display object.
   */
  @Test
  public void getDisplayTestIntro() {
    Assertions.assertEquals("class ooga.view.display.introDisplay.IntroDisplay",
        myView.getMyDisplay().getClass().toString());
  }

  /**
   * Assesses functionality of getDisplay() method and ensuring that it gets updated when a user
   * switches to a new display.
   */
  @Test
  public void getDisplayTestGameSettings() {
    // Switch to Game Settings
    myDisplay.switchDisplay(Config.GAME_SETTINGS_PATH + Config.GAME_SETTINGS);
    Assertions.assertEquals("class ooga.view.display.gameSettings.GameSettings",
        myView.getMyDisplay().getClass().toString());
  }

  /**
   * Assesses functionality of getDisplay() method and ensuring that it gets updated when a user
   * switches to a new display.
   */
  @Test
  public void getDisplayTestLanguageSettings() {
    // Switch to Game Settings
    myDisplay.switchDisplay(Config.LANGUAGE_SETTINGS);
    Assertions.assertEquals("class ooga.view.display.LanguageSettings",
        myView.getMyDisplay().getClass().toString());
  }

  /**
   * Assesses functionality of getDisplay() method and ensuring that it gets updated when a user
   * switches to a new display.
   */
  @Test
  public void getDisplayTestProfileSettings() {
    // Switch to Game Settings
    myDisplay.switchDisplay(Config.PROFILE_DISPLAY_PATH + Config.PROFILE_SETTINGS);
    Assertions.assertEquals("class ooga.view.display.profileDisplay.ProfileSettings",
        myView.getMyDisplay().getClass().toString());
  }

  /**
   * Assesses functionality of getDisplay() method and ensuring that it gets updated when a user
   * switches to a new display.
   */
  @Test
  public void getDisplayTestDesignSettings() {
    // Switch to Game Settings
    myDisplay.switchDisplay(Config.DESIGN_SETTINGS_PATH + Config.DESIGN_SETTINGS);
    Assertions.assertEquals("class ooga.view.display.designSettings.DesignSettings",
        myView.getMyDisplay().getClass().toString());
  }

  /**
   * Asserts root is not null.
   */
  @Test
  void getRootTest() {
    Assertions.assertNotNull(myView.getRoot());
  }

  /**
   * Makes sure a scene is properly created and a root is set.
   */
  @Test
  void makeSceneTest() {
    myView.makeScene(ooga.Config.MY_WIDTH, ooga.Config.MY_HEIGHT);
    Assertions.assertNotNull(myView.getRoot());
  }

  /**
   * Tests getTheme() for default theme.
   */
  @Test
  void testSetThemeDefault() {
    myView.setTheme("default");
    Assertions.assertEquals("default", myView.getCurrentTheme());
  }

  /**
   * Tests setTheme method for cotton candy theme.
   */
  @Test
  void testSetThemeCandy() {
    myView.setTheme("candy");
    Assertions.assertEquals("candy", myView.getCurrentTheme());
  }

  /**
   * Tests setTheme method for duke theme.
   */
  @Test
  void testSetThemeDuke() {
    myView.setTheme("duke");
    Assertions.assertEquals("duke", myView.getCurrentTheme());
  }

  /**
   * Tests setTheme method for UNC theme.
   */
  @Test
  void testSetThemeUNC() {
    myView.setTheme("tarheels");
    Assertions.assertEquals("tarheels", myView.getCurrentTheme());
  }

  /**
   * Tests setTheme method for sunset theme.
   */
  @Test
  void testSetThemeSunset() {
    myView.setTheme("sunset");
    Assertions.assertEquals("sunset", myView.getCurrentTheme());
  }

  /**
   * Tests setTheme method for primary colors theme.
   */
  @Test
  void testSetThemePrimary() {
    myView.setTheme("primary");
    Assertions.assertEquals("primary", myView.getCurrentTheme());
  }

  /**
   * Tests setTheme method for gothic theme.
   */
  @Test
  void testSetThemeGothic() {
    myView.setTheme("gothic");
    Assertions.assertEquals("gothic", myView.getCurrentTheme());
  }

  /**
   * This method tests getFont() method.
   */
  @Test
  void testSetFontArial() {
    myView.setFont(Config.ARIAL);
    Assertions.assertEquals(Config.ARIAL, myView.getFont());
  }

  /**
   * Tests setFont() method.
   */
  @Test
  void testSetFontLuminari() {
    myView.setFont(Config.LUMINARI);
    Assertions.assertEquals(Config.LUMINARI, myView.getFont());
  }

  /**
   * Tests setFont() method.
   */
  @Test
  void testSetFontPhosphate() {
    myView.setFont(Config.PHOSPHATE);
    Assertions.assertEquals(Config.PHOSPHATE, myView.getFont());
  }

  /**
   * Tests getSize() method.
   */
  @Test
  void testSetSizeSmall() {
    myView.setSize(Config.SMALL);
    Assertions.assertEquals(Config.SMALL, myView.getSize());
  }

  /**
   * Tests getSize() method.
   */
  @Test
  void testSetSizeMedium() {
    myView.setSize(Config.MEDIUM);
    Assertions.assertEquals(Config.MEDIUM, myView.getSize());
  }

  /**
   * Tests getSize() method.
   */
  @Test
  void testSetSizeLarge() {
    myView.setSize(Config.LARGE);
    Assertions.assertEquals(Config.LARGE, myView.getSize());
  }

  /**
   * Tests getSize() method.
   */
  @Test
  void testSetSizeExtraLarge() {
    myView.setSize(Config.XLARGE);
    Assertions.assertEquals(Config.XLARGE, myView.getSize());
  }

  /**
   * Tests noMorePossibleCardsToDraw() event.
   */
  @Test
  void noMorePossibleCardsToDrawTest(){
    runAsJFXAction( () ->
      myView.noMorePossibleCardsToDraw());
  }

  /**
   * Tests gameIsOver() method to make sure program navigates to EndDisplay.
   */
  @Test
  void testGameIsOverDisplay() {
    myView.gameIsOver(0);
    Assertions.assertEquals("class ooga.view.display.EndDisplay",
        myView.getMyDisplay().getClass().toString());
  }
}
