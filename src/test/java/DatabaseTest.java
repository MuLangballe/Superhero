import domain_model.Database;
import domain_model.Superhero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    Database db;
    Scanner keyboard;



    File testfile = new File("TestSuperhero.csv");

    public ArrayList<Superhero> loadSuperheroTest(){
        ArrayList<Superhero> testSuperheroes = new ArrayList<>();
        Scanner keyboard;

            try {
                keyboard = new Scanner(testfile, StandardCharsets.ISO_8859_1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Superhero superhero;
            while (keyboard.hasNext()){
                String line = keyboard.nextLine();
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

                testSuperheroes.add(superhero);
            }
            keyboard.close();
            return testSuperheroes;


    }

    public void saveListOfTestSuperheroes(ArrayList<Superhero> testsuperheroes){
        try(PrintStream output = new PrintStream(testfile)){
            for (Superhero superhero : testsuperheroes) {
                output.println(superhero.getName() + ";"
                        + superhero.getRealName() + ";"
                        + superhero.getSuperPower() + ";"
                        + superhero.getYearCreated() + ";"
                        + superhero.isHuman() + ";"
                        + superhero.getStrength()
                );
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }





    @BeforeEach
    void setUp(){
        db = new Database();
        ArrayList<Superhero> testSuperheroes = new ArrayList<>();
        keyboard = new Scanner(System.in);
        Superhero s1 = new Superhero("Spin", "Miles", "Arachnosting", 2020, true, 1770);
        Superhero s2 = new Superhero("Superman", "Clark", "Styrke", 1940, false, 2330);
        Superhero s3 = new Superhero("Batman", "Bruce", "Uhygge", 1950, true, 3000);
        Superhero s4 = new Superhero("Spiderman", "Peter", "Spin", 1975, true, 1700);
        testSuperheroes.add(s1);
        testSuperheroes.add(s2);
        testSuperheroes.add(s3);
        testSuperheroes.add(s4);
        saveListOfTestSuperheroes(testSuperheroes);
    }
    @Test
    void createSuperhero() {
        int startSize = loadSuperheroTest().size();

        db.createSuperhero("Superman", "Clark", "Styrke", 1940, false, 2330);
        int expectedDBSize = startSize + 1;
        int actualSize = db.getSize();

        assertEquals(expectedDBSize, actualSize);
    }

    @Test
    void findSuperhero() {
        ArrayList<Superhero> result = db.findSuperhero("Spin");
        int expectedSize = 1;
        int actualSize = result.size();
        assertEquals(expectedSize, actualSize);
    }

//    @Test
//    void editSuperhero() {
//        // arrange
//        ArrayList<Superhero> result = db.findSuperhero("Span");
//        int expectedSize = 1;
//        int actualSize = result.size();
//        // act
//        db.editSuperhero("Spin", keyboard);
//        keyboard.nextLine().equals("Span");
//        keyboard.nextLine().equals("Miles");
//        keyboard.nextLine().equals(2020);
//        keyboard.equals(true);
//        keyboard.nextLine().equals(1770);
//        // assert
//        assertEquals(expectedSize, actualSize);
//    }

    @Test
    void deleteSuperhero1(){
        db.deleteSuperhero("Spin");
        ArrayList<Superhero> result = db.findSuperhero("Spin");
        assertTrue(result.isEmpty());
        //forudsætter at findSuperhero virker
    }

    @Test
    void seeAllHeroes() {
        int expectedSize = 4;
        ArrayList<Superhero> actualSize = db.seeAllHeroes();
        assertEquals(expectedSize, actualSize.size());
    }
}

