import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public void startProgram() {
        int menuChoice;
        Database superheroDatabase = new Database();
        Scanner keyboard = new Scanner(System.in);

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
            case 1 :
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
            break;

            case 2 :
                System.out.println("Liste af superhelte: ");
                ArrayList<Superhero> superheroes = superheroDatabase.seeAllHeroes();
                for (Superhero superhero : superheroes) {
                    System.out.println(superhero);
                }
            break;

            case 3 :
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
            break;

            case 4 :
                System.out.println("Indtast superhelten du ønsker at redigere: ");
                String superheroToEdit = keyboard.nextLine();
                superheroDatabase.editSuperhero(superheroToEdit, keyboard);
            break;

            case 5 :
                System.out.println("Indtast helten du ønsker at slette: ");
                ArrayList<Superhero> superherolist = superheroDatabase.seeAllHeroes();
                for (Superhero superhero : superherolist){
                    System.out.println(superhero);
                }
                String superheroToDelete  = keyboard.nextLine();
                boolean succesDelete = superheroDatabase.deleteSuperhero1(superheroToDelete);
                        if(succesDelete){
                            System.out.println(superheroToDelete + " blev slettet");
                        }
                        else {
                            System.out.println("Der er ingen superhelt der kan slettes");
                        }
            break;
            }
        } while (menuChoice != 9);
    }
}
