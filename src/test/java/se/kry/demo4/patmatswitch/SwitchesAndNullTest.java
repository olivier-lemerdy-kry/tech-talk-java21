package se.kry.demo4.patmatswitch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SwitchesAndNullTest {

    static Stream<Arguments> inputAndExpected() {
        return Stream.of(
                arguments(null, "Object is null"),
                arguments(1, "Object is an integer: 1"),
                arguments("foobar", "Object is a string: foobar"),
                arguments(ChronoUnit.DAYS, "Object is a chrono unit: Days"),
                arguments(Instant.EPOCH, "Object is not recognized")
        );
    }

    @ParameterizedTest
    @MethodSource("inputAndExpected")
    void test_java21_switch_without_null_case(Object input, String expected) {
        String result;
        try {
            result = switch (input) {
                case Integer i -> "Object is an integer: " + i;
                case String s -> "Object is a string: " + s;
                case ChronoUnit c -> "Object is a chrono unit: " + c;
                default -> "Object is not recognized";
            };
        } catch (NullPointerException e) {
            result = "Object is null";
        }
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("inputAndExpected")
    void test_java21_switch_with_null_case(Object input, String expected) {
        var result = switch (input) {
            case null -> "Object is null";
            case Integer i -> "Object is an integer: " + i;
            case String s -> "Object is a string: " + s;
            case ChronoUnit c -> "Object is a chrono unit: " + c;
            default -> "Object is not recognized";
        };
        assertThat(result).isEqualTo(expected);
    }
}
