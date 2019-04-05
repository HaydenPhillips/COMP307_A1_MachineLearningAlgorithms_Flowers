import java.io.FileReader;
import java.util.*;

public class NearestNeighbour {

    public static ArrayList<Flower> flowerListTraining = new ArrayList<>();
    public static ArrayList<Flower> flowerListTest = new ArrayList<>();
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
                } else if (s.equals("C:\\Users\\ASUS\\Documents\\Uni Work\\COMP307\\A1\\src\\iris-test.txt"))
                    flowerListTest.add(new Flower(sL, sW, pL, pW, name1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String kNearest(ArrayList<Flower> flowerTraining, Flower testFlower, int k) {
        HashMap<Double, String> distances = new HashMap<>();
        for (Flower trainFlower : flowerTraining) {
            //Performs Euclidean calculation between points in Test and Training
            double dist = euclideanDistance(testFlower, trainFlower);
            //puts them into a new Hashmap with the distances and name of flower
            distances.put(dist, trainFlower.getName());
        }
        ArrayList<Double> disList = new ArrayList<>(distances.keySet());
        Collections.sort(disList);

        FlowerTypeCount iVe = new FlowerTypeCount(0, "Iris-versicolor");
        FlowerTypeCount iVi = new FlowerTypeCount(0, "Iris-virginica");
        FlowerTypeCount iSe = new FlowerTypeCount(0, "Iris-setosa");
        for (int i = 0; i < k; i++) {
            String predictionName = distances.get(disList.get(i));

            if (predictionName.equals("Iris-versicolor")) {
                iVe.addCount();
            } else if (predictionName.equals("Iris-virginica")) {
                iVi.addCount();
            } else if(predictionName.equals("Iris-setosa")) {
                iSe.addCount();
            }
        }

        if (iVe.getCount() > iVi.getCount() && iVe.getCount() > iSe.getCount()) return iVe.getName();
        if (iVi.getCount() > iVe.getCount() && iVi.getCount() > iSe.getCount()) return iVi.getName();
        if (iSe.getCount() > iVe.getCount() && iSe.getCount() > iVi.getCount()) return iSe.getName();

        return null;
    }

    public static int getAccuracy(int k) {
        int correct = 0;
        int count = 0;
        for (int i = 0; i < flowerListTest.size(); i++) {
            count++;
            Flower testFlower = flowerListTest.get(i);
            String tempPredict = kNearest(flowerListTraining, testFlower, k);

            if (testFlower.getName().equals(tempPredict)) {
                correct++;
            }
        }
        double percent = (((double) correct) / ((double) count)) * 100;
        System.out.println("Correct: "+correct);
        System.out.println("Total: "+count);
        System.out.println("When K = "+k+ ", percent = "+percent+"%");
        return correct;
    }


    private static double euclideanDistance(Flower flowerA, Flower flowerB) {
        double sepalLengthRange = range(flowerListTraining, "sepalLength");
        double sepalWidthRange = range(flowerListTraining, "sepalWidth");
        double petalLengthRange = range(flowerListTraining, "petalLength");
        double petalWidthRange = range(flowerListTraining, "petalWidth");

        double sepLength = Math.pow(((flowerA.getSepL() - flowerB.getSepL()) / sepalLengthRange), 2);
        double sepWidth = Math.pow(((flowerA.getSepW() - flowerB.getSepW()) / sepalWidthRange), 2);
        double petLength = Math.pow(((flowerA.getPetL() - flowerB.getPetL()) / petalLengthRange), 2);
        double petWidth = Math.pow(((flowerA.getPetW() - flowerB.getPetW()) / petalWidthRange), 2);

        double dist = Math.sqrt(sepLength + sepWidth + petLength + petWidth);
        return dist;
    }

    public static double range(ArrayList<Flower> flowerList, String measurement) {

        double min = 100;
        double max = 0;

        if (!flowerList.isEmpty()) {
            for (final Flower flower : flowerList) {
                switch (measurement) {
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

        scanFiles(arg[0]);
        scanFiles(arg[1]);
        getAccuracy(1);
        getAccuracy(3);
    }

}
