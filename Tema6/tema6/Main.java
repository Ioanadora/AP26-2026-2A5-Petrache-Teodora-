package com.lab6.tema6;

import com.lab6.tema6.dao.GenreDAO;
import com.lab6.tema6.model.Genre;
import com.lab6.tema6.service.ReportService;

public class Main {

    public static void main(String[] args) {


        GenreDAO genreDAO = new GenreDAO();

        Genre genre = new Genre();
        genre.setNameGenre("Action");
        genreDAO.create(genre);

        System.out.println("Genre inserted!");


        ReportService reportService = new ReportService();
        reportService.generateReport();

        System.out.println("HTML report generated!");
    }
}