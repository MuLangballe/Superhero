package domain_model;

public class Superhero {

    private String name;
    private String realName;
    private String superPower;
    private int yearCreated;
    private boolean isHuman;
    private int strength;

    public Superhero(String name, String realName, String superPower, int yearCreated, boolean isHuman, int strength) {
        this.name = name;
        this.realName = realName;
        this.superPower = superPower;
        this.yearCreated = yearCreated;
        this.isHuman = isHuman;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSuperPower() {
        return superPower;
    }

    public void setSuperPower(String superPower) {
        this.superPower = superPower;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public boolean getIsHuman(){
        return isHuman;
    }

    public void setHuman(boolean human) {
        this.isHuman = human;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String isHumanFormatter(boolean isHuman){
        if (isHuman) {
            return "Yes";
        } else {
            return "No";
        }
    }


    @Override
    public String toString() {
        return  "Superhero name: " + name +
                ", Real name: " + realName +
                ", Superpower: " + superPower +
                ", Origin: " + yearCreated +
                ", Is human: " + isHumanFormatter(isHuman) +
                ", Strength: " + strength;
    }

}
