package se.kry.demo4.patmatswitch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CaseRefinementTest {
    static Stream<Arguments> inputAndExpected() {
        return Stream.of(
                arguments(1, "Object is a small integer: 1"),
                arguments(1_000_000_000, "Object is a big integer: 1000000000"),
                arguments("foobar", "Object is a string: foobar"),
                arguments(ChronoUnit.DAYS, "Object is a chrono unit: Days"),
                arguments(ChronoUnit.MONTHS, "Object is a chrono unit: Months"),
                arguments(ChronoUnit.YEARS, "Object is a chrono unit: Years"),
                arguments(DayOfWeek.MONDAY, "Object is a day of week: Monday"),
                arguments(DayOfWeek.SATURDAY, "Object is a day of week: Saturday"),
                arguments(Instant.EPOCH, "Object is not recognized")
        );
    }

    @ParameterizedTest
    @MethodSource("inputAndExpected")
    void test_java21_case_old(Object input, String expected) {
        var result = switch (input) {
            case Integer i when i < 1_000 -> "Object is a small integer: " + i;
            case Integer i -> "Object is a big integer: " + i;
            case String s -> "Object is a string: " + s;
            case ChronoUnit c -> {
                if (c == ChronoUnit.DAYS) {
                    yield "Object is a chrono unit: Days";
                } else if (c == ChronoUnit.MONTHS) {
                    yield "Object is a chrono unit: Months";
                } else if (c == ChronoUnit.YEARS) {
                    yield "Object is a chrono unit: Years";
                } else {
                    yield "Object is not recognized";
                }
            }
            case DayOfWeek d -> {
                if (d == DayOfWeek.MONDAY) {
                    yield "Object is a day of week: Monday";
                } else if (d == DayOfWeek.SATURDAY) {
                    yield "Object is a day of week: Saturday";
                } else {
                    yield "Object is not recognized";
                }
            }
            default -> "Object is not recognized";
        };
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("inputAndExpected")
    void test_java21_case_refinement(Object input, String expected) {
        var result = switch (input) {
            case Integer i when i < 1_000 -> "Object is a small integer: " + i;
            case Integer i -> "Object is a big integer: " + i;
            case String s -> "Object is a string: " + s;
            case ChronoUnit c when c == ChronoUnit.DAYS -> "Object is a chrono unit: Days";
            case ChronoUnit c when c == ChronoUnit.MONTHS -> "Object is a chrono unit: Months";
            case ChronoUnit c when c == ChronoUnit.YEARS -> "Object is a chrono unit: Years";
            case DayOfWeek d when d == DayOfWeek.MONDAY -> "Object is a day of week: Monday";
            case DayOfWeek d when d == DayOfWeek.SATURDAY -> "Object is a day of week: Saturday";
            default -> "Object is not recognized";
        };
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("inputAndExpected")
    void test_java_21_case_a_step_further(Object input, String expected) {
        var result = switch (input) {
            case Integer i when i < 1_000 -> "Object is a small integer: " + i;
            case Integer i -> "Object is a big integer: " + i;
            case String s -> "Object is a string: " + s;
            case ChronoUnit.DAYS -> "Object is a chrono unit: Days";
            case ChronoUnit.MONTHS -> "Object is a chrono unit: Months";
            case ChronoUnit.YEARS -> "Object is a chrono unit: Years";
            case DayOfWeek.MONDAY -> "Object is a day of week: Monday";
            case DayOfWeek.SATURDAY -> "Object is a day of week: Saturday";
            default -> "Object is not recognized";
        };
        assertThat(result).isEqualTo(expected);
    }
}
