package ooga.ViewTests;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.view.Card;
import ooga.view.FactoryComponents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ArrayList;
import java.util.List;

/**
 * This class tests the functionality of the Card.java class
 */
public class CardTests extends DukeApplicationTest {

  /**
   * CardFactory object.
   */
  private FactoryComponents myFactoryComponents;

  /**
   * Override start method.
   *
   * @param stage Stage scene is built on.
   */
  @Override
  public void start(Stage stage) {
    myFactoryComponents = new FactoryComponents();
  }

  /**
   * This test ensures proper functionality of the face up Card constructor
   */
  @Test
  void checkCreateFaceUpCard() {
    List<String> value = new ArrayList<>();
    value.add("number");
    String color = "RED";
    List<Integer> parameter = new ArrayList<>();
    parameter.add(6);
    Card card = new Card(value, color, parameter);

    // Card should have value of 6
    Assertions.assertEquals(6, card.getParameters().get(0));
    // Card should NOT have value of 3
    Assertions.assertNotEquals(3, card.getParameters().get(0));
    // Card should be RED
    Assertions.assertEquals("RED", card.getColor());
    // Card should NOT be GREEN
    Assertions.assertNotEquals("GREEN", card.getColor());
    // Card should be of number type
    Assertions.assertEquals("number", card.getValue().get(0));
    // Card should NOT be of wild type
    Assertions.assertNotEquals("wild", card.getParameters().get(0));
  }

  /**
   * This test ensures proper functionality of the face-down Card constructor
   */
  @Test
  void checkCreateFaceDownCard() {
    List<String> value = new ArrayList<>();
    value.add("number");
    String color = "RED";
    List<Integer> parameter = new ArrayList<>();
    parameter.add(6);
    Card card = new Card(value, color, true, parameter);

    // Card should have value of 6
    Assertions.assertEquals(6, card.getParameters().get(0));
    // Card should NOT have value of 3
    Assertions.assertNotEquals(3, card.getParameters().get(0));
    // Card should be RED
    Assertions.assertEquals("RED", card.getColor());
    // Card should NOT be GREEN
    Assertions.assertNotEquals("GREEN", card.getColor());
    // Card should be of number type
    Assertions.assertEquals("number", card.getValue().get(0));
    // Card should NOT be of wild type
    Assertions.assertNotEquals("wild", card.getParameters().get(0));
  }

  /**
   * This test ensures proper functionality of the createLabel method
   */
  @Test
  void checkCreateLabel() {
    Label label = myFactoryComponents.createLabel("UnoTest");
    String expected = "UnoTest";
    String incorrect = "PokerTest";

    String style = "label";

    // Label should have text of "UnoTest"
    Assertions.assertEquals(expected, label.getText());
    // Label should not have text of "PokerTest"
    Assertions.assertNotEquals(incorrect, label.getText());
    // Label should have style class of "label"
    Assertions.assertEquals(style, label.getStyleClass().get(0));
  }
}