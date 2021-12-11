package com.example.demo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsvUtilFile {
    private CsvUtilFile(){}

    public static List<Player> getPlayers(){
        File archivo = new File("src/main/resources/data.csv");//URL DEL ARCHIVO EN EL COMPUTADOR
        List<Player> list = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(archivo.getAbsolutePath()))) {
            List<String[]> registers = reader.readAll();
            registers.forEach(strings -> list.add(new Player(
                    Integer.parseInt(strings[0].trim()),
                    strings[1],
                    Integer.parseInt(Optional.of(strings[2].trim()).filter(h -> !h.isBlank()).orElse("0")),
                    strings[3],
                    strings[4],
                    Integer.parseInt(strings[5].trim()),
                    Integer.parseInt(strings[6].trim()),
                    strings[7]
            )));
            return list;

        } catch (IOException | CsvException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
