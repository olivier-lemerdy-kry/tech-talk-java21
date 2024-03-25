package se.kry.demo3.recordpattern;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class RecordPatternTest {

    private static Object patientObject;

    private static Object appointmentObject;

    @BeforeAll
    static void beforeAll() {
        var patient = new Patient("John Doe", LocalDate.of(2001, Month.JANUARY, 1));
        patientObject = patient;
        var date = LocalDate.of(2024, Month.AUGUST, 7);
        appointmentObject = new Appointment(patient, date.atTime(15, 30), date.atTime(16, 45));
        ;
    }

    @Test
    void test_before_java_17() {
        if (patientObject instanceof Patient) {
            assertThat(((Patient) patientObject).name()).isEqualTo("John Doe");
        }
    }

    @Test
    void test_java_17() {
        if (patientObject instanceof Patient patient) {
            assertThat(patient.name()).isEqualTo("John Doe");
        }
    }

    @Test
    void test_java_21() {
        if (patientObject instanceof Patient(String name, LocalDate __)) {
            assertThat(name).isEqualTo("John Doe");
        }
    }

    @Test
    void test_java_21_nested() {
        if (appointmentObject instanceof Appointment(
                Patient(String name, LocalDate b), LocalDateTime s, LocalDateTime e
        )) {
            assertThat(name).isEqualTo("John Doe");
        }
    }
}
