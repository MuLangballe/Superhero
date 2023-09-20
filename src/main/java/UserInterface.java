import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public void startProgram() {
        int menuChoice;
        Database superheroDatabase = new Database();
        Scanner keyboard = new Scanner(System.in);

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

            if (menuChoice == 1) {
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

                superheroDatabase.createSuperhero(superHeroName, superHeroRealName, superHeroSuperPower, superHeroYearCreated, isSuperheroHuman, superHeroStrength);
                superheroDatabase.seeAllHeroes();

            } else if (menuChoice == 2) {
                System.out.println("Liste af superhelte: ");
                ArrayList<Superhero> superheroes = superheroDatabase.seeAllHeroes();
                for (Superhero superhero : superheroes) {
                    System.out.println(superhero);
                }

            } else if (menuChoice == 3) {
                System.out.println("indtast søgning: ");
                String searchSuperhero = keyboard.nextLine();
                ArrayList<Superhero> searchResults = superheroDatabase.findSuperhero(searchSuperhero);

                if (searchResults.isEmpty()) {
                    System.out.println("Kunne ikke finde superhelten");
                } else {
                    for (Superhero superhero : searchResults) {
                        System.out.println(superhero);
                    }
                }

            } else if (menuChoice == 4) {
                System.out.println("Indtast superhelten du ønsker at redigere: ");
                String superheroToEdit = keyboard.nextLine();
                superheroDatabase.editSuperhero(superheroToEdit, keyboard);

            } else if (menuChoice == 5) {
                System.out.println("Indtast helten du ønsker at slette: ");
                String superheroToDelete  = keyboard.nextLine();
                superheroDatabase.deleteHero(superheroToDelete, keyboard);
            }
        } while (menuChoice != 9);
    }
}
