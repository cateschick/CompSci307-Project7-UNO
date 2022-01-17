package ooga.ModelTests;

import java.util.List;
import java.util.Map;
import ooga.Config;
import ooga.model.cardcollection.CardCollection;
import ooga.model.cardcollection.DeckCardCollection;
import ooga.model.cardcollection.deck.DrawDeck;
import ooga.model.cardcollection.deck.PlayDeck;
import ooga.model.cardcollection.hand.PlayerHand;
import ooga.model.cards.CardColor;
import ooga.model.cards.cardcomponents.Card;
import ooga.model.cards.cardcomponents.CardInfo;
import ooga.model.cards.cardcomponents.UnoCard;
import ooga.model.cards.cardcomponents.UnoCardInfo;
import ooga.model.exceptions.CardNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests that test the card collection module
 *
 * @author Alicia Wu
 */
public class CardCollectionTests extends ModelTests {

  /**
   * Test creating a new player hand, should be empty
   */
  @Test
  void makeNewPlayerHand() {
    CardCollection hand = new PlayerHand(Config.EMPTY_CARD_COLLECTION);
    Assertions.assertEquals(0, hand.getAllCards().size());
  }

  /**
   * Test creating a new play deck, should be empty
   */
  @Test
  void makeNewPlayDeck() {
    CardCollection playDeck = new PlayDeck(Config.EMPTY_CARD_COLLECTION);
    Assertions.assertEquals(0, playDeck.getAllCards().size());
  }

  /**
   * Test creating a new draw deck, should have 10 cards
   */
  @Test
  void makeNewDrawDeck() {
    Map<CardColor, List<CardInfo[]>> list = makeComplexCardsInfo();
    Assertions.assertNotNull(list);
    CardCollection drawDeck = new DrawDeck(list);
    Assertions.assertEquals(10, drawDeck.getAllCards().size());
  }

  /**
   * Test adding to a card collection
   */
  @Test
  void testAddCard() {
    CardCollection drawDeck = new DrawDeck(makeComplexCardsInfo());
    Card greenCard = new UnoCard(CardColor.GREEN, new CardInfo[]{new UnoCardInfo("number", 9)});
    drawDeck.addCard(greenCard);
    Assertions.assertEquals(11, drawDeck.getAllCards().size());
  }

  /**
   * Test adding a list of cards to a card collection
   */
  @Test
  void testAddListOfCards() {
    CardCollection drawDeck = new DrawDeck(makeComplexCardsInfo());
    CardCollection playDeck = new PlayDeck(Config.EMPTY_CARD_COLLECTION);
    playDeck.addAll(drawDeck.getAllCards());
    Assertions.assertEquals(10, playDeck.getAllCards().size());
  }

  /**
   * Test adding to a top of the play deck
   */
  @Test
  void testAddCardToTop() {
    DeckCardCollection playDeck = new PlayDeck(Config.EMPTY_CARD_COLLECTION);
    Card greenCard = new UnoCard(CardColor.GREEN, new CardInfo[]{new UnoCardInfo("skip", 1)});
    Card blueCard = new UnoCard(CardColor.BLUE, new CardInfo[]{new UnoCardInfo("number", 0)});
    // play deck should always add cards to top
    playDeck.addCard(blueCard);
    Assertions.assertEquals(blueCard, playDeck.peekTopCard());
    playDeck.addCard(greenCard);
    Assertions.assertEquals(greenCard, playDeck.peekTopCard());
  }

  /**
   * Test removing a card from a card collection
   */
  @Test
  void testRemoveCard() {
    DeckCardCollection drawDeck = new DrawDeck(makeComplexCardsInfo());
    drawDeck.getTopCard();
    Assertions.assertEquals(9, drawDeck.getSize());
    drawDeck.getTopCard();
    Assertions.assertEquals(8, drawDeck.getSize());
  }

  /**
   * Test transfer a card from draw deck to hand
   */
  @Test
  void testTransferCardDrawToHand() {
    DeckCardCollection drawDeck = new DrawDeck(makeComplexCardsInfo());
    CardCollection playerHand = new PlayerHand(Config.EMPTY_CARD_COLLECTION);
    Assertions.assertDoesNotThrow(
        () -> drawDeck.transferCardTo(playerHand, drawDeck.peekTopCard()));
    Assertions.assertEquals(1, playerHand.getSize());
  }

