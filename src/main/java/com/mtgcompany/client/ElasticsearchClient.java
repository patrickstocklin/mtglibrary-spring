package com.mtgcompany.client;

import com.mtgcompany.domain.ScryfallResponse;
import feign.Headers;
import feign.RequestLine;

public interface ElasticsearchClient {

    @RequestLine("POST /pat/_doc/")
    @Headers("Content-Type: application/json")
    ScryfallResponse addCard(ScryfallResponse response);
}
