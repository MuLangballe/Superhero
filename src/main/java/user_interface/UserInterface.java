package user_interface;

import domain_model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.*;

public class UserInterface {
    private final Controller controller;

    public UserInterface(Controller controller) {
        this.controller = controller;
    }

    Scanner keyboard = new Scanner(System.in);

    public void startProgram() {
        int menuChoice;
        //controller.loadAllSuperhero();

        //TODO: (nice to do) ændre sproget til engelsk

        do {
            System.out.println("""
                    Velkommen til SUPERHERO UNIVERSET.\s
                     1. Opret superhelt\s
                     2. Se liste af superhelte\s
                     3. Søg efter superhelt\s
                     4. Rediger eksistrende superhelt\s
                     5. Slet superhelt\s
                     6. Sortér din liste efter ét parameter\s
                     7. Sortér din liste efter to parametre\s
                     9. Afslut""");
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
                    if (!resultsToEdit.isEmpty()) {
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
                    controller.saveListOfSuperheroes(controller.seeAllHeroes());

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

                case 6:
                    System.out.println("""
                            Hvilken parameter vil du sortere listen efter?\s
                            1. Superheltenavn: Alfabetisk\s
                             2. Rigtige navn: Alfabetisk\s
                             3. Superkræfter: Alfabetisk\s
                             4. Oprindelsesår: ældste først\s
                             5. Race: Mennesker øverst\s
                             6. Styrke: Stærkeste øverst\s
                             7. Sorter efter 2 parametre\s
                            """);

                    int menuChoice2;
                    menuChoice2 = keyboard.nextInt();
                    keyboard.nextLine();

                    switch (menuChoice2) {
                        case 1:
                            Collections.sort(controller.seeAllHeroes(), new NameComparator());
                            for (Superhero superhero : controller.seeAllHeroes()) {
                                System.out.println(superhero);
                                controller.saveListOfSuperheroes(controller.seeAllHeroes());
                            }
                            break;

                        case 2:
                            Collections.sort(controller.seeAllHeroes(), new RealNameComparator());
                            for (Superhero superhero : controller.seeAllHeroes()) {
                                System.out.println(superhero);
                                controller.saveListOfSuperheroes(controller.seeAllHeroes());
                            }
                            break;

                        case 3:
                            Collections.sort(controller.seeAllHeroes(), new SuperpowerComparator());
                            for (Superhero superhero : controller.seeAllHeroes()) {
                                System.out.println(superhero);
                                controller.saveListOfSuperheroes(controller.seeAllHeroes());
                            }
                            break;

                        case 4:
                            Collections.sort(controller.seeAllHeroes(), new YearComparator());
                            for (Superhero superhero : controller.seeAllHeroes()) {
                                System.out.println(superhero);
                                controller.saveListOfSuperheroes(controller.seeAllHeroes());
                            }
                            break;

                        case 5:
                            Collections.sort(controller.seeAllHeroes(), new IsHumanComparator().reversed());
                            for (Superhero superhero : controller.seeAllHeroes()) {
                                System.out.println(superhero);
                                controller.saveListOfSuperheroes(controller.seeAllHeroes());
                            }
                            break;


                        case 6:
                            Collections.sort(controller.seeAllHeroes(), new StrengthComparator().reversed());
                            for (Superhero superhero : controller.seeAllHeroes()) {
                                System.out.println(superhero);
                                controller.saveListOfSuperheroes(controller.seeAllHeroes());
                            }
                            break;
                    }

                case 7:
                    System.out.println("""
                            Which two attributes do you want to sort by? \s
                             1. Superheltenavn: Alfabetisk\s
                             2. Rigtige navn: Alfabetisk\s
                             3. Superkræfter: Alfabetisk\s
                             4. Oprindelsesår: ældste først\s
                             5. Race: Mennesker øverst\s
                             6. Styrke: Stærkeste øverst\s
                            \s""");
                    System.out.println("Select the first attribute: ");
                    int choice1 = keyboard.nextInt();
                    keyboard.nextLine();
                    System.out.println("Select the 2nd attribute:");
                    int choice2 = keyboard.nextInt();
                    keyboard.nextLine();

                    Map<Integer, Comparator<Superhero>> sortByAttributes = new HashMap<>(); //Map <De her skal være java klasse den læser på. Det må ikke være primitive typer såsom double f.eks long, int, double. Brug java wrapper klasses Integet og String f.eks>

                    sortByAttributes.put(1, new NameComparator());
                    sortByAttributes.put(2, new RealNameComparator());
                    sortByAttributes.put(3, new SuperpowerComparator());
                    sortByAttributes.put(4, new YearComparator());
                    sortByAttributes.put(5, new IsHumanComparator().reversed());
                    sortByAttributes.put(6, new StrengthComparator().reversed());

                    Comparator<Superhero> customComparator = sortByAttributes.get(choice1).thenComparing(sortByAttributes.get(choice2));

                    Collections.sort(controller.seeAllHeroes(), customComparator);
                    for (Superhero superhero : controller.seeAllHeroes()) {
                        System.out.println(superhero);
                        controller.saveListOfSuperheroes(controller.seeAllHeroes());
                    }
                    break;
            }
        } while (menuChoice != 9);
    }
}


