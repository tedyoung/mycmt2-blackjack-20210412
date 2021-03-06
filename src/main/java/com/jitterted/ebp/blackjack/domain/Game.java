package com.jitterted.ebp.blackjack.domain;

import com.jitterted.ebp.blackjack.domain.port.GameMonitor;

public class Game {

  private final Deck deck;

  private final Hand dealerHand = new Hand();
  private final Hand playerHand = new Hand();
  private boolean playerDone;

  private final GameMonitor gameMonitor;

  public Game() {
    this(new Deck());
  }

  public Game(Deck deck) {
    this(deck, game -> {});
  }

  public Game(Deck deck, GameMonitor gameMonitor) {
    this.deck = deck;
    this.gameMonitor = gameMonitor;
  }

  public void initialDeal() {
    // if initial deal already happened, then throw exception
    dealRoundOfCards();
    dealRoundOfCards();
    updatePlayerDoneTo(playerHand.isBlackjack());
  }

  public void playerHits() {
    playerHand.drawFrom(deck);
    updatePlayerDoneTo(playerHand.isBusted());
  }

  public void playerStands() {
    dealerTurn();
    updatePlayerDoneTo(true);
  }

  public void updatePlayerDoneTo(boolean isDone) {
    playerDone = isDone;
    if (playerDone) {
      gameMonitor.roundCompleted(this);
    }
  }

  private void dealRoundOfCards() {
    // why: players first because this is the rule
    playerHand.drawFrom(deck);
    dealerHand.drawFrom(deck);
  }

  public GameOutcome determineOutcome() {
    // throw IllegalStateException if game is not done
    if (playerHand.isBlackjack()) {
      return GameOutcome.PLAYER_WINS_BLACKJACK;
    }
    if (playerHand.isBusted()) {
      return GameOutcome.PLAYER_BUSTS;
    }
    if (dealerHand.isBusted()) {
      return GameOutcome.DEALER_BUSTS;
    }
    if (playerHand.beats(dealerHand)) {
      return GameOutcome.PLAYER_BEATS_DEALER;
    }
    if (playerHand.pushes(dealerHand)) {
      return GameOutcome.PLAYER_PUSHES;
    }
    return GameOutcome.PLAYER_LOSES;
  }

  private void dealerTurn() {
    // Dealer makes its choice automatically based on a simple heuristic (<=16 must hit, =>17 must stand)
    if (!playerHand.isBusted()) {
      while (dealerHand.dealerMustDrawCard()) {
        dealerHand.drawFrom(deck);
      }
    }
  }

  // Expose only as some read-/view-only version (But still a DOMAIN OBJECT)
  public Hand playerHand() {
    return playerHand;
  }

  public Hand dealerHand() {
    return dealerHand;
  }


  public boolean isPlayerDone() {
    return playerDone;
  }
}
