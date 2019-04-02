public class Flower extends NearestNeighbour {

    double sepL;
    double sepW;
    double petL;
    double petW;
    String name;

    public Flower(double sepL, double sepW, double petL, double petW, String name){
        this.sepL = sepL;
        this.sepW = sepW;
        this.petL = petL;
        this.petW = petW;
        this.name = name;
    }

    public double getSepL() {
        return sepL;
    }

    public double getSepW() {
        return sepW;
    }

    public double getPetL() {
        return petL;
    }

    public double getPetW() {
        return petW;
    }

    public String getName() {
        return name;
    }
}
