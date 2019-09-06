package com.mtgcompany.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class ElasticsearchErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String message, Response response) {
        if (HttpStatus.NOT_FOUND.value() == response.status()) {
            return new Exception(message);
        } else {
            return new Exception("Other Exception thrown by Elasticsearch");
        }
    }

}
