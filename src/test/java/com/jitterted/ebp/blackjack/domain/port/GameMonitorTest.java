package com.jitterted.ebp.blackjack.domain.port;

import com.jitterted.ebp.blackjack.domain.Deck;
import com.jitterted.ebp.blackjack.domain.Game;
import com.jitterted.ebp.blackjack.domain.Rank;
import com.jitterted.ebp.blackjack.domain.StubDeck;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class GameMonitorTest {

  @Test
  public void playerStandsCompletesGameSendsToMonitor() throws Exception {
    // creates the spy based on the interface
    GameMonitor gameMonitorSpy = spy(GameMonitor.class);
    Game game = new Game(new Deck(), gameMonitorSpy);
    game.initialDeal();

    game.playerStands();

    // verify that the roundCompleted method was called with any instance of a Game class
    verify(gameMonitorSpy).roundCompleted(any(Game.class));
  }

  @Test
  public void playerHitsGoesBustSendsGameToMonitor() throws Exception {
    GameMonitor gameMonitorSpy = spy(GameMonitor.class);
    Deck deck = StubDeck.playerHitsAndBustsDeck();
    Game game = new Game(deck, gameMonitorSpy);
    game.initialDeal();

    game.playerHits();

    verify(gameMonitorSpy).roundCompleted(any(Game.class));
  }

  @Test
  public void playerHitsDoesNotBustResultsInNoGameSentToMonitor() throws Exception {
    GameMonitor gameMonitorSpy = spy(GameMonitor.class);
    Deck deck =  new StubDeck(Rank.TEN, Rank.TWO,
                              Rank.EIGHT, Rank.FIVE,
                              Rank.TWO);
    Game game = new Game(deck, gameMonitorSpy);
    game.initialDeal();

    game.playerHits();

    verify(gameMonitorSpy, never()).roundCompleted(any());
    // Could use the following, but is not recommended as per docs, "never() is more explicit and communicates the intent well."
    // verifyNoInteractions(gameMonitorSpy);
  }

  @Test
  public void playerDealtBlackjackSendGameToMonitor() throws Exception {
    GameMonitor gameMonitorSpy = spy(GameMonitor.class);
    Game game = new Game(StubDeck.playerDealtBlackjack(),
                         gameMonitorSpy);

    game.initialDeal();

    verify(gameMonitorSpy).roundCompleted(any(Game.class));
  }

}