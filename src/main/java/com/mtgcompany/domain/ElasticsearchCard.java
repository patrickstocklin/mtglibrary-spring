package com.mtgcompany.domain;

public class ElasticsearchCard {

    private String object;
    private String id;
    private String name;
    private Price prices;
    private ImageUri image_uris;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return this.prices;
    }

    public void setPrice(Price price) {
        this.prices = price;
    }

    public ImageUri getImage_uris() {
        return image_uris;
    }

    public void setImage_uris(ImageUri image_uris) {
        this.image_uris = image_uris;
    }

}
