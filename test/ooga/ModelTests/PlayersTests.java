package ooga.ModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import ooga.model.cards.CardColor;
import ooga.model.cards.cardcomponents.Card;
import ooga.model.cards.cardcomponents.CardInfo;
import ooga.model.cards.cardcomponents.UnoCard;
import ooga.model.cards.cardcomponents.UnoCardInfo;
import ooga.model.exceptions.CardNotFoundException;
import ooga.model.players.GamePlayer;
import ooga.model.players.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayersTests {

  private Player myPlayer;
  private int defaultID = 0;

  @BeforeEach
  void setup() {
    myPlayer = new GamePlayer(defaultID);
  }

  @Test
  void testPlayCardDontHave() {
    //try to play a card the player doesn't have, throws an exception
    Card card = makeCard("Number", 8, CardColor.YELLOW);
    Assertions.assertThrows(CardNotFoundException.class,() -> myPlayer.playCard(card));
  }

  @Test
  void testAddCardToHand() {
    int beforeSize = myPlayer.getAllCards().size();
    Card card = makeCard("Number", 8, CardColor.YELLOW);
    myPlayer.addToHand(card);
    int afterSize = myPlayer.getAllCards().size();
    assertEquals(1, afterSize - beforeSize);
    assertTrue(myPlayer.getAllCards().contains(card));
  }

  @Test
  void testPlayCardYouDoHave() {
    Card card = makeCard("Number", 8, CardColor.YELLOW);
    myPlayer.addToHand(card);
    int beforeSize = myPlayer.getAllCards().size();
    Assertions.assertDoesNotThrow(() -> myPlayer.playCard(card));
    int afterSize = myPlayer.getAllCards().size();
    assertEquals(-1, afterSize - beforeSize);
    assertFalse(myPlayer.getAllCards().contains(card));
  }

  @Test
  void testAddAllToHand() {
    Card card1 = makeCard("Number", 8, CardColor.YELLOW);
    Card card2 = makeCard("Number", 5, CardColor.GREEN);

    ArrayList<Card> cardsToAdd = new ArrayList<>();
    cardsToAdd.add(card1);
    cardsToAdd.add(card2);
    testAddCardToHand();
    int beforeSize = myPlayer.getAllCards().size();
    myPlayer.getPlayerHand().addAll(cardsToAdd);
    int afterSize = myPlayer.getAllCards().size();
    assertTrue(afterSize - beforeSize == 2);
    assertTrue(myPlayer.getAllCards().contains(card2));
  }

  @Test
  void testFindValidCardsToPlayBasicTopCard() {
    Card topCard = makeCard("Number", 5, CardColor.BLUE);

    Card c1 = makeCard("Number", 4, CardColor.BLUE);
    Card c2 = makeCard("Number", 6, CardColor.RED);
    List<Card> list = Arrays.asList(new Card[]{c1, c2});
    myPlayer.addAllToHand(list);

    Collection<Card> myValidCards = myPlayer.findValidCardsToPlay(topCard);
    assertEquals(1, myValidCards.size());
  }

  @Test
  void testFindValidCardsToPlaySkipCard() {
    myPlayer = new GamePlayer(0);
    Card topCard = makeCard("Skip", 1, CardColor.BLUE);

    Card c1 = makeCard("Number", 4, CardColor.BLUE);
    Card c2 = makeCard("Number", 1, CardColor.RED);
    Card c3 = makeCard("Reverse", 1, CardColor.BLUE);
    List<Card> list = Arrays.asList(new Card[]{c1, c2, c3});
    myPlayer.addAllToHand(list);

    Collection<Card> myValidCards = myPlayer.findValidCardsToPlay(topCard);
    assertEquals(2, myValidCards.size());
  }

  @Test
  void testCardsWithSameInfo() {
    myPlayer = new GamePlayer(0);
    Card c2 = makeCard("Skip", 1, CardColor.BLUE);
    Card c1 = makeCard("Number", 4, CardColor.BLUE);
    Card c3 = makeCard("Reverse", 1, CardColor.BLUE);
    List<Card> list = Arrays.asList(c1, c2, c3);
    myPlayer.addAllToHand(list);
    List<Card> cards = myPlayer.findCardsWithSameCardInfo(new CardInfo[]{new UnoCardInfo("number", 4)}).stream().toList();
    Assertions.assertEquals(c1, cards.get(0));
  }

  @Test
  void testCardsWithSameInfoComplexCard() {
    myPlayer = new GamePlayer(0);
    UnoCardInfo info = new UnoCardInfo("skip", 1);
    UnoCardInfo info1 = new UnoCardInfo("draw", 2);
    Card c2 = new UnoCard(CardColor.BLUE, new UnoCardInfo[]{info, info1});
    Card c1 = makeCard("Number", 4, CardColor.BLUE);
    Card c3 = makeCard("Reverse", 1, CardColor.BLUE);
    List<Card> list = Arrays.asList(c1, c2, c3);
    myPlayer.addAllToHand(list);
    List<Card> cards = myPlayer.findCardsWithSameCardInfo(new CardInfo[]{new UnoCardInfo("skip", 1)}).stream().toList();
    Assertions.assertEquals(0, cards.size());
  }

  private Card makeCard(String type, int param, CardColor color) {
    UnoCardInfo info = new UnoCardInfo(type, param);
    return new UnoCard(color, new UnoCardInfo[]{info});
  }
}
