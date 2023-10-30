import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    Database db;
    Scanner keyboard;

    @BeforeEach
    void setUp(){
        db = new Database();
        keyboard = new Scanner(System.in);
        db.createSuperhero("Spin", "Miles", "Arachnosting", 2020, true, 1770);
       // db.createSuperhero("Superman", "Clark", "Styrke", 1940, false, 2330);
        // db.createSuperhero("Batman", "Bruce", "Uhygge", 1950, true, 3000);
       // db.createSuperhero("Spiderman", "Peter", "Spin", 1975, true, 1700);
    }
    @Test
    void createSuperhero() {
        int startSize = db.getSize();
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

    @Test
    void editSuperhero() {
    }

    @Test
    void deleteSuperhero(){
        db.deleteHero("Spin", keyboard);
        ArrayList<Superhero> result = db.findSuperhero("Spin");
        assertTrue(result.isEmpty());
        //forudsætter at findSuperhero  virker
    }

    @Test
    void seeAllHeroes() {
        ArrayList<Superhero> result = db.seeAllHeroes();
        int expectedSize = 1;
        int actualSize = result.size();
        assertEquals(expectedSize, actualSize);
    }
}