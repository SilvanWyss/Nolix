package ch.nolix.core.commontypetool.iteratortool.iterabletool;

import org.junit.jupiter.api.Test;

import ch.nolix.core.commontypetool.iteratortool.IterableTool;
import ch.nolix.core.independent.list.List;
import ch.nolix.core.testing.standardtest.StandardTest;

final class ContainsAnyMethodTest extends StandardTest {

  @Test
  void testCase_containsAny_whenGivenIterableIsNull() {

    //setup
    final Iterable<Object> iterable = null;
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAny(iterable);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_containsAny_whenGivenIterableIsEmpty() {

    //setup
    final Iterable<Object> iterable = new List<>();
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAny(iterable);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_containsAny_whenGivenIterableContains1Element() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "antelope" });
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAny(iterable);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_containsAny_whenGivenIterableContains2Elements() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "antelope", "elephant" });
    final var testUnit = new IterableTool();

    //execution
    final var result = testUnit.containsAny(iterable);

    //verification
    expect(result).isTrue();
  }
}
