package com.lab5.tema5.commands;

import com.lab5.tema5.model.Catalog;

public class ListCommand implements Command {

    private Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() {
        catalog.getCatalogResources().values().forEach(System.out::println);
    }
}