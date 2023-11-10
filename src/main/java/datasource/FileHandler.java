package datasource;

import domain_model.Superhero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public ArrayList<Superhero> loadAllSuperheroes(File CSVPath) {
        ArrayList<Superhero> superheroes = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(CSVPath, StandardCharsets.ISO_8859_1);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        Superhero superhero;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] parametre = line.split(";");
            String name = parametre[0];
            String realName = parametre[1];
            String superPowers = parametre[2];
            String yearCreated = parametre[3];
            String isHuman = parametre[4];
            String strength = parametre[5];
            superhero = new Superhero(
                    name,
                    realName,
                    superPowers,
                    Integer.parseInt(yearCreated),
                    Boolean.parseBoolean(isHuman),
                    Integer.parseInt(strength)
            );

            superheroes.add(superhero);
        }

        return superheroes;
    }

    public void saveListSuperHeroes(ArrayList<Superhero> superheroes, File CSVPath) {

        try (PrintStream output = new PrintStream(CSVPath)) {
            for (Superhero superhero : superheroes) {
                output.println(superhero.getName() + ";"
                        + superhero.getRealName() + ";"
                        + superhero.getSuperPower() + ";"
                        + superhero.getYearCreated() + ";"
                        + superhero.getIsHuman() + ";"
                        + superhero.getStrength()
                );
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
