import java.io.FileReader;
import java.util.*;

public class NearestNeighbour {

    public static ArrayList<Flower> flowerListTraining = new ArrayList<>();
    public static ArrayList<Flower> flowerListTest = new ArrayList<>();
    public static HashMap<Double, String> distances = new HashMap<>();
    public static HashMap<Double, String> n = new HashMap<>();
    public static HashMap<Double, String> Neighbours = new HashMap<>();
    private static double sL;
    private static double sW;
    private static double pL;
    private static double pW;
    private static String name1;


    public static void scanFiles(String s) {
        try {
            FileReader file = new FileReader(s);
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                if (scan.hasNextDouble()) sL = scan.nextDouble();
                if (scan.hasNextDouble()) sW = scan.nextDouble();
                if (scan.hasNextDouble()) pL = scan.nextDouble();
                if (scan.hasNextDouble()) pW = scan.nextDouble();
                if (scan.hasNext()) name1 = scan.next();

                if (s.equals("C:\\Users\\ASUS\\Documents\\Uni Work\\COMP307\\A1\\src\\iris-training.txt")) {
                    flowerListTraining.add(new Flower(sL, sW, pL, pW, name1));
                } else if(s.equals("C:\\Users\\ASUS\\Documents\\Uni Work\\COMP307\\A1\\src\\iris-test.txt"))flowerListTest.add(new Flower(sL, sW, pL, pW, name1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String kNearest(ArrayList<Flower> flowerTraining, Flower testFlower, int k) {
        for(Flower trainFlower: flowerTraining){
            //Performs Euclidean calculation between points in Test and Training
            double dist = euclideanDistance(testFlower, trainFlower);
            //puts them into a new Hashmap with the distances and name of flower
            distances.put(dist, trainFlower.getName());
        }
        double min = 100;
        for(double d : distances.keySet()) {
            if(d < min) min = d;
            //may need to remove closest neighbour
        }

        Neighbours.put(min, distances.get(min));

        String predictions = Neighbours.get(min);
        System.out.println(predictions);
        return predictions;
    }

    private static double euclideanDistance(Flower flowerA, Flower flowerB) {
        double sepalLengthRange = range(flowerListTraining, "sepalLength");
        double sepalWidthRange = range(flowerListTraining, "sepalWidth");
        double petalLengthRange = range(flowerListTraining, "petalLength");
        double petalWidthRange = range(flowerListTraining, "petalWidth");

        double sepLength = Math.pow(((flowerA.getSepL() - flowerB.getSepL())/sepalLengthRange),2);
        double sepWidth = Math.pow(((flowerA.getSepW() - flowerB.getSepW())/sepalWidthRange),2);
        double petLength = Math.pow(((flowerA.getPetL() - flowerB.getPetL())/petalLengthRange),2);
        double petWidth = Math.pow(((flowerA.getPetW() - flowerB.getPetW())/petalWidthRange),2);

        double dist = Math.sqrt(sepLength + sepWidth + petLength + petWidth);
        return dist;
    }

    public static double range(ArrayList<Flower> flowerList, String measurement){

        double min = 100;
        double max = 0;

        if (!flowerList.isEmpty()){
            for (final Flower flower : flowerList) {
                switch(measurement){
                    case "sepalLength":
                        max = Math.max(max, flower.getSepL());
                        min = Math.min(min, flower.getSepL());
                        break;
                    case "sepalWidth":
                        max = Math.max(max, flower.getSepW());
                        min = Math.min(min, flower.getSepW());
                        break;
                    case "petalLength":
                        max = Math.max(max, flower.getPetL());
                        min = Math.min(min, flower.getPetL());
                        break;
                    case "petalWidth":
                        max = Math.max(max, flower.getPetW());
                        min = Math.min(min, flower.getPetW());
                        break;
                }
            }
        }
        return max - min;
    }

    public static void main(String[] arg) {

        scanFiles("C:\\Users\\ASUS\\Documents\\Uni Work\\COMP307\\A1\\src\\iris-training.txt");
        scanFiles("C:\\Users\\ASUS\\Documents\\Uni Work\\COMP307\\A1\\src\\iris-test.txt");
        kNearest(flowerListTraining, flowerListTest.get(0), 1);

    }

}