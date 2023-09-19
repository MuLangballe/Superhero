import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    Database db;

    @BeforeEach
    void setUp(){
        db = new Database();
       // db.createSuperhero("Superman", "Clark", "Styrke", 1940, false, 2330);
        // db.createSuperhero("Batman", "Bruce", "Uhygge", 1950, true, 3000);
       // db.createSuperhero("Spiderman", "Peter", "Spin", 1975, true, 1700);
    }
    @Test
    void createSuperhero() {
        int startSize = db.getSize();
        db.createSuperhero("Spin", "Miles", "Arachnosting", 2020, true, 1770);
        int expectedDBSize = startSize + 1;
        int actualSize = db.getSize();

        assertEquals(expectedDBSize, actualSize);

    }

    @Test
    void findSuperhero() {
        db.createSuperhero("Spin", "Miles", "Arachnosting", 2020, true, 1770);
        ArrayList<Superhero> result = db.findSuperhero("Spin");
        int expectedSize = 1;
        int actualSize = result.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void editSuperhero() {

    }

    @Test
    void seeAllHeroes() {
    }
}