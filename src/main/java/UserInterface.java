import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

        private Controller controller;
        public UserInterface (Controller controller){
            this.controller=controller;
        }

        Scanner keyboard = new Scanner(System.in);



    public void startProgram() {
        int menuChoice;
        //controller.loadAllSuperhero();

        //TODO: (nice to do) ændre sproget til engelsk

        do {
            System.out.println("Velkommen til SUPERHERO UNIVERSET. \n " +
                    "1. Opret superhelt \n " +
                    "2. Se liste af superhelte \n " +
                    "3. Søg efter superhelt \n " +
                    "4. Rediger eksistrende superhelt \n " +
                    "5. Slet superhelt \n " +
                    "9. Afslut");
            while (!keyboard.hasNextInt()) {
                String text = keyboard.next();
                System.out.println("'" + text + "'" + " er ikke et gyldigt tal. Prøv igen.");
            }
            menuChoice = keyboard.nextInt();
            keyboard.nextLine();


            switch (menuChoice) {
                case 1:
                    System.out.println("Tilføj superhelt: indtast superheltens navn: ");
                    String superHeroName = keyboard.nextLine();

                    System.out.println("Indtast superheltens RIGTIGE navn: ");
                    String superHeroRealName = keyboard.nextLine();

                    System.out.println("Indtast superheltens superkraft: ");
                    String superHeroSuperPower = keyboard.nextLine();

                    System.out.println("Indtast året superhelten blev skabt: ");
                    while (!keyboard.hasNextInt()) {
                        String text = keyboard.next();
                        System.out.println("'" + text + "'" + " er ikke et gyldigt tal. Prøv igen.");
                    }
                    int superHeroYearCreated = keyboard.nextInt();
                    keyboard.nextLine();

                    System.out.println("Er superhelten et menneske? [j/n]");
                    char isHumanInput = keyboard.next().charAt(0);
                    boolean isSuperheroHuman = true;
                    switch (isHumanInput) {
                        case 'j' -> {
                        }
                        case 'n' -> isSuperheroHuman = false;
                        default -> System.out.println("Ugyldigt input.");
                    }

                    System.out.println("Hvor stærk er superhelten(i tal)?");
                    while (!keyboard.hasNextInt()) {
                        String text = keyboard.next();
                        System.out.println("'" + text + "'" + " er ikke et gyldigt tal. Prøv igen.");
                    }
                    int superHeroStrength = keyboard.nextInt();

                    controller.createSuperhero(superHeroName, superHeroRealName, superHeroSuperPower, superHeroYearCreated, isSuperheroHuman, superHeroStrength);
                    controller.seeAllHeroes();
                    break;

                case 2:
                    System.out.println("Liste af superhelte: ");
                    ArrayList<Superhero> superheroes = controller.seeAllHeroes();
                    for (Superhero superhero : superheroes) {
                        System.out.println(superhero);
                    }
                    break;

                case 3:
                    System.out.println("indtast søgning: ");
                    String searchSuperhero = keyboard.nextLine();
                    ArrayList<Superhero> searchResults = controller.findSuperhero(searchSuperhero);

                    if (searchResults.isEmpty()) {
                        System.out.println("Kunne ikke finde superhelten");
                    } else {
                        for (Superhero superhero : searchResults) {
                            System.out.println(superhero);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Indtast superhelten du ønsker at redigere: ");
                    String brugerInput = keyboard.nextLine();

                    ArrayList<Superhero> resultsToEdit = controller.findSuperhero(brugerInput);
                    Superhero superheroToEdit;

                    while (resultsToEdit.isEmpty()) {
                        System.out.println("Superhelt ikke fundet!");
                        System.out.println("Indtast superhelten du ønsker at redigere: ");
                        String newSearch = keyboard.nextLine();
                        resultsToEdit = controller.findSuperhero(newSearch);
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

                    break;

                case 5:
                    System.out.println("Indtast helten du ønsker at slette: ");
                    ArrayList<Superhero> superherolist = controller.seeAllHeroes();
                    for (Superhero superhero : superherolist) {
                        System.out.println(superhero);
                    }
                    String superheroToDelete = keyboard.nextLine();
                    boolean succesDelete = controller.deleteSuperhero(superheroToDelete);
                    if (succesDelete) {
                        System.out.println(superheroToDelete + " blev slettet");
                    } else {
                        System.out.println("Der er ingen superhelt der kan slettes");
                    }
                    break;

                case 9:
                    // Save all superheroes

            }
        } while (menuChoice != 9);
    }

}

