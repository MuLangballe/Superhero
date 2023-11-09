import datasource.FileHandler;
import domain_model.Database;
import domain_model.Superhero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    int tal;
    FileHandler fh = new FileHandler();
    Database db;
    File testfile;
    ArrayList<Superhero> testSuperheroes = new ArrayList<>();

    @BeforeEach
    void setUp() {
        testfile = new File("src/test/java/TestSuperhero.csv");

        Superhero s1 = new Superhero("Spin", "Miles", "Arachnosting", 2020, true, 1770);
        Superhero s2 = new Superhero("Superman", "Clark", "Styrke", 1940, false, 2330);
        Superhero s3 = new Superhero("Batman", "Bruce", "Uhygge", 1950, true, 3000);
        Superhero s4 = new Superhero("Spiderman", "Peter", "Spin", 1975, true, 1700);

        testSuperheroes.add(s1);
        testSuperheroes.add(s2);
        testSuperheroes.add(s3);
        testSuperheroes.add(s4);

        try (PrintStream output = new PrintStream(testfile)) {
            for (Superhero superhero : testSuperheroes) {
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
        db = new Database(testfile);

    }
    
    @Test 
    void loadAllSuperheroes(){
        int expectedSize = 4;
        int actualSize = fh.loadAllSuperheroes(testfile).size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void createSuperhero() {
        int startSize = db.getSize();
        db.createSuperhero("Dronning Margrethe", "Margrethe", "Kæderygning", 1901, false, 2330);
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

    @Test
    void deleteSuperhero1() {
        db.deleteSuperhero("Spin");
        int expectedSize = 3;
        int actualSize = db.getSize();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void viewSuperheroes() {
        int expectedSize = 4;
        ArrayList<Superhero> actualSize = db.seeAllHeroes();
        assertEquals(expectedSize, actualSize.size());
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


    }



