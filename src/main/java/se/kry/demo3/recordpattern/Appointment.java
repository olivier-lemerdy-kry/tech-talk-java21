package se.kry.demo3.recordpattern;

import java.time.LocalDateTime;

public record Appointment(Patient subject, LocalDateTime start, LocalDateTime end) {
}
