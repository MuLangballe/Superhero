import java.util.Comparator;

public class NameComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero superheroName1, Superhero superheroName2) {
        return superheroName1.getName().toLowerCase().trim().compareTo(superheroName2.getName().toLowerCase().trim());
    }
}
