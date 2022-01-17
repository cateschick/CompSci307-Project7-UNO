package ooga.ViewTests.displayTests.profileDisplayTests;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.data.dataExceptions.UserNotFoundException;
import ooga.view.Config;
import ooga.view.View;
import ooga.view.resources.Resources;
import ooga.view.display.profileDisplay.ProfileSettings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class MyProfileDisplayTests extends DukeApplicationTest {

  /**
   * View object.
   */
  private View myView;
  /**
   * ProfileSettings object.
   */
  private ProfileSettings mySettings;
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
  public void start(Stage stage) throws UserNotFoundException {
    // Remove test user from JSON file
    myView = new View();
    Scene myScene = myView.makeScene(ooga.Config.MY_WIDTH, ooga.Config.MY_HEIGHT);
    stage.setScene(myScene);

    myView.getMyDisplay().switchDisplay(Config.PROFILE_DISPLAY_PATH +
        Config.MY_PROFILE_DISPLAY);
    myPane = myView.getMyDisplay().createDisplay();
    myView.getRoot().getChildren().add(myPane);
    mySettings = new ProfileSettings(myView);

    stage.show();
  }

  /**
   * Tests createDisplay.
   */
  @Test
  public void createDisplayTest() {
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.PROFILE_DISPLAY_PATH
        + Config.MY_PROFILE_DISPLAY));
  }

  /**
   * Checks for additional profile picture options upon clicking on profile picture.
   */
  @Test
  public void testProfilePictureClickable() {
    runAsJFXAction(() -> ProfileSettings.switchToUser("Cate"));

    BorderPane myProfile = (BorderPane) myPane.getCenter();
    VBox userInfo = (VBox) myProfile.getTop();

    Button profilePicture = (Button) userInfo.getChildren().get(0);
    assert profilePicture != null;
    clickOn(profilePicture);
  }

  /**
   * Tests username edit feature.
   */
  @Test
  public void testUsernameClickable() {
    runAsJFXAction(() -> mySettings.switchToUser("Cate"));

    BorderPane myProfile = (BorderPane) myPane.getCenter();
    VBox userInfo = (VBox) myProfile.getTop();

    Button username = (Button) userInfo.getChildren().get(1);
    assert username != null;
    clickOn(username);
  }

  /**
   * Tests delete profile.
   */
  @Test
  public void testDeleteProfile() {
    runAsJFXAction(() ->
        ProfileSettings.switchToUser("Cate"));

    Button deleteProfile = (Button) myPane.getRight();

    assert deleteProfile != null;
    clickOn(deleteProfile);

    String messageKey = Resources.getKey(Config.DELETE_PROFILE_PROMPT);
    Assertions.assertEquals(messageKey, getDialogMessage());
  }
}