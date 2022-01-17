package ooga.ViewTests.userAlertTests;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.view.Config;
import ooga.view.View;
import ooga.view.userAlert.UserAlert;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class userAlertTests extends DukeApplicationTest {

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

    myView.getMyDisplay().switchDisplay(Config.INTRO_DISPLAY_PATH +
        Config.INTRO_DISPLAY);
    myPane = myView.getMyDisplay().createDisplay();
    myView.getRoot().getChildren().add(myPane);

    stage.show();
  }

  /**
   * Tests showError() method.
   */
  @Test
  public void testShowError() {
    runAsJFXAction(() ->
    {
      Exception e = new NullPointerException();
      UserAlert.showError(e);
    });
  }

  /**
   * Tests show message.
   */
  @Test
  public void testShowMessage(){
    runAsJFXAction(() ->
    {
      UserAlert.showMessage("Title", Config.SUCCESS);
    });
  }
}
