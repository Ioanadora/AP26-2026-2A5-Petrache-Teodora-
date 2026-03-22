package com.compulosry5;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


public class ResourceRepository {
    Map<String,Resource>resources=new HashMap<>();
    public void addResource(Resource resource) throws DuplicateResourceException {
        if (resources.containsKey(resource.getId())) {
            throw new DuplicateResourceException("Resource with ID " + resource.getId() + " already exists.");
        }
        resources.put(resource.getId(), resource);
    }
    public Resource findById(String id) throws ResourceNotFoundException {
        Resource res = resources.get(id);
        if (res == null) throw new ResourceNotFoundException("Resource with ID " + id + " not found.");
        return res;
    }
    public void openResource(String id) throws Exception {
        Resource resource = findById(id);
        Desktop desktop = Desktop.getDesktop();
        String location = resource.getLocation();
        if (location.startsWith("http")) {
            desktop.browse(new URI(location));
        } else {
            desktop.open(new File(location));
        }
    }

    public void printAll() {
        resources.values().forEach(System.out::println);
    }
}
