package racingcar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IOController {

    private final InputStreamReader ir = new InputStreamReader(System.in);
    private final BufferedReader br = new BufferedReader(ir);

    private final String PARSER=",";
    private final String GET_CAR_NAMES_MESSAGE="경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private final String GET_ROUND_COUNT_MESSAGE="시도할 횟수는 몇회인가요?";

    private final String ETC_IO_ERROR = "[ERROR] 입력 오류입니다.";
    private final String CAR_NAMES_DUPLICATE_ERROR="[ERROR] 자동차 이름은 모두 달라야 합니다.";
    private final String CAR_NAMES_LENGTH_ERROR="[ERROR] 자동차 이름의 길이는 1자 이상 5자 이하여야합다.";
    private final String ROUND_COUNT_RANGE_ERROR="[ERROR] 시도 횟수는 자연수여야 합니다.";

    public IOController() {
    }

    public List<String> getCarNames(){
        System.out.println(GET_CAR_NAMES_MESSAGE);

        try{
            String carNamesString = br.readLine();
            return checkAndParseCarNamesString(carNamesString);
        } catch (IOException e) {
            System.out.println(ETC_IO_ERROR);
            occurInputError();
        }

        return new ArrayList<>();
    }

    private List<String> checkAndParseCarNamesString(String carNamesString){
        List<String> carNames=new ArrayList<>(Arrays.asList(carNamesString.split(PARSER)));

        for(String s:carNames){
            if(s.length()<1 || s.length()>5) {
                System.out.println(CAR_NAMES_LENGTH_ERROR);
                occurInputError();
            }
        }

        if(carNames.size() != carNames.stream().distinct().count()){
            System.out.println(CAR_NAMES_DUPLICATE_ERROR);
            occurInputError();
        }

        return carNames;
    }

    public int getRoundCount(){
        System.out.println(GET_ROUND_COUNT_MESSAGE);

        int roundCount=0;
        try{
            roundCount= Integer.parseInt(br.readLine());
        } catch(IOException e){
            System.out.println(ETC_IO_ERROR);
            occurInputError();
        }

        if(roundCount<=0) {
            System.out.println(ROUND_COUNT_RANGE_ERROR);
            occurInputError();
        }
        return roundCount;
    }

    public void printExecutingMessage(){
        System.out.println("실행 결과");
    }

    public void printRound(CarsRepository carsRepository){
        carsRepository.printRound();
    }

    public void printWinners(List<String> winnerNames){
        System.out.println(String.join(", ",winnerNames));
    }

    private void occurInputError(){
        throw new IllegalArgumentException();
    }
}
