package racingcar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {

    private int roundCount;
    private CarsRepository carsRepository = new CarsRepository();
    private IOController ioController = new IOController();
    private int carsRepositorySize;

    public GameController() {
    }

    public void playGame() {
        //get input cars name
        carsRepository.setCars(ioController.getCarNames());
        carsRepositorySize = carsRepository.getSize();

        //get input round count
        roundCount = ioController.getRoundCount();

        //play round
        ioController.printExecutingMessage();
        while (keepPlayRound()) {
            playRound();
            ioController.printRound(carsRepository);
        }

        //print Winner
        ioController.printWinners(carsRepository.getWinnerNames());
    }

    private boolean keepPlayRound() {
        if (roundCount > 0) return true;
        return false;
    }

    private void playRound() {
        roundCount--;
        CarsRepository.raceCarsBy(getRacingOptions());
    }

    private static final int MIN_NUMBER = 0;
    private static final int MAX_NUMBER = 9;
    private static final int BASE_NUMBER = 4;

    private List<Boolean> getRacingOptions() {
        List<Boolean> options = new ArrayList<>();

        for (int i = 0; i < carsRepositorySize; ++i) {
            int randomNumber = pickNumberInRange(MIN_NUMBER, MAX_NUMBER);

            if (randomNumber >= BASE_NUMBER) {
                options.add(true);
            }
            if (randomNumber < BASE_NUMBER) {
                options.add(false);
            }
        }

        return options;
    }

    private int pickNumberInRange(int min, int max) {
        if (min > max) {
            int tmp = min;
            min = max;
            max = tmp;
        }

        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}
