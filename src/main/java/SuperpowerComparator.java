import java.util.Comparator;

public class SuperpowerComparator implements Comparator<Superhero> {
    @Override
    public int compare(Superhero compareSuperPower1, Superhero compareSuperPower2) {
        return compareSuperPower1.getSuperPower().toLowerCase().trim().compareTo(compareSuperPower2.getSuperPower().toLowerCase().trim());
    }
}
