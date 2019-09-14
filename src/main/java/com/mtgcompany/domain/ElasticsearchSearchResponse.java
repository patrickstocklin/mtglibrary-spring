package com.mtgcompany.domain;

public class ElasticsearchSearchResponse {

    private String took;
    private String timed_out;
    private ElasticsearchSearchHits hits;

    public ElasticsearchSearchResponse() {

    }

    public String getTook() {
        return took;
    }

    public void setTook(String took) {
        this.took = took;
    }

    public String getTimed_out() {
        return timed_out;
    }

    public void setTimed_out(String timed_out) {
        this.timed_out = timed_out;
    }

    public ElasticsearchSearchHits getHits() {
        return hits;
    }

    public void setHits(ElasticsearchSearchHits hits) {
        this.hits = hits;
    }

}
