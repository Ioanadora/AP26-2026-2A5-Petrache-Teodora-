package com.lab5.tema5.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class Catalog implements Serializable {

    private Map<String, Resource> catalogResources = new HashMap<>();

    public void add(Resource resource) {
        catalogResources.put(resource.getId(), resource);
    }

    public Resource findById(String id) {
        return catalogResources.get(id);
    }

    public Map<String, Resource> getCatalogResources() {
        return catalogResources;
    }

    public Collection<Resource> getAllResources() {
        return catalogResources.values();
    }
}