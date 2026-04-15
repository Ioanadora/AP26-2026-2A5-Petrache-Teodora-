package com.lab5.tema5.model;

import java.io.Serializable;
import java.util.Map;

public class Resource implements Serializable {
    private String id;
    private String nameResource;
    private String locationResource;
    private Map<String, String> properties;

    public Resource() {}

    public Resource(String id, String nameResource, String locationResource, Map<String, String> properties) {
        this.id = id;
        this.nameResource = nameResource;
        this.locationResource = locationResource;
        this.properties = properties;
    }

    public String getId() {
        return id;
    }

    public String getNameResource() {
        return nameResource;
    }

    public String getLocationResource() {
        return locationResource;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNameResource(String nameResource) {
        this.nameResource = nameResource;
    }

    public void setLocationResource(String locationResource) {
        this.locationResource = locationResource;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Resource{" + "id='" + id + '\'' + ", nameResource='" + nameResource + '\'' + ", locationResource='" + locationResource + '\'' + ", properties=" + properties + '}';
    }
}