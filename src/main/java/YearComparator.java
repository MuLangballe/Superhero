import java.util.Comparator;

public class YearComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero year1, Superhero year2){
        return Integer.compare(year1.getYearCreated(),year2.getYearCreated());
    }
}
