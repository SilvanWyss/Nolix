package ch.nolix.coretest.containertest.sequencesearchtest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.sequencesearch.SequencePattern;
import ch.nolix.core.testing.standardtest.StandardTest;

final class SequencePatternTest extends StandardTest {

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerContainsMatchingSequences_1A() {

    //setup
    final var letters = ImmutableList.withElement("A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("A"))
      .withConditionForNext(e -> e.equals("B"));

    //execution
    final var result = testUnit.getMatchingSequencesFrom(letters);

    //verification
    expect(result).hasElementCount(4);
    expect(result.getStoredAt1BasedIndex(1)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAt1BasedIndex(2)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAt1BasedIndex(3)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAt1BasedIndex(4)).containsExactlyEqualing("A", "B");
  }

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerContainsMatchingSequences_1B() {

    //setup
    final var letters = ImmutableList.withElement("A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("A"))
      .withBlankForNext();

    //execution
    final var result = testUnit.getMatchingSequencesFrom(letters);

    //verification
    expect(result).hasElementCount(4);
    expect(result.getStoredAt1BasedIndex(1)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAt1BasedIndex(2)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAt1BasedIndex(3)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAt1BasedIndex(4)).containsExactlyEqualing("A", "B");
  }

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerContainsMatchingSequences_2A() {

    //setup
    final var letters = ImmutableList.withElement("A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("A"))
      .withConditionForNext(e -> e.equals("B"))
      .withSequenceCondition(s -> s.getSum(String::length).intValue() == 2);

    //execution
    final var result = testUnit.getMatchingSequencesFrom(letters);

    //verification
    expect(result).hasElementCount(4);
    expect(result.getStoredAt1BasedIndex(1)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAt1BasedIndex(2)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAt1BasedIndex(3)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAt1BasedIndex(4)).containsExactlyEqualing("A", "B");
  }

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerContainsMatchingSequences_2B() {

    //setup
    final var letters = ImmutableList.withElement("A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("A"))
      .withBlankForNext()
      .withSequenceCondition(s -> s.getSum(String::length).intValue() == 2);

    //execution
    final var result = testUnit.getMatchingSequencesFrom(letters);

    //verification
    expect(result).hasElementCount(4);
    expect(result.getStoredAt1BasedIndex(1)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAt1BasedIndex(2)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAt1BasedIndex(3)).containsExactlyEqualing("A", "B");
    expect(result.getStoredAt1BasedIndex(4)).containsExactlyEqualing("A", "B");
  }

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerDoesNotContainMatchinSequences_1A() {

    //setup
    final var letters = ImmutableList.withElement("A", "A", "C", "A", "A", "C", "A", "A", "C", "A", "A", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("A"))
      .withConditionForNext(e -> e.equals("B"));

    //execution
    final var result = testUnit.getMatchingSequencesFrom(letters);

    //verification
    expect(result).isEmpty();
  }

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerDoesNotContainMatchinSequences_1B() {

    //setup
    final var letters = ImmutableList.withElement("A", "A", "C", "A", "A", "C", "A", "A", "C", "A", "A", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("B"))
      .withBlankForNext();

    //execution
    final var result = testUnit.getMatchingSequencesFrom(letters);

    //verification
    expect(result).isEmpty();
  }

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerDoesNotContainMatchinSequences_2A() {

    //setup
    final var letters = ImmutableList.withElement("A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("A"))
      .withConditionForNext(e -> e.equals("B"))
      .withSequenceCondition(s -> s.getSum(String::length).intValue() > 2);

    //execution
    final var result = testUnit.getMatchingSequencesFrom(letters);

    //verification
    expect(result).isEmpty();
  }

  @Test
  void testCase_getMatchingSequencesFrom_whenTheGivenContainerDoesNotContainMatchinSequences_2B() {

    //setup
    final var letters = ImmutableList.withElement("A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C");
    final var testUnit = //
    SequencePattern
      .forElementType(String.class)
      .withConditionForNext(e -> e.equals("A"))
      .withBlankForNext()
      .withSequenceCondition(s -> s.getSum(String::length).intValue() > 2);

    //execution
    final var result = testUnit.getMatchingSequencesFrom(letters);

    //verification
    expect(result).isEmpty();
  }
}
