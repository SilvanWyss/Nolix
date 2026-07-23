/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.datastructure.sequencesearch;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.sequencesearch.SequencePattern;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class SequencePatternTest extends StandardTest {
  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerContainsMatchingSequences_1A() {
    // setup
    final var letters = ImmutableList.withElements("A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("A"))
      .withConditionForNext(e -> e.equals("B"));

    // execute
    final var result = testUnit.getMatchingSequencesFrom(letters);

    // verify
    expect(result).hasElementCount(4);
    expect(result.getStoredAtOneBasedIndex(1)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAtOneBasedIndex(2)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAtOneBasedIndex(3)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAtOneBasedIndex(4)).containsExactlyEqualing("A", "B");
  }

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerContainsMatchingSequences_1B() {
    // setup
    final var letters = ImmutableList.withElements("A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("A"))
      .withBlankForNext();

    // execute
    final var result = testUnit.getMatchingSequencesFrom(letters);

    // verify
    expect(result).hasElementCount(4);
    expect(result.getStoredAtOneBasedIndex(1)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAtOneBasedIndex(2)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAtOneBasedIndex(3)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAtOneBasedIndex(4)).containsExactlyEqualing("A", "B");
  }

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerContainsMatchingSequences_2A() {
    // setup
    final var letters = ImmutableList.withElements("A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("A"))
      .withConditionForNext(e -> e.equals("B"))
      .withSequenceCondition(s -> s.getSum(String::length).intValue() == 2);

    // execute
    final var result = testUnit.getMatchingSequencesFrom(letters);

    // verify
    expect(result).hasElementCount(4);
    expect(result.getStoredAtOneBasedIndex(1)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAtOneBasedIndex(2)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAtOneBasedIndex(3)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAtOneBasedIndex(4)).containsExactlyEqualing("A", "B");
  }

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerContainsMatchingSequences_2B() {
    // setup
    final var letters = ImmutableList.withElements("A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("A"))
      .withBlankForNext()
      .withSequenceCondition(s -> s.getSum(String::length).intValue() == 2);

    // execute
    final var result = testUnit.getMatchingSequencesFrom(letters);

    // verify
    expect(result).hasElementCount(4);
    expect(result.getStoredAtOneBasedIndex(1)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAtOneBasedIndex(2)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAtOneBasedIndex(3)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAtOneBasedIndex(4)).containsExactlyEqualing("A", "B");
  }

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerDoesNotContainMatchinSequences_1A() {
    // setup
    final var letters = ImmutableList.withElements("A", "A", "C", "A", "A", "C", "A", "A", "C", "A", "A", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("A"))
      .withConditionForNext(e -> e.equals("B"));

    // execute
    final var result = testUnit.getMatchingSequencesFrom(letters);

    // verify
    expect(result).isEmpty();
  }

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerDoesNotContainMatchinSequences_1B() {
    // setup
    final var letters = ImmutableList.withElements("A", "A", "C", "A", "A", "C", "A", "A", "C", "A", "A", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("B"))
      .withBlankForNext();

    // execute
    final var result = testUnit.getMatchingSequencesFrom(letters);

    // verify
    expect(result).isEmpty();
  }

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerDoesNotContainMatchinSequences_2A() {
    // setup
    final var letters = ImmutableList.withElements("A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("A"))
      .withConditionForNext(e -> e.equals("B"))
      .withSequenceCondition(s -> s.getSum(String::length).intValue() > 2);

    // execute
    final var result = testUnit.getMatchingSequencesFrom(letters);

    // verify
    expect(result).isEmpty();
  }

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerDoesNotContainMatchinSequences_2B() {
    // setup
    final var letters = ImmutableList.withElements("A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("A"))
      .withBlankForNext()
      .withSequenceCondition(s -> s.getSum(String::length).intValue() > 2);

    // execute
    final var result = testUnit.getMatchingSequencesFrom(letters);

    // verify
    expect(result).isEmpty();
  }
}
