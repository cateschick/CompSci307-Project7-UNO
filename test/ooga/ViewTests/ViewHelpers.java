package ooga.ViewTests;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ooga.view.Config;
import ooga.view.resources.Resources;
import ooga.view.View;
import util.DukeApplicationTest;

/**
 * These methods help to test the program's displays by containing methods used in most or all
 * display classes in the program. Also contains constants used during tests.
 *
 * @author Cate Schick cms168.
 */
public class ViewHelpers extends DukeApplicationTest {

  /**
   * Toolbar key.
   */
  public static final String TOOLBAR_KEY = "ToolBar";
  /**
   * Imageview key.
   */
  public static final String IMAGEVIEW_KEY = "ImageView";
  /**
   * HBox key.
   */
  public static final String HBOX_KEY = "HBox";
  /**
   * GridPane key.
   */
  public static final String GRIDPANE_KEY = "Grid";
  /**
   * VBox key.
   */
  public static final String VBOX_KEY = "VBox";

  /**
   * Makes sure proper display is created.
   *
   * @param myView    View associated with Display.
   * @param className expected class name.
   * @return True or False
   */
  public static boolean checkCreateDisplay(final View myView,
      final String className) {
    return myView.getMyDisplay().toString().contains(className);
  }

  /**
   * Makes sure an HBox containing title is at the top of display.
   *
   * @param myPane BorderPane containing display content.
   * @return True or False
   */
  public static boolean checkTitleHBox(final BorderPane myPane) {
    Node h = myPane.getTop();
    return (h.toString().contains(HBOX_KEY));
  }

  /**
   * Makes sure title has proper text.
   *
   * @param myPane        BorderPane containing display content.
   * @param expectedTitle Expected text in title.
   * @return True or False
   */
  public static boolean checkTitleName(final BorderPane myPane, final
  String expectedTitle) {
    HBox h = (HBox) myPane.getTop();
    Node title = h.getChildren().get(0);
    String key = Resources.getKey(expectedTitle);
    assert key != null;
    return title.toString().contains(key);
  }

  /**
   * Checks that the specified number of buttons are present in Display.
   *
   * @param children   List of children.
   * @param numButtons Expected number of buttons
   * @return True or False
   */
  public static boolean checkNumButtons(final ObservableList<Node> children,
      final int numButtons) {
    return numButtons == children.size();
  }

  /**
   * Checks that a back button is present in a BorderPane.
   *
   * @param myPane BorderPane containing display content.
   * @return True or False
   */
  public static boolean checkBackButton(final BorderPane myPane) {
    Node child = myPane.getLeft();
    String back = Config.BACK_BUTTON;

    return child.toString().contains(back);
  }

  /**
   * This method checks that a display contains a specified container.
   *
   * @param display      Display containing content.
   * @param expectedType Expected container type.
   */
  public static boolean checkContentDisplay(final BorderPane display, final String expectedType) {
    Node child = display.getCenter();
    return child.toString().contains(expectedType);
  }

  /**
   * This method clicks on a specified button.
   */
  public static Button findButton(final ObservableList<Node> children, final String key) {
    for (Object o : children) {
      if (o.toString().contains(key)) {
        return (Button) o;
      }
    }
    return null;
  }
}
