package generic.ex3;

public class PairMain {
    public static void main(String[] args) {
        Pair<String, String> nameData = new Pair<>("name", "송준수");
        Pair<String, Integer> ageData = new Pair<>("age", 26);
        Pair<String, Boolean> datingData = new Pair<>("dating", false);

        // Getter 로 데이터 출력
        System.out.println("nameData value : " + nameData.getValue());
        System.out.println("ageData value : " + ageData.getValue());
        System.out.println("datingData value : " + datingData.getValue());

        // toString 으로 인스턴스 출력
        System.out.println(nameData);
        System.out.println(ageData);
        System.out.println(datingData);
    }
}
