package se.kry.demo3.recordpattern;

import java.time.LocalDate;

public record Patient(String name, LocalDate birthDate) {
}
