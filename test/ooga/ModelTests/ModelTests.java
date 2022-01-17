package ooga.ModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.model.cards.CardColor;
import ooga.model.cards.CardFactory;
import ooga.model.cards.CardMaker;
import ooga.model.cards.UnoCardFactory;
import ooga.model.cards.UnoCardMaker;
import ooga.model.cards.cardcomponents.Card;
import ooga.model.cards.cardcomponents.CardInfo;
import ooga.model.cards.cardcomponents.UnoCardInfo;

/**
 * Abstract test class containing methods that concrete test subclasses share
 *
 * @author Alicia Wu
 */
public class ModelTests {

  protected Card makeCard(CardColor color, CardInfo[] info) {
    CardMaker maker = new UnoCardMaker();
    return maker.makeCard(color, info);
  }

  protected void checkCardIsExpected(CardColor expectedColor, CardInfo[] expectedInfo, Card card) {
    assertEquals(expectedColor, card.getCardColor());
    assertEquals(expectedInfo.length, card.getCardInfo().length);
    for (int i = 0; i < expectedInfo.length; i++) {
      assertEquals(expectedInfo[i].getType(), card.getCardInfo()[i].getType());
      assertEquals(expectedInfo[i].getParam(), card.getCardInfo()[i].getParam());
    }
  }

  protected List<Card> makeCardsInFactory(Map<CardColor, List<CardInfo[]>> cardsInfo) {
    CardFactory cardFactory = new UnoCardFactory();
    return cardFactory.makeCards(cardsInfo);
  }

  protected List<Card> getCardsOfColor(List<Card> allCards, CardColor color) {
    List<Card> cardsOfColor = new ArrayList<>();
    for (int i = 0; i < allCards.size(); i++) {
      if (allCards.get(i).getCardColor() == color) {
        cardsOfColor.add(allCards.get(i));
      }
    }
    return cardsOfColor;
  }

  protected Map<CardColor, List<CardInfo[]>> makeComplexCardsInfo() {
    Map<CardColor, List<CardInfo[]>> cardsInfo = new HashMap<>();
    List<CardInfo[]> redCardsInfo = new ArrayList<>();
    redCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 0)});
    redCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 7)});
    redCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 7)});
    redCardsInfo.add(new CardInfo[]{new UnoCardInfo("reverse", 1)});
    cardsInfo.put(CardColor.RED, redCardsInfo);
    List<CardInfo[]> greenCardsInfo = new ArrayList<>();
    greenCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 0)});
    greenCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 7)});
    greenCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 7)});
    greenCardsInfo.add(new CardInfo[]{new UnoCardInfo("reverse", 1)});
    cardsInfo.put(CardColor.GREEN, greenCardsInfo);
    List<CardInfo[]> wildCardsInfo = new ArrayList<>();
    wildCardsInfo.add(new CardInfo[]{new UnoCardInfo("wild", 0), new UnoCardInfo("draw", 4)});
    wildCardsInfo.add(new CardInfo[]{new UnoCardInfo("wild", 0)});
    cardsInfo.put(CardColor.NONE, wildCardsInfo);
    return cardsInfo;
  }

  public Map<CardColor, List<CardInfo[]>> makeVerySimpleCardsInfo() {
    Map<CardColor, List<CardInfo[]>> cardsInfo = new HashMap<>();
    List<CardInfo[]> redCardsInfo = new ArrayList<>();
    redCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 7)});
    cardsInfo.put(CardColor.RED, redCardsInfo);
    List<CardInfo[]> greenCardsInfo = new ArrayList<>();
    greenCardsInfo.add(new CardInfo[]{new UnoCardInfo("draw", 2)});
    cardsInfo.put(CardColor.GREEN, greenCardsInfo);
    List<CardInfo[]> yellowCardsInfo = new ArrayList<>();
    yellowCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 5)});
    cardsInfo.put(CardColor.YELLOW, yellowCardsInfo);
    return cardsInfo;
  }

  protected Map<CardColor, List<CardInfo[]>> makeSimpleWithWildCardsInfo() {
    Map<CardColor, List<CardInfo[]>> cardsInfo = new HashMap<>();
    List<CardInfo[]> redCardsInfo = new ArrayList<>();
    redCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 7)});
    cardsInfo.put(CardColor.RED, redCardsInfo);
    List<CardInfo[]> wildCardsInfo = new ArrayList<>();
    wildCardsInfo.add(new CardInfo[]{new UnoCardInfo("wild", 0)});
    cardsInfo.put(CardColor.NONE, wildCardsInfo);
    return cardsInfo;
  }

  protected Map<CardColor, List<CardInfo[]>> makeSimpleVariedCardsInfo() {
    Map<CardColor, List<CardInfo[]>> cardsInfo = new HashMap<>();
    List<CardInfo[]> redCardsInfo = new ArrayList<>();
    redCardsInfo.add(new CardInfo[]{new UnoCardInfo("reverse", 1)});
    cardsInfo.put(CardColor.RED, redCardsInfo);
    List<CardInfo[]> blueCardsInfo = new ArrayList<>();
    blueCardsInfo.add(new CardInfo[]{new UnoCardInfo("draw", 2)});
    blueCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 1)});
    blueCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 9)});
    cardsInfo.put(CardColor.BLUE, blueCardsInfo);
    List<CardInfo[]> greenCardsInfo = new ArrayList<>();
    greenCardsInfo.add(new CardInfo[]{new UnoCardInfo("draw", 2)});
    cardsInfo.put(CardColor.GREEN, greenCardsInfo);
    List<CardInfo[]> yellowCardsInfo = new ArrayList<>();
    yellowCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 5)});
    cardsInfo.put(CardColor.YELLOW, yellowCardsInfo);
    List<CardInfo[]> wildCardsInfo = new ArrayList<>();
    wildCardsInfo.add(new CardInfo[]{new UnoCardInfo("wild", 0)});
    cardsInfo.put(CardColor.NONE, wildCardsInfo);
    return cardsInfo;
  }

  public Map<CardColor, List<CardInfo[]>> makeRedNumberedCardsInfo() {
    Map<CardColor, List<CardInfo[]>> cardsInfo = new HashMap<>();
    List<CardInfo[]> redCardsInfo = new ArrayList<>();
    redCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 0)});
    redCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 1)});
    redCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 7)});
    redCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 4)});
    cardsInfo.put(CardColor.RED, redCardsInfo);
    return cardsInfo;
  }
}
