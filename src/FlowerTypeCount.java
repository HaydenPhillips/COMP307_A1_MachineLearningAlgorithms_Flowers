public class FlowerTypeCount extends NearestNeighbour{

    int num;
    String name;

    public FlowerTypeCount(int count, String name){
        this.num = count;
        this.name = name;
    }

    public void addCount(){
        int curNum = this.getCount();
        curNum++;
        this.setCount(curNum);
    }

    public int getCount() {
        return num;
    }

    public String getName() {
        return name;
    }

    public void setCount(int num) {
        this.num = num;
    }


}
