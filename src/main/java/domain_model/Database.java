package domain_model;

import datasource.FileHandler;

import java.util.ArrayList;

public class Database {

    FileHandler fh = new FileHandler();
    ArrayList<Superhero> superheroes = fh.loadAllSuperheroes();

    public void createSuperhero(String name, String realName, String superPower, int yearCreated, boolean isHuman, int strength) {
        superheroes.add(new Superhero(name, realName, superPower, yearCreated, isHuman, strength));
        fh.saveListSuperHeroes(superheroes);
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

//        for (domain_model.Superhero i: fh.loadAllSuperheroes()) {
//        if (fh.loadAllSuperheroes() != null){
//            superheroes.add(i);
//        }
//        }
        return superheroes;
    }

    public boolean deleteSuperhero(String superheroName){
        for (Superhero superhero : superheroes) {
            if (superhero.getName().toLowerCase().equals(superheroName.toLowerCase())) {
                superheroes.remove(superhero);
                fh.saveListSuperHeroes(superheroes);
                return true;
            }
        }
        return false;
    }

        public int getSize () {
            return superheroes.size();
        }
    }
