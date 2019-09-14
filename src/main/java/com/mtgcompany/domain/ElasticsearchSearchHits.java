package com.mtgcompany.domain;

import java.util.List;

public class ElasticsearchSearchHits {

    private List<ElasticsearchHit> hits;

    public ElasticsearchSearchHits() {

    }

    public List<ElasticsearchHit> getHits() {
        return hits;
    }

    public void setHits(List<ElasticsearchHit> hits) {
        this.hits = hits;
    }
}
