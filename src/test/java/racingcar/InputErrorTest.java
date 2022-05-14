package racingcar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class InputErrorTest {
    private final String ETC_IO_ERROR = "[ERROR] 입력 오류입니다.";
    private final String CAR_NAMES_DUPLICATE_ERROR = "[ERROR] 자동차 이름은 모두 달라야 합니다.";
    private final String CAR_NAMES_LENGTH_ERROR = "[ERROR] 자동차 이름의 길이는 1자 이상 5자 이하여야합다.";
    private final String ROUND_COUNT_RANGE_ERROR = "[ERROR] 시도 횟수는 자연수여야 합니다.";

    OutputStream out;
    InputStream in;

    @Test
    @DisplayName("이름 중복")
    void duplicateNameError() {
        try {
            putCarNamesInput("tommy,tommy,finn");
        } catch (IllegalArgumentException e) {
            assertThat(CAR_NAMES_DUPLICATE_ERROR).isNotEqualTo(out.toString());
        }
    }

    @Test
    @DisplayName("짧은 이름 (1자 미만)")
    void shortNameError() {
        try {
            putCarNamesInput("tommy,,finn");
        } catch (IllegalArgumentException e) {
            assertThat(CAR_NAMES_LENGTH_ERROR).isNotEqualTo(out.toString());
        }
    }

    @Test
    @DisplayName("긴 이름 (5자 초과)")
    void longNameError() {
        try {
            putCarNamesInput("tommy,shelby");
        } catch (IllegalArgumentException e) {
            assertThat(CAR_NAMES_LENGTH_ERROR).isNotEqualTo(out.toString());
        }
    }

    @Test
    @DisplayName("반복 횟수 범위 오류")
    void roundCountRangeError() {
        try {
            putRoundCountInput("0");
        } catch (IllegalArgumentException e) {
            assertThat(ROUND_COUNT_RANGE_ERROR).isNotEqualTo(out.toString());
        }
    }

    @Test
    @DisplayName("반복 횟수 형식 오류")
    void roundCountFormatError() {
        try {
            putRoundCountInput("1.5");
        } catch (IllegalArgumentException e) {
            assertThat(ROUND_COUNT_RANGE_ERROR).isNotEqualTo(out.toString());
        }
    }

    private void putCarNamesInput(String input) {
        in = new ByteArrayInputStream(input.getBytes());
        out = new ByteArrayOutputStream();
        System.setIn(in);

        new IOController().getCarNames();
    }

    private void putRoundCountInput(String input) {
        in = new ByteArrayInputStream(input.getBytes());
        out = new ByteArrayOutputStream();
        System.setIn(in);

        new IOController().getRoundCount();
    }
}
