package ch08.sec05;

import ch08.sec02.Audio;
import ch08.sec02.RemoteControl;
import ch08.sec02.Television;

public class RemoteControlExample {
    public static void main(String[] args) {
        //인터페이스 변수 선언
        RemoteControl rc;

        rc = new Television(); // 기존 코드
        rc.turnOn();
        rc.setVolume(5);

        //디폴트 메소드 호출
        rc.setMute(true);
        rc.setMute(false);

        System.out.println();

        rc = new Audio(); // setMute 재정의 코드
        rc.turnOn();
        rc.setVolume(5);

        //디폴트 메소드 호출
        rc.setMute(true);
        rc.setMute(false);
    }
}

