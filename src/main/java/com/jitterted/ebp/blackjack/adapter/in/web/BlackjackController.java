package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlackjackController {

  private final Game game;

  @Autowired
  public BlackjackController(Game game) {
    this.game = game;
  }

  @PostMapping("/start-game")
  public String startGame() {
    // query Game: can we do an initial deal?
    game.initialDeal();
    return redirect(game);
  }

  @GetMapping("/game")
  public String gameView(Model model) {
    model.addAttribute("gameView", GameView.of(game));
    return "blackjack";
  }

  @PostMapping("/hit")
  public String hitCommand() {
    game.playerHits();
    return redirect(game);
  }

  private String redirect(Game game) {
    if (game.isPlayerDone()) {
      return "redirect:/done";
    }
    return "redirect:/game";
  }

  @GetMapping("/done")
  public String viewDone(Model model) {
    GameView gameView = GameView.of(game);
    model.addAttribute("gameView", gameView);
    model.addAttribute("outcome", game.determineOutcome().toString());
    return "done";
  }

  @PostMapping("/stand")
  public String standCommand() {
    game.playerStands();
    return redirect(game);
  }
}
