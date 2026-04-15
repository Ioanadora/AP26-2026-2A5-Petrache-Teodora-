package com.lab5.tema5.commands;

import com.lab5.tema5.exception.CatalogException;
import com.lab5.tema5.model.Catalog;
import com.lab5.tema5.model.Resource;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;

public class ViewCommand implements Command {

    private Catalog catalog;
    private String id;

    public ViewCommand(Catalog catalog, String id) {
        this.catalog = catalog;
        this.id = id;
    }

    @Override
    public void execute() throws CatalogException {
        Resource resource = catalog.findById(id);

        if (resource == null) {
            throw new CatalogException("Resource not found: " + id);
        }

        try {
            Desktop desktop = Desktop.getDesktop();
            String location = resource.getLocationResource();

            if (location.startsWith("http")) {
                desktop.browse(new URI(location));
            } else {
                desktop.open(new File(location));
            }

        } catch (Exception e) {
            throw new CatalogException("Cannot open resource", e);
        }
    }
}