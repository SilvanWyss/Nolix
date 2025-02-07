package ch.nolix.core.commontypetool.iteratortool.iterableexaminer;

import org.junit.jupiter.api.Test;

import ch.nolix.core.commontypetool.iteratortool.IterableExaminer;
import ch.nolix.core.independent.list.List;
import ch.nolix.core.testing.standardtest.StandardTest;

final class IsEmptyMethodTest extends StandardTest {

  @Test
  void testCase_isEmpty_whenGivenIterableIsNull() {

    //setup
    final Iterable<Object> iterable = null;
    final var testUnit = new IterableExaminer();

    //execution
    final var result = testUnit.isEmpty(iterable);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_isEmpty_whenGivenIterableIsEmpty() {

    //setup
    final Iterable<Object> iterable = new List<>();
    final var testUnit = new IterableExaminer();

    //execution
    final var result = testUnit.isEmpty(iterable);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_isEmpty_whenGivenIterableContains1Element() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "antelope" });
    final var testUnit = new IterableExaminer();

    //execution
    final var result = testUnit.isEmpty(iterable);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_isEmpty_whenGivenIterableContains2Elements() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "antelope", "elephant" });
    final var testUnit = new IterableExaminer();

    //execution
    final var result = testUnit.isEmpty(iterable);

    //verification
    expect(result).isFalse();
  }
}
