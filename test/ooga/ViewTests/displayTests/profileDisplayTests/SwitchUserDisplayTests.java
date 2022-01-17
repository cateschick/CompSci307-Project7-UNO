package ooga.ViewTests.displayTests.profileDisplayTests;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.view.Config;
import ooga.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

/**
 * These tests assess the functionality of the DesignSettings.java class.
 *
 * @author Cate Schick cms168
 */
public class SwitchUserDisplayTests extends DukeApplicationTest {

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

    myView.getMyDisplay().switchDisplay(Config.PROFILE_DISPLAY_PATH +
        Config.SWITCH_PROFILE_DISPLAY);
    myPane = myView.getMyDisplay().createDisplay();
    myView.getRoot().getChildren().add(myPane);

    stage.show();
  }

  /**
   * Tests createDisplay.
   */
  @Test
  public void createDisplayTest() {
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.PROFILE_DISPLAY_PATH +
        Config.SWITCH_PROFILE_DISPLAY));
  }
}

