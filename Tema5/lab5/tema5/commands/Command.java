package com.lab5.tema5.commands;

import com.lab5.tema5.exception.CatalogException;

public interface Command {
    void execute()throws CatalogException;
}
