package se.kry.demo2.seqcollections;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SequencedCollectionsTest {

    @Test
    void sequenced_collection() {
        SequencedCollection<String> sequencedCollection = Stream.of("Alpha", "Bravo", "Charlie", "Delta")
                .collect(Collectors.toCollection(ArrayList::new));

        assertThat(sequencedCollection).containsExactly("Alpha", "Bravo", "Charlie", "Delta");
        assertThat(sequencedCollection.reversed()).containsExactly("Delta", "Charlie", "Bravo", "Alpha");
        assertThat(sequencedCollection.getFirst()).isEqualTo("Alpha");
        assertThat(sequencedCollection.getLast()).isEqualTo("Delta");

        sequencedCollection.addFirst("Before Alpha");
        sequencedCollection.addLast("After Delta");
        assertThat(sequencedCollection).containsExactly("Before Alpha", "Alpha", "Bravo", "Charlie", "Delta", "After Delta");
    }

    @Test
    void sequenced_set() {
        SequencedSet<String> sequencedSet = new TreeSet<>(Set.of("Alpha", "Charlie", "Bravo", "Delta"));

        assertThat(sequencedSet).containsExactly("Alpha", "Bravo", "Charlie", "Delta");
        assertThat(sequencedSet.reversed()).containsExactly("Delta", "Charlie", "Bravo", "Alpha");
        assertThat(sequencedSet.getFirst()).isEqualTo("Alpha");
        assertThat(sequencedSet.getLast()).isEqualTo("Delta");

        assertThatThrownBy(() -> sequencedSet.addFirst("Before Alpha"))
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> sequencedSet.addLast("After Delta"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void sequenced_map() {
        SequencedMap<Integer, String> sequencedMap = new LinkedHashMap<>();
        sequencedMap.put(1, "Alpha");
        sequencedMap.put(2, "Bravo");
        sequencedMap.put(3, "Charlie");
        sequencedMap.put(4, "Delta");

        assertThat(sequencedMap).containsExactly(
                entry(1, "Alpha"),
                entry(2, "Bravo"),
                entry(3, "Charlie"),
                entry(4, "Delta"));
        assertThat(sequencedMap.reversed()).containsExactly(
                entry(4, "Delta"),
                entry(3, "Charlie"),
                entry(2, "Bravo"),
                entry(1, "Alpha"));
        assertThat(sequencedMap.firstEntry()).isEqualTo(entry(1, "Alpha"));
        assertThat(sequencedMap.lastEntry()).isEqualTo(entry(4, "Delta"));

        sequencedMap.putFirst(5, "Before Alpha");
        sequencedMap.putLast(3, "After Delta");

        assertThat(sequencedMap).containsExactly(
                entry(5, "Before Alpha"),
                entry(1, "Alpha"),
                entry(2, "Bravo"),
                entry(4, "Delta"),
                entry(3, "After Delta"));
    }
}
