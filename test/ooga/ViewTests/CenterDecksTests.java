package ooga.ViewTests;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.Card;
import ooga.view.CenterDecks;
import ooga.view.PlayerHand;
import ooga.view.View;
import ooga.view.display.Display;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

/**
 * This class tests the functionality of the CenterDecks.java class
 */
public class CenterDecksTests extends DukeApplicationTest {

  /**
   * CenterDecks object
   */
  private CenterDecks myCenterDecks;
  /**
   * PlayerHand object
   */
  private PlayerHand myPlayerHand;
  /**
   * View object.
   */
  private View myView;
  /**
   * Display object.
   */
  private Display myDisplay;

  private int[] gameDimensions = {800,1200};


  /**
   * Override start method.
   *
   * @param stage Stage scene is built on.
   */
  @Override
  public void start(Stage stage) {
    myPlayerHand = new PlayerHand();
    myCenterDecks = new CenterDecks();
    myView = new View();
    myDisplay = new Display(myView);
    myView.makeScene(ooga.Config.MY_WIDTH, ooga.Config.MY_HEIGHT);
  }

  /**
   * This test ensures proper functionality of the createCenterDecks method
   */
  @Test
  void checkCreateCenterDecks() {
    HBox center = myCenterDecks.createCenterDecks(myPlayerHand, null);
    ObservableList<Node> children = center.getChildren();

    VBox buttons = (VBox) children.get(2);

    Button playButton = (Button) buttons.getChildren().get(0);

    String expected = "CenterDecks";

    String expectedButton = "Play";

    // Center Decks should have 2 decks, 1 button, and 1 label
    Assertions.assertEquals(4, children.size());
    // Center Decks should have style class of CenterDecks
    Assertions.assertEquals(expected, center.getStyleClass().get(0));
    // First (most left) node should be a Card
    Assertions.assertEquals(children.get(0).getClass(), Card.class);
    // Second node should be a Card
    Assertions.assertEquals(children.get(1).getClass(), Card.class);
    // Third node should be a VBox
    Assertions.assertEquals(children.get(2).getClass(), VBox.class);
    // Last (most right) node should be a Label
    Assertions.assertEquals(children.get(3).getClass(), Label.class);

    // Button should read "Play"
    Assertions.assertEquals(playButton.getText(), expectedButton);
  }

  /**
   * This test ensures proper functionality of the displayPirateSwapMessage method
   */
  @Test
  void checkDisplayPirateSwapMessage() {
    myCenterDecks.displayPlayCardMessage();
    myCenterDecks.displayPirateSwapMessage();


    String expected = "EXECUTING PIRATE SWAP! Player 0 and a random player are swapping hands!";
    // Executing a pirate swap should display the message above
    Assertions.assertEquals(expected, myCenterDecks.getActiveText().getText());
  }

  /**
   * This test ensures proper functionality of the displayPlayCardMessage method
   */
  @Test
  void checkDisplayCardMessage() {
    myCenterDecks.displayPlayCardMessage();

    String expected = "It's your turn!";
    // Displaying the play card message should create a label with "It's your turn!"
    Assertions.assertEquals(expected, myCenterDecks.getActiveText().getText());
  }

  /**
   * This test ensures proper functionality of the setDeckDisplay method
   */
  @Test
  void checkSetDeckDisplay() {
    List<String> testValues = new ArrayList<>();
    List<Integer> testParameters = new ArrayList<>();
    testValues.add("number");
    testParameters.add(7);
    Card discardDeck = new Card(testValues, "red", testParameters);
    Card drawDeck = new Card(testValues, "red", true, testParameters);
    HBox deck = new HBox();
    deck.getChildren().addAll(discardDeck, drawDeck);
    myCenterDecks.setDeckDisplay(deck);
    Card discard = (Card) myCenterDecks.getDeckDisplay().getChildren().get(0);

    // deckDisplay should be an HBox of 2 cards
    Assertions.assertEquals(2, myCenterDecks.getDeckDisplay().getChildren().size());
    // discard pile should be color red
    Assertions.assertEquals("red", discard.getColor());
    // discard pile should be a 7
    Assertions.assertEquals(7, discard.getParameters().get(0));
  }

  /**
   * This test ensures proper functionality of the updateDiscardDeckTopCard method
   */
  @Test
  void checkUpdateDiscardDeckTopCard() {
    List<String> testValues = new ArrayList<>();
    List<Integer> testParameters = new ArrayList<>();
    testValues.add("number");
    testParameters.add(7);
    Card discardDeck = new Card(testValues, "red", testParameters);
    myCenterDecks.updateDiscardDeckTopCard(discardDeck);

    // discard pile should be color red
    Assertions.assertEquals("red", myCenterDecks.getDiscardCard().getColor());
    // discard pile should be a 7
    Assertions.assertEquals(7, myCenterDecks.getDiscardCard().getParameters().get(0));
  }
}

