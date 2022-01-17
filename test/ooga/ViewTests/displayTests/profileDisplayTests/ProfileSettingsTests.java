package ooga.ViewTests.displayTests.profileDisplayTests;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.view.Config;
import ooga.view.View;
import ooga.view.display.profileDisplay.ProfileSettings;
import ooga.view.resources.Resources;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class ProfileSettingsTests extends DukeApplicationTest {

  /**
   * View object.
   */
  private View myView;
  /**
   * Settings object for non-static methods.
   */
  private ProfileSettings mySettings;
  /**
   * Valid user key.
   */
  private static final String VALID_USER = "Cate";
  /**
   * Invalid user key.
   */
  private static final String INVALID_USER = "InvalidUser";
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

    myView.getMyDisplay().switchDisplay(Config.PROFILE_DISPLAY_PATH +
        Config.PROFILE_SETTINGS);
    myPane = myView.getMyDisplay().createDisplay();
    myView.getRoot().getChildren().add(myPane);
    mySettings = new ProfileSettings(myView);

    stage.show();
  }

  /**
   * Tests createDisplay.
   */
  @Test
  void createDisplayTest() {
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.PROFILE_DISPLAY_PATH
        + Config.PROFILE_SETTINGS));
  }

  /**
   * Makes sure an HBox containing title is at the top of display.
   */
  @Test
  void checkTitleHBox() {
    Assertions.assertTrue(ViewHelpers.checkTitleHBox(myPane));
  }

  /**
   * Makes sure the proper title is displayed.
   */
  @Test
  void checkTitleName() {
    Assertions.assertTrue(ViewHelpers.checkTitleName(myPane, Config.PROFILE_SETTINGS_TITLE));
  }

  /**
   * Tests getProfilePicture on valid user.
   */
  @Test
  void getCurrentUserTestGuest() {
    // Initially, this should be set to Guest
    runAsJFXAction(() -> ProfileSettings.switchToUser(Config.GUEST));
    Assertions.assertEquals(Config.GUEST, ProfileSettings.getCurrentUser());
  }

  /**
   * Tests switchToUser() method.
   */
  @Test
  void testSwitchToUser() {
    runAsJFXAction(() -> ProfileSettings.switchToUser(VALID_USER));
    Assertions.assertEquals(VALID_USER, ProfileSettings.getCurrentUser());
  }

  /**
   * Tests switchToUser() handling of invalid user.
   * <p>
   * Look for dialogue shown to user.
   */
  @Test
  void testSwitchToUserInvalid() {
    runAsJFXAction(() -> ProfileSettings.switchToUser(INVALID_USER));
    Assertions.assertEquals(Config.GUEST, ProfileSettings.getCurrentUser());
  }

  /**
   * Tests Edit Profile button.
   */
  @Test
  void testButtonsEditProfile() {
    VBox h = (VBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String key = Resources.getKey(Config.EDIT_PROFILE);

    Button b = ViewHelpers.findButton(children, key);
    assert b != null;
    clickOn(b);

    // Check that program navigated to My Profile Display
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.PROFILE_DISPLAY_PATH +
        Config.MY_PROFILE_DISPLAY));
  }

  /**
   * Tests Switch Profile button.
   */
  @Test
  void testButtonsSwitchProfile() {
    VBox h = (VBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String key = Resources.getKey(Config.SWITCH_PROFILE);

    Button b = ViewHelpers.findButton(children, key);
    assert b != null;
    clickOn(b);

    // Check that program navigated to My Profile Display
    Assertions.assertTrue(ViewHelpers.checkCreateDisplay(myView, Config.PROFILE_DISPLAY_PATH +
        Config.SWITCH_PROFILE_DISPLAY));
  }

  /**
   * Tests Play As Guest button.
   */
  @Test
  void testButtonsPlayAsGuest() {
    VBox h = (VBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String key = Resources.getKey(Config.PLAY_AS_GUEST);

    // Switch to another user.
    runAsJFXAction(() -> ProfileSettings.switchToUser(VALID_USER));

    Button b = ViewHelpers.findButton(children, key);
    assert b != null;
    clickOn(b);

    // Check that current user is Guest
    Assertions.assertEquals(Config.GUEST, ProfileSettings.getCurrentUser());
  }

  /**
   * Tests Play As Guest button when current user is already Guest.
   */
  @Test
  void testButtonsPlayAsGuestAlreadyGuest() {
    VBox h = (VBox) myPane.getCenter();
    ObservableList<Node> children = h.getChildren();
    String key = Resources.getKey(Config.PLAY_AS_GUEST);

    // Switch to another user.
    runAsJFXAction(() -> ProfileSettings.switchToUser(Config.GUEST));

    // Check that dialog is shown alerting user that Guest cannot switch to Guest
    Button b = ViewHelpers.findButton(children, key);
    assert b != null;
    clickOn(b);
  }

  /**
   * Tests getProfilePicture() method with Guest photo.
   */
  @Test
  void testGetProfilePictureGuest() {
    ImageView guest = mySettings.getProfilePicture(Config.GUEST);
    assert guest != null;
    Image profilePhoto = guest.getImage();
    Assertions.assertNotNull(profilePhoto);
  }

  /**
   * Tests getProfilePicture() method with Valid User photo.
   */
  @Test
  void testGetProfilePictureValidUser() {
    ImageView guest = mySettings.getProfilePicture(VALID_USER);
    assert guest != null;
    Image profilePhoto = guest.getImage();
    Assertions.assertNotNull(profilePhoto);
  }

  /**
   * Tests getProfilePicture() method with Invalid User photo. Asserts method sets invalid user's
   * profile picture to Guest picture.
   */
  @Test
  void testGetProfilePictureInvalidUser() {
    runAsJFXAction(() -> {
      ImageView guest = mySettings.getProfilePicture(INVALID_USER);
      Image profilePhoto = guest.getImage();
      Assertions.assertNotNull(profilePhoto);
    });
  }
}
