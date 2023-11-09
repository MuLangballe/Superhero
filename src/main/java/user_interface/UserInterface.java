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



        do {
            System.out.println("""
                    Welcome to your superhero UNIVERSE.\s
                     1. Add superhero\s
                     2. See list of superheroes\s
                     3. Search for a superhero\s
                     4. Edit added superhero\s
                     5. Delete superhero\s
                     6. Sort your superhero list after a specified attribute\s
                     7. Sort your superhero list after two specified attributes\s
                     9. Exit program""");
            while (!keyboard.hasNextInt()) {
                String text = keyboard.next();
                System.out.println("'" + text + "'" + " not a valid number, try again");
            }
            menuChoice = keyboard.nextInt();
            keyboard.nextLine();


            switch (menuChoice) {
                case 1:
                    System.out.println("Add the superheroe's name: ");
                    String superHeroName = keyboard.nextLine();

                    System.out.println("Type the superheroe's real name: ");
                    String superHeroRealName = keyboard.nextLine();

                    System.out.println("Your superheroe's superpower: ");
                    String superHeroSuperPower = keyboard.nextLine();

                    System.out.println("The year your superhero came to existence: ");
                    while (!keyboard.hasNextInt()) {
                        String text = keyboard.next();
                        System.out.println("'" + text + "'" + " not a valid number. Try again");
                    }
                    int superHeroYearCreated = keyboard.nextInt();
                    keyboard.nextLine();

                    System.out.println("Is your superhero a human? [j/n]");
                    char isHumanInput = keyboard.next().charAt(0);
                    boolean isSuperheroHuman = true;
                    switch (isHumanInput) {
                        case 'y' -> {
                        }
                        case 'n' -> isSuperheroHuman = false;
                        default -> System.out.println("Invalid input.");
                    }

                    System.out.println("Your superheroe's power level (in numbers)?");
                    while (!keyboard.hasNextInt()) {
                        String text = keyboard.next();
                        System.out.println("'" + text + "'" + " not a valid number. Try again");
                    }
                    int superHeroStrength = keyboard.nextInt();

                    controller.createSuperhero(superHeroName, superHeroRealName, superHeroSuperPower, superHeroYearCreated, isSuperheroHuman, superHeroStrength);
                    controller.viewSuperheroes();
                    break;

                case 2:
                    System.out.println("List of superheroes: ");
                    ArrayList<Superhero> superheroes = controller.viewSuperheroes();
                    for (Superhero superhero : superheroes) {
                        System.out.println(superhero);
                    }
                    break;

                case 3:
                    System.out.println("Enter search: ");
                    String searchSuperhero = keyboard.nextLine();
                    ArrayList<Superhero> searchResults = controller.findSuperhero(searchSuperhero);

                    if (searchResults.isEmpty()) {
                        System.out.println("Error: superhero not found");
                    } else {
                        for (Superhero superhero : searchResults) {
                            System.out.println(superhero);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Which superhero do you want to edit: ");
                    String brugerInput = keyboard.nextLine();

                    ArrayList<Superhero> resultsToEdit = controller.findSuperhero(brugerInput);
                    Superhero superheroToEdit;

                    while (resultsToEdit.isEmpty()) {
                        System.out.println("Superhero not found!");
                        System.out.println("Enter the superhero you want to edit: ");
                        String newSearch = keyboard.nextLine();
                        resultsToEdit = controller.findSuperhero(newSearch);
                    }

                    if (resultsToEdit.size() > 1) {
                        System.out.println("Choose superhero: ");
                        int count = 1;
                        for (Superhero sh : resultsToEdit) {
                            System.out.println(count++ + " " +
                                    sh.getName() + " \n " +
                                    sh.getRealName() + " \n " +
                                    sh.getSuperPower() + " \n " +
                                    sh.getYearCreated() + " \n " +
                                    sh.getIsHuman() + "\n" +
                                    sh.getStrength());
                        }
                        int superheroChoice = keyboard.nextInt();
                        keyboard.nextLine();

                        superheroToEdit = resultsToEdit.get(superheroChoice - 1);

                    } else {
                        superheroToEdit = resultsToEdit.get(0);
                    }
                    if (!resultsToEdit.isEmpty()) {
                        System.out.println("Edit information. Press enter to continue.");
                        String newValue;
                        System.out.println("Name: " + superheroToEdit.getName());
                        newValue = keyboard.nextLine();
                        if (!newValue.isEmpty()) {
                            superheroToEdit.setName(newValue);
                        }
                        System.out.println("Real name: " + superheroToEdit.getRealName());
                        newValue = keyboard.nextLine();
                        if (!newValue.isEmpty()) {
                            superheroToEdit.setRealName(newValue);
                        }
                        System.out.println("Superpower: " + superheroToEdit.getSuperPower());
                        newValue = keyboard.nextLine();
                        if (!newValue.isEmpty()) {
                            superheroToEdit.setSuperPower(newValue);
                        }
                        System.out.println("Origin: " + superheroToEdit.getYearCreated());
                        newValue = keyboard.nextLine();
                        if (!newValue.isEmpty()) {
                            superheroToEdit.setYearCreated(Integer.parseInt(newValue));
                        }
                        System.out.println("Strength: " + superheroToEdit.getStrength());
                        newValue = keyboard.nextLine();
                        if (!newValue.isEmpty()) {
                            superheroToEdit.setStrength(Integer.parseInt(newValue));
                        }
                        System.out.println(superheroToEdit.getName() + " has been updated.");
                    }
                    controller.saveListOfSuperheroes(controller.viewSuperheroes());

                    break;

                case 5:
                    System.out.println("Type the superhero you want to begone: ");
                    ArrayList<Superhero> superherolist = controller.viewSuperheroes();
                    for (Superhero superhero : superherolist) {
                        System.out.println(superhero);
                    }
                    String superheroToDelete = keyboard.nextLine();
                    boolean succesDelete = controller.deleteSuperhero(superheroToDelete);
                    if (succesDelete) {
                        System.out.println(superheroToDelete + " has been terminated from your superhero UNIVERSE");
                    } else {
                        System.out.println("No superhero has been deleted");
                    }
                    break;

                case 6:
                    System.out.println("""
                            Which attribute do you want to sort by?\s
                            1. Suphero name: Alphabetical\s
                             2. Real name: Alphabetical\s
                             3. Superpower: Alphabetical\s
                             4. Origin: oldest first\s
                             5. Race: Humans on top\s
                             6. Strength: Strongest on top\s
                             7. Sort after two attributes\s
                            """);

                    int menuChoice2;
                    menuChoice2 = keyboard.nextInt();
                    keyboard.nextLine();

                    switch (menuChoice2) {
                        case 1:
                            Collections.sort(controller.viewSuperheroes(), new NameComparator());
                            for (Superhero superhero : controller.viewSuperheroes()) {
                                System.out.println(superhero);
                                controller.saveListOfSuperheroes(controller.viewSuperheroes());
                            }
                            break;

                        case 2:
                            Collections.sort(controller.viewSuperheroes(), new RealNameComparator());
                            for (Superhero superhero : controller.viewSuperheroes()) {
                                System.out.println(superhero);
                                controller.saveListOfSuperheroes(controller.viewSuperheroes());
                            }
                            break;

                        case 3:
                            Collections.sort(controller.viewSuperheroes(), new SuperpowerComparator());
                            for (Superhero superhero : controller.viewSuperheroes()) {
                                System.out.println(superhero);
                                controller.saveListOfSuperheroes(controller.viewSuperheroes());
                            }
                            break;

                        case 4:
                            Collections.sort(controller.viewSuperheroes(), new YearComparator());
                            for (Superhero superhero : controller.viewSuperheroes()) {
                                System.out.println(superhero);
                                controller.saveListOfSuperheroes(controller.viewSuperheroes());
                            }
                            break;

                        case 5:
                            Collections.sort(controller.viewSuperheroes(), new IsHumanComparator().reversed());
                            for (Superhero superhero : controller.viewSuperheroes()) {
                                System.out.println(superhero);
                                controller.saveListOfSuperheroes(controller.viewSuperheroes());
                            }
                            break;


                        case 6:
                            Collections.sort(controller.viewSuperheroes(), new StrengthComparator().reversed());
                            for (Superhero superhero : controller.viewSuperheroes()) {
                                System.out.println(superhero);
                                controller.saveListOfSuperheroes(controller.viewSuperheroes());
                            }
                            break;
                    }

                case 7:
                    System.out.println("""
                            Which two attributes do you want to sort by?\s
                            1. Suphero name: Alphabetical\s
                             2. Real name: Alphabetical\s
                             3. Superpower: Alphabetical\s
                             4. Origin: oldest first\s
                             5. Race: Humans on top\s
                             6. Strength: Strongest on top\s
                             7. Sort after two attributes\s
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

                    Collections.sort(controller.viewSuperheroes(), customComparator);
                    for (Superhero superhero : controller.viewSuperheroes()) {
                        System.out.println(superhero);
                        controller.saveListOfSuperheroes(controller.viewSuperheroes());
                    }
                    break;
            }
        } while (menuChoice != 9);
    }
}


