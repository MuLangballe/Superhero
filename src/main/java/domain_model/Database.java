package domain_model;

import datasource.FileHandler;
import java.io.File;
import java.util.ArrayList;

public class Database {

    private File CSVPath;
    private FileHandler fh;
    private ArrayList<Superhero> superheroes;

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

    public ArrayList<Superhero> viewSuperheroes() {
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

    public void printSuperheroes(){
        for (Superhero superhero : superheroes){
            System.out.println(superhero);
        }
    }

        public int getSize () {
            return superheroes.size();
        }
    }
