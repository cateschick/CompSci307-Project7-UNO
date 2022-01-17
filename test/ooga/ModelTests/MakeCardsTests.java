package ooga.ModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.model.cards.CardColor;
import ooga.model.cards.UnoCardMaker;
import ooga.model.cards.cardcomponents.Card;
import ooga.model.cards.cardcomponents.CardInfo;
import ooga.model.cards.cardcomponents.UnoCardInfo;
import ooga.model.exceptions.InvalidCardException;
import ooga.model.exceptions.InvalidCardParameterException;
import ooga.model.exceptions.WildCardNotOfColorNoneException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests that test making cards (card factory/maker)
 *
 * @author Alicia Wu
 */
public class MakeCardsTests extends ModelTests {

  /**
   * Test if ordering of information matters (it shouldn't)
   */
  @Test
  void makeSameCardDiffOrdering() {
    Card wildDraw4 = new UnoCardMaker().makeCard(CardColor.NONE,
        new CardInfo[]{new UnoCardInfo("wild", 0), new UnoCardInfo("draw", 4)});
    checkCardIsExpected(CardColor.NONE,
        new CardInfo[]{new UnoCardInfo("draw", 4), new UnoCardInfo("wild", 0)}, wildDraw4);
  }

  /**
   * Test giving a negative integer for parameter
   */
  @Test
  void makeCardWithNegParam() {
    Assertions.assertThrows(InvalidCardParameterException.class, () -> {
      Card number = new UnoCardMaker().makeCard(CardColor.NONE,
          new CardInfo[]{new UnoCardInfo("number", -3)});
    });
  }

  /**
   * Test making a card of color none but of a different type other than wild
   */
  @Test
  void makeCardOfColorNoneButNotWild() {
    Assertions.assertThrows(WildCardNotOfColorNoneException.class, () -> {
      Card card = new UnoCardMaker().makeCard(CardColor.NONE, new CardInfo[]{new UnoCardInfo("number", 0)});
    });
  }

  /**
   * Test making a card of color none but of a different type other than wild
   */
  @Test
  void makeCardOfCardWildButNotColorNone() {
    Assertions.assertThrows(WildCardNotOfColorNoneException.class, () -> {
      Card card = new UnoCardMaker().makeCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("wild", 0)});
    });
  }


  /**
   * Test making complex card with negative integer for parameters
   */
  @Test
  void makeComplexCardWithNegParams() {
    Assertions.assertThrows(InvalidCardParameterException.class, () -> {
      Card wildDraw = new UnoCardMaker().makeCard(CardColor.NONE,
          new CardInfo[]{new UnoCardInfo("wild", -3), new UnoCardInfo("draw", -1)});
    });
  }

  /**
   * Test making a card with null color
   */
  @Test
  void makeCardWithNullColor() {
    Assertions.assertThrows(InvalidCardException.class, () -> {
      Card number = new UnoCardMaker().makeCard(null,
          new CardInfo[]{new UnoCardInfo("number", 3)});
    });
  }

  /**
   * Test making a card with empty card info
   */
  @Test
  void makeCardWithEmptyCardInfo() {
    Assertions.assertThrows(InvalidCardException.class, () -> {
      Card number = new UnoCardMaker().makeCard(CardColor.RED,
          new CardInfo[]{});
    });
  }

  /**
   * Test making a card with null card info
   */
  @Test
  void makeCardWithNullCardInfo() {
    Assertions.assertThrows(InvalidCardException.class, () -> {
      Card number = new UnoCardMaker().makeCard(CardColor.YELLOW, null);
    });
  }

  /**
   * Test if a red number 3 card can be made
   */
  @Test
  void makeARedThreeCard() {
    CardInfo num3 = new UnoCardInfo("number", 3);
    Card newCard = makeCard(CardColor.RED, new CardInfo[]{num3});
    checkCardIsExpected(CardColor.RED, new CardInfo[]{num3}, newCard);
  }

  /**
   * Test if a blue skip once card can be made
   */
  @Test
  void makeABlueSkipCard() {
    CardInfo skip1 = new UnoCardInfo("skip", 1);
    Card newCard = makeCard(CardColor.BLUE, new CardInfo[]{skip1});
    checkCardIsExpected(CardColor.BLUE, new CardInfo[]{skip1}, newCard);
  }

  /**
   * Test if a wild card can be made
   */
  @Test
  void makeAWildCard() {
    CardInfo wild0 = new UnoCardInfo("wild", 0);
    Card newCard = makeCard(CardColor.NONE, new CardInfo[]{wild0});
    checkCardIsExpected(CardColor.NONE, new CardInfo[]{wild0}, newCard);
  }

  /**
   * Test if a wild draw 4 card can be made
   */
  @Test
  void makeAWildDraw4Card() {
    CardInfo wild0 = new UnoCardInfo("wild", 0);
    CardInfo draw4 = new UnoCardInfo("draw", 4);
    Card newCard = makeCard(CardColor.NONE, new CardInfo[]{draw4, wild0});
    checkCardIsExpected(CardColor.NONE, new CardInfo[]{draw4, wild0}, newCard);
  }

  /**
   * Test if card factory can make 2 red cards
   */
  @Test
  void makeATwoRedInFactory() {
    List<CardInfo[]> redCardsInfo = new ArrayList<>();
    redCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 3)});
    redCardsInfo.add(new CardInfo[]{new UnoCardInfo("reverse", 1)});
    Map<CardColor, List<CardInfo[]>> cardMap = new HashMap<>();
    cardMap.put(CardColor.RED, redCardsInfo);

    List<Card> cardDeck = makeCardsInFactory(cardMap);
    assertEquals(2, cardDeck.size());

    checkCardIsExpected(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 3)},
        cardDeck.get(0));
    checkCardIsExpected(CardColor.RED, new CardInfo[]{new UnoCardInfo("reverse", 1)},
        cardDeck.get(1));
  }

  /**
   * Test if card factory can make complex cards
   */
  @Test
  void makeComplexCardsInFactory() {
    Map<CardColor, List<CardInfo[]>> cardsInfo = makeComplexCardsInfo();
    List<Card> cardDeck = makeCardsInFactory(cardsInfo);
    assertEquals(10, cardDeck.size());
    assertEquals(4, getCardsOfColor(cardDeck, CardColor.RED).size());
    assertEquals(4, getCardsOfColor(cardDeck, CardColor.GREEN).size());
    assertEquals(2, getCardsOfColor(cardDeck, CardColor.NONE).size());
  }


}
