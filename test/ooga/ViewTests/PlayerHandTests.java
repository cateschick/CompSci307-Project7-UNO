package ooga.ViewTests;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.Card;
import ooga.view.FactoryComponents;
import ooga.view.PlayerHand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

/**
 * This class tests the functionality of the PlayerHand.java class
 */
public class PlayerHandTests extends DukeApplicationTest {

  /**
   * PlayerHand object.
   */
  private PlayerHand myPlayerHand;

  /**
   * FactoryComponents object
   */
  private FactoryComponents myFactoryComponents;
  private int[] gameDimensions = {800,1200};

  private Controller controller;
  /**
   * Override start method.
   *
   * @param stage Stage scene is built on.
   */
  @Override
  public void start(Stage stage) {
    myPlayerHand = new PlayerHand();
    myFactoryComponents = new FactoryComponents();
  }

  /**
   * This test ensures proper functionality of the initializePlayerHandsAtStart method
   */
  @Test
  void checkInitializePlayerHandsAtStart() {
    List<List<String>> handCardColors = List.of(List.of("GREEN"),List.of("RED"),List.of("BLUE"),List.of("YELLOW"));
    List<List<List<String>>> handCardTypes = List.of(List.of(List.of("number")),List.of(List.of("number")),List.of(List.of("number")),List.of(List.of("number")));
    List<List<List<Integer>>> handCardParameters = List.of(List.of(List.of(1)),List.of(List.of(2)),List.of(List.of(3)),List.of(List.of(4)));
    myPlayerHand.initializePlayerHandsAtStart(handCardColors, handCardTypes, handCardParameters);


    // Number of players should be 4
    Assertions.assertEquals(4, myPlayerHand.getNumberOfPlayers());
    // Number of players should not be 3
    Assertions.assertNotEquals(3, myPlayerHand.getNumberOfPlayers());
    // Number of horizontal hands developed should be 4
    Assertions.assertEquals(4, myPlayerHand.getHorizontalHands().size());
    // Number of horizontal hands developed should be 4
    Assertions.assertNotEquals(3, myPlayerHand.getHorizontalHands().size());
    // Number of vertical hands developed should be 4
    Assertions.assertEquals(4, myPlayerHand.getVerticalHands().size());
    // Number of vertical hands developed should not be 3
    Assertions.assertNotEquals(3, myPlayerHand.getVerticalHands().size());
  }

  /**
   * This test ensures proper functionality of the updateCurrentPlayersHand method
   */
  @Test
  void checkUpdateCurrentPlayersHand() {
    List<List<String>> handCardColors = List.of(List.of("GREEN"),List.of("RED"),List.of("BLUE"),List.of("YELLOW"));
    List<List<List<String>>> handCardTypes = List.of(List.of(List.of("number")),List.of(List.of("number")),List.of(List.of("number")),List.of(List.of("number")));
    List<List<List<Integer>>> handCardParameters = List.of(List.of(List.of(1)),List.of(List.of(2)),List.of(List.of(3)),List.of(List.of(4)));
    myPlayerHand.initializePlayerHandsAtStart(handCardColors, handCardTypes, handCardParameters);

    // Green 4 and Red 6 were created and updated from above
    List<String> handCardColor = List.of("GREEN", "RED");
    List<List<String>> handCardType = List.of(List.of("number"), List.of("number"));
    List<List<Integer>> handCardParameter = List.of(List.of(4), List.of(6));
    myPlayerHand.updateCurrentPlayersHand(handCardColor, handCardType, handCardParameter);

    Card first = (Card) myPlayerHand.getHorizontalHands().get(0).getChildren().get(0);
    Card second = (Card) myPlayerHand.getHorizontalHands().get(0).getChildren().get(1);

    // Number of cards in hand should be 2
    Assertions.assertEquals(2, myPlayerHand.getHorizontalHands().get(0).getChildren().size());
    // Number of cards in hand should not be 1 , as before the update
    Assertions.assertNotEquals(1, myPlayerHand.getHorizontalHands().get(0).getChildren().size());

    // First card in hand should be a green 4
    Assertions.assertEquals("GREEN", first.getColor());
    // First card in hand should be a green 4
    Assertions.assertEquals(4, first.getParameters().get(0));
    // Second card in hand should be a red 6
    Assertions.assertEquals("RED", second.getColor());
    // Second card in hand should be a red 6
    Assertions.assertEquals(6, second.getParameters().get(0));
  }

