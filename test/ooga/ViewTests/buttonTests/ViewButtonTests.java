package ooga.ViewTests.buttonTests;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.ViewTests.ViewHelpers;
import ooga.view.Config;
import ooga.view.View;
import ooga.view.button.ViewButton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

final public class ViewButtonTests extends DukeApplicationTest {

  /**
   * View object.
   */
  private View myView;
  /**
   * BorderPane with display content.
   */
  private BorderPane myPane;
  /**
   * Test Button.
   */
  private Button myButton;
  /**
   * Test button array.
   */
  private Button[] myButtonArray;
  /**
   * Number to be incremented on button click.
   */
  private static int testNum;

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

    myView.getMyDisplay().switchDisplay(Config.DESIGN_SETTINGS_PATH +
        Config.DESIGN_SETTINGS);
    myPane = myView.getMyDisplay().createDisplay();
    myView.getRoot().getChildren().add(myPane);

    myButton = ViewButton.makeButton("Test",
        e -> addOne());
    myButtonArray = new Button[]{myButton};

    myPane.setCenter(myButton);

    stage.show();
  }

  /**
   * This method adds 1 to testNum to make sure clicking button carries out proper action.
   */
  private void addOne() {
    testNum++;
  }

  /**
   * Ensures makeButton method functions properly.
   */
  @Test
  void makeButtonTest() {
    ObservableList<Node> children = myPane.getChildren();
    Assertions.assertTrue(children.toString().contains(myButton.toString()));
  }

  /**
   * Checks to see that button is properly labelled.
   */
  @Test
  void buttonTextTest() {
    verifyThat(myButton, hasText("Test"));
  }

  /**
   * Checks that clicking on a button performs the proper action.
   */
  @Test
  void testButtonFunction() {
    Button b = ViewHelpers.findButton(myPane.getChildren(), "Test");
    assert b != null;
    clickOn(b);

    Assertions.assertEquals(1, testNum);
  }

  /**
   * This test checks styleButtons() addition of current font size to the style class.
   */
  @Test
  void styleButtonsTestStyleClass() {
    // Clear other children in pane and style with button array.
    runAsJFXAction(() -> {
      myPane.getChildren().clear();
      ViewButton.styleButtons(myButtonArray, myPane);
    });

    // Button should have style class of Button+currentSize
    String currentSize = myView.getSize();
    Assertions.assertTrue(myButton.getStyleClass().contains("Button" + currentSize));
  }

  /**
   * This test checks styleButtons() addition of button to a non-null display.
   */
  @Test
  void styleButtonsTestNumChildren() {
    // Clear other children in pane and style with button array.
    runAsJFXAction(() -> {
      myPane.getChildren().clear();
      ViewButton.styleButtons(myButtonArray, myPane);
    });

    // myPane should now only have one child, myButton
    Assertions.assertTrue(ViewHelpers.checkNumButtons(myPane.getChildren(), 1));
  }

  /**
   * This test checks styleButtons() addition of button to a non-null display.
   */
  @Test
  void styleButtonsTestChild() {
    // Clear other children in pane and style with button array.
    runAsJFXAction(() -> {
      myPane.getChildren().clear();
      ViewButton.styleButtons(myButtonArray, myPane);
    });

    // myPane should now only have one child, myButton
    Assertions.assertTrue(myPane.getChildren().toString().contains(myButton.toString()));
  }

  /**
   * This test checks styleButtons() with a NULL display.
   */
  @Test
  void styleButtonsTestNullDisplay() {
    // Clear other children in pane but do NOT add to styleButtons.
    runAsJFXAction(() -> {
      myPane.getChildren().clear();
      ViewButton.styleButtons(myButtonArray, null);
    });

    // myPane should now have ZERO CHILDREN
    Assertions.assertTrue(ViewHelpers.checkNumButtons(myPane.getChildren(), 0));
  }

  /**
   * This method checks the setButtonSize() method.
   */
  @Test
  void testSetButtonSize() {
    String test = "TestStyle";
    ViewButton.setButtonSize(myButton, test);
    String currentSize = myView.getSize();
    Assertions.assertTrue(myButton.getStyleClass().contains(
        test + currentSize));
  }

  /**
   * Tests addTooltip() method to make sure a message is displayed.
   */
  @Test
  void testAddTooltip() {
    Button b = new Button();
    ViewButton.addTooltip(b, "Test");
    Assertions.assertNotNull(b.getTooltip());
  }

  /**
   * Tests addTooltip() method to make sure proper message is attached to button.
   */
  @Test
  void testAddTooltipMessage() {
    String testMessage = "Test";
    Button b = new Button();
    ViewButton.addTooltip(b, testMessage);
    Assertions.assertEquals("Test", b.getTooltip().getText());
  }
}
