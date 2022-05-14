package racingcar;

import java.util.ArrayList;
import java.util.List;

public class CarsRepository {

    private static List<Car> carsRepository=new ArrayList<>();

    public CarsRepository() {
    }

    public void setCars(List<String> carNames){
        for(String carName:carNames) carsRepository.add(new Car(carName));
    }

    public int getSize(){
        return carsRepository.size();
    }

    public static void raceCarsBy(List<Boolean> racingOptions){
        int idx=0;

        for(Car c:carsRepository){
            c.race(racingOptions.get(idx));
            idx++;
        }
    }

    public void printRound(){
        for(Car c:carsRepository) {
            c.printCar();
        }
    }

    public static List<String> getWinnerNames(){
        List<String> winners=new ArrayList<>();

        int maxPosition=getMaxPosition();
        for(Car c:carsRepository){
            if(maxPosition==c.getPosition()) {
                winners.add(c.getName());
            }
        }
        return winners;
    }

    private static int getMaxPosition(){
        int maxPosition=0;

        for(Car c:carsRepository){
            int cPosition=c.getPosition();
            if(maxPosition<cPosition) maxPosition=cPosition;
        }

        return maxPosition;
    }
}
