package com.lab6.tema6.service;

import com.lab6.tema6.dao.MovieDAO;
import com.lab6.tema6.model.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReportService {

    private final MovieDAO movieDAO = new MovieDAO();

    public void generateReport() {
        try {

            List<Movie> movies = movieDAO.findAllFromView();


            String template;
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("template.html")) {

                if (inputStream == null) {
                    throw new RuntimeException("Template not");
                }

                template = new String(inputStream.readAllBytes());
            }


            StringBuilder rows = new StringBuilder();

            for (Movie movie : movies) {
                rows.append("<tr>")
                        .append("<td>").append(movie.getId()).append("</td>")
                        .append("<td>").append(movie.getTitle()).append("</td>")
                        .append("<td>").append(movie.getDuration()).append("</td>")
                        .append("<td>").append(movie.getScore()).append("</td>")
                        .append("</tr>");
            }


            String html = template.replace("{{rows}}", rows.toString());


            Files.write(Paths.get("report.html"), html.getBytes());

            System.out.println("Report generated: report.html");

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}