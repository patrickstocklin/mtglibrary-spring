package com.mtgcompany.controller;

import com.mtgcompany.client.ScryfallClient;
import com.mtgcompany.domain.ScryfallResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") //TODO change me!
public class ScryfallController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScryfallController.class);

    @Autowired
    private ScryfallClient scryfallClient;

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Magic Time");
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/pong")
    public String pong() {
        LOGGER.info("Received Signal from Webapp");
        return "pong";
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/card/{cardName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ScryfallResponse> getCard(@PathVariable("cardName") String cardName) {
        LOGGER.info("Getting Card Name: {}", cardName);
        ScryfallResponse response = scryfallClient.getPrice(cardName);
        LOGGER.info("Current Prices: {}", response.getPrice());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
