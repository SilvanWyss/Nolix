//package declaration
package ch.nolix.coretest.commontypetooltest.iterabletooltest;

//JUnit imports
import org.junit.jupiter.api.Test;

import ch.nolix.core.commontypetool.iterabletool.GlobalIterableTool;
import ch.nolix.core.independent.container.List;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class GlobalIterableToolTest extends StandardTest {

  //method
  @Test
  void testCase_containsAny_whenGivenIterableIsNull() {

    //setup
    final Iterable<Object> iterable = null;

    //execution
    final var result = GlobalIterableTool.containsAny(iterable);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_containsAny_whenGivenIterableIsEmpty() {

    //setup
    final Iterable<Object> iterable = new List<>();

    //execution
    final var result = GlobalIterableTool.containsAny(iterable);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_containsAny_whenGivenIterableContains1Element() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "x" });

    //execution
    final var result = GlobalIterableTool.containsAny(iterable);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_containsAny_whenGivenIterableContains2Elements() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "x", "xx" });

    //execution
    final var result = GlobalIterableTool.containsAny(iterable);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_containsEqualing_whenGivenIterableIsNull() {

    //setup
    final Iterable<Object> iterable = null;

    //execution
    final var result = GlobalIterableTool.containsEqualing(iterable, "x");

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_containsEqualing_whenGivenIterableIsEmpty() {

    //setup
    final Iterable<Object> iterable = new List<>();

    //execution
    final var result = GlobalIterableTool.containsEqualing(iterable, "x");

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_containsEqualing_whenGivenIterableContainsOnlyUnequalElements() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "x", "xx", "xxx" });

    //execution
    final var result = GlobalIterableTool.containsEqualing(iterable, "xxxx");

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_containsEqualing_whenGivenIterableContainsEqualElementAndUnequalElements() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "x", "xx", "xxx" });

    //execution
    final var result = GlobalIterableTool.containsEqualing(iterable, "xx");

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_containsEqualing_whenGivenIterableContainsOnlyEqualElements() {

    //setup
    final Iterable<Object> iterable = new List<>(new String[] { "x", "x", "x" });

    //execution
    final var result = GlobalIterableTool.containsEqualing(iterable, "x");

    //verification
    expect(result);
  }
}
