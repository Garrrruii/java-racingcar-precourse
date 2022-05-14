package racingcar;

public class Car {
    private final String name;
    private int position = 0;

    public Car(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public String getName(){
        return name;
    }

    public void race(boolean racingOption){
        if(racingOption) {
            position++;
        }
    }

    public void printCar(){
        System.out.print(name+" : ");
        for(int i=0;i<position;++i) System.out.print('-');
        System.out.println();
    }

}