  /**
   * This test ensures proper functionality of the createUserHand method
   */
  @Test
  void checkCreateUserHand() {
    // User Hand (0th index) should be a green 1
    List<List<String>> handCardColors = List.of(List.of("GREEN"),List.of("RED"),List.of("BLUE"),List.of("YELLOW"));
    List<List<List<String>>> handCardTypes = List.of(List.of(List.of("number")),List.of(List.of("number")),List.of(List.of("number")),List.of(List.of("number")));
    List<List<List<Integer>>> handCardParameters = List.of(List.of(List.of(1)),List.of(List.of(2)),List.of(List.of(3)),List.of(List.of(4)));
    myPlayerHand.initializePlayerHandsAtStart(handCardColors, handCardTypes, handCardParameters);

    HBox user = myPlayerHand.createUserHand("Luke");

    String expected = "Luke";
    String unexpected = "Jack";

    String expectedStyle = "PlayerHand";

    // Number of objects in hand should be 2 (1 card, 1 label)
    Assertions.assertEquals(2, user.getChildren().size());
    // Number of objects in hand shouldn't just be one
    Assertions.assertNotEquals(1, user.getChildren().size());

    Label label = (Label) user.getChildren().get(1);
    // Hand should have label "Luke"
    Assertions.assertEquals(expected, label.getText());
    // Hand shouldn't have label "Jack"
    Assertions.assertNotEquals(unexpected, label.getText());

    // Hand should have style class "PlayerHand"
    Assertions.assertEquals(expectedStyle, user.getStyleClass().toString());
  }

  /**
   * This test ensures proper functionality of the:
   * createLeftOpponentHand,
   * createTopOpponentHand, and
   * createRightOpponentHand methods
   */
  @Test
  void checkCreateOpponentHands() {
    // Left player (1st index) should have RED 2
    // Top player (2nd index) should have BLUE 3
    // Right player (3rd index) should have Yellow 4
    List<List<String>> handCardColors = List.of(List.of("GREEN"),List.of("RED"),List.of("BLUE"),List.of("YELLOW"));
    List<List<List<String>>> handCardTypes = List.of(List.of(List.of("number")),List.of(List.of("number")),List.of(List.of("number")),List.of(List.of("number")));
    List<List<List<Integer>>> handCardParameters = List.of(List.of(List.of(1)),List.of(List.of(2)),List.of(List.of(3)),List.of(List.of(4)));
    myPlayerHand.initializePlayerHandsAtStart(handCardColors, handCardTypes, handCardParameters);

    VBox left = myPlayerHand.createLeftOpponentHand("Left");
    HBox top = myPlayerHand.createTopOpponentHand("Top");
    VBox right = myPlayerHand.createRightOpponentHand("Right");

    String expectedL = "Left";
    String expectedT = "Top";
    String expectedR = "Right";
    String unexpected = "Luke";

    String expectedStyle = "PlayerHand";

    // Number of objects in  each hand should be 2 (1 card, 1 label)
    Assertions.assertEquals(2, left.getChildren().size());
    // Number of objects in  each hand should be 2 (1 card, 1 label)
    Assertions.assertEquals(2, top.getChildren().size());
    // Number of objects in  each hand should be 2 (1 card, 1 label)
    Assertions.assertEquals(2, right.getChildren().size());
    // Number of objects in hand shouldn't just be one
    Assertions.assertNotEquals(1, left.getChildren().size());

    Label label = (Label) left.getChildren().get(1);
    // Left hand should have label "Left"
    Assertions.assertEquals(expectedL, label.getText());
    // Hand shouldn't have label "Luke"
    Assertions.assertNotEquals(unexpected, label.getText());

    label = (Label) top.getChildren().get(1);
    // Top hand should have label "Top"
    Assertions.assertEquals(expectedT, label.getText());
    // Hand shouldn't have label "Luke"
    Assertions.assertNotEquals(unexpected, label.getText());

    label = (Label) right.getChildren().get(1);
    // Right hand should have label "Right"
    Assertions.assertEquals(expectedR, label.getText());
    // Hand shouldn't have label "Luke"
    Assertions.assertNotEquals(unexpected, label.getText());

    // Hands should have style class "PlayerHand"
    Assertions.assertEquals(expectedStyle, left.getStyleClass().toString());
    Assertions.assertEquals(expectedStyle, top.getStyleClass().toString());
    Assertions.assertEquals(expectedStyle, right.getStyleClass().toString());
  }

  /**
   * This test ensures proper functionality of the setCurrentPlayer method
   */
  @Test
  void checkSetCurrentPLayer() {
    int start = 0;

    // Should start with the user's turn
    Assertions.assertEquals(start, myPlayerHand.getCurrentPlayer());

    myPlayerHand.setCurrentPlayer(3);
    // Should now be the right player's turn (3rd player)
    Assertions.assertEquals(3, myPlayerHand.getCurrentPlayer());
  }
  /**
   * This test ensures proper functionality of the setValidCards method
   */
  @Test
  void checkSetValidCards() {
    List<Integer> valids = List.of(1,2,3);

    myPlayerHand.setValidCards(valids);

    Assertions.assertEquals(List.of(1,2,3), myPlayerHand.getValidCards());
  }
}

