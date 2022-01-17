package ooga.ModelTests;

import java.util.Arrays;
import java.util.List;
import ooga.model.cards.CardColor;
import ooga.model.cards.UnoCardMaker;
import ooga.model.cards.cardcomparators.CardComparator;
import ooga.model.cards.cardcomponents.Card;
import ooga.model.cards.cardcomponents.CardInfo;
import ooga.model.cards.cardcomponents.UnoCardInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests that test sorting cards
 *
 * @author Alicia Wu
 */
public class CardComparatorTests extends ModelTests {

  /**
   * Test if smaller numbered card of same color will come before
   */
  @Test
  void testNumberOrdering() {
    Card red5 = new UnoCardMaker().makeCard(
        CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 5)});
    Card red8 = new UnoCardMaker().makeCard(
        CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 8)});
    List<Card> cards = Arrays.asList(red8, red5);
    cards.sort(new CardComparator());
    checkOrdering(Arrays.asList(red5, red8), cards);
  }

  /**
   * Test if color is being sorted correctly
   */
  @Test
  void testDiffColorOrdering() {
    Card red5 = new UnoCardMaker().makeCard(
        CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 5)});
    Card green5 = new UnoCardMaker().makeCard(
        CardColor.GREEN, new CardInfo[]{new UnoCardInfo("number", 5)});
    List<Card> cards = Arrays.asList(red5, green5);
    cards.sort(new CardComparator());
    checkOrdering(Arrays.asList(green5, red5), cards);
  }

  /**
   * Test if card with more info will come after
   */
  @Test
  void testNumberOfParamsSorting() {
    Card red5 = new UnoCardMaker().makeCard(
        CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 5)});
    Card redComplex = new UnoCardMaker().makeCard(
        CardColor.RED, new CardInfo[]{new UnoCardInfo("skip", 1), new UnoCardInfo("draw", 2)});
    List<Card> cards = Arrays.asList(redComplex, red5);
    cards.sort(new CardComparator());
    checkOrdering(Arrays.asList(red5, redComplex), cards);
  }

  /**
   * Test if cards with more info and diff parameters will be properly sorted
   */
  @Test
  void testMultipleParamValueSorting() {
    Card redComplex1 = new UnoCardMaker().makeCard(
        CardColor.RED, new CardInfo[]{new UnoCardInfo("skip", 1), new UnoCardInfo("draw", 4)});
    Card redComplex2 = new UnoCardMaker().makeCard(
        CardColor.RED, new CardInfo[]{new UnoCardInfo("skip", 1), new UnoCardInfo("draw", 2)});
    List<Card> cards = Arrays.asList(redComplex1, redComplex2);
    cards.sort(new CardComparator());
    checkOrdering(Arrays.asList(redComplex2, redComplex1), cards);
  }

  /**
   * Test if cards are properly sorted based on type
   */
  @Test
  void testCardTypeSorting() {
    Card redSkip = new UnoCardMaker().makeCard(
        CardColor.RED, new CardInfo[]{new UnoCardInfo("skip", 1)});
    Card redDraw = new UnoCardMaker().makeCard(
        CardColor.RED, new CardInfo[]{new UnoCardInfo("draw", 2)});
    List<Card> cards = Arrays.asList(redSkip, redDraw);
    cards.sort(new CardComparator());
    checkOrdering(Arrays.asList(redDraw, redSkip), cards);
  }

  /**
   * Test if numbered cards are properly sorted to be in front
   */
  @Test
  void testNumberedCardTypeSorting() {
    Card redNumber = new UnoCardMaker().makeCard(
        CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 5)});
    Card redNonNumber = new UnoCardMaker().makeCard(
        CardColor.RED, new CardInfo[]{new UnoCardInfo("draw", 1)});
    List<Card> cards = Arrays.asList(redNonNumber, redNumber);
    cards.sort(new CardComparator());
    checkOrdering(Arrays.asList(redNumber, redNonNumber), cards);
  }

  /**
   * Test sorting of more complex card collection
   */
  @Test
  void testComplexSorting() {
    List<Card> cards = makeCardsInFactory(makeComplexCardsInfo());
    cards.sort(new CardComparator());
    checkCardIsExpected(CardColor.GREEN, new CardInfo[]{new UnoCardInfo("number", 0)},
        cards.get(0));
    checkCardIsExpected(CardColor.NONE, new CardInfo[]{new UnoCardInfo("wild", 0)}, cards.get(4));
    checkCardIsExpected(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 0)}, cards.get(6));
    checkCardIsExpected(CardColor.RED, new CardInfo[]{new UnoCardInfo("reverse", 1)}, cards.get(9));
  }

  private void checkOrdering(List<Card> expected, List<Card> sorted) {
    Assertions.assertEquals(expected, sorted);
    for (int i = 0; i < expected.size(); i++) {
      Assertions.assertEquals(expected.get(i), sorted.get(i));
    }
  }

}
