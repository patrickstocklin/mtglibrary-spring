package com.mtgcompany.controller;

import com.google.gson.internal.LinkedTreeMap;
import com.mtgcompany.client.ElasticsearchClient;
import com.mtgcompany.client.ScryfallClient;
import com.mtgcompany.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") //TODO change me!
//TODO break this up and really refactor these pojos i mean seriously it is getting out of control
public class ScryfallController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScryfallController.class);

    @Autowired
    private ScryfallClient scryfallClient;

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Magic Time");
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

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/card/{cardName}/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ScryfallResponse> addCard(@PathVariable("cardName") String cardName) {
        LOGGER.info("Adding Card Name: {}", cardName);
        ScryfallResponse response = scryfallClient.getPrice(cardName);
        elasticsearchClient.addCard(response);
        LOGGER.info("Added {}", cardName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/collection/{collectionName}/exist")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getCollectionName(@PathVariable("collectionName") String collectionName) {
        LOGGER.info("Getting Elasticsearch Name: {}", collectionName);

        try {
            LinkedTreeMap mapOfIndices = elasticsearchClient.getIndexName(collectionName);
            LOGGER.info("Retrieved Collections Map {}", mapOfIndices.toString());

            if (mapOfIndices.containsKey(collectionName)) {
                LOGGER.info("CollectionIndex {} exists in Elasticsearch", mapOfIndices.get(collectionName));
                return new ResponseEntity<>(collectionName, HttpStatus.OK);
            } else {
                LOGGER.info("CollectionIndex does not exist under name {}", collectionName);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        //TO DO: Specify different Exceptions w/ Decoder
        } catch (Exception e) {
            LOGGER.info("CollectionIndex does not exist under name {}", collectionName);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/collection/{collectionName}/card/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CardsResponse> getCollection(@PathVariable("collectionName") String collectionName) {
        LOGGER.info("Getting Elasticsearch Name: {}", collectionName);

        try {
            ElasticsearchSearchResponse cards = elasticsearchClient.getCardsFromIndex(collectionName);
            LOGGER.info("Retrieved Collections Map {}", cards.toString());

            CardsResponse response = new CardsResponse();
            List<Card> cardList = new ArrayList<>();
            cards.getHits().getHits().forEach(hit -> cardList.add(hit.get_source()));
            response.setCards(cardList);


            LOGGER.info(response.toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
            //TO DO: Specify different Exceptions w/ Decoder
        } catch (Exception e) {
            LOGGER.info("CollectionIndex does not exist under name {}", collectionName);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/collection/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CollectionsResponse> getCollections() {
        LOGGER.info("Getting a List of all Collections");
        LinkedTreeMap<String, LinkedTreeMap> mapOfIndices = elasticsearchClient.getIndices();
        LOGGER.info("Retrieved Collections Map {}", mapOfIndices.toString());

        List<CollectionIndex> collections = new ArrayList<>();
        mapOfIndices.keySet().forEach(index -> collections.add(new CollectionIndex(index)));

        LOGGER.info(collections.toString());

        CollectionsResponse response = new CollectionsResponse();
        response.setCollections(collections);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
