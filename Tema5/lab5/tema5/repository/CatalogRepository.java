package com.lab5.tema5.repository;

import com.lab5.tema5.model.Catalog;

import javax.xml.catalog.CatalogException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.*;
public class CatalogRepository {
    public void save(Catalog catalog,String path)throws CatalogException{
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(catalog);
        }catch(IOException e){
            throw new CatalogException("Error saving catalog", e);
        }
    }

    public Catalog load(String path) throws CatalogException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            return (Catalog) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new CatalogException("Error loading catalog", e);
        }
    }
}
