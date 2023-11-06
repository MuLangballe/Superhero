import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Controller {
    private Database database;
    private FileHandler fh;


    public Controller(){
        database = new Database();
    }

    public void createSuperhero(String name, String realname, String superpower, int yearCreated, boolean isHuman, int strength){
        database.createSuperhero(name, realname, superpower, yearCreated, isHuman, strength);
    }


    public boolean deleteSuperhero(String superheroName){
        return database.deleteSuperhero(superheroName);
    }

    public ArrayList<Superhero> findSuperhero(String superhero){
        return database.findSuperhero(superhero);
    }

    public ArrayList<Superhero> seeAllHeroes(){
        return database.seeAllHeroes();
    }

    public ArrayList<Superhero> loadAllSuperhero(){
        return fh.loadAllSuperheroes();
    }

    public void saveListOfSuperheroes(ArrayList<Superhero> superheroes) {
        fh.saveListSuperHeroes(superheroes);
    }
}
