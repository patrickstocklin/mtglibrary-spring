package com.mtgcompany.client;

import com.google.gson.internal.LinkedTreeMap;
import com.mtgcompany.domain.ElasticsearchSearchResponse;
import com.mtgcompany.domain.ScryfallResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface ElasticsearchClient {

    @RequestLine("POST /pat/_doc/")
    @Headers("Content-Type: application/json")
    //TODO: Change this return type to ES Response Object
    ScryfallResponse addCard(ScryfallResponse response);

    @RequestLine("GET /{index}/_search?pretty=true&size=1000")
    @Headers("Content-Type: application/json")
    //TODO: Cast this response object to an ES Response Object
    ElasticsearchSearchResponse getCardsFromIndex(@Param("index") String index);

    @RequestLine("GET /{index}")
    @Headers("Content-Type: application/json")
    //TODO: Cast this response object to an ES Response Object
    LinkedTreeMap getIndexName(@Param("index") String index);

    @RequestLine("GET /_aliases")
    @Headers("Content-Type: application/json")
    LinkedTreeMap<String, LinkedTreeMap> getIndices();

}
