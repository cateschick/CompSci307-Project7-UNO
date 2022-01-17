package ooga.ControllerTests;

import javafx.stage.Stage;
import ooga.Config;
import ooga.controller.Controller;
import ooga.controller.DataController;
import ooga.controller.DisplayController;
import ooga.data.GameStarter;
import ooga.model.cards.CardColor;
import ooga.model.cards.cardcomponents.CardInfo;
import ooga.model.cards.cardcomponents.UnoCard;
import ooga.model.cards.cardcomponents.UnoCardInfo;
import ooga.model.games.CardGame;
import ooga.model.games.Game;
import ooga.view.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


/**
 * JUnit tests that test display controller card parsing
 *
 * @author Ryleigh Byrne
 */
public class DisplayControllerTests extends DukeApplicationTest {
    private DisplayController displayController;
    private DataController dataController;
    private Controller controller;
    private List<String> testValues = new ArrayList<>();
    private List<Integer> testParameters = new ArrayList<>();
    private ooga.model.cards.cardcomponents.Card returnedCard;
    private CardInfo[] listCardInfo;
    private String cardColor;
    private CardGame game;
    private GameStarter gameStarter;
    private String filePath = Config.DEFAULT_BASIC_GAME;
    private ooga.model.cards.cardcomponents.Card modelCard;
    private Stage myStage;
    private int[] gameDimensions = {800, 800};

    /**
     * Override start method.
     *
     * @param stage Stage scene is built on.
     */
    @Override
    public void start(Stage stage) {
        myStage = stage;
        controller = new Controller(myStage, new int[]{ooga.Config.MY_WIDTH, ooga.Config.MY_HEIGHT});
        controller.generateView();
    }

    void setupTestValues() throws Exception {
        game = gameStarter.createGame(filePath);
        controller.initializeNewGame(game);
        displayController = new DisplayController(controller);
        cardColor = "BLUE";
        testValues.add("number");
        testParameters.add(7);
        Card cardPlayed = new Card(testValues, cardColor,  testParameters);
        returnedCard = displayController.convertViewCardToModelCard(cardPlayed);
        CardInfo testCardInfo = new UnoCardInfo("number", 7);
        listCardInfo = new CardInfo[]{testCardInfo};
    }

    /**
     * Tests that view card to model card colors are successfully converted
     * @throws Exception
     */
    @Test
    void testConvertViewCardToModelCardColor() throws Exception {
        setupTestValues();
        ooga.model.cards.cardcomponents.Card modelCardVersion = new UnoCard(CardColor.valueOf(cardColor), listCardInfo);
        Assertions.assertEquals(modelCardVersion.getCardColor(), returnedCard.getCardColor());
    }

    /**
     * Tests that view card to model card values are successfully converted
     * @throws Exception
     */
    @Test
    void testConvertViewCardToModelCardValues() throws Exception {
        setupTestValues();
        ooga.model.cards.cardcomponents.Card modelCardVersion = new UnoCard(CardColor.valueOf(cardColor), listCardInfo);
        Assertions.assertEquals(modelCardVersion.getCardInfo()[0].getType(), returnedCard.getCardInfo()[0].getType());
    }

    /**
     * Tests that view card to model card parameters are successfully converted
     * @throws Exception
     */
    @Test
    void testConvertViewCardToModelCardParams() throws Exception {
        setupTestValues();
        ooga.model.cards.cardcomponents.Card modelCardVersion = new UnoCard(CardColor.valueOf(cardColor), listCardInfo);
        Assertions.assertEquals(modelCardVersion.getCardInfo()[0].getParam(), returnedCard.getCardInfo()[0].getParam());
    }

    /**
     * Tests if generated model card is converted into the actual players hand in model
     * @throws Exception
     */

    @Test
    void testChangeModelCardToCardInPlayersHandColors() throws Exception {
        setupTestValues();
        Random rand = new Random();
        int sizeOfHand = game.getCurrPlayer().getAllCards().size();
        int randomCardInt = rand.nextInt(sizeOfHand);
        Card viewCard = displayController.convertModelCardToViewCard(game.getCurrPlayer().getAllCards().get(randomCardInt));
        ooga.model.cards.cardcomponents.Card modelCard = displayController.convertViewCardToModelCard(viewCard);
        ooga.model.cards.cardcomponents.Card convertedCard = displayController.changeConvertedModelCardToCardInHand(modelCard);
        ooga.model.cards.cardcomponents.Card card = game.getCurrPlayer().getAllCards().get(randomCardInt);
        Assertions.assertEquals(convertedCard, card);
    }





}
