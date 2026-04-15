package com.lab5.tema5.commands;

import com.lab5.tema5.exception.CatalogException;
import com.lab5.tema5.model.Catalog;
import freemarker.template.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command {

    private Catalog catalog;

    public ReportCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() throws CatalogException {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setClassForTemplateLoading(getClass(), "/templates");

            Template template = cfg.getTemplate("report.ftl");

            Map<String, Object> data = new HashMap<>();
            data.put("resources", catalog.getCatalogResources());

            File outFile = new File("report.html");
            Writer out = new FileWriter(outFile);

            template.process(data, out);
            out.close();

            java.awt.Desktop.getDesktop().open(outFile);

        } catch (Exception e) {
            throw new CatalogException("Error generating report", e);
        }
    }
}