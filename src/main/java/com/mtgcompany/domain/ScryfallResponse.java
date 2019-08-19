package com.mtgcompany.domain;

public class ScryfallResponse {

    private String object;
    private String id;
    private Price prices;

    public String getObject() {
        return this.object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Price getPrice() {
        return this.prices;
    }

    public void setPrice(Price price) {
        this.prices = price;
    }
}
