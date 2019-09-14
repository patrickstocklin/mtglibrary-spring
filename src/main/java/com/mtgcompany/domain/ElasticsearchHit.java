package com.mtgcompany.domain;

public class ElasticsearchHit {

    private Card _source;

    public ElasticsearchHit() {

    }

    public Card get_source() {
        return _source;
    }

    public void set_source(Card _source) {
        this._source = _source;
    }

}
