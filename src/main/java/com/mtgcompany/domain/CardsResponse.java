package com.mtgcompany.domain;

import java.util.List;

public class CardsResponse {

    private List<Card> cards;

    public CardsResponse() {

    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

}
