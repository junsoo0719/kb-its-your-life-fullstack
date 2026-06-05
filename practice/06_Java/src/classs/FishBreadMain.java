package classs;

public class FishBreadMain {
    public static void main(String[] args) {
        FishBread fish1 = new FishBread("팥", "잉어", 888);
        FishBread fish2 = new FishBread();

//        System.out.println(fish1);
//
//        fish1.taste = "슈크림";
//        fish1.shape = "잉어";
//        fish1.price = 666;

        fish1.printFishBread();
        fish2.printFishBread();
    }
}
