package com.jitterted.ebp.blackjack.adapter.in.console;

import com.jitterted.ebp.blackjack.domain.GameOutcome;

class ConsoleGameOutcome {

  // transform/translate Enum to String for Console output
  static String of(GameOutcome gameOutcome) {
    if (gameOutcome == GameOutcome.PLAYER_BUSTS) {
      return "You Busted, so you lose.  💸";
    } else if (gameOutcome == GameOutcome.DEALER_BUSTS) {
      return "Dealer went BUST, Player wins! Yay for you!! 💵";
    } else if (gameOutcome == GameOutcome.PLAYER_BEATS_DEALER) {
      return "You beat the Dealer! 💵";
    } else if (gameOutcome == GameOutcome.PLAYER_PUSHES) {
      return "Push: The house wins, you Lose. 💸";
    } else if (gameOutcome == GameOutcome.PLAYER_LOSES) {
      return "You lost to the Dealer. 💸";
    } else if (gameOutcome == GameOutcome.PLAYER_WINS_BLACKJACK) {
      return "You win with Blackjack!";
    } else {
      throw new UnsupportedOperationException();
    }
  }

}