  /**
   * Test transfer a card from hand to play deck
   */
  @Test
  void testTransferCardHandToPlay() {
    DeckCardCollection drawDeck = new DrawDeck(makeComplexCardsInfo());
    CardCollection playerHand = new PlayerHand(Config.EMPTY_CARD_COLLECTION);
    DeckCardCollection playDeck = new PlayDeck(Config.EMPTY_CARD_COLLECTION);
    Assertions.assertDoesNotThrow(() -> {
      drawDeck.transferCardTo(playerHand, drawDeck.peekTopCard());
      drawDeck.transferCardTo(playerHand, drawDeck.peekTopCard());
      Assertions.assertEquals(2, playerHand.getSize());
      playerHand.transferCardTo(playDeck, playerHand.getAllCards().get(0));
      Assertions.assertEquals(1, playerHand.getSize());
      Assertions.assertEquals(1, playDeck.getSize());
    });
  }

  /**
   * Test transfer all cards from play to draw deck and vice versa
   */
  @Test
  void testTransferCardPlayAndDraw() {
    DeckCardCollection drawDeck = new DrawDeck(makeComplexCardsInfo());
    DeckCardCollection playDeck = new PlayDeck(Config.EMPTY_CARD_COLLECTION);
    Assertions.assertDoesNotThrow(() -> {
      drawDeck.transferAll(playDeck);
      playDeck.transferAll(drawDeck);
    });
  }

  /**
   * Test sorting a players hand
   */
  @Test
  void testSortHand() {
    DeckCardCollection drawDeck = new DrawDeck(makeSimpleVariedCardsInfo());
    drawDeck.reorganize();
    CardCollection playerHand = new PlayerHand(Config.EMPTY_CARD_COLLECTION);
    Assertions.assertDoesNotThrow(() -> drawDeck.transferAll(playerHand));
    playerHand.reorganize();
    Card firstCard = playerHand.getAllCards().get(0);
    checkCardIsExpected(CardColor.BLUE, new CardInfo[]{new UnoCardInfo("number", 1)}, firstCard);
    Card secondCard = playerHand.getAllCards().get(1);
    checkCardIsExpected(CardColor.BLUE, new CardInfo[]{new UnoCardInfo("number", 9)}, secondCard);
    Card thirdCard = playerHand.getAllCards().get(2);
    checkCardIsExpected(CardColor.BLUE, new CardInfo[]{new UnoCardInfo("draw", 2)}, thirdCard);
    Card lastCard = playerHand.getAllCards().get(6);
    checkCardIsExpected(CardColor.YELLOW, new CardInfo[]{new UnoCardInfo("number", 5)}, lastCard);
  }

  /**
   * Test shuffling draw deck
   */
  @Test
  void testShuffleDrawDeck() {
    DeckCardCollection drawDeck = new DrawDeck(makeComplexCardsInfo());
    DeckCardCollection oldDeck = drawDeck;
    drawDeck.reorganize();
    Assertions.assertNotEquals(oldDeck.getTopCard(), drawDeck.getTopCard());
  }

  /**
   * Test finding the ordering of cards in a collection
   */
  @Test
  void testFindCardOrderingsInCollection() {
    CardCollection playerHand = new PlayerHand(Config.EMPTY_CARD_COLLECTION);
    Card cardToFind = new UnoCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 4)});
    Assertions.assertThrows(CardNotFoundException.class, () -> playerHand.findCardsOrderingInCollection(List.of(cardToFind)));
  }

  /**
   * Test finding the ordering of multiple cards in a collection
   */
  @Test
  void testFindMultipleCards() {
    Card c2 = new UnoCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 1)});
    Card c3 = new UnoCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 7)});
    Card c1 = new UnoCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 4)});
    CardCollection playerHand = new PlayerHand(Config.EMPTY_CARD_COLLECTION);
    playerHand.addAll(List.of(c2, c3, c1));
    List<Integer> locations = playerHand.findCardsOrderingInCollection(List.of(c1, c3));
    Assertions.assertEquals(2, locations.size());
    Assertions.assertEquals(1, locations.get(0));
    Assertions.assertEquals(2, locations.get(1));
  }

  /**
   * Test trying to find a card that doesn't exist in the hand will cause an error
   */
  @Test
  void testFindCardNotExisting() {
    Card c2 = new UnoCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 1)});
    Card c3 = new UnoCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 7)});
    Card c1 = new UnoCard(CardColor.RED, new CardInfo[]{new UnoCardInfo("number", 4)});
    CardCollection playerHand = new PlayerHand(Config.EMPTY_CARD_COLLECTION);
    playerHand.addAll(List.of(c2, c3, c1));
    List<Integer> locations = playerHand.findCardsOrderingInCollection(List.of(c1));
    Assertions.assertEquals(1, locations.size());
    Assertions.assertEquals(1, locations.get(0));
  }

}
