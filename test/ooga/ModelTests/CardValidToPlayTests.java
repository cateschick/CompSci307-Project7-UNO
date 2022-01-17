package ooga.ModelTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.model.board.CardValidator;
import ooga.model.board.UnoCardValidator;
import ooga.model.cards.CardColor;
import ooga.model.cards.cardcomponents.Card;
import ooga.model.cards.cardcomponents.CardInfo;
import ooga.model.cards.cardcomponents.UnoCard;
import ooga.model.cards.cardcomponents.UnoCardInfo;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests that test card validation
 *
 * @author Alicia Wu
 */
public class CardValidToPlayTests extends ModelTests {

  /**
   * Test if a red #1 card can be played on top of a green #1 card
   */
  @Test
  void SameNumberCardsTest() {
    Card redCard = new UnoCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 1)});
    Card greenCard = new UnoCard(CardColor.GREEN, new CardInfo[]{new UnoCardInfo("number", 1)});
    checkAllValid(Arrays.asList(redCard, greenCard));
  }

  /**
   * Test if a blue reverse card can be played on top of a green reverse card
   */
  @Test
  void SameTypeCardTest() {
    Card blueCard = new UnoCard(CardColor.BLUE, new CardInfo[]{new UnoCardInfo("reverse", 1)});
    Card greenCard = new UnoCard(CardColor.GREEN, new CardInfo[]{new UnoCardInfo("reverse", 1)});
    checkAllValid(Arrays.asList(blueCard, greenCard));
  }

  /**
   * Test if a blue skip 1 card can be played on top of a red skip 2 card -- should fail
   */
  @Test
  void SameTypeCardDiffParamTest() {
    Card blueCard = new UnoCard(CardColor.BLUE, new CardInfo[]{new UnoCardInfo("skip", 1)});
    Card redCard = new UnoCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("skip", 2)});
    assertFalse(new UnoCardValidator().isValidToPlay(redCard, blueCard));
  }

  /**
   * Test if different yellow and blue cards can be played on top of each other -- should fail
   */
  @Test
  void DiffColoredCardsTest() {
    Card blueCard = new UnoCard(CardColor.BLUE, new CardInfo[]{new UnoCardInfo("draw", 2)});
    Card yellowCard = new UnoCard(CardColor.YELLOW, new CardInfo[]{new UnoCardInfo("number", 2)});
    assertFalse(new UnoCardValidator().isValidToPlay(blueCard, yellowCard));
  }

  /**
   * Test if a wild card can be played on top of any color/type card and vice versa
   */
  @Test
  void WildCardOnAnyTest() {
    Card wildCard = new UnoCard(CardColor.NONE, new CardInfo[]{new UnoCardInfo("wild", 1)});
    Card yellowCard = new UnoCard(CardColor.YELLOW, new CardInfo[]{new UnoCardInfo("number", 9)});
    Card redCard = new UnoCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("skip", 2)});
    Card greenCard = new UnoCard(CardColor.GREEN, new CardInfo[]{new UnoCardInfo("reverse", 3)});
    Card blueCard = new UnoCard(CardColor.BLUE, new CardInfo[]{new UnoCardInfo("draw", 4)});
    Card anotherWildCard = new UnoCard(CardColor.NONE, new CardInfo[]{new UnoCardInfo("wild", 1)});
    checkOneValidAgainstMany(wildCard,
        Arrays.asList(yellowCard, redCard, blueCard, greenCard, anotherWildCard));
  }

  /**
   * Test if a wild draw 4 card can be played on top of any color/type card and vice versa
   */
  @Test
  void WildDraw4CardOnAnyTest() {
    Card wildCard = new UnoCard(CardColor.NONE,
        new CardInfo[]{new UnoCardInfo("wild", 1), new UnoCardInfo("draw", 4)});
    Card yellowCard = new UnoCard(CardColor.YELLOW, new CardInfo[]{new UnoCardInfo("number", 9)});
    Card redCard = new UnoCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("skip", 2)});
    Card greenCard = new UnoCard(CardColor.GREEN, new CardInfo[]{new UnoCardInfo("reverse", 3)});
    Card blueCard = new UnoCard(CardColor.BLUE, new CardInfo[]{new UnoCardInfo("draw", 4)});
    Card anotherWildCard = new UnoCard(CardColor.NONE, new CardInfo[]{new UnoCardInfo("wild", 1)});
    checkOneValidAgainstMany(wildCard,
        Arrays.asList(yellowCard, redCard, blueCard, greenCard, anotherWildCard));
  }

  /**
   * Test if a wild draw 4 card can be played on a wild card
   */
  @Test
  void WildDraw4OnWildTest() {
    Card wild4Card = new UnoCard(CardColor.NONE,
        new CardInfo[]{new UnoCardInfo("wild", 1), new UnoCardInfo("draw", 4)});
    Card wildCard = new UnoCard(CardColor.NONE, new CardInfo[]{new UnoCardInfo("wild", 1)});
    checkAllValid(Arrays.asList(wildCard, wild4Card));
  }

  /**
   * Test if any yellow card can be played on top of any yellow card
   */
  @Test
  void TwoYellowCardsTest() {
    List<CardInfo[]> yellowCardsInfo = new ArrayList<>();
    yellowCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 3)});
    yellowCardsInfo.add(new CardInfo[]{new UnoCardInfo("skip", 1)});
    yellowCardsInfo.add(new CardInfo[]{new UnoCardInfo("reverse", 1)});
    yellowCardsInfo.add(new CardInfo[]{new UnoCardInfo("draw", 2)});
    yellowCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 0)});
    yellowCardsInfo.add(new CardInfo[]{new UnoCardInfo("number", 3)});
    Map<CardColor, List<CardInfo[]>> cardMap = new HashMap<>();
    cardMap.put(CardColor.YELLOW, yellowCardsInfo);

    List<Card> cardDeck = makeCardsInFactory(cardMap);
    checkAllValid(cardDeck);
  }

  private void checkOneValidAgainstMany(Card card, List<Card> cards) {
    CardValidator validator = new UnoCardValidator();
    for (int i = 0; i < cards.size() - 1; i++) {
      assertTrue(validator.isValidToPlay(card, cards.get(i)));
      assertTrue(validator.isValidToPlay(cards.get(i), card));
    }
  }

  private void checkAllValid(List<Card> cards) {
    CardValidator validator = new UnoCardValidator();
    for (int i = 0; i < cards.size() - 1; i++) {
      for (int j = i + 1; j < cards.size(); j++) {
        assertTrue(validator.isValidToPlay(cards.get(i), cards.get(j)));
      }
    }
  }

}
