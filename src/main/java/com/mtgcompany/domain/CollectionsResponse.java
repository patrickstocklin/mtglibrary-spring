package com.mtgcompany.domain;

import java.util.List;

public class CollectionsResponse {

    private List<CollectionIndex> collections;

    public List<CollectionIndex> getCollections() {
        return collections;
    }

    public void setCollections( List<CollectionIndex> collections) {
        this.collections = collections;
    }
}
