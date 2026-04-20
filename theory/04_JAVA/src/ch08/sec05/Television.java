package ch08.sec06;

import ch08.sec02.RemoteControl;

public class Television implements ch08.sec02.RemoteControl {
    //필드
    private int volume;

    @Override
    public void turnOn() {
        System.out.println("TV를 켭니다.");
    }

    //turnOff() 추상 메소드 오버라이딩
    @Override
    public void turnOff() {
        System.out.println("TV를 끕니다.");
    }

    //setVolume() 추상 메소드 오버라이딩
    @Override
    public void setVolume(int volume) {
        if(volume> ch08.sec02.RemoteControl.MAX_VOLUME) {
            this.volume = ch08.sec02.RemoteControl.MAX_VOLUME;
        } else if(volume< ch08.sec02.RemoteControl.MIN_VOLUME) {
            this.volume = RemoteControl.MIN_VOLUME;
        } else {
            this.volume = volume;
        }
        System.out.println(
                "현재 TV 볼륨: " + this.volume);
    }
}
