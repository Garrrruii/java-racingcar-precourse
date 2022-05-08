package racingcar;

import java.util.Random;

public class GameController {

    private int roundCount;
    private CarsRepository carsRepository = new CarsRepository();
    private int carsRepositorySize;

    public GameController() {
    }

    public void playGame(){
        //get input cars name
        carsRepository.setCars(IOController.getCarNames());
        carsRepositorySize=carsRepository.getSize();

        //get input round count
        roundCount=IOController.getRoundCount();

        //play round
        while(keepPlayRound()){
            playRound();
        }

        //print Winner
        IOController.printWinners(carsRepository.getWinners());
    }

    private boolean keepPlayRound(){
        if(roundCount>0) return true;
        return false;
    }

    private void playRound(){
        roundCount--;
        for(int i=0;i<carsRepositorySize;++i){
            boolean racingOption=getRacingOption();
            CarsRepository.raceCar(i,racingOption);
        }
    }

    private static final int MIN_NUMBER=0;
    private static final int MAX_NUMBER=9;
    private static final int BASE_NUMBER=4;

    private boolean getRacingOption(){
        int randomNumber=pickNumberInRange(MIN_NUMBER,MAX_NUMBER);

        if(randomNumber>=BASE_NUMBER) return true;
        return false;
    }

    private int pickNumberInRange(int min, int max){
        if (min > max) {
            int tmp = min;
            min = max;
            max = tmp;
        }

        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}
