package com.lab5.tema5.commands;

import com.lab5.tema5.model.Catalog;
import com.lab5.tema5.model.Resource;

public class AddCommand implements Command {

    private Catalog catalog;
    private Resource resource;

    public AddCommand(Catalog catalog, Resource resource) {
        this.catalog = catalog;
        this.resource = resource;
    }

    @Override
    public void execute() {
        catalog.add(resource);
    }
}