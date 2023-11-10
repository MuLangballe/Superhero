package domain_model;

import datasource.FileHandler;

import java.io.File;
import java.util.ArrayList;

public class Controller {
    private final Database database;
    private final FileHandler fh;
    File CSVPath;

    public Controller(){
        CSVPath = new File("SuperheroList.csv");
        database = new Database(CSVPath);
        fh = new FileHandler();
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

    public ArrayList<Superhero> viewSuperheroes(){
        return database.viewSuperheroes();
    }

    public void printSuperheroes(){
        database.printSuperheroes();
    }


    public void saveListOfSuperheroes(ArrayList<Superhero> superheroes) {
        fh.saveListSuperHeroes(superheroes, CSVPath);
    }
}
