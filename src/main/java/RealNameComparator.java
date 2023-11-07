import java.util.Comparator;

public class RealNameComparator implements Comparator<Superhero> {

@Override
public int compare(Superhero realName1, Superhero realName2){
    return realName1.getRealName().toLowerCase().compareTo(realName2.getRealName().toLowerCase());
}

}
