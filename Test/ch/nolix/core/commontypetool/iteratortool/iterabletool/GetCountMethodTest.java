package ch.nolix.core.commontypetool.iteratortool.iterabletool;

import org.junit.jupiter.api.Test;

import ch.nolix.core.commontypetool.iteratortool.IterableTool;
import ch.nolix.core.independent.list.List;
import ch.nolix.core.testing.standardtest.StandardTest;

final class GetCountMethodTest extends StandardTest {

  @Test
  void testCase_getCount_whenGivenIterableIsNull() {

    //setup
    final Iterable<Object> iterable = null;

    //execution & verification
    expectRunning(() -> IterableTool.getCount(iterable)).throwsException();
  }

  @Test
  void testCase_getCount_whenGivenIterableIsEmpty() {

    //setup
    final Iterable<Object> iterable = new List<>();

    //execution
    final var result = IterableTool.getCount(iterable);

    //verification
    expect(result).isEqualTo(0);
  }

  @Test
  void testCase_getCount_whenGivenIterableContains1Element() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "antelope" });

    //execution
    final var result = IterableTool.getCount(iterable);

    //verification
    expect(result).isEqualTo(1);
  }

  @Test
  void testCase_getCount_whenGivenIterableContains2Elements() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "antelope", "elephant" });

    //execution
    final var result = IterableTool.getCount(iterable);

    //verification
    expect(result).isEqualTo(2);
  }
}
