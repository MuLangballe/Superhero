import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Database {
    Superhero testHero = new Superhero("Mu", "Mik", "Styrke", 92,true, 233);
    Superhero testHero2 = new Superhero("Mus", "Mar", "Flyve", 92,true, 232);

    ArrayList<Superhero> superheroes = new ArrayList<>(Arrays.asList(testHero, testHero2));


    public void createSuperhero(String name, String realName, String superPower, int yearCreated, boolean isHuman, int strength) {
        superheroes.add(new Superhero(name, realName, superPower, yearCreated, isHuman, strength));
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

    public void editSuperhero(String superhero, Scanner keyboard) {
        ArrayList<Superhero> resultsToEdit = findSuperhero(superhero);
        Superhero superheroToEdit = null;

        while(resultsToEdit.isEmpty()) {
            System.out.println("Superhelt ikke fundet!");
            System.out.println("Indtast superhelten du ønsker at redigere: ");
            String newSearch = keyboard.nextLine();
            resultsToEdit = findSuperhero(newSearch);
        }

        if (resultsToEdit.size() > 1) {
            System.out.println("Vælg superhelt: ");
            int count = 1;
            for (Superhero sh : resultsToEdit) {
                System.out.println(count++ +
                        sh.getName() + " \n " +
                        sh.getRealName() + " \n " +
                        sh.getSuperPower() + " \n " +
                        sh.getYearCreated() + " \n " +
                        sh.getStrength());
            }
            int superheroChoice = keyboard.nextInt();
            keyboard.nextLine();

            superheroToEdit = resultsToEdit.get(superheroChoice - 1);

        } else {
            superheroToEdit = resultsToEdit.get(0);
        }
        if (resultsToEdit != null) {
            System.out.println("Rediger information. Tryk ENTER hvis information ikke skal redigeres.");
            String newValue;
            System.out.println("Navn: " + superheroToEdit.getName());
            newValue = keyboard.nextLine();
            if (!newValue.isEmpty()) {
                superheroToEdit.setName(newValue);
            }
            System.out.println("Rigtige navn: " + superheroToEdit.getRealName());
            newValue = keyboard.nextLine();
            if (!newValue.isEmpty()) {
                superheroToEdit.setRealName(newValue);
            }
            System.out.println("Superpower: " + superheroToEdit.getSuperPower());
            newValue = keyboard.nextLine();
            if (!newValue.isEmpty()) {
                superheroToEdit.setSuperPower(newValue);
            }
            System.out.println("Oprindelsesår: " + superheroToEdit.getYearCreated());
            newValue = keyboard.nextLine();
            if (!newValue.isEmpty()) {
                superheroToEdit.setYearCreated(Integer.parseInt(newValue));
            }
            System.out.println("Styrke: " + superheroToEdit.getStrength());
            newValue = keyboard.nextLine();
            if (!newValue.isEmpty()) {
                superheroToEdit.setStrength(Integer.parseInt(newValue));
            }
            System.out.println(superheroToEdit.getName() + " er opdateret.");
        }
    }

    public void seeAllHeroes() {
        for (Superhero superhero : superheroes) {
            System.out.println(superhero);
        }
    }

    public int getSize(){
        return superheroes.size();
    }
}
