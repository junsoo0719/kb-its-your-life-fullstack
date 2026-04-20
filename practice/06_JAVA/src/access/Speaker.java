package access;

public class Speaker {
    private boolean power = false;
    private int volume = 0;

    public Speaker() {
        System.out.println("전원을 켭니다");
        this.power = true;
    }

    public void volumeUp() {
        if (this.volume <= 100) {
            this.volume++;
            System.out.println("볼륨을 올립니다. 현재 볼륨 : " + this.volume);
        } else {
            System.out.println("볼륨이 이미 최대치 입니다");
        }
    }

    public void volumeDown() {
        if (this.volume >= 1) {
            this.volume--;
            System.out.println("볼륨을 내립니다. 현재 볼륨 : " + this.volume);
        } else {
            System.out.println("볼륨은 0 이하로 내릴 수 없습니다");
        }
    }
}