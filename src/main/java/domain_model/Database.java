package domain_model;

import datasource.FileHandler;
import java.io.File;
import java.util.ArrayList;

public class Database {

    File CSVPath;
    FileHandler fh;
    ArrayList<Superhero> superheroes;

    public Database(File CSVPath){
        this.CSVPath = CSVPath;
        fh = new FileHandler();
        superheroes = fh.loadAllSuperheroes(CSVPath);
    }

    public void createSuperhero(String name, String realName, String superPower, int yearCreated, boolean isHuman, int strength) {
        superheroes.add(new Superhero(name, realName, superPower, yearCreated, isHuman, strength));
        fh.saveListSuperHeroes(superheroes, CSVPath);
    }

    public ArrayList<Superhero> findSuperhero(String superhero) {
        ArrayList<Superhero> results = new ArrayList<>();
        for (Superhero superhero1 : superheroes) {
            if (superhero1.getName().toLowerCase().contains(superhero.toLowerCase())) {
                results.add(superhero1);
            }
        }
        return results;
    }

    public ArrayList<Superhero> seeAllHeroes() {
        return superheroes;
    }

    public boolean deleteSuperhero(String superheroName){
        for (Superhero superhero : superheroes) {
            if (superhero.getName().equalsIgnoreCase(superheroName)) {
                superheroes.remove(superhero);
                fh.saveListSuperHeroes(superheroes, CSVPath);
                return true;
            }
        }
        return false;
    }

        public int getSize () {
            return superheroes.size();
        }
    }
