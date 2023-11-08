package domain_model;

import java.util.Comparator;

public class IsHumanComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero isHuman, Superhero otherIsHuman){
        return Boolean.compare(isHuman.getIsHuman(), otherIsHuman.getIsHuman());
    }

}
