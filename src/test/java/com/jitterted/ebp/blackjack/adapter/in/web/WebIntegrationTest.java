package com.jitterted.ebp.blackjack.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class WebIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void getOfHomePageIsStatus200Ok() throws Exception {
    mockMvc.perform(get("/index.html"))
           .andExpect(status().isOk());
  }

  @Test
  public void postToStartEndpointIsRedirection() throws Exception {
    mockMvc.perform(post("/start-game"))
           .andExpect(status().is3xxRedirection());
  }

  @Test
  public void getOfGameEndpointIsStatus200Ok() throws Exception {
    mockMvc.perform(get("/game"))
           .andExpect(status().isOk());
  }

  @Test
  public void postToHitCommandIsRedirection() throws Exception {
    mockMvc.perform(post("/hit"))
           .andExpect(status().is3xxRedirection());
  }

  @Test
  public void getOfDoneEndpointIs200Ok() throws Exception {
    mockMvc.perform(get("/done"))
           .andExpect(status().isOk());
  }

  @Test
  public void postToStandEndpointIsRedirection() throws Exception {
    mockMvc.perform(post("/stand"))
           .andExpect(status().is3xxRedirection());
  }
}
