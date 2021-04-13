package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CardDisplayTest {
  
  @Test
  public void displayTenCard() throws Exception {
    Card card = new Card(Suit.DIAMONDS, Rank.TEN);

    assertThat(ConsoleCard.display(card))
        .isEqualTo("[31m┌─────────┐[1B[11D│10       │[1B[11D│         │[1B[11D│    ♦    │[1B[11D│         │[1B[11D│       10│[1B[11D└─────────┘");
  }

  @Test
  public void displayNonTenCard() throws Exception {
    Card card = new Card(Suit.SPADES, Rank.EIGHT);

    assertThat(ConsoleCard.display(card))
        .isEqualTo("[30m┌─────────┐[1B[11D│8        │[1B[11D│         │[1B[11D│    ♠    │[1B[11D│         │[1B[11D│        8│[1B[11D└─────────┘");
  }

}