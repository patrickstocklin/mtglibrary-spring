package com.mtgcompany.client;

import com.mtgcompany.domain.ScryfallResponse;
import feign.Param;
import feign.RequestLine;

public interface ScryfallClient {

    @RequestLine("GET /cards/named?fuzzy={cardName}")
    ScryfallResponse getPrice(@Param("cardName") String cardName);

}
